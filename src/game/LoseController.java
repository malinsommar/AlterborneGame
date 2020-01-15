package game;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @author lydia marcondes, Malin Sommar
 */
public class LoseController {

    private LoseView loseView = new LoseView();

    private final String USERNAME = "Alterborne";
    private final String PASSWORD = "YEET";
    private final String CONNECTION = "jdbc:mysql://localhost:3306/alterborne";

    private String name;
    private int xp;

    /**
     *
     * @param getXp
     * @param getName
     */
     void startLoseScreen(int getXp, String getName) {

        MusicPick.musicStart("gwyn","music");

         name = getName;
         xp = getXp;
         dataBase();
         loseView.loseScreen();
         hover();

        loseView.continueButton.addActionListener(e-> System.exit(0));
    }

    /**
     *
     */
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

    /**
     *
     */
    private void hover(){
        loseView.continueButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loseView.continueButton.setBackground(Color.gray);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                loseView.continueButton.setBackground(Color.darkGray);
            }
        });
    }
}