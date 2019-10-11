package visuals;

import main.Game;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;
/**
 * This class handles the creation and calling of integer images
 * based on a supplied player score.  Inside of a GUI, player statistics
 * will be drawn on the screen by changing images for the 1s and 10s places
 * of each statistic
 *
 * @author group 16
 */
public class statsText extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final int timeOnesX = 1150;
	private static final int timeTensX = 1100;
	private static final int timeOnesY = 500;
	private static final int timeTensY = 500;

	private static final int scoreOnesX = 1150;
	private static final int scoreTensX = 1100;
	private static final int scoreHundredsX = 1050;
	private static final int scoreOnesY = 650;
	private static final int scoreTensY = 650;
	private static final int scoreHundredsY = 650;

	private static final int livesOnesX = 1150;
	private static final int livesOnesY = 800;

	private static final int scoreIconX = 970;
	private static final int scoreIconY = 650;

	private static final int timeIconX = 970;
	private static final int timeIconY = 500;

	private static final int livesIconX = 970;
	private static final int livesIconY = 800;


	private Image zero;
	private Image one;
	private Image two;
	private Image three;
	private Image four;
	private Image five;
	private Image six;
	private Image seven;
	private Image eight;
	private Image nine;

	private Image fishIcon;
	private Image timeIcon;
	private Image livesIcon;

	private static final String zeroPath = "/visuals/numberImages/0.png";
	private static final String onePath = "/visuals/numberImages/1.png";
	private static final String twoPath = "/visuals/numberImages/2.png";
	private static final String threePath = "/visuals/numberImages/3.png";
	private static final String fourPath = "/visuals/numberImages/4.png";
	private static final String fivePath = "/visuals/numberImages/5.png";
	private static final String sixPath = "/visuals/numberImages/6.png";
	private static final String sevenPath = "/visuals/numberImages/7.png";
	private static final String eightPath = "/visuals/numberImages/8.png";
	private static final String ninePath = "/visuals/numberImages/9.png";

	private static final String livesIconPath = "/visuals/numberImages/lifeupIcon.png";
	private static final String fishIconPath = "/visuals/numberImages/fishIcon.png";
	private static final String altFishIconPath = "/visuals/numberImages/altFishIcon.png";
	private static final String timeIconPath = "/visuals/numberImages/timeIcon.png";

	private Image timeTensImage = null;
	private Image timeOnesImage = null;
	private Image scoreHundredsImage = null;
	private Image scoreTensImage = null;
	private Image scoreOnesImage = null;
	private Image livesOnesImage = null;

	private static String errorPath = "/visuals/errorImages/error.png";
	private static final int errorX = 940;
	private static final int errorY = 380;

	private static int timeLast=0;
	private static int livesLast=0;
	private static int scoreLast=0;

	private static boolean error = false;

	/**
	 * This constructor initializes the paths to the stored reference images.
	 * If the secret level is being played, different images are drawn instead.
	 *
	 * Note that the images for each number are named as the number itself
	 * (zero refers to the image displaying the number 0)
	 */
	public statsText(){
		zero = getImage(zeroPath);
		one = getImage(onePath);
		two = getImage(twoPath);
		three = getImage(threePath);
		four = getImage(fourPath);
		five = getImage(fivePath);
		six = getImage(sixPath);
		seven = getImage(sevenPath);
		eight = getImage(eightPath);
		nine = getImage(ninePath);

		livesIcon = getImage(livesIconPath);
		timeIcon = getImage(timeIconPath);
		fishIcon = getImage(fishIconPath);
		if (Game.getHiddenLevel()) {
			fishIcon = getImage(altFishIconPath);
		}
	}

	/**
	 * This method returns an image stored at a supplied path.
	 * @param path to the stored image
	 * @return Image
	 */
	public Image getImage(String imagePath){
		ImageIcon i = new ImageIcon(getClass().getResource(imagePath));
		return i.getImage();
	}

	/**
	 * This method identifies which number to draw in the 1s, 10s, and 100s place
	 * depending on which type of statistic is provided (of time, score and lives) and the
	 * respective value of the statistic, then calls an appropriate method
	 * to assign each image for drawing
	 * @param image to draw
	 * @param string (time, score or life)
	 * @see drawOnes(image, string), drawTens(image, string), drawHundreds(image, string)
	 */
	private void getNumbers(int number, String type) {

		int ones = number % 10;
		int tens = (number%100)/10;
		int hundreds = number/100;

		if (type == "time" || type == "score" || type == "lives"){
			switch(ones){
				case 0: drawOnes(zero, type);
					break;
				case 1: drawOnes(one, type);
					break;
				case 2: drawOnes(two, type);
					break;
				case 3: drawOnes(three, type);
					break;
				case 4: drawOnes(four, type);
					break;
				case 5: drawOnes(five, type);
					break;
				case 6: drawOnes(six, type);
					break;
				case 7: drawOnes(seven, type);
					break;
				case 8: drawOnes(eight, type);
					break;
				case 9: drawOnes(nine, type);
					break;
			}
		}

		if (type == "score" || type == "time"){
			switch(tens){
				case 0: drawTens(zero, type);
					break;
				case 1: drawTens(one, type);
					break;
				case 2: drawTens(two, type);
					break;
				case 3: drawTens(three, type);
					break;
				case 4: drawTens(four, type);
					break;
				case 5: drawTens(five, type);
					break;
				case 6: drawTens(six, type);
					break;
				case 7: drawTens(seven, type);
					break;
				case 8: drawTens(eight, type);
					break;
				case 9: drawTens(nine, type);
					break;
			}
		}

		if (type == "score"){
			switch(hundreds){
				case 0: drawHundreds(zero);
					break;
				case 1: drawHundreds(one);
					break;
				case 2: drawHundreds(two);
					break;
				default:System.out.println("Max Score Exceeded");
			}
		}

	}


	/**
	 * This method assigns an appropriate image for the ones place
	 * depending on the supplied statistic
	 * @param image to draw
	 * @param string (time, score or life)
	 */
	private void drawOnes(Image image, String type) {
		switch (type){
			case "time": timeOnesImage = image;
				break;
			case "score": scoreOnesImage = image;
				break;
			case "lives": livesOnesImage = image;
				break;
		}

	}

	/**
	 * This method assigns an appropriate image for the tens place
	 * depending on the supplied statistic
	 * @param image to draw
	 * @param string (time, score or life)
	 */
	private void drawTens(Image image, String type) {
		switch (type){
			case "time": timeTensImage = image;
				break;
			case "score": scoreTensImage = image;
				break;
			default:
				break;
		}
	}

	/**
	 * This method assigns an appropriate image for the hundreds place
	 * depending on the supplied statistic
	 * @param image to draw
	 * @param string (time, score or life)
	 */
	private void drawHundreds(Image image){
		scoreHundredsImage = image;
	}

	/**
	 * This method supplies an integer representing the value of a score
	 * and the type of score as a string to the getNumbers method
	 * @param time
	 * @param lives
	 * @param score
	 * @see getNumbers(int, String)
	 */
	public void updateStatsText(int time, int lives, int score){
		getNumbers(time, "time");
		getNumbers(lives, "lives");
		getNumbers(score, "score");
		timeLast = time;
		livesLast = lives;
		scoreLast = score;
	}

	public static void setError(String imagePath, boolean errorStatus) {
		errorPath = imagePath;
		error = errorStatus;
	}
	/**
	 * This method returns the remaining game time
	 * @return game time as an integer
	 */
	public static int getTime() {
		return timeLast;
	}
	/**
	 * This method returns the player's life.
	 * @return player lives as an integer
	 */
	public static int getLives() {
		return livesLast;
	}
	/**
	 * This method returns the player's score.
	 * @return player score as an integer
	 */
	public static int getScore() {
		return scoreLast;
	}
	/**
	 * This method draws statistics related images for the player to see at a specified size.
	 * Incase of an error, errorPath is a changing variable, thus getImage will be called on it as it is drawm.
	 */
	public void paint(Graphics g){
		super.paint(g);
		if (error) {
			g.drawImage(getImage(errorPath), errorX, errorY, null);
		}
		g.drawImage(fishIcon, scoreIconX, scoreIconY, null);
		g.drawImage(timeIcon, timeIconX, timeIconY, null);
		g.drawImage(livesIcon, livesIconX, livesIconY, null);
		g.drawImage(timeTensImage, timeTensX, timeTensY, null);
		g.drawImage(timeOnesImage, timeOnesX, timeOnesY, null);
		g.drawImage(scoreHundredsImage, scoreHundredsX, scoreHundredsY, null);
		g.drawImage(scoreTensImage, scoreTensX, scoreTensY, null);
		g.drawImage(scoreOnesImage, scoreOnesX, scoreOnesY, null);
		g.drawImage(livesOnesImage, livesOnesX, livesOnesY, null);

	}
}
