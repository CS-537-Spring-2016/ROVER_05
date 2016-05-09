package swarmBots;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import common.Coord;
import common.Group;
import common.MapTile;
import common.ScanMap;
import enums.Science;
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
	
	
	// all the sockets of blue team - output
    List<Socket> outputSockets = new ArrayList<Socket>();

    // objects contains each rover IP, port, and name
    List<Group> blue = new ArrayList<Group>();

    // every science detected will be added in to this set
    Set<Coord> science_discovered = new HashSet<Coord>();

    // this set contains all the science the ROVERED has shared
    // thus whatever thats in science_collection that is not in display_science
    // are "new" and "unshared"
    Set<Coord> displayed_science = new HashSet<Coord>();
    
    // ROVER current location
    Coord roverLoc;
    
    // Your ROVER is going to listen for connection with this
    ServerSocket listenSocket;
    
    

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
	
	/**
     * Try to connect each socket on a separate thread. Will try until it works.
     * When socket is created, save it to a LIST
     * 
     * @author Shay
     *
     */
    class RoverComm implements Runnable {

        String ip;
        int port;
        Socket socket;

        public RoverComm(String ip, int port) {
            this.ip = ip;
            this.port = port;
        }

        @Override
        public void run() {
            do {
                try {
                    socket = new Socket(ip, port);
                } catch (UnknownHostException e) {

                } catch (IOException e) {

                }
            } while (socket == null);
            
            outputSockets.add(socket);
            System.out.println(socket.getPort() + " " + socket.getInetAddress());
        }

    }
    
    /**
     * add all the group's rover into a LIST
     */
    public void initConnection() {
        // dummy value # 1
        blue.add(new Group("Dummy Group #1", "localhost", 53799));

        // blue rooster
        blue.add(new Group("GROUP_01", "localhost", 53701));
        blue.add(new Group("GROUP_02", "localhost", 53702));
        blue.add(new Group("GROUP_03", "localhost", 53703));
        blue.add(new Group("GROUP_04", "localhost", 53704));
        blue.add(new Group("GROUP_06", "localhost", 53706));
        blue.add(new Group("GROUP_07", "localhost", 53707));
        blue.add(new Group("GROUP_08", "localhost", 53708));
        blue.add(new Group("GROUP_09", "localhost", 53709));
    }

	private void run() throws IOException, InterruptedException {

		// Make connection and initialize streams
		//TODO - need to close this socket
		Socket socket = new Socket(SERVER_ADDRESS, PORT_ADDRESS); // set port here
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
		
		
        /*
         * connect to all the ROVERS on a separate thread
         */
        initConnection();
        for (Group group : blue) {
            new Thread(new RoverComm(group.ip, group.port)).start();
        }

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
				
				// class variable
                roverLoc = extractLOC(line);
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
			// ****************** Check scan map for science and shared them ***********************
            
            detectCrystal(scanMap.getScanMap());
            System.out.println("SCIENCE DISCOVERED: " + science_discovered);
            shareScience();
            
            // *********************************************************************

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
			
			Thread.sleep(sleepTime);

			
		}

	}

	// ################ Support Methods ###########################
	
	/**
     * iterate through a scan map to find a tile with radiation. get the
     * adjusted (absolute) coordinate of the tile and added into a hash set
     * 
     * @param scanMapTiles
     * @author Shay
     */
    private void detectCrystal(MapTile[][] scanMapTiles) {
        for (int x = 0; x < scanMapTiles.length; x++) {
            for (int y = 0; y < scanMapTiles[x].length; y++) {
                MapTile mapTile = scanMapTiles[x][y];
                if (mapTile.getScience() == Science.CRYSTAL) {
                    int tileX = roverLoc.xpos + (x - 5);
                    int tileY = roverLoc.ypos + (y - 5);
                    Coord coord = new Coord(mapTile.getTerrain(), mapTile.getScience(), tileX, tileY);
                    science_discovered.add(coord);
                }
            }
        }
    }
    
    
    /**
     * write to each rover the coords of a tile that contains radiation. will
     * only write to them if the coords are new.
     * 
     * @author Shay
     */
    private void shareScience() {
        for (Coord c : science_discovered) {
            if (!displayed_science.contains(c)) {
                for (Socket s : outputSockets)
                    try {
                        new DataOutputStream(s.getOutputStream()).writeBytes(c.toString() + "\r\n");
                    } catch (Exception e) {

                    }
                displayed_science.add(c);
            }
        }
    }

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
	
	
	public String switchDirectionEdge(MapTile[][] scanMapTiles, String direction) {
		switch (direction) {
		case "E":
			return west;
		case "S":
			return east;
		case "N":
			return south;
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
		

//		out.println("TIMER");
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
