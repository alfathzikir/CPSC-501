package main;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import animal.KeyInput;
import animal.Penguin;
import collectible.Collectible;
import animal.Polarbear;
import visuals.BackgroundImage;
import visuals.EndgameGraphics;
import visuals.statsText;
import stats.Statistics;

import java.io.*;

import animal.Animal;

/**
 * @author Group 16
 * @version
 *
 * This class creates the level specific elements of the game.
 * It also contains the swing timer that runs the game.
 * It places and updates all items and players/enemies.
 * Once the game is over, the endgame panel is called, and the game is over.
 */
public class Game extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;

	/**
	 * Animal image paths
	 */
	private static final String PENG_PATH_UP = "/animal/Animal_Images/penguinUp.png";
	private static final String PENG_PATH_DOWN = "/animal/Animal_Images/penguinDown.png";
	private static final String PENG_PATH_LEFT = "/animal/Animal_Images/penguinLeft.png";
	private static final String PENG_PATH_RIGHT = "/animal/Animal_Images/penguinRight.png";
	private static final String BLUE_POLARBEAR_IMAGE_DOWN = "/animal/Animal_Images/bluePolarbearDown.png";
	private static final String PURPLE_POLARBEAR_IMAGE_DOWN = "/animal/Animal_Images/purplePolarbearDown.png";
	private static final String BLUE_POLARBEAR_IMAGE_UP = "/animal/Animal_Images/bluePolarbearUp.png";
	private static final String PURPLE_POLARBEAR_IMAGE_UP = "/animal/Animal_Images/purplePolarbearUp.png";
	private static final String BLUE_POLARBEAR_IMAGE_LEFT = "/animal/Animal_Images/bluePolarbearLeft.png";
	private static final String PURPLE_POLARBEAR_IMAGE_LEFT = "/animal/Animal_Images/purplePolarbearLeft.png";
	private static final String BLUE_POLARBEAR_IMAGE_RIGHT = "/animal/Animal_Images/bluePolarbearRight.png";
	private static final String PURPLE_POLARBEAR_IMAGE_RIGHT = "/animal/Animal_Images/purplePolarbearRight.png";
	private static final String HIDDEN_LEVEL_MONSTER = "/animal/Hidden_Version/monster.png";
	private static final String HIDDEN_LEVEL_MONSTER_TWO = "/animal/Hidden_Version/monster2.png";

	private static final String EMPTY_IMAGE = "/collectible/images/empty.png";


	/**
	 * Filepaths for map files, save files, and background images
	 */
	private static final String FILENAME_1 = "IOFiles/Level1.txt";
	private static final String FILENAME_2 = "IOFiles/Level2.txt";
	private static final String MAPPATH_1 = "/Backgrounds/map1.png";
	private static final String MAPPATH_2 = "/Backgrounds/map2.png";
	private static final String MAPPATH_3 = "/Backgrounds/map3.png";
	private static final String SAVEFILE_1 = "IOFiles/Level1Save.txt";
	private static final String SAVEFILE_2 = "IOFiles/Level2Save.txt";
	private static final String STATS1 = "IOFiles/Level1Stats.txt";
	private static final String STATS1SAVE = "IOFiles/Level1SaveStats.txt";
	private static final String STATS2 = "IOFiles/Level2Stats.txt";
	private static final String STATS2SAVE = "IOFiles/Level2SaveStats.txt";

	/**
	 * TIMER_DELAY is the 'tick' duration of the gameLoopTimer
	 * The gameLoopTimer is the swing timer that fires the game events
	 * Timer running refers to the 1 second interval clock that is used to keep track
	 * of time during the game.
	 */
	private static final int TIMER_DELAY = 200;
	private Timer gameLoopTimer;
	private boolean timerRunning = false;

	/**
	 * gameState is the current level.
	 * backgroundState is the current map image being displayed.
	 * endCondition is the state that caused the game to end (out of time or lives).
	 */
	private static String gameState;
	private int backgroundState = 1;
	private int endCondition;

	/**
	 * The saveArray is the most recent stored positions of everything in the game.
	 * The gameStatsFile and gameMapFile are the files to write to when saving.
	 * hiddenLevel controls whether or not the hidden level is the active level.
	 */
	private int[][] saveArray = new int[30][31];
	private String gameStatsFile;
	private String gameMapFile;
	private static boolean hiddenLevel = false;

	/**
	 * The mapSizex and mapSizeY are the dimensions of the BackgroundImage map.
	 */
	private int mapSizeX = 1350;
	private int mapSizeY = 930;
	private BackgroundImage map;

	/**
	 * Penguin is the player, and the polar bears are the enemies.
	 */
	private Penguin penguin;
	private Polarbear blueBear;
	private Polarbear purpleBear;

	/**
	 * Instances of Statistics, FileInput, FIleOutput, and statsText.
	 */
	private Statistics stats = new Statistics();
	private FileInput initializeMap = new FileInput();
	private FileIO loadFiles = new FileIO();
	private statsText imageText;

	/**
	 * This constructor creates an instance of the Game.
	 * It creates the level that is specified by the menu button clicked.
	 *
	 * @param isLevelOne
	 * @param isLoadingOne
	 * @throws Exception
	 */
	public Game(boolean isLevelOne, boolean isLoadingOne) throws Exception{
		setFocusable(true);
		boolean isSaveLevel;

		//Create the specified level
		try {
			//Level 1
			if (isLevelOne && isLoadingOne == false){
				isSaveLevel = false;
				gameState = "LEVEL_ONE";
				loadLevel(FILENAME_1, STATS1, MAPPATH_1, SAVEFILE_1, isSaveLevel);
			}
			//Level 2
			else if (isLevelOne == false && isLoadingOne){
				isSaveLevel = false;
				gameState = "LEVEL_TWO";
				loadLevel(FILENAME_2, STATS2, MAPPATH_2, SAVEFILE_2, isSaveLevel);
			}
			//Level 1 save
			else if (isLevelOne && isLoadingOne){
				isSaveLevel = true;
				gameState = "LEVEL_ONE";
				loadLevel(FILENAME_1, STATS1SAVE, MAPPATH_1, SAVEFILE_1, isSaveLevel);
			}
			//Level 2 save
			else if (isLevelOne == false && isLoadingOne == false){
				isSaveLevel = true;
				gameState = "LEVEL_TWO";
				loadLevel(FILENAME_2, STATS2SAVE, MAPPATH_2, SAVEFILE_2, isSaveLevel);
			}
			if (hiddenLevel) {
				//Hidden level overrides any other level
				isSaveLevel = false;
				gameState= "HIDDEN";
				loadLevel(FILENAME_2, STATS2, MAPPATH_3, SAVEFILE_2, isSaveLevel);
			}
		}catch (IOException ioe) {
			ioe.printStackTrace();
			String error = "Error: Failed to load map from file.";
			new ErrorMessage(error);
			System.out.println(error);
		}

		//Set the save files according to map in use
		if (gameState == "LEVEL_ONE"){
			gameStatsFile = "source/IOFiles/Level1SaveStats.txt";
			gameMapFile = "source/IOFiles/Level1Save.txt";
		}else if (gameState == "LEVEL_TWO"){
			gameStatsFile = "source/IOFiles/Level2SaveStats.txt";
			gameMapFile = "source/IOFiles/Level2Save.txt";
		}
	}

	/**
	 * Creates a level based on the parameters given.
	 *
	 * @param filename
	 * @param statsFile
	 * @param mapFile
	 * @param saveFile
	 * @param state
	 * @param isSave
	 * @throws Exception
	 */
	private void loadLevel(String filename, String statsFile, String mapFile, String saveFile, boolean isSave) throws Exception{
		try{
			//Load the map from the saveFile if the level being loaded is a save
			//or load the map from the original level file if the level is a new level or the hidden level.
			if (isSave){
				initializeMap.getLayoutFromFile(saveFile);
			}else{
				initializeMap.getLayoutFromFile(filename);
			}

			initializeMap.setStartLocations();
			initializeMap.createTerrain();
			initializeMap.createCollectibles();
			loadFiles.loadStats(statsFile);

			//Create the penguin(player) and bears(enemies)
			penguin = new Penguin(initializeMap.getPengStartX(), initializeMap.getPengStartY(), Animal.Direction.NULL, PENG_PATH_UP, PENG_PATH_DOWN, PENG_PATH_LEFT, PENG_PATH_RIGHT);
			if (!hiddenLevel) {
				purpleBear = new Polarbear(initializeMap.getPurpleBearStartX(), initializeMap.getPurpleBearStartY(), Animal.Direction.NULL, PURPLE_POLARBEAR_IMAGE_UP, PURPLE_POLARBEAR_IMAGE_DOWN, PURPLE_POLARBEAR_IMAGE_LEFT, PURPLE_POLARBEAR_IMAGE_RIGHT);
				blueBear = new Polarbear(initializeMap.getBlueBearStartX(), initializeMap.getBlueBearStartY(), Animal.Direction.NULL, BLUE_POLARBEAR_IMAGE_UP, BLUE_POLARBEAR_IMAGE_DOWN, BLUE_POLARBEAR_IMAGE_LEFT, BLUE_POLARBEAR_IMAGE_RIGHT);
			}else {
				purpleBear = new Polarbear(initializeMap.getPurpleBearStartX(), initializeMap.getPurpleBearStartY(), Animal.Direction.NULL, HIDDEN_LEVEL_MONSTER, HIDDEN_LEVEL_MONSTER_TWO, HIDDEN_LEVEL_MONSTER, HIDDEN_LEVEL_MONSTER_TWO);
				blueBear = new Polarbear(initializeMap.getBlueBearStartX(), initializeMap.getBlueBearStartY(), Animal.Direction.NULL, HIDDEN_LEVEL_MONSTER_TWO, HIDDEN_LEVEL_MONSTER, HIDDEN_LEVEL_MONSTER_TWO, HIDDEN_LEVEL_MONSTER);
			}

			stats.setScore(loadFiles.getScore());
			stats.setLives(loadFiles.getLives());
			stats.setTime(loadFiles.getTime());

			addKeyListener(new KeyInput(penguin));
			//Start the stats display
			imageText = new statsText();

			map = new BackgroundImage(mapFile, mapSizeX, mapSizeY);

			//After creating the player and enemies in the level, reset the default start locations
			//so that respawns happen at the correct location.
			if (isSave){
				initializeMap.getLayoutFromFile(filename);
				initializeMap.setStartLocations();
			}

			//Begin gameloop
			gameLoopTimer = new Timer(TIMER_DELAY, this);
			gameLoopTimer.start();
			System.out.println("Game Started");

		}catch(IOException ioe){
			ioe.printStackTrace();
			String error = "Error: Failed to load game from file.";
			new ErrorMessage(error);
			System.out.println(error);
		}
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g){
		super.paint(g);

		switch (backgroundState) {
			case 1:
				//Paint game images
				map.paint(g);
				imageText.paint(g);
				initializeMap.paintItems(g);
				penguin.paint(g);
				purpleBear.paint(g);
				blueBear.paint(g);
				break;
			case 2:
				//paint the end game screen
				EndgameGraphics done = new EndgameGraphics(endCondition);
				done.run();
				break;
			default:
				String error = "Error: Invalid background state.";
				new ErrorMessage(error);
				System.out.println(error);
		}
	}

	/**
	 * This method tests to see if the penguin is on an item,
	 * and picks up the item and removes it from the screen if it is.
	 */
	public void itemChecker(){

		int pengX = penguin.getXcoord();
		int pengY = penguin.getYcoord();

		//Get the type of collectible at the location of the penguin.
		Collectible.Type type;
		type = initializeMap.getType(pengX, pengY);

		//If a collectible is at the players current location, collect it and remove it fromt he map.
		if(type == Collectible.Type.TIMEUP){
			initializeMap.setImage(pengX, pengY, EMPTY_IMAGE);
			initializeMap.setType(pengX, pengY, Collectible.Type.EMPTY);
			stats.addTime();
		}else if(type == Collectible.Type.LIFE){
			initializeMap.setImage(pengX, pengY, EMPTY_IMAGE);
			initializeMap.setType(pengX, pengY, Collectible.Type.EMPTY);
			stats.addLife();
		}else if(type == Collectible.Type.FISH){
			initializeMap.setImage(pengX, pengY, EMPTY_IMAGE);
			initializeMap.setType(pengX, pengY, Collectible.Type.EMPTY);
			stats.addScore();
		}
	}

	/**
	 * @param bgState
	 */
	public void setBackgroundState(int bgState)
	{
		this.backgroundState = bgState;
	}

	/**
	 * This method respawns the penguin and the polar bears.
	 * The game is saved immediately after respawn.
	 */
	public void respawn(){
		stats.removeLife();

		//Reset player and enemy locations
		penguin.setXcoord(initializeMap.getPengStartX());
		penguin.setYcoord(initializeMap.getPengStartY());
		blueBear.setXcoord(initializeMap.getBlueBearStartX());
		blueBear.setYcoord(initializeMap.getBlueBearStartY());
		purpleBear.setXcoord(initializeMap.getPurpleBearStartX());
		purpleBear.setYcoord(initializeMap.getPurpleBearStartY());
		penguin.setHasMoved(false);
		penguin.setDirection(Animal.Direction.NORTH);

		//Save the level it is not the hidden level
		if (!hiddenLevel) {
			prepSave();
			writeStats();
			writeSave();
		}
	}

	/**
	 * This method can be called to check if the game has met any of the conditions
	 * to end the game. Once the game is ended, the saves are reset.
	 */
	public void checkOver()
	{
		boolean isOver;

		//Set isOver to true if lives or time are 0.
		if (stats.getLife()==0){
			setBackgroundState(2);
			isOver = true;
			endCondition = 0;
			stats.stopClock();
		}else if (stats.getTime()==0){
			setBackgroundState(2);
			isOver = true;
			endCondition = 1;
			stats.stopClock();
		}else{
			isOver = false;
		}

		//If the game is over, reset the saves.
		if (isOver){
			boolean isStats;
			if (gameState == "LEVEL_ONE"){
				isStats = false;
				loadFiles.resetFiles(SAVEFILE_1,FILENAME_1,isStats);
				isStats = true;
				loadFiles.resetFiles(STATS1SAVE,STATS1,isStats);
			}else if(gameState == "LEVEL_TWO") {
				isStats = false;
				loadFiles.resetFiles(SAVEFILE_2, FILENAME_2,isStats);
				isStats = true;
				loadFiles.resetFiles(STATS2SAVE, STATS2,isStats);

			}
			else if(gameState =="HIDDEN") {
				System.out.print("Hidden level does not get saved.");
			}
			gameLoopTimer.stop();
		}
	}

	/**
	 * This method fills the saveArray with whatever is at the location. It iterates through
	 * the array, and checks every other array for items at that location. If a bear and a collectible
	 * are at the same location, it creates an int in the array that represents both of them.
	 */
	public void prepSave(){

		//Set the locations of all items/animals/bounds in the saveArray.
		for(int x = 0; x < 30; x++){
			for(int y = 0; y < 31; y++){
				if (initializeMap.isWall(x, y)){
					saveArray[x][y] = 8;
				}else if(x == purpleBear.getXcoord() && y == purpleBear.getYcoord() && initializeMap.getType(x,y) == Collectible.Type.FISH){
					saveArray[x][y] = 26;
				}else if(x == blueBear.getXcoord() && y == blueBear.getYcoord() && initializeMap.getType(x,y) == Collectible.Type.FISH){
					saveArray[x][y] = 27;
				}else if(x == purpleBear.getXcoord() && y == purpleBear.getYcoord() && initializeMap.getType(x,y) == Collectible.Type.LIFE){
					saveArray[x][y] = 36;
				}else if(x == blueBear.getXcoord() && y == blueBear.getYcoord() && initializeMap.getType(x,y) == Collectible.Type.LIFE){
					saveArray[x][y] = 37;
				}else if(x == purpleBear.getXcoord() && y == purpleBear.getYcoord() && initializeMap.getType(x,y) == Collectible.Type.TIMEUP){
					saveArray[x][y] = 46;
				}else if(x == blueBear.getXcoord() && y == blueBear.getYcoord() && initializeMap.getType(x,y) == Collectible.Type.TIMEUP){
					saveArray[x][y] = 47;
				}else if(initializeMap.getType(x, y) == Collectible.Type.FISH){
					saveArray[x][y] = 2;
				}else if(initializeMap.getType(x, y) == Collectible.Type.LIFE){
					saveArray[x][y] = 3;
				}else if(initializeMap.getType(x, y) == Collectible.Type.TIMEUP){
					saveArray[x][y] = 4;
				}else if(x == penguin.getXcoord() && y == penguin.getYcoord()){
					saveArray[x][y] = 0;
				}else if(x == purpleBear.getXcoord() && y == purpleBear.getYcoord()){
					saveArray[x][y] = 6;
				}else if(x == blueBear.getXcoord() && y == blueBear.getYcoord()){
					saveArray[x][y] = 7;
				}else{
					saveArray[x][y] = 1;
				}
			}
		}
	}

	/**
	 * THis method writes the save statistics to a file.
	 */
	public void writeStats(){
		FileWriter statsWriter;
		try{
			statsWriter = new FileWriter(gameStatsFile, false);
			statsWriter.write(stats.getScore()+ " " + stats.getLife() + " " + stats.getTime());
			statsWriter.close();
		}catch (IOException ioe){
			ioe.printStackTrace();
			visuals.statsText.setError("/visuals/errorImages/write.png", true);
			System.out.println("Error saving stats.");
		}
	}

	/**
	 * This method writes the save map to a file.
	 */
	public void writeSave(){
		FileWriter mapWriter;
		try {
			mapWriter = new FileWriter(gameMapFile, false);
			for (int y = 0; y < 31; y++) {
				for (int x = 0; x < 30; x++) {
					mapWriter.write(saveArray[x][y]+" ");
				}
				mapWriter.write("\n");
			}
			mapWriter.close();

		} catch (IOException ioe) {
			ioe.printStackTrace();
			visuals.statsText.setError("/visuals/errorImages/write.png", true);
			System.out.println("Error saving map status.");
		}
	}

	/**
	 * @param hiddenState
	 */
	public static void setHiddenLevel(boolean hiddenState) {
		hiddenLevel = hiddenState;
	}

	/**
	 * @return
	 */
	public static boolean getHiddenLevel() {
		return hiddenLevel;
	}
	/**
	 * This method contains all actions or method calls that are executed by the gameLoopTimer.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Penguin copyPenguin;

		//Wait for penguin movement to start polar bear movement
		if(penguin.hasMoved()){
			copyPenguin = new Penguin(penguin,PENG_PATH_UP, PENG_PATH_DOWN, PENG_PATH_LEFT, PENG_PATH_RIGHT );

			purpleBear.movePolarbear(copyPenguin);
			blueBear.movePolarbear(copyPenguin);
			if (!hiddenLevel) {
				prepSave();
				writeStats();
				writeSave();
			}
		}
		//Start the clock when the player moves
		if(penguin.hasMoved() && !timerRunning){
			stats.start();
			timerRunning = true;
		}

		//Test to see if the game should end.
		checkOver();

		//penguin respawn
		if (((penguin.getXcoord() == purpleBear.getXcoord()) && penguin.getYcoord() == purpleBear.getYcoord())||((penguin.getXcoord() == blueBear.getXcoord()) && penguin.getYcoord() == blueBear.getYcoord())){
			respawn();
		}
		//update penguin/polar bears and check for item pickup
		penguin.updateVelocity();
		itemChecker();
		imageText.updateStatsText(stats.getTime(), stats.getLife(), stats.getScore());

		//update images
		repaint();
	}
}
