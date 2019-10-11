package main;

/**
 * This class contains an x and y location.
 * It is extended by animal, terrain, and collectible.
 * 
 * @author Group 16
 */
public abstract class Element {
	
	private int xCoord;
    private int yCoord;

    protected Element(){
    }
    
    public Element (int i, int j) {
        xCoord = i;
        yCoord = j;
    }
    
    /**
     * @return (x coordinate)
     */
    protected int getX() {
        return xCoord;
    }
    
    /**
     * @return (y coordinate)
     */
    protected int getY() {
        return yCoord;
    }

    /**
     * @param newX (value to set x coordinate to)
     */
    protected void setX(int newX) {
        xCoord = newX;
    }

    /**
     * @param newY (value to set y coordinate to)
     */
    protected void setY(int newY) {
        yCoord = newY;
    }
}
