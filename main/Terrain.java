package main;

/**
 * This class extends Element and adds a boolean that
 * determines if the location is a wall or a path.
 * 
 * @author Group 16
 */
public class Terrain {
	
	private boolean isWall;

	/**
	 * @return (whether or not the location is a wall)
	 */
	public boolean isWall() {
		return isWall;
	}

	/**
	 * @param isWall (whether or not to set the location to be a wall)
	 */
	public void setWall(boolean isWall) {
		this.isWall = isWall;
	}
}
