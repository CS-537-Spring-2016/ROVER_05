

/*package swarmBots;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class DummyClientHandler implements Runnable {

    private Socket connectionSocket;

    public DummyClientHandler(Socket connectionSocket) {

        this.connectionSocket = connectionSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(connectionSocket.getInputStream()));

            while (true) {
                System.out.println(br.readLine());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    	//Scan Crystal Science
    	public void detectCrystalScience(MapTile[][] scanMapTiles, Coord currentLoc)  //Called by move_Rover();
    	 {       
    		 int centerIndex = (scanMap.getEdgeSize() - 1) / 2;
    		 int xPos = currentLoc.xpos - centerIndex;
    		 int yPos = currentLoc.ypos - centerIndex;
    		 System.out.println("xPos"+xPos+"yPos"+yPos);//This gives the current location 
    		 System.out.println("ScanMap Length "+scanMapTiles.length);//Here we are scanning 11*11
    		 int crystalXPosition, crystalYPosition;
    	     for (int x = 0; x < scanMapTiles.length; x++) //Iterating through X coordinate
    	     {
               for (int y = 0; y < scanMapTiles.length; y++) //Iterating through Y coordinate
               {
                   if (scanMapTiles[x][y].getScience() == Science.CRYSTAL) //Checking for crystal Science and locating the crystal
                   {
                   		crystalXPosition = xPos + x;
                       	crystalYPosition = yPos + y;
    		                Coord coord = new Coord(crystalXPosition ,crystalYPosition);//Coordination class constructor with two arguments
    		                System.out.println("Crystal position discovered:In "+scanMapTiles[x][y].getTerrain()+" at the position "+coord);
    		                crystalCoordinates.add(coord);
                   }
               }
    	     }
    	 }

        
        
    }
    
    
    package swarmBots;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class DummyClientHandler implements Runnable {

    private Socket connectionSocket;

    public DummyClientHandler(Socket connectionSocket) {

        this.connectionSocket = connectionSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(connectionSocket.getInputStream()));

            while (true) {
                System.out.println(br.readLine());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    	//Scan Crystal Science
    	public void detectCrystalScience(MapTile[][] scanMapTiles, Coord currentLoc)  //Called by move_Rover();
    	 {       
    		 int centerIndex = (scanMap.getEdgeSize() - 1) / 2;
    		 int xPos = currentLoc.xpos - centerIndex;
    		 int yPos = currentLoc.ypos - centerIndex;
    		 System.out.println("xPos"+xPos+"yPos"+yPos);//This gives the current location 
    		 System.out.println("ScanMap Length "+scanMapTiles.length);//Here we are scanning 11*11
    		 int crystalXPosition, crystalYPosition;
    	     for (int x = 0; x < scanMapTiles.length; x++) //Iterating through X coordinate
    	     {
               for (int y = 0; y < scanMapTiles.length; y++) //Iterating through Y coordinate
               {
                   if (scanMapTiles[x][y].getScience() == Science.CRYSTAL) //Checking for crystal Science and locating the crystal
                   {
                   		crystalXPosition = xPos + x;
                       	crystalYPosition = yPos + y;
    		                Coord coord = new Coord(crystalXPosition ,crystalYPosition);//Coordination class constructor with two arguments
    		                System.out.println("Crystal position discovered:In "+scanMapTiles[x][y].getTerrain()+" at the position "+coord);
    		                crystalCoordinates.add(coord);
//    		                for(Coord X: crystalCoordinates)
//    		                {
//    		                System.out.println("Crystal Coordiantes:"+X);
//    		                }
                   }
               }
    	     }
    	 }

        
        
    }
    
package swarmBots;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class DummyClientHandler implements Runnable {

    private Socket connectionSocket;

    public DummyClientHandler(Socket connectionSocket) {

        this.connectionSocket = connectionSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(connectionSocket.getInputStream()));

            while (true) {
                System.out.println(br.readLine());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    	//Scan Crystal Science
    	public void detectCrystalScience(MapTile[][] scanMapTiles, Coord currentLoc)  //Called by move_Rover();
    	 {       
    		 int centerIndex = (scanMap.getEdgeSize() - 1) / 2;
    		 int xPos = currentLoc.xpos - centerIndex;
    		 int yPos = currentLoc.ypos - centerIndex;
    		 System.out.println("xPos"+xPos+"yPos"+yPos);//This gives the current location 
    		 System.out.println("ScanMap Length "+scanMapTiles.length);//Here we are scanning 11*11
    		 int crystalXPosition, crystalYPosition;
    	     for (int x = 0; x < scanMapTiles.length; x++) //Iterating through X coordinate
    	     {
               for (int y = 0; y < scanMapTiles.length; y++) //Iterating through Y coordinate
               {
                   if (scanMapTiles[x][y].getScience() == Science.CRYSTAL) //Checking for crystal Science and locating the crystal
                   {
                   		crystalXPosition = xPos + x;
                       	crystalYPosition = yPos + y;
    		                Coord coord = new Coord(crystalXPosition ,crystalYPosition);//Coordination class constructor with two arguments
    		                System.out.println("Crystal position discovered:In "+scanMapTiles[x][y].getTerrain()+" at the position "+coord);
    		                crystalCoordinates.add(coord);
//    		                for(Coord X: crystalCoordinates)
//    		                {
//    		                System.out.println("Crystal Coordiantes:"+X);
//    		                }
                   }
               }
    	     }
    	 }

        
        
    }
    


package swarmBots;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class DummyClientHandler implements Runnable {

    private Socket connectionSocket;

    public DummyClientHandler(Socket connectionSocket) {

        this.connectionSocket = connectionSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(connectionSocket.getInputStream()));

            while (true) {
                System.out.println(br.readLine());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    	//Scan Crystal Science
    	public void detectCrystalScience(MapTile[][] scanMapTiles, Coord currentLoc)  //Called by move_Rover();
    	 {       
    		 int centerIndex = (scanMap.getEdgeSize() - 1) / 2;
    		 int xPos = currentLoc.xpos - centerIndex;
    		 int yPos = currentLoc.ypos - centerIndex;
    		 System.out.println("xPos"+xPos+"yPos"+yPos);//This gives the current location 
    		 System.out.println("ScanMap Length "+scanMapTiles.length);//Here we are scanning 11*11
    		 int crystalXPosition, crystalYPosition;
    	     for (int x = 0; x < scanMapTiles.length; x++) //Iterating through X coordinate
    	     {
               for (int y = 0; y < scanMapTiles.length; y++) //Iterating through Y coordinate
               {
                   if (scanMapTiles[x][y].getScience() == Science.CRYSTAL) //Checking for crystal Science and locating the crystal
                   {
                   		crystalXPosition = xPos + x;
                       	crystalYPosition = yPos + y;
    		                Coord coord = new Coord(crystalXPosition ,crystalYPosition);//Coordination class constructor with two arguments
    		                System.out.println("Crystal position discovered:In "+scanMapTiles[x][y].getTerrain()+" at the position "+coord);
    		                crystalCoordinates.add(coord);
//    		                for(Coord X: crystalCoordinates)
//    		                {
//    		                System.out.println("Crystal Coordiantes:"+X);
//    		                }
                   }
               }
    	     }
    	 }

        
        
    }
    

package swarmBots;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class DummyClientHandler implements Runnable {

    private Socket connectionSocket;

    public DummyClientHandler(Socket connectionSocket) {

        this.connectionSocket = connectionSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(connectionSocket.getInputStream()));

            while (true) {
                System.out.println(br.readLine());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    	//Scan Crystal Science
    	public void detectCrystalScience(MapTile[][] scanMapTiles, Coord currentLoc)  //Called by move_Rover();
    	 {       
    		 int centerIndex = (scanMap.getEdgeSize() - 1) / 2;
    		 int xPos = currentLoc.xpos - centerIndex;
    		 int yPos = currentLoc.ypos - centerIndex;
    		 System.out.println("xPos"+xPos+"yPos"+yPos);//This gives the current location 
    		 System.out.println("ScanMap Length "+scanMapTiles.length);//Here we are scanning 11*11
    		 int crystalXPosition, crystalYPosition;
    	     for (int x = 0; x < scanMapTiles.length; x++) //Iterating through X coordinate
    	     {
               for (int y = 0; y < scanMapTiles.length; y++) //Iterating through Y coordinate
               {
                   if (scanMapTiles[x][y].getScience() == Science.CRYSTAL) //Checking for crystal Science and locating the crystal
                   {
                   		crystalXPosition = xPos + x;
                       	crystalYPosition = yPos + y;
    		                Coord coord = new Coord(crystalXPosition ,crystalYPosition);//Coordination class constructor with two arguments
    		                System.out.println("Crystal position discovered:In "+scanMapTiles[x][y].getTerrain()+" at the position "+coord);
    		                crystalCoordinates.add(coord);
//    		                for(Coord X: crystalCoordinates)
//    		                {
//    		                System.out.println("Crystal Coordiantes:"+X);
//    		                }
                   }
               }
    	     }
    	 }

        
        
    }
    

package swarmBots;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class DummyClientHandler implements Runnable {

    private Socket connectionSocket;

    public DummyClientHandler(Socket connectionSocket) {

        this.connectionSocket = connectionSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(connectionSocket.getInputStream()));

            while (true) {
                System.out.println(br.readLine());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    	//Scan Crystal Science
    	public void detectCrystalScience(MapTile[][] scanMapTiles, Coord currentLoc)  //Called by move_Rover();
    	 {       
    		 int centerIndex = (scanMap.getEdgeSize() - 1) / 2;
    		 int xPos = currentLoc.xpos - centerIndex;
    		 int yPos = currentLoc.ypos - centerIndex;
    		 System.out.println("xPos"+xPos+"yPos"+yPos);//This gives the current location 
    		 System.out.println("ScanMap Length "+scanMapTiles.length);//Here we are scanning 11*11
    		 int crystalXPosition, crystalYPosition;
    	     for (int x = 0; x < scanMapTiles.length; x++) //Iterating through X coordinate
    	     {
               for (int y = 0; y < scanMapTiles.length; y++) //Iterating through Y coordinate
               {
                   if (scanMapTiles[x][y].getScience() == Science.CRYSTAL) //Checking for crystal Science and locating the crystal
                   {
                   		crystalXPosition = xPos + x;
                       	crystalYPosition = yPos + y;
    		                Coord coord = new Coord(crystalXPosition ,crystalYPosition);//Coordination class constructor with two arguments
    		                System.out.println("Crystal position discovered:In "+scanMapTiles[x][y].getTerrain()+" at the position "+coord);
    		                crystalCoordinates.add(coord);
//    		                for(Coord X: crystalCoordinates)
//    		                {
//    		                System.out.println("Crystal Coordiantes:"+X);
//    		                }
                   }
               }
    	     }
    	 }

        
        
    }
    

package swarmBots;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class DummyClientHandler implements Runnable {

    private Socket connectionSocket;

    public DummyClientHandler(Socket connectionSocket) {

        this.connectionSocket = connectionSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(connectionSocket.getInputStream()));

            while (true) {
                System.out.println(br.readLine());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    	//Scan Crystal Science
    	public void detectCrystalScience(MapTile[][] scanMapTiles, Coord currentLoc)  //Called by move_Rover();
    	 {       
    		 int centerIndex = (scanMap.getEdgeSize() - 1) / 2;
    		 int xPos = currentLoc.xpos - centerIndex;
    		 int yPos = currentLoc.ypos - centerIndex;
    		 System.out.println("xPos"+xPos+"yPos"+yPos);//This gives the current location 
    		 System.out.println("ScanMap Length "+scanMapTiles.length);//Here we are scanning 11*11
    		 int crystalXPosition, crystalYPosition;
    	     for (int x = 0; x < scanMapTiles.length; x++) //Iterating through X coordinate
    	     {
               for (int y = 0; y < scanMapTiles.length; y++) //Iterating through Y coordinate
               {
                   if (scanMapTiles[x][y].getScience() == Science.CRYSTAL) //Checking for crystal Science and locating the crystal
                   {
                   		crystalXPosition = xPos + x;
                       	crystalYPosition = yPos + y;
    		                Coord coord = new Coord(crystalXPosition ,crystalYPosition);//Coordination class constructor with two arguments
    		                System.out.println("Crystal position discovered:In "+scanMapTiles[x][y].getTerrain()+" at the position "+coord);
    		                crystalCoordinates.add(coord);
//    		                for(Coord X: crystalCoordinates)
//    		                {
//    		                System.out.println("Crystal Coordiantes:"+X);
//    		                }
                   }
               }
    	     }
    	 }

        
        
    }
    

package swarmBots;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class DummyClientHandler implements Runnable {

    private Socket connectionSocket;

    public DummyClientHandler(Socket connectionSocket) {

        this.connectionSocket = connectionSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(connectionSocket.getInputStream()));

            while (true) {
                System.out.println(br.readLine());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    	//Scan Crystal Science
    	public void detectCrystalScience(MapTile[][] scanMapTiles, Coord currentLoc)  //Called by move_Rover();
    	 {       
    		 int centerIndex = (scanMap.getEdgeSize() - 1) / 2;
    		 int xPos = currentLoc.xpos - centerIndex;
    		 int yPos = currentLoc.ypos - centerIndex;
    		 System.out.println("xPos"+xPos+"yPos"+yPos);//This gives the current location 
    		 System.out.println("ScanMap Length "+scanMapTiles.length);//Here we are scanning 11*11
    		 int crystalXPosition, crystalYPosition;
    	     for (int x = 0; x < scanMapTiles.length; x++) //Iterating through X coordinate
    	     {
               for (int y = 0; y < scanMapTiles.length; y++) //Iterating through Y coordinate
               {
                   if (scanMapTiles[x][y].getScience() == Science.CRYSTAL) //Checking for crystal Science and locating the crystal
                   {
                   		crystalXPosition = xPos + x;
                       	crystalYPosition = yPos + y;
    		                Coord coord = new Coord(crystalXPosition ,crystalYPosition);//Coordination class constructor with two arguments
    		                System.out.println("Crystal position discovered:In "+scanMapTiles[x][y].getTerrain()+" at the position "+coord);
    		                crystalCoordinates.add(coord);
//    		                for(Coord X: crystalCoordinates)
//    		                {
//    		                System.out.println("Crystal Coordiantes:"+X);
//    		                }
                   }
               }
    	     }
    	 }

        
        
    }
    

package swarmBots;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class DummyClientHandler implements Runnable {

    private Socket connectionSocket;

    public DummyClientHandler(Socket connectionSocket) {

        this.connectionSocket = connectionSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(connectionSocket.getInputStream()));

            while (true) {
                System.out.println(br.readLine());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    	//Scan Crystal Science
    	public void detectCrystalScience(MapTile[][] scanMapTiles, Coord currentLoc)  //Called by move_Rover();
    	 {       
    		 int centerIndex = (scanMap.getEdgeSize() - 1) / 2;
    		 int xPos = currentLoc.xpos - centerIndex;
    		 int yPos = currentLoc.ypos - centerIndex;
    		 System.out.println("xPos"+xPos+"yPos"+yPos);//This gives the current location 
    		 System.out.println("ScanMap Length "+scanMapTiles.length);//Here we are scanning 11*11
    		 int crystalXPosition, crystalYPosition;
    	     for (int x = 0; x < scanMapTiles.length; x++) //Iterating through X coordinate
    	     {
               for (int y = 0; y < scanMapTiles.length; y++) //Iterating through Y coordinate
               {
                   if (scanMapTiles[x][y].getScience() == Science.CRYSTAL) //Checking for crystal Science and locating the crystal
                   {
                   		crystalXPosition = xPos + x;
                       	crystalYPosition = yPos + y;
    		                Coord coord = new Coord(crystalXPosition ,crystalYPosition);//Coordination class constructor with two arguments
    		                System.out.println("Crystal position discovered:In "+scanMapTiles[x][y].getTerrain()+" at the position "+coord);
    		                crystalCoordinates.add(coord);
//    		                for(Coord X: crystalCoordinates)
//    		                {
//    		                System.out.println("Crystal Coordiantes:"+X);
//    		                }
                   }
               }
    	     }
    	 }

        
        
    }
    

package swarmBots;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class DummyClientHandler implements Runnable {

    private Socket connectionSocket;

    public DummyClientHandler(Socket connectionSocket) {

        this.connectionSocket = connectionSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(connectionSocket.getInputStream()));

            while (true) {
                System.out.println(br.readLine());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    	//Scan Crystal Science
    	public void detectCrystalScience(MapTile[][] scanMapTiles, Coord currentLoc)  //Called by move_Rover();
    	 {       
    		 int centerIndex = (scanMap.getEdgeSize() - 1) / 2;
    		 int xPos = currentLoc.xpos - centerIndex;
    		 int yPos = currentLoc.ypos - centerIndex;
    		 System.out.println("xPos"+xPos+"yPos"+yPos);//This gives the current location 
    		 System.out.println("ScanMap Length "+scanMapTiles.length);//Here we are scanning 11*11
    		 int crystalXPosition, crystalYPosition;
    	     for (int x = 0; x < scanMapTiles.length; x++) //Iterating through X coordinate
    	     {
               for (int y = 0; y < scanMapTiles.length; y++) //Iterating through Y coordinate
               {
                   if (scanMapTiles[x][y].getScience() == Science.CRYSTAL) //Checking for crystal Science and locating the crystal
                   {
                   		crystalXPosition = xPos + x;
                       	crystalYPosition = yPos + y;
    		                Coord coord = new Coord(crystalXPosition ,crystalYPosition);//Coordination class constructor with two arguments
    		                System.out.println("Crystal position discovered:In "+scanMapTiles[x][y].getTerrain()+" at the position "+coord);
    		                crystalCoordinates.add(coord);
//    		                for(Coord X: crystalCoordinates)
//    		                {
//    		                System.out.println("Crystal Coordiantes:"+X);
//    		                }
                   }
               }
    	     }
    	 }

        
        
    }
    

package swarmBots;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class DummyClientHandler implements Runnable {

    private Socket connectionSocket;

    public DummyClientHandler(Socket connectionSocket) {

        this.connectionSocket = connectionSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(connectionSocket.getInputStream()));

            while (true) {
                System.out.println(br.readLine());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    	//Scan Crystal Science
    	public void detectCrystalScience(MapTile[][] scanMapTiles, Coord currentLoc)  //Called by move_Rover();
    	 {       
    		 int centerIndex = (scanMap.getEdgeSize() - 1) / 2;
    		 int xPos = currentLoc.xpos - centerIndex;
    		 int yPos = currentLoc.ypos - centerIndex;
    		 System.out.println("xPos"+xPos+"yPos"+yPos);//This gives the current location 
    		 System.out.println("ScanMap Length "+scanMapTiles.length);//Here we are scanning 11*11
    		 int crystalXPosition, crystalYPosition;
    	     for (int x = 0; x < scanMapTiles.length; x++) //Iterating through X coordinate
    	     {
               for (int y = 0; y < scanMapTiles.length; y++) //Iterating through Y coordinate
               {
                   if (scanMapTiles[x][y].getScience() == Science.CRYSTAL) //Checking for crystal Science and locating the crystal
                   {
                   		crystalXPosition = xPos + x;
                       	crystalYPosition = yPos + y;
    		                Coord coord = new Coord(crystalXPosition ,crystalYPosition);//Coordination class constructor with two arguments
    		                System.out.println("Crystal position discovered:In "+scanMapTiles[x][y].getTerrain()+" at the position "+coord);
    		                crystalCoordinates.add(coord);
//    		                for(Coord X: crystalCoordinates)
//    		                {
//    		                System.out.println("Crystal Coordiantes:"+X);
//    		                }
                   }
               }
    	     }
    	 }

        
        
    }
    

package swarmBots;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class DummyClientHandler implements Runnable {

    private Socket connectionSocket;

    public DummyClientHandler(Socket connectionSocket) {

        this.connectionSocket = connectionSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(connectionSocket.getInputStream()));

            while (true) {
                System.out.println(br.readLine());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    	//Scan Crystal Science
    	public void detectCrystalScience(MapTile[][] scanMapTiles, Coord currentLoc)  //Called by move_Rover();
    	 {       
    		 int centerIndex = (scanMap.getEdgeSize() - 1) / 2;
    		 int xPos = currentLoc.xpos - centerIndex;
    		 int yPos = currentLoc.ypos - centerIndex;
    		 System.out.println("xPos"+xPos+"yPos"+yPos);//This gives the current location 
    		 System.out.println("ScanMap Length "+scanMapTiles.length);//Here we are scanning 11*11
    		 int crystalXPosition, crystalYPosition;
    	     for (int x = 0; x < scanMapTiles.length; x++) //Iterating through X coordinate
    	     {
               for (int y = 0; y < scanMapTiles.length; y++) //Iterating through Y coordinate
               {
                   if (scanMapTiles[x][y].getScience() == Science.CRYSTAL) //Checking for crystal Science and locating the crystal
                   {
                   		crystalXPosition = xPos + x;
                       	crystalYPosition = yPos + y;
    		                Coord coord = new Coord(crystalXPosition ,crystalYPosition);//Coordination class constructor with two arguments
    		                System.out.println("Crystal position discovered:In "+scanMapTiles[x][y].getTerrain()+" at the position "+coord);
    		                crystalCoordinates.add(coord);
//    		                for(Coord X: crystalCoordinates)
//    		                {
//    		                System.out.println("Crystal Coordiantes:"+X);
//    		                }
                   }
               }
    	     }
    	 }

        
        
    }
    


*/