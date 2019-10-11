package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GenerateMenu extends JPanel {
    private static final long serialVersionUID = 1L;

    /**
     * Constants for actions of primary (parent) menu
     */
    private static final String START_GAME = "START";
    private static final String LOAD = "LOAD";
    private static final String ABOUT_GAME = "ABOUT";
    private static final String EXIT_GAME = "EXIT";

    /**
     * Constants for actions of "LOAD" sub-menu
     */
    private final String LEVEL_ONE = "LVL1";
    private final String LEVEL_TWO = "LVL2";
    
    private final String LOAD_SAVED_ONE = "LOAD1";
    private final String LOAD_SAVED_TWO = "LOAD2";

    /**
     * Constants for actions of "LOAD" sub-menu
     */
    private final String PARENT_MENU = "PARENT_MENU";
    private final String BACK = "BACK";

    /**
     * Constants for use in toggling card views
     */
    private final String ABOUT = "ABOUT";
    private final String LEVEL_OPTIONS = "LEVEL_OPTIONS";
    private final String START_OPTIONS = "START_OPTIONS";


    /**
     * cardFrame is used to hold the CardLayout instance 'menuCards'
     * parentCards is used to toggle card views
     */
    private final Container parentCards;
    private static CardLayout menuCards;
    private static JPanel aboutGame;
    private static JFrame cardFrame;
    private static JPanel parentButtonPane;
    private static JPanel levelButtonPane;
    private static JPanel startButtonPane;
    private JButton returnButton;
    private static MenuActionListener listener;

	//This method creates the card layout used to display the menus.
    public GenerateMenu() {
        setLayout(new FlowLayout());
        setSize(700, 500);

		//A listener is added to respond to the buttons.
        listener = new MenuActionListener();
        parentButtonPane = new JPanel(new GridBagLayout());
        menuCards = new CardLayout();
        cardFrame = new JFrame("Mr. Penguin");
        cardFrame.setLayout(menuCards);

        parentMenuButtons();
        parentButtonPane.setVisible(true);

        levelOptions();
        levelButtonPane.setVisible(true);

        aboutOption();
        aboutGame.setVisible(true);
        
        startOption();
        startButtonPane.setVisible(true);

		//Add buttons to the cardFrame.
        cardFrame.add(parentButtonPane, PARENT_MENU);
        cardFrame.add(aboutGame, ABOUT);
        cardFrame.add(levelButtonPane, LEVEL_OPTIONS);
        cardFrame.add(startButtonPane, START_OPTIONS);
        
		//fnish initializing the menu JFrame.
        cardFrame.pack();
        cardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cardFrame.setVisible(true);
        cardFrame.setResizable(false);

        parentCards = cardFrame.getContentPane();
        menuCards.show(parentCards, PARENT_MENU);
    }

	/**
	 * This method creates the buttons for the main menu
	 */
    private void parentMenuButtons() {
        GridBagConstraints grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.VERTICAL;
        JButton buttons[];
        buttons = new JButton[4];

		//Create the start button
        buttons[0] = new JButton(START_GAME);
        buttons[0].setIcon(new ImageIcon(getClass().getResource("/visuals/menuGraphics/start.png")));
        grid.weighty = 0;
        grid.weightx = 0.5;
        grid.gridwidth = 3;
        grid.gridy = 0;
        parentButtonPane.add(buttons[0], grid);

		//Creates the load button.
        buttons[1] = new JButton(LOAD);
        buttons[1].setIcon(new ImageIcon(getClass().getResource("/visuals/menuGraphics/load.png")));
        grid.gridy = 1;
        parentButtonPane.add(buttons[1], grid);

		//Creates the about button.
        buttons[2] = new JButton(ABOUT_GAME);
        buttons[2].setIcon(new ImageIcon(getClass().getResource("/visuals/menuGraphics/about.png")));
        grid.gridy = 2;
        parentButtonPane.add(buttons[2], grid);

		//Creates the exit button.
        buttons[3] = new JButton(EXIT_GAME);
        buttons[3].setIcon(new ImageIcon(getClass().getResource("/visuals/menuGraphics/exit.png")));
        grid.gridy = 3;
        parentButtonPane.add(buttons[3], grid);

		//Format buttons.
        for (int i = 0;i<4;i++) {
            buttons[i].setFont(buttons[i].getFont().deriveFont(Font.BOLD, 24f));
            buttons[i].setText(" ");
            buttons[i].setOpaque(true);
            buttons[i].setHorizontalTextPosition(JButton.CENTER);
            buttons[i].setVerticalTextPosition(JButton.CENTER);
            buttons[i].addActionListener(listener);
        }

		//Assign actions to each button.
        buttons[0].setActionCommand(START_GAME);
        buttons[1].setActionCommand(LOAD);
        buttons[2].setActionCommand(ABOUT_GAME);
        buttons[3].setActionCommand(EXIT_GAME);
    }
	
    /**
	 * This method creates the buttons for selecting a new level.
	 */
    private void startOption(){

        JButton startButtons[];
        startButtons = new JButton[3];

        startButtonPane = new JPanel(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.VERTICAL;
		
		//Create the level1 button.
        startButtons[0] = new JButton(LEVEL_ONE);
        startButtons[0].setIcon(new ImageIcon(getClass().getResource("/visuals/menuGraphics/levelone.png")));
        startButtons[0].setText("Level One");
        grid.weighty = 0;
        grid.weightx = 0.5;
        grid.gridwidth = 3;
        grid.gridy = 0;
        startButtonPane.add(startButtons[0], grid);

		//Create the level 2 button.
        startButtons[1] = new JButton(LEVEL_TWO);
        startButtons[1].setIcon(new ImageIcon(getClass().getResource("/visuals/menuGraphics/leveltwo.png")));
        startButtons[1].setText("Level Two");
        grid.gridy = 1;
        startButtonPane.add(startButtons[1], grid);

		//Create the return to main menu button.
        startButtons[2] = new JButton(BACK);
        startButtons[2].setIcon(new ImageIcon(getClass().getResource("/visuals/menuGraphics/return.png")));
        startButtons[2].setText("Return to Main Menu");
        grid.gridy = 2;
        startButtonPane.add(startButtons[2], grid);

		//Format the buttons.
        for (int i = 0;i<3;i++) {
            startButtons[i].setFont(startButtons[i].getFont().deriveFont(Font.BOLD, 24f));
            startButtons[i].setText(" ");
            startButtons[i].setOpaque(true);
            startButtons[i].setHorizontalTextPosition(JButton.CENTER);
            startButtons[i].setVerticalTextPosition(JButton.CENTER);
            startButtons[i].addActionListener(listener);
        }
		//Assign an action to each button.
        startButtons[0].setActionCommand(LEVEL_ONE);
        startButtons[1].setActionCommand(LEVEL_TWO);
        startButtons[2].setActionCommand(BACK);
    }
	
	/**
	 * THis method displays the about planel when called.
	 */
    public void aboutOption() {
        aboutGame = new JPanel(new GridBagLayout());
        aboutGame.setBackground(Color.white);

        GridBagConstraints grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.VERTICAL;
        
		//Display a summary of the game.
        JLabel textInfo = new JLabel("<html><center>This is a pacman style game. "
        		+ "The goal of this game is to get as many points as possible while avoiding the polarbear.<br>"
        		+ "Power ups are provided to help you get as many points as possible.<br>"
        		+ "Good luck and have fun!<br>"
        		+ "</html>",JLabel.CENTER);
        textInfo.setFont(new Font("Times new roman",1,26));

		//Display the credits.
        JLabel credits = new JLabel("<html><br>CPSC 233: Winter 2017 Final Project<br>"
        		+ "<center>Alan Fung<br>"
        		+ "Alfath Zikir<br>"
        		+ "Brent Schreimer<br>"
        		+ "Gabriela Wcislo<br>"
        		+ "Sarah Hatcher<br>"
        		+ " <br>"
        		+ " <br>"
        		+ " <br>"
        		+ " <br>"
        		+ " <br>"
        		+ " <br>"
        		+ " <br>"
        		+ " <br>"
        		+ " <br>"
        		+ "</html>", JLabel.CENTER);
        credits.setFont(new Font("Times new roman",1,30));
        credits.setForeground(Color.black);

		//Create the return to main menu button.
        returnButton = new JButton(BACK);
        returnButton.addActionListener(listener);
        returnButton.setActionCommand(BACK);
        returnButton.setIcon(new ImageIcon(getClass().getResource("/visuals/menuGraphics/return.png")));
        returnButton.setText(" ");

        grid.weighty = 0;
        grid.weightx = 0.5;
        grid.gridwidth = 3;
        grid.gridy = 0;
        aboutGame.add(textInfo,grid);
        
        grid.gridy = 1;
        aboutGame.add(credits, grid);

        grid.gridy = 2;
        aboutGame.add(returnButton, grid);
    }

	/**
	 * This method displays the options for loading a level
	 * from a save file.
	 */
    private void levelOptions() {
        int OPTION_COUNT = 3;

        JButton levelButtons[];
        levelButtons = new JButton[OPTION_COUNT];

        levelButtonPane = new JPanel(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.VERTICAL;
        
		//Create the load level 2 button.
        levelButtons[0] = new JButton(LOAD_SAVED_ONE);
        levelButtons[0].setIcon(new ImageIcon(getClass().getResource("/visuals/menuGraphics/loadone.png")));
        levelButtons[0].setText("Load level 1");
        grid.weighty = 0;
        grid.weightx = 0.5;
        grid.gridwidth = 3;
        grid.gridy = 0;
        levelButtonPane.add(levelButtons[0], grid);

		//Create the load level 2 button.
        levelButtons[1] = new JButton(LOAD_SAVED_TWO);
        levelButtons[1].setIcon(new ImageIcon(getClass().getResource("/visuals/menuGraphics/loadtwo.png")));
        levelButtons[1].setText("Load level 2");
        grid.gridy = 1;
        levelButtonPane.add(levelButtons[1], grid);

		//Create the return to main menu button.
        levelButtons[2] = new JButton(BACK);
        levelButtons[2].setIcon(new ImageIcon(getClass().getResource("/visuals/menuGraphics/return.png")));
        levelButtons[2].setText("Return to Main Menu");
        grid.gridy = 2;
        levelButtonPane.add(levelButtons[2], grid);

		//Format the buttons.
        for (int i = 0;i<OPTION_COUNT;i++) {
            levelButtons[i].setFont(levelButtons[i].getFont().deriveFont(Font.BOLD, 24f));
            levelButtons[i].setText(" ");
            levelButtons[i].setOpaque(true);
            levelButtons[i].setHorizontalTextPosition(JButton.CENTER);
            levelButtons[i].setVerticalTextPosition(JButton.CENTER);
            levelButtons[i].addActionListener(listener);
        }
		//Assign an action to each button.
        levelButtons[0].setActionCommand(LOAD_SAVED_ONE);
        levelButtons[1].setActionCommand(LOAD_SAVED_TWO);
        levelButtons[2].setActionCommand(BACK);
    }


    class MenuActionListener extends JFrame implements ActionListener {

        private static final long serialVersionUID = 1L;
        
        final Level level = new Level();

		//This method performs the actions associated with clicking on a button.
		//If a level is created, the menu is disposed.
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case START_GAME:
                    menuCards.show(parentCards, START_OPTIONS);
                    break;

                case LOAD:
                    System.out.println("load game");
                    menuCards.show(parentCards, LEVEL_OPTIONS);
                    break;

                case ABOUT_GAME:
                    System.out.println("about game");
                    menuCards.show(parentCards, ABOUT);
                    break;

                case EXIT_GAME:
                    System.out.println("exit game");
                    System.exit(0);
                    break;

                case LEVEL_ONE:
                	level.createLevel(true, false);
                    System.out.println("Loading level one..");
                    cardFrame.dispose();
                    break;

                case LEVEL_TWO:
                	level.createLevel(false, true);
                    System.out.println("Loading level two..");
                    cardFrame.dispose();
                    break;
                    
                case LOAD_SAVED_ONE:
                	level.createLevel(true, true);
                    System.out.println("Loading previously saved game for level 1..");
                    cardFrame.dispose();
                    break;
                    
                case LOAD_SAVED_TWO:
                	level.createLevel(false, false);
                    System.out.println("Loading previously saved game for level 2..");
                    cardFrame.dispose();
                    break;

                case BACK:
                    System.out.println("return to menu");
                    menuCards.show(parentCards, PARENT_MENU);

            }
        }
    }
}