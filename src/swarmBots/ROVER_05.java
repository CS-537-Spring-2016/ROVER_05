package swarmBots;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import common.Coord;
import common.MapTile;
import common.ScanMap;
import communication.Group;
import communication.RoverCommunication;
import enums.RoverDriveType;
import enums.RoverToolType;
import enums.Terrain;

/**
 * The seed that this program is built on is a chat program example found here:
 * http://cs.lmu.edu/~ray/notes/javanetexamples/ Many thanks to the authors for
 * publishing their code examples
 */

// communication thread to recive messages 
//thread , while loop , stream 

public class ROVER_05 {

	Coord[] targetLocations = new Coord[3];
	int i = 0;
	BufferedReader in;
	PrintWriter out;
	String rovername;
	ScanMap scanMap;
	int sleepTime;
	String SERVER_ADDRESS = "localhost";
	static final int PORT_ADDRESS = 9537;

	Set<String> scienceLocations = new HashSet<String>();

	String north = "N";
	String south = "S";
	String east = "E";
	String west = "W";
	String direction = west;
	
	
	/* Communication Module*/
    RoverCommunication rocom;
    
	public ROVER_05() {
		// constructor
		System.out.println("ROVER_05 rover object constructed");
		rovername = "ROVER_05";
		SERVER_ADDRESS = "localhost";
		// this should be a safe but slow timer value
		sleepTime = 300; // in milliseconds - smaller is faster, but the server will cut connection if it is too small
	}
	
	public ROVER_05(String serverAddress) {
		// constructor
		System.out.println("ROVER_05 rover object constructed");
		rovername = "ROVER_05";
		SERVER_ADDRESS = serverAddress;
		sleepTime = 200; // in milliseconds - smaller is faster, but the server will cut connection if it is too small
	}
	
	

	private void run() throws IOException, InterruptedException {

		// Make connection and initialize streams
		//TODO - need to close this socket
		Socket socket = new Socket(SERVER_ADDRESS, PORT_ADDRESS); // set port here
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
		
		
        // ******************* SET UP COMMUNICATION MODULE by Shay
        // *********************
        /* Your Group Info */
        Group group = new Group(rovername, SERVER_ADDRESS, 53705, RoverDriveType.WHEELS,
                RoverToolType.RANGE_BOOSTER, RoverToolType.SPECTRAL_SENSOR);

        /* Setup communication, only communicates with gatherers */
        rocom = new RoverCommunication(group, Group.getGatherers(Group.blueCorp(SERVER_ADDRESS)));

        /* Connect to the other ROVERS */
        rocom.run();

        // ****************************************************************

		//Gson gson = new GsonBuilder().setPrettyPrinting().create();

		// Process all messages from server, wait until server requests Rover ID
		// name
		while (true) {
			String line = in.readLine();
			if (line.startsWith("SUBMITNAME")) {
				out.println(rovername); // This sets the name of this instance
										// of a swarmBot for identifying the
										// thread to the server
				break;
			}
		}

		String line = "";

		int counter = 0;

		boolean stuck = false; // just means it did not change locations between
								// requests,
								// could be velocity limit or obstruction etc.
		boolean blocked = false;

		Coord currentLoc = null;
		Coord previousLoc = null;

		targetLocations[0] = new Coord(0, 0);

		out.println("START_LOC");
		line = in.readLine();
		if (line == null) {
			System.out.println("ROVER_05 check connection to server");
			line = "";
		}
		if (line.startsWith("LOC")) {
			// loc = line.substring(4);
			Coord Loc = extractLOC(line);
			targetLocations[2] = new Coord(Loc.xpos, Loc.ypos);
		}

		// targetLocations[1] = new Coord(50, 50);
		// targetLocations[2] = new Coord(5, 5);

		// start Rover controller process
		while (true) {

			// currently the requirements allow sensor calls to be made with no
			// simulated resource cost

			// **** location call ****
			out.println("LOC");
			line = in.readLine();
			if (line == null) {
				System.out.println("ROVER_05 check connection to server");
				line = "";
			}
			if (line.startsWith("LOC")) {
				// loc = line.substring(4);
				currentLoc = extractLOC(line);
			}
			System.out.println("ROVER_05 currentLoc at start: " + currentLoc);

			// after getting location set previous equal current to be able to
			// check for stuckness and blocked later
			previousLoc = currentLoc;

			// **** get equipment listing ****
			ArrayList<String> equipment = new ArrayList<String>();
			equipment = getEquipment();
			
			System.out.println("ROVER_05 equipment list results " + equipment + "\n");

			
			this.doScan();
			scanMap.debugPrintMap();

			// MOVING

			MapTile[][] scanMapTiles = scanMap.getScanMap();

			make_a_move(scanMapTiles, currentLoc);
			// another call for current location
			out.println("LOC");
			line = in.readLine();
			if (line.startsWith("LOC")) {
				currentLoc = extractLOC(line);
			}

			System.out.println("ROVER_05 currentLoc after recheck: " + currentLoc);
			System.out.println("ROVER_05 previousLoc: " + previousLoc);

			// test for stuckness

			System.out.println("ROVER_05 stuck test " + stuck);
			
			
            /* ********* Detect and Share Science by Shay ***************/
            rocom.detectAndShare(scanMap.getScanMap(), currentLoc, 5);
            /* *************************************************/
			
			Thread.sleep(sleepTime);

			
		}

	}

