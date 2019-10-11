package main;

import javax.swing.*;

public class Level extends JFrame{

	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 1350;
    private static final int HEIGHT = 1000;

    private static JFrame mapOne;

    /**
     * Creates a JFrame which holds the game and all its components.
     * @param isLevelOne
     * @param isLevelOneSave
     */
	public void createLevel(boolean isLevelOne, boolean isLevelOneSave) {
        mapOne = new JFrame("Mr. Penguin");
        mapOne.setSize(WIDTH, HEIGHT);
        mapOne.setResizable(true);
        mapOne.setLocationRelativeTo(null);
        mapOne.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Try to add the game to the JFrame
		//If it fails, display an error message.
        try {
            mapOne.add(new Game(isLevelOne, isLevelOneSave));
            mapOne.setVisible(true);
        } catch (Exception e1) {
            mapOne.setVisible(false);
            mapOne.dispose();
            String error = "Error loading level.";
            new ErrorMessage(error);
            System.out.println(error);
			e1.printStackTrace();
        }
    }

	//Removes the map.
    public static void deleteMap() {
        mapOne.setVisible(false);
        mapOne.dispose();
	}
}
