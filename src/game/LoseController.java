package game;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class LoseController {

    private LoseScreen loseScreen = new LoseScreen();

    private final String USERNAME = "Alterborne";
    private final String PASSWORD = "YEET";
    private final String CONNECTION = "jdbc:mysql://localhost:3306/alterborne";

    private String name;
    private int xp;

     void startLoseScreen(int getXp) {

        MusicPick.musicStart("gwyn","music");

        xp = getXp;
        loseScreen.loseScreen();
        hover();

            loseScreen.continueButton.addActionListener(e -> this.name = loseScreen.name.getText());
            loseScreen.continueButton.addActionListener(e -> System.out.println("hejehejhe" + name));
            loseScreen.continueButton.addActionListener(e -> loseScreen.loseFrame.dispose());
            loseScreen.continueButton.addActionListener(e -> dataBase());

    }

    private void dataBase(){
        try {
            Connection conn = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
            Statement stmt=conn.createStatement();

            stmt.executeUpdate("INSERT INTO abtable " +  "VALUES ('"+name+"', "+xp+")");

            conn.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void hover(){
        loseScreen.continueButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loseScreen.continueButton.setBackground(Color.gray);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                loseScreen.continueButton.setBackground(Color.darkGray);
            }
        });
    }
}