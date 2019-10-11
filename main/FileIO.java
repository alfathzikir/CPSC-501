package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Group 16
 * @version 
 * This class contains methods that read and write to the save and stats files.
 */
public class FileIO {
	
	private int time;
	private int score;
	private int lives;
	
	/**
	 * This method resets the save file or stats file to the default for the level.
	 * 
	 * @param destPath
	 * @param sourcePath
	 */
	public void resetFiles(String destPath, String sourcePath, boolean isStats){

		try{
			BufferedReader input = new BufferedReader(new FileReader("source/" + sourcePath));

			FileWriter destFile = new FileWriter("source/" + destPath,false);
			BufferedWriter output = new BufferedWriter(destFile);

			String line = null;

			//Copy lines from the source file(original level/stats) to the destination file(save/stats file).
			while((line = input.readLine()) != null){
				output.write(line);
				if (isStats){
					output.write(" ");
				}else{
				output.write("\n");
				}
			}
			input.close();
			output.close();

		}catch(IOException ioe){
			ioe.printStackTrace();
			visuals.statsText.setError("/visuals/errorImages/write.png", true);
			System.out.println("Error resetting files.");
		}
	}

	/**
	 * This method loads the stats from the stats file associated with a level.
	 * If reading from the file fails, the stats are set to the default for the level.
	 * The three integers are read to an array of strings, and each value is parsed to
	 * an int and assigned to the appropriate variable.
	 * 
	 * @param FilePath
	 */
	public void loadStats(String FilePath){
		BufferedReader buffer = null;

		try{
			buffer = new BufferedReader(new FileReader("source/" + FilePath));

			//Read the line in the file and split it into an array of strings.
			String line;
			line = buffer.readLine();
			String[] values = line.split(" ");
			System.out.println(values[0] + " " + values[1] + " " + values[2]);

			//assign each value to it's corresponding statistic.
			score = Integer.parseInt(values[0]);
			lives = Integer.parseInt(values[1]);
			time = Integer.parseInt(values[2]);

			buffer.close();

		}catch(IOException ioe){
			//Reset to the default values if loading the stats fails.
			visuals.statsText.setError("/visuals/errorImages/corrupted.png", true);
			System.out.println("Error loading saved statistics. Using default values.");
			score = 0;
			lives = 2;
			time = 30;
		}
	}

	/**
	 * @return time in seconds
	 */
	public int getTime() {
		return time;
	}

	/**
	 * @param time in seconds
	 */
	public void setTime(int time) {
		this.time = time;
	}

	/**
	 * @return score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @return lives
	 */
	public int getLives() {
		return lives;
	}

	/**
	 * @param lives
	 */
	public void setLives(int lives) {
		this.lives = lives;
	}
}
