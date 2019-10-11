package visuals;

import main.ErrorMessage;
import main.Game;
import main.GenerateMenu;
import main.Level;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * This class sets up all end-game images and creates a 'game-over' frame.
 * An option to return to the main menu is also defined here
 * 
 * Create an instance of this class and pass an integer as a parameter to specify the
 * end-game image (0 for losing, 1 for winning)
 * new EndGameGraphics(int)
 * 
 * Call redraw() to show the new image.
 * 
 * @author Group 16
 */

public class EndgameGraphics extends JFrame {
	private static final long serialVersionUID = 1L;

	private int winOrLose;

	private String loseImagePath = "/Backgrounds/NoLivesEnd.png";
	private String winGamePath = "/Backgrounds/TimeUpEnd.png";

	private final String SHOW_MENU = "Return to Menu";
	private final String HIDDEN_LEVEL = "Play hidden level";

	Image endGameImage = null;
	ImageIcon backImage;

	JButton showMenu;
	JPanel endPanel;
	JPanel statsPanel;
	JFrame endFrame;
	
	JButton hiddenLevel;

	
	/**
	 * This constructor initializes the endgame state
	 * @param integer 0 or 1 depending on whether the the player died or ran out of time
	 * 			defaults to 1
	 * 
	 */
	public EndgameGraphics(int state)
	{
		if (winOrLose == 0 || winOrLose ==1)
		{
			this.winOrLose = state;
		}
		else
		{
			this.winOrLose = 1;
			System.out.println("Defaulted to winning background");
		}
	}

	/**
	 * This method returns the image stored at the supplied pathname
	 * @param Pathname of image being retrieved
	 * @return Image
	 */
	public Image getImage(String imagePath){
		ImageIcon i = new ImageIcon(getClass().getResource(imagePath));
		return i.getImage();
	}

	class MenuActionListener extends JFrame implements ActionListener {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
				case SHOW_MENU:
					Game.setHiddenLevel(false);
					endFrame.dispose();
					new GenerateMenu();
					break;
				case HIDDEN_LEVEL:
					endFrame.dispose();
					hiddenLevel();
					break;
			}
		}
	}

	/**
	 * This method assigns strings with game statistic information
	 * then creates labels in a panel containing the strings.  The appearance of the labels
	 * and their layout management is specified.
	 */
	public void statsInfo() {
		String timeInfo = "Time: " + statsText.getTime();
		String scoreInfo = "Score: " + statsText.getScore();
		String livesInfo = "Lives: " + statsText.getLives();

		JLabel topBar = new JLabel();
		topBar.setIcon(new ImageIcon(getClass().getResource("/visuals/statsTopBar.png")));

		JLabel timeLabel = new JLabel(timeInfo, JLabel.CENTER);
		timeLabel.setFont(new Font("Futura",Font.ITALIC,50));
		timeLabel.setForeground(Color.decode("#946bcb"));

		JLabel scoreLabel = new JLabel(scoreInfo, JLabel.CENTER);
		scoreLabel.setFont(new Font("Futura",Font.ITALIC,50));
		scoreLabel.setForeground(Color.decode("#946bcb"));

		JLabel livesLabel = new JLabel(livesInfo, JLabel.CENTER);
		livesLabel.setFont(new Font("Futura",Font.ITALIC,50));
		livesLabel.setForeground(Color.decode("#946bcb"));

		statsPanel = new JPanel(new GridBagLayout());
		Dimension size = new Dimension(550, 400);
		statsPanel.setPreferredSize(size);
		statsPanel.setBorder(BorderFactory.createMatteBorder(
				10, 10, 10, 10, Color.decode("#adbedc")));
		statsPanel.setBackground(Color.white);

		GridBagConstraints grid = new GridBagConstraints();
		grid.fill = GridBagConstraints.VERTICAL;

		grid.weighty = 0;
		grid.weightx = 0.5;
		grid.gridy = 0;
		statsPanel.add(topBar, grid);
		grid.gridy = 1;
		statsPanel.add(timeLabel, grid);
		grid.gridy = 2;
		statsPanel.add(scoreLabel, grid);
		grid.gridy = 3;
		statsPanel.add(livesLabel, grid);

	}

	/**
	 * This method deletes the existing game map and creates the 'game-over' frame
	 * with options to return to the main menu.  Player statistics are printed in one of the
	 * frame's panels.
	 * 
	 * @see deleteMap()
	 * @see statsInfo()
	 */
	public void returnButton() {
		Level.deleteMap();
		setBackgroundImage();

		endPanel = new JPanel(new BorderLayout());
		Dimension panelSize = new Dimension(1350, 1000);
		endPanel.setPreferredSize(panelSize);


		JLabel contentImage = new JLabel();
		contentImage.setIcon(new ImageIcon(endGameImage));
		endPanel.add(contentImage, BorderLayout.CENTER);

		showMenu = new JButton(SHOW_MENU);
		showMenu.setIcon(new ImageIcon(getClass().getResource("/visuals/menuGraphics/mainmenu.png")));
		hiddenLevel = new JButton(HIDDEN_LEVEL);
		hiddenLevel.setIcon(new ImageIcon(getClass().getResource("/visuals/menuGraphics/hiddenlevel.png")));

		MenuActionListener listener = new MenuActionListener();
		showMenu.addActionListener(listener);
		hiddenLevel.addActionListener(listener);
		hiddenLevel.setActionCommand(HIDDEN_LEVEL);
		showMenu.setActionCommand(SHOW_MENU);

		hiddenLevel.setText(" ");
		showMenu.setText(" ");


		JPanel buttons = new JPanel(new BorderLayout());
		buttons.add(showMenu, BorderLayout.WEST);
		buttons.add(hiddenLevel, BorderLayout.EAST);

		statsInfo();
		endPanel.add(statsPanel, BorderLayout.EAST);

		endPanel.add(buttons, BorderLayout.SOUTH);
		endFrame = new JFrame("Mr. Penguin");

		endFrame.setContentPane(endPanel);
		endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		endFrame.pack();
		endFrame.setVisible(true);
	}

	/**
	 * creates and starts the hidden level
	 * @see Game.setHiddenLevel(boolean)
	 * @see Level.createLevel(boolean, boolean)
	 */
	public void hiddenLevel() {
		Level level = new Level();
		Game.setHiddenLevel(true);
		level.createLevel(false, false);
		System.out.println("Loading hidden level ...");
	}

	/**
	 * This method assigns an appropriate end-game image when it's called
	 * @see int winOrLose
	 */
	public void setBackgroundImage() {
		switch (winOrLose)
		{
			case 0:
				endGameImage = getImage(loseImagePath);
				backImage = new ImageIcon(loseImagePath);
				break;
			case 1:
				endGameImage = getImage(winGamePath);
				backImage = new ImageIcon(winGamePath);
				break;
			default:
				String error = "Error: Invalid image end game state.";
				new ErrorMessage(error);
				System.out.println(error);
		}
	}
	/**
	 * This method calls returnButton() and prints a debugging message to
	 * the terminal
	 * 
	 * @see returnButton()
	 */
	public void run()
	{
		System.out.println("HIDDEN STATE: " + Game.getHiddenLevel());
		returnButton();
	}
}