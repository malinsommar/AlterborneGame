package game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * This method contains all methods needed to start LoseFrame and sends data to database.
 *
 * @author lydia marcondes, Malin Sommar
 * @version 1
 */
public class DataBase {


    /**
     * Send info about xp and username to dataBase.
     *
     * @param getXp Final xp
     * @param getName UserName
     */
     void sendToDataBase(int getXp, String getName) {

         try {
             String USERNAME = "Alterborne";
             String PASSWORD = "YEET";
             String CONNECTION = "jdbc:mysql://localhost:3306/alterborne";
             Connection conn = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
            Statement stmt=conn.createStatement();

            stmt.executeUpdate("INSERT INTO abtable " +  "VALUES ('"+ getName +"', "+ getXp +")");

            conn.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}