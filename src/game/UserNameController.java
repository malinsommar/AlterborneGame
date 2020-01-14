package game;

import davidtest.overworld.map.Functionality.InputHandler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Malin Sommar
 */
public class UserNameController {

    private UserNameView unw = new UserNameView();
    boolean done = false;
     TextField nameField;

    /**
     *
     */
    void startUserNameFrame() {
        unw.userNameScreen();
        hover();
        nameField = new TextField();
        nameField.setBounds(150,200,200,30);
        nameField.setFont(unw.pixelMplus.deriveFont(28f));
        unw.usernameJFrame.add(nameField);
        unw.continueButton.addActionListener(e -> exitUserNameFrame());
    }
    private void exitUserNameFrame() {
        unw.usernameJFrame.dispose();
        done = true;
    }


    /**
     *
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
