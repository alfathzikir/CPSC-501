package main;

import javax.swing.JPanel;

/**
 * This class contains the main method of our game.
 * It creates an instance of GenerateMenu, which creates
 * a menu that calls the rest of the game.
 * 
 * @author Group 16
 */
public class Main extends JPanel {
    private static final long serialVersionUID = 1L;
    public static void main(String[] args) {
        new GenerateMenu();
    }
}