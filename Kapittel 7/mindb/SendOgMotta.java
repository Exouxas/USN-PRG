import static java.lang.System.*;
import static javax.swing.JOptionPane.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Math.*;
import java.util.*;
import java.sql.*;

public class SendOgMotta {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:mindb.db";
        String melding = "";
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();

            melding = "drop table if exists Vare";
            stmt.executeUpdate(melding);


            melding = "create table Vare(Nr integer primary key, Name vachar(50));";
            stmt.executeUpdate(melding);

            melding = "insert into Vare values(1, 'Spade');"
                    + "insert into Vare values(2, 'Hakke');"
                    + "insert into Vare values(3, 'Spett');";
            stmt.executeUpdate(melding);

            melding = "select Name from Vare where Nr > 1;";
            ResultSet result = stmt.executeQuery(melding);

            while(result.next()){
                out.println(result.getString("Name"));
            }
        }catch(Exception e){
            out.println("Failed: " + e.toString());
            out.println("Melding: " + melding);
        }
        finally{
            try{
                if(conn != null){
                    conn.close();
                }
            }catch(Exception e){
                out.println("Failed to close db");
            }
        }
        
    }
}