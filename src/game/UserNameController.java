package game;

import java.awt.*;

public class UserNameController {

    UserNameView unw = new UserNameView();

    boolean done = false;
     public static TextField nameField;

    public void startUserNameFrame() {
        unw.userNameScreen();
        hover();
        nameField = new TextField();
        nameField.setBounds(150,200,200,30);
        nameField.setFont(unw.pixelMplus.deriveFont(28f));
        unw.usernameJFrame.add(nameField);

        unw.continueButton.addActionListener(e -> unw.usernameJFrame.dispose());
        unw.continueButton.addActionListener(e -> done = true);
    }

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
