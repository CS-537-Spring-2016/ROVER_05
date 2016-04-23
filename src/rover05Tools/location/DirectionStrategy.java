package rover05Tools.location;

public class DirectionStrategy {

	// based on the various situation decide on which direction to move on
	// wisely

	int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void moveNorth(int x, int y) {

		//get the currect location 
		LocationGetter loc=new LocationGetter();
		int xCor=loc.getX();
		int yCor=loc.getX();
		
		//sense the things using range extender and make decision
		
	}

	public void moveSouth(int x, int y) {
		
		//get the currect location 
		LocationGetter loc=new LocationGetter();
		int xCor=loc.getX();
		int yCor=loc.getX();
		
		//sense the things using range extender and make decision

				

	}

	public void moveEast(int x, int y) {
		
		//get the currect location 
		LocationGetter loc=new LocationGetter();
		int xCor=loc.getX();
		int yCor=loc.getX();
		
		//sense the things using range extender and make decision

		
		

	}

	public void moveWest(int x, int y) {
		
		//get the currect location 
		LocationGetter loc=new LocationGetter();
		int xCor=loc.getX();
		int yCor=loc.getX();
		
		//sense the things using range extender and make decision


	}

}
