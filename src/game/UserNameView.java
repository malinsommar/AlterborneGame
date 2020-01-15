package game;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * UsernameView contains all swing variables to start UserNameView.
 *
 * @author Malin Sommar
 * @version 1
 */
public class UserNameView {

    JFrame usernameJFrame = new JFrame();
    JButton continueButton;
    Font pixelMplus;
    JLabel enterLabel;

    /**
     * Starts the username JFrame and calls private methods needed for the frame.
     */
    public void userNameScreen(){

        usernameJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        usernameJFrame.setLayout(null);
        usernameJFrame.setSize(500, 400);
        usernameJFrame.setLocation(400,138);

        importFont();
        addButtonsLabels();

        usernameJFrame.add(continueButton);
        usernameJFrame.add(enterLabel);

        continueButton.setVisible(true);

        usernameJFrame.setUndecorated(true);
        usernameJFrame.setVisible(true);
    }

    /**
     * Set info for all JLabels and buttons
     */
    public void addButtonsLabels(){

        enterLabel = new JLabel("What's your name?");
        enterLabel.setForeground(Color.black);
        enterLabel.setFont((pixelMplus.deriveFont(30f)));
        Dimension size = enterLabel.getPreferredSize();
        enterLabel.setBounds(130, 30, size.width, size.height);

        continueButton = new JButton("Onwards!");
        continueButton.setSize(300, 100);
        continueButton.setLocation(100, 280);
        continueButton.setForeground(Color.white);
        continueButton.setFont(pixelMplus.deriveFont(30f));
        continueButton.setBackground(Color.gray);
        continueButton.setBorder(null);
        continueButton.setFocusPainted(false);
    }

    /**
     * Import font pixelMplus
     */
    private void importFont() {
        try {
            pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("PixelMplus10-Regular.ttf"));
        } catch (IOException | FontFormatException ignored) {
        }
    }
}
