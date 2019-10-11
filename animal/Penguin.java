package animal;

import java.awt.event.KeyEvent;
import java.awt.Graphics;

/**
 * This class extends animal, and adds the ability to respond to key input.
 * 
 * @author Group 16
 */
public class Penguin extends Animal{

	/**
	 * Whether or not the penguin has moved.
	 */
	boolean hasMoved = false;

    public Penguin(int x, int y, Direction direction, String pengPathUp, String pengPathDown, String pengPathLeft, String pengPathRight) {
        super(x, y, direction, pengPathUp, pengPathDown, pengPathLeft, pengPathRight);
    }
    
    public Penguin(Penguin aPenguin,String PENG_PATH_UP, String PENG_PATH_DOWN, String PENG_PATH_LEFT, String PENG_PATH_RIGHT) {
        this(aPenguin.getXcoord(),aPenguin.getYcoord(),aPenguin.getDirection(), PENG_PATH_UP, PENG_PATH_DOWN, PENG_PATH_LEFT, PENG_PATH_RIGHT);
       
    }

    /**
     * This method moves the penguin based on if the next space in
     * the current direction of travel is free or not.
     */
    public void updateVelocity() {
    	
    	//Get the current loaction
    	int x = getXcoord();
    	int y = getYcoord();
    	
    	//Update the location if the next space in the direction the penguin is facing is free.
    	if (isUpFree(x, y) && (getDirection() == Direction.NORTH)){
    		y -= 1;
    	}else if (isDownFree(x, y) && (getDirection() == Direction.SOUTH)){
    		y += 1;
    	}else if (isRightFree(x, y) && (getDirection() == Direction.EAST)){
			x += 1;
    	}else if (isLeftFree(x, y) && (getDirection() == Direction.WEST)){
    		x -= 1;
    	}else{
    		//System.out.println("Bound hit!");
    	}
        
    	//Set the position to be the new location.
        setXcoord(x);
        setYcoord(y);
    }

    /**
     * This method sets the direction of the penguin based on the key
     * pressed and available space around it.
     * 
     * @param e
     */
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
        //get position in the array
        int x = getXcoord();
    	int y = getYcoord();

    	//Set the direction if a key is pressed and that direction is free.
        if (key == KeyEvent.VK_LEFT && isLeftFree(x, y)) {
            setDirection(Direction.WEST);
            System.out.println("Left");
            setHasMoved(true);
        }
        if (key == KeyEvent.VK_RIGHT && isRightFree(x, y)) {
            setDirection(Direction.EAST);
            System.out.println("Right");
            setHasMoved(true);
        }
        if (key == KeyEvent.VK_UP && isUpFree(x, y)) {
            setDirection(Direction.NORTH);
            System.out.println("Up");
            setHasMoved(true);
        }
        if (key == KeyEvent.VK_DOWN && isDownFree(x, y)) {
            setDirection(Direction.SOUTH);
            System.out.println("Down");
            setHasMoved(true);
        }
    }

    /**
     * This method allows for an action to be assigned to the penguin
     * when the key is released, such as stopping the penguin.
     * Currently, there is no action performed when the key is released,
     * so the penguin continues in the direction it was facing.
     * 
     * @param e
     */
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
        }

        if (key == KeyEvent.VK_RIGHT) {
        }

        if (key == KeyEvent.VK_UP) {
        }

        if (key == KeyEvent.VK_DOWN) {
        }
    }

    /**
     * A required method for key input to work. Could be used to log
     * keystrokes, or enter a cheat code or message.
     * 
     * @param e
     */
    public void keyTyped(KeyEvent e){
    }
    
    /**
     * @param b (Whether or not the penguin has moved)
     */
    public void setHasMoved(boolean b){
    	hasMoved = b;
    }
    
    /**
     * @return whether or not the penguin has moved.
     */
    public boolean hasMoved(){
    	return hasMoved;
    }
    
    /* (non-Javadoc)
     * @see animal.Animal#paint(java.awt.Graphics)
     */
    public void paint(Graphics g){
    	super.paint(g);
    }
}