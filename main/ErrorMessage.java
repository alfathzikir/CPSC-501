package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class displays error notifications in the case of an exception in the code.
 * 
 * Created by gabrielawcislo on 2017-04-11.
 */
    public class ErrorMessage extends JFrame {
        private static final long serialVersionUID = 1L;

        private final String SHOW_MENU = "Show Menu";

        private String errorInfo;

        private JFrame errorFrame;
        private JPanel errorPanel;
        private JButton showMenu;

		/**
		 * This class returns the user to the main menu when the button is clicked.
		 * 
		 */
        class MenuActionListener extends JFrame implements ActionListener {
            private static final long serialVersionUID = 1L;

            public void actionPerformed(ActionEvent e) {
                switch (e.getActionCommand()) {
                    case SHOW_MENU:
                        Game.setHiddenLevel(false);
                        errorFrame.dispose();
                        new GenerateMenu();
                        break;
                }
            }
        }

		/**
		 * @param error (The type of error to notify the user of)
		 */
        public ErrorMessage(String error) {
            errorInfo = error;
            setupErrorNotification();
        }

		/**
		 * This method displays the appropriate error image and text.
		 */
        public void setupErrorNotification() {
            errorFrame = new JFrame();

			//Create the panel to place the images/text/buttons in.
            errorPanel = new JPanel(new BorderLayout());
            Dimension panelSize = new Dimension(400, 450);
            errorPanel.setPreferredSize(panelSize);

			//Set the background to the error occurred image.
            JLabel errorImage = new JLabel();
            errorImage.setIcon(new ImageIcon(getClass().getResource("/visuals/errorImages/error.png")));

			//Create the text that displays the type of error.
            JLabel errorText = new JLabel(errorInfo);
            errorText.setFont(new Font("Times new roman",1,30));
            errorText.setForeground(Color.black);

			//Display the return to menu button.
            showMenu = new JButton(SHOW_MENU);
            showMenu.setIcon(new ImageIcon(getClass().getResource("/visuals/errorImages/mainmenu.png")));

			//Create a new listener to give the button an effect.
            MenuActionListener listener = new MenuActionListener();
            showMenu.addActionListener(listener);
            showMenu.setActionCommand(SHOW_MENU);
            showMenu.setText(" ");

			//Add the error description, image, and return button.
            errorPanel.add(errorImage, BorderLayout.CENTER);
            errorPanel.add(errorText, BorderLayout.NORTH);
            errorPanel.add(showMenu, BorderLayout.SOUTH);

			//Create the JFrame in which the error is displayed.
            errorFrame.setContentPane(errorPanel);
            errorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			errorFrame.setLocationRelativeTo(null);
            errorFrame.pack();
            errorFrame.setVisible(true);
        }
    }