	// ################ Support Methods ###########################
	
	private void clearReadLineBuffer() throws IOException {
		while (in.ready()) {
		
			String garbage = in.readLine();
		}
	}

	// method to retrieve a list of the rover's equipment from the server
	private ArrayList<String> getEquipment() throws IOException {
	
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		out.println("EQUIPMENT");

		String jsonEqListIn = in.readLine(); // grabs the string that was
												// returned first
		if (jsonEqListIn == null) {
			jsonEqListIn = "";
		}
		StringBuilder jsonEqList = new StringBuilder();
	

		if (jsonEqListIn.startsWith("EQUIPMENT")) {
			while (!(jsonEqListIn = in.readLine()).equals("EQUIPMENT_END")) {
				if (jsonEqListIn == null) {
					break;
				}
				
				jsonEqList.append(jsonEqListIn);
				jsonEqList.append("\n");
				
			}
		} else {
			// in case the server call gives unexpected results
			clearReadLineBuffer();
			return null; // server response did not start with "EQUIPMENT"
		}

		String jsonEqListString = jsonEqList.toString();
		ArrayList<String> returnList;
		returnList = gson.fromJson(jsonEqListString, new TypeToken<ArrayList<String>>() {
		}.getType());
	

		return returnList;
	}

	// sends a SCAN request to the server and puts the result in the scanMap
	// array
	public void doScan() throws IOException {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		out.println("SCAN");

		String jsonScanMapIn = in.readLine(); // grabs the string that was
												// returned first
		if (jsonScanMapIn == null) {
		
			jsonScanMapIn = "";
		}
		StringBuilder jsonScanMap = new StringBuilder();
		

		if (jsonScanMapIn.startsWith("SCAN")) {
			while (!(jsonScanMapIn = in.readLine()).equals("SCAN_END")) {
				
				jsonScanMap.append(jsonScanMapIn);
				jsonScanMap.append("\n");
				
			}
		} else {
			// in case the server call gives unexpected results
			clearReadLineBuffer();
			return; // server response did not start with "SCAN"
		}
		

		String jsonScanMapString = jsonScanMap.toString();
		// debug print json object to a file
		// new MyWriter( jsonScanMapString, 0); //gives a strange result -
		// prints the \n instead of newline character in the file

		// convert from the json string back to a ScanMap object
		scanMap = gson.fromJson(jsonScanMapString, ScanMap.class);
	}

