package animal;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import main.FileInput;

/**
 * The animal class is the parent class of penguin and polarbear and extends Element.
 * It adds images and directions to the element, as well as implementing boundary checking.
 * 
 * @author Group 2016
 */

public abstract class Animal {

	private int xcoord;
	private int ycoord;
	private Direction direction;
	private final Image animalImageUp;
	private final Image animalImageDown;
	private final Image animalImageLeft;
	private final Image animalImageRight;
	
	// Directions that can be used
	public enum Direction {NORTH, SOUTH, EAST, WEST, NULL}
	
	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param passedDirection
	 * @param imagePathUp
	 * @param imagePathDown
	 * @param imagePathLeft
	 * @param imagePathRight
	 */
	Animal(int x, int y, Direction passedDirection, String imagePathUp, String imagePathDown, String imagePathLeft, String imagePathRight){
		if(x >= 1 && x <= 29){
			this.xcoord = x;
		}
		if(y >= 1 && y <= 30){
			this.ycoord = y;
		}
		if(passedDirection == Direction.NORTH || passedDirection == Direction.EAST|| passedDirection == Direction.SOUTH|| direction == Direction.WEST){
			this.direction = passedDirection;
		}

		//System.out.println("TEST:\t Direction.NORTH = \\t" + Direction.NORTH);

		animalImageUp =  getImage(imagePathUp);
		animalImageDown =  getImage(imagePathDown);
		animalImageLeft =  getImage(imagePathLeft);
		animalImageRight =  getImage(imagePathRight);
	}
	
	/**
	 * Deals with painting the animals depending on the direction
	 * @param g
	 */
	void paint(Graphics g){
		Image animalImage;

		if ((direction == Direction.NORTH) || (direction == null)) {
			animalImage = animalImageUp;
		}
		else if (direction == Direction.EAST) {
			animalImage = animalImageRight;
		}
		else if (direction == Direction.SOUTH) {
			animalImage = animalImageDown;
		}
		else if (direction == Direction.WEST) {
			animalImage = animalImageLeft;
		}

		else {
			animalImage = null;
			System.out.println("ERROR: Direction image of penguin not switching correctly, value of direction =\t" + direction);
		}

		g.drawImage(animalImage, xcoord*30, ycoord*30, null);
	}
	
	/**
	 * It gets the image path and returns the image to print
	 * @param imagePath
	 * @return i.getImage()
	 */
	private Image getImage(String imagePath){
		ImageIcon i = new ImageIcon(getClass().getResource(imagePath));
		return i.getImage();
	}
	
	/**
	 * @return xcoord
	 */
	public int getXcoord() {
		return xcoord;
	}
	
	/**
	 * @param xcoord
	 */
	public void setXcoord(int xcoord) {
		this.xcoord = xcoord;
	}
	
	/**
	 * @return ycoord
	 */
	public int getYcoord() {
		return ycoord;
	}
	
	/**
	 * @param ycoord
	 */
	public void setYcoord(int ycoord) {
		this.ycoord = ycoord;
	}
	
	/**
	 * @return direction
	 */
	Direction getDirection() {
		return direction;
	}
	
	/**
	 * @param passedDirection
	 */
	public void setDirection(Direction passedDirection) {
		this.direction = passedDirection;
	}
	
	/**
	 * Checks if there is a wall above the animal
	 * @param x
	 * @param y
	 * @return a boolean isFree
	 */
	boolean isUpFree(int x, int y){
		boolean isFree;
		isFree = !FileInput.bounds[x][y - 1].isWall();
		return isFree;
	}
	
	/**
	 * Checks if there is a wall below the penguin
	 * @param x
	 * @param y
	 * @return a boolean isFree
	 */
	boolean isDownFree(int x, int y){
		boolean isFree;
		isFree = !FileInput.bounds[x][y + 1].isWall();
		return isFree;
	}
	
	/**
	 * Checks if there is a wall to the right of the penguin
	 * @param x
	 * @param y
	 * @return a boolean isFree
	 */
	boolean isRightFree(int x, int y){
		boolean isFree;
		isFree = !FileInput.bounds[x + 1][y].isWall();
		return isFree;
	}
	
	/**
	 * Checks if there is a wall to the left of the penguin
	 * @param x
	 * @param y
	 * @return a boolean isFree
	 */
	boolean isLeftFree(int x, int y){
		boolean isFree;
		isFree = !FileInput.bounds[x - 1][y].isWall();
		return isFree;
	}

}