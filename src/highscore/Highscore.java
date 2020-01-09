package highscore;

import OldClasses.LevelUp;
import game.LevelUpController;
import game.LoseScreen;

import java.sql.*;

public class Highscore {
    private static final String USERNAME = "Alterborne";
    private static final String PASSWORD = "YEET";
    private static final String CONNECTION = "jdbc:mysql://localhost:3306/alterborne";
    private static LevelUpController lvl = new LevelUpController();
    private static LoseScreen lose = new LoseScreen();
    static String name = lose.userName; //new Scanner(System.in).next();
    static int xp = lvl.xp;

    public static void main(String[] args) {
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
            Statement stmt=conn.createStatement();

            stmt.executeUpdate("INSERT INTO abtable " +  "VALUES ('"+name+"', "+xp+")");

            conn.close();

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}