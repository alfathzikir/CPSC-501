package visuals;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 * This class sets up the image for the game-map, which the player 'walks on'.
 * 
 * Specify the path where the map image is stored, and its pixel width/height
 * when creating a new background image.
 * new BackGroundImage(string, int, int)
 *
 * @author Group 16
 */
public class BackgroundImage extends JPanel{

	private static final long serialVersionUID = 1L;
	private String background;
	
	private static int IMAGE_SIZE_X;
	private static int IMAGE_SIZE_Y;
	/**
	 * Constructor to initialize the images size and the location where the reference image is stored
	 * @param path to the reference image
	 * @param desired width of map
	 * @param desired height of map
	 */
	public BackgroundImage(String path, int SizeX, int SizeY){
		background = path;
		IMAGE_SIZE_X = SizeX;
		IMAGE_SIZE_Y = SizeY;
	}
	
	/**
	 * This override overrides paint(Graphics), specifying that the gamemap will be drawn
	 * starting at the top left (0,0)
	 */
	public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(getBackgroundImage(), 0, 0, this);
	}
	
	/**
	 * This method returns the image after it has been resized to the correct size for the map.
	 * @return Image
	 */
	public BufferedImage getBackgroundImage(){
		ImageIcon i = new ImageIcon(getClass().getResource(background));
        Image image = i.getImage();
        BufferedImage scaledImage = new BufferedImage(IMAGE_SIZE_X, IMAGE_SIZE_Y, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(image, 0, 0, IMAGE_SIZE_X, IMAGE_SIZE_Y, null);
        g2.dispose();
        return scaledImage;
    }

}
