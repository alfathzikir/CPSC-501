package collectible;

import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import main.Element;

/**
 * This class extends Element.
 * It adds a type and an image to the Element class.
 *
 *
 * @author Group 16
 */
public class Collectible extends Element{

	/**
	 * The type determines what kind of item the collectible is.
	 * The imagePath is the associated image for the type.
	 */
	private Type type;
	public enum Type {FISH, LIFE, TIMEUP, EMPTY}
  private String imagePath;

	/**
	 * This constructor sets the type, location, and visual for the collectible.
	 *
	 * @param x
	 * @param y
	 * @param passedType
	 * @param imagePathNew
	 */
	public Collectible(int x, int y, Type passedType, String imagePathNew){
		this.type = passedType;
		setX(x);
		setY(y);
		imagePath = imagePathNew;
	}

	/**
	 * This method gets the image from the filepath.
	 *
	 * @param imagePath
	 * @return (The image in the imagePath)
	 */
	private Image getImage(String imagePath){
		ImageIcon i = new ImageIcon(getClass().getResource(imagePath));
		return i.getImage();
	}

	/**
	 * This method paints the item at a location.
	 *
	 * @param g
	 */
	public void paint(Graphics g){
		g.drawImage(getImage(imagePath), getX()*30, getY()*30, null);
	}

	/**
	 * @return (The type of collectible)
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @param passedType (The type of item the collectible is set to)
	 */
	public void setType(Type passedType) {
		this.type = passedType;
	}

	/**
	 * @param path (The location of the associated image)
	 */
	public void setImagePath(String path){
		imagePath = path;
	}
}