	// this takes the LOC response string, parses out the x and y values and
	// returns a Coord object
	public static Coord extractLOC(String sStr) {
		sStr = sStr.substring(4);
		if (sStr.lastIndexOf(" ") != -1) {
			String xStr = sStr.substring(0, sStr.lastIndexOf(" "));
			// System.out.println("extracted xStr " + xStr);
			String yStr = sStr.substring(sStr.lastIndexOf(" ") + 1);
			// System.out.println("extracted yStr " + yStr);
			return new Coord(Integer.parseInt(xStr), Integer.parseInt(yStr));
		}
		return null;

	}

	/**
	 * Runs the client
	 * 
	 */
	public static void main(String[] args) throws Exception {
		ROVER_05 client = new ROVER_05();
		client.run();
	}



	//  move

	public void move(String direction) {
		out.println("MOVE " + direction);
	}

	// check for sand / rover / wall in the next move

	public boolean isValidMove(MapTile[][] scanMapTiles, String direction) {
		int centerIndex = (scanMap.getEdgeSize() - 1) / 2;
		int x = centerIndex, y = centerIndex;

		switch (direction) {
		case "N":
			y = y - 1;
			break;
		case "S":
			y = y + 1;
			break;
		case "E":
			x = x + 1;
			break;
		case "W":
			x = x - 1;
			break;
		}

		if (scanMapTiles[x][y].getTerrain() == Terrain.SAND || scanMapTiles[x][y].getTerrain() == Terrain.NONE||scanMapTiles[x][y].getTerrain() == Terrain.ROCK
				|| scanMapTiles[x][y].getHasRover() == true)
			return false;

		return true;
	}

	
	// have we reached a wall ??

	public boolean isWall(MapTile[][] scanMapTiles, String direction) {
		int centerIndex = (scanMap.getEdgeSize() - 1) / 2;
		int x = centerIndex, y = centerIndex;
		switch (direction) {
		case "N":
			y = y - 1;
			break;
		case "S":
			y = y + 1;
			break;
		case "E":
			x = x + 1;
			break;
		case "W":
			x = x - 1;
			break;
		}

		if (scanMapTiles[x][y].getTerrain() == Terrain.NONE||scanMapTiles[x][y].getTerrain() == Terrain.SAND||scanMapTiles[x][y].getTerrain() == Terrain.ROCK)
			return true;
		return false;
	}

	// if blocked / stuck change the direction
	public String switchDirection(MapTile[][] scanMapTiles, String direction) {
		switch (direction) {
		case "E":
			return south;
		case "S":
			return west;
		case "N":
			return east;
		case "W":
			return north;
		default:
			return null;

		}
	}

	// Move
	public void make_a_move(MapTile[][] scanMapTiles, Coord currentLoc) throws IOException {
		int centerIndex = (scanMap.getEdgeSize() - 1) / 2;
		int x = centerIndex, y = centerIndex;
		

//out.println("TIMER");
//		String line = in.readLine();
//		int time = 0;
//		if (line == null) {
//			System.out.println(rovername + " check connection to server");
//			line = "";
//		}
//		if (line.startsWith("TIMER")) {
//			String timeRemaining = line.substring(6);
//			time = Integer.parseInt(timeRemaining);
//			System.out.println(rovername + " timeRemaining: " + timeRemaining);
//		}
//
//		if (time <= 120000) {
//			i = 2;
//		}

		if (i == 2) {

			int cx = currentLoc.xpos, cy = currentLoc.ypos;
			int tx = targetLocations[i].xpos, ty = targetLocations[i].ypos;

			if (tx == cx && cy == ty)
				i++;

			if (cx > tx) {
				direction = west;
			}

			else if (cx < tx) {
				direction = east;
			} else if (cy > ty) {
				direction = north;
			} else if (cy < ty) {
				direction = south;
			}

		}

		if (isValidMove(scanMapTiles, direction)) {
			move(direction);

		} else {

			while (!isValidMove(scanMapTiles, direction)) {

				direction = switchDirection(scanMapTiles, direction);
			}
			move(direction);
		}
	}

}
