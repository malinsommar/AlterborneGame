package game;

import java.awt.*;

/**
 * This method contains all methods needed to start UserNameFrame and get username from player.
 *
 * @author Malin Sommar
 * @version 1
 */
public class UserNameController {

    private UserNameView unw = new UserNameView();
    boolean done = false;
     TextField nameField;

    /**
     *Starts the usernameView and sets actionListeners.
     */
    void startUserNameFrame() {
        unw.userNameScreen();
        hover();
        nameField = new TextField();
        nameField.setBounds(150,200,200,30);
        nameField.setFont(unw.pixelMplus.deriveFont(28f));
        unw.usernameJFrame.add(nameField);
        unw.continueButton.addActionListener(e -> done = true);
        unw.continueButton.addActionListener(e -> unw.usernameJFrame.dispose());
    }


    /**
     * Adds mouseListeners to buttons
     */
    private void hover(){
        unw.continueButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                unw.continueButton.setBackground(Color.darkGray);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) { }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                unw.continueButton.setBackground(Color.gray);
            }
        });
    }

}
