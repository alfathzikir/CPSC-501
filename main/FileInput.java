package main;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import collectible.Collectible;

/**
 * This class reads the level files, and creates the necessary arrays and location from the file.
 *
 * @author Group 16
 */
public class FileInput {

	/**
	 * intArray is the array to which the integers found in the level file are assigned.
	 * items[][] is the array of collectibles that is generated based on the locations of
	 * the collectibles in the level file. bounds[][] is the array that contains only walls or paths.
	 */
	private int[][] intArray = new int[30][31];
	private Collectible items[][] = new Collectible[30][31];
	public final static Terrain bounds[][] = new Terrain[30][31];

	/**
	 * Images for the collectibles.
	 */
	private static final String FISH_IMAGE = "/collectible/images/fish2.png";
	private static final String LIFEUP_IMAGE = "/collectible/images/lifeup_angle.png";
	private static final String TIMEUP_IMAGE = "/collectible/images/timeup_angle.png";
	private static final String EMPTY_IMAGE = "/collectible/images/empty.png";
	private static final String HIDDEN_LEVEL_FISH = "/collectible/images/alt_fish.png";

	/**
	 * Start locations for the animals. -1 by default, but set according to the locations
	 * found in level text file.
	 */
	private int pengStartX = -1;
	private int pengStartY = -1;
	private int purpleBearStartX = -1;
	private int purpleBearStartY = -1;
	private int blueBearStartX = -1;
	private int blueBearStartY = -1;

	/**
	 * This method reads the integers in the level file and assigns them to the corresponding
	 * positions in intArray.
	 *
	 * @param FilePath
	 * @throws Exception
	 */
	public void getLayoutFromFile(String FilePath) throws Exception {

		InputStream stream = ClassLoader.getSystemResourceAsStream(FilePath);
		BufferedReader buffer = new BufferedReader(new InputStreamReader(stream));

		//if there is an error with input, try using this line instead of the previous two.
		//BufferedReader buffer = new BufferedReader(new FileReader(filename));

		String line = null;
		int y = 0;
		int x = 0;
		try{
			while ((line = buffer.readLine()) != null){
				String[] values = line.split(" ");
				for (String str : values){
					int str_int = Integer.parseInt(str);
					intArray[y][x]=str_int;
					y=y+1;
				}
				x=x+1;
				y = 0;
			}
		}catch(IOException e){
			visuals.statsText.setError("/visuals/errorImages/read.png", true);
			System.out.println("Error converting file to int Array");
		}
	}

	/**
	 * This is a debug method that prints the contents of the text file to the console if called.
	 */
	public void printBounds() {

		for (int x = 0; x < 30; x++){
			for (int y = 0; y < 31; y++){
				System.out.print(intArray[x][y] + " ");
			}
			System.out.println();
		}
	}

	/**
	 * THis method iterates through the intArray, and fills the Terrain[][] with new Terrain
	 * objects that are either walls or paths.
	 *
	 * @return Terrain[][]
	 */
	public Terrain[][] createTerrain(){
		for (int x = 0; x < 30; x++){
			for (int y = 0; y < 31; y++){
				if(intArray[x][y] == 8){
					bounds[x][y] = new Terrain();
					bounds[x][y].setWall(true);
				}
				else{
					bounds[x][y] = new Terrain();
					bounds[x][y].setWall(false);
				}
			}
		}
		return bounds;
	}

	/**
	 * This method places collectibles in the items[][], and assigns them a type and image
	 * based off of the text file.
	 */
	public void createCollectibles(){
		for (int x = 0; x < 30; x++){
			for (int y = 0; y < 31; y++){
				if(intArray[x][y] == 2 || intArray[x][y] == 26 || intArray[x][y] == 27){
					//Create a fish
					if (Game.getHiddenLevel()) {
						items[x][y] = new Collectible(x, y, Collectible.Type.FISH, HIDDEN_LEVEL_FISH);
					}
					else {
						items[x][y] = new Collectible(x, y, Collectible.Type.FISH, FISH_IMAGE);
					}
				}else if(intArray[x][y] == 3 || intArray[x][y] == 36 || intArray[x][y] == 37){
					//Create a LifeUp
					items[x][y] = new Collectible(x, y, Collectible.Type.LIFE, LIFEUP_IMAGE);
				}else if(intArray[x][y] == 4 || intArray[x][y] == 46 || intArray[x][y] == 47){
					//Create a TimeUp
					items[x][y] = new Collectible(x, y, Collectible.Type.TIMEUP, TIMEUP_IMAGE);
				}else{
					//Create an empty collectible
					items[x][y] = new Collectible(x, y, Collectible.Type.EMPTY, EMPTY_IMAGE);
				}
			}
		}
	}

	/**
	 * This method sets the start locations for the animals based off of the text file.
	 * Multiple integers can be a start location for the bears as the two digit numbers indicate
	 * a collectible at the same location. A number in the twenties indicates a fish, the thrities
	 * a timeUp, and the forties a lifeUp.
	 */
	public void setStartLocations(){
		for (int x = 0; x < 30; x++){
			for (int y = 0; y < 31; y++){
				if(intArray[x][y] == 0){
					pengStartX = x;
					pengStartY = y;
				}else if (intArray[x][y] == 6 || intArray[x][y] == 26 || intArray[x][y] == 36 || intArray[x][y] == 46){
					purpleBearStartX = x;
					purpleBearStartY = y;
				}else if(intArray[x][y] == 7 || intArray[x][y] == 27 || intArray[x][y] == 37 || intArray[x][y] == 47){
					blueBearStartX = x;
					blueBearStartY = y;
				}
			}
		}
	}

	/**
	 * This method iterates through the item[][] and paints all the items.
	 * @param g
	 */
	public void paintItems(Graphics g) {

		for (int x = 0; x < 30; x++){
			for (int y = 0; y < 31; y++){
				items[x][y].paint(g);
			}
		}
	}

	/**
	 * @param x
	 * @param y
	 * @return the type of collectible
	 */
	public Collectible.Type getType(int x, int y){
		return items[x][y].getType();
	}

	/**
	 * @param x
	 * @param y
	 * @param type of collectible to set the item at location x, y to.
	 */
	public void setType(int x, int y, Collectible.Type type){
		items[x][y].setType(type);
	}

	/**
	 * @param x
	 * @param y
	 * @param path of Image for the item at location x, y.
	 */
	public void setImage(int x, int y, String path){
		items[x][y].setImagePath(path);
	}

	/**
	 * @param x
	 * @param y
	 * @return whether or not a location is a wall.
	 */
	public boolean isWall(int x, int y){
		return bounds[x][y].isWall();
	}

	/**
	 * @return the x start location of the penguin.
	 */
	public int getPengStartX() {
		return pengStartX;
	}

	/**
	 * @return the y start location of the penguin.
	 */
	public int getPengStartY() {
		return pengStartY;
	}

	/**
	 * @return the x start location of the purple bear.
	 */
	public int getPurpleBearStartX() {
		return purpleBearStartX;
	}

	/**
	 * @return the y start location of the purple bear.
	 */
	public int getPurpleBearStartY() {
		return purpleBearStartY;
	}

	/**
	 * @return the x start location of the blue bear.
	 */
	public int getBlueBearStartX() {
		return blueBearStartX;
	}

	/**
	 * @return the y start location of the blue bear.
	 */
	public int getBlueBearStartY() {
		return blueBearStartY;
	}
}
