import static java.lang.System.*;
import static javax.swing.JOptionPane.*;
import java.sql.*;
 
 
/**  TEST AV DATABASE
  *  Programmet demonstrerer oppkobling til en SQLite-database.
  *  Databasefilen testbase.db vil bli opprettet, hvis den ikke finnes.
  *  
  */
public class DBEksempel {
 
  public static void main(String[] args) { 
    String url = "jdbc:sqlite:testbase.db";
 
    Connection conn = null;
 
    try { 
      conn = DriverManager.getConnection(url);
 
      if (conn != null) {
        showMessageDialog(null,"Oppkobling til databasen " + url + " var vellykket. ");
      }
 
    }  
    catch (SQLException e) {
      showMessageDialog(null,"Oppkobling til databasen feilet! \n Se detaljer i konsollet");
      out.println("Oppkobling til databasen " + url + " feilet.\n" + e.toString());
    }
    finally {
      try {
        conn.close();
        showMessageDialog(null,"Forbindelsen til databasen er lukket igjen." + "\n"
                             + "Se filen testbase.db  i mappen merket db"); 
      }
      catch (SQLException e) {
        showMessageDialog(null,"Klarte ikke å lukke forbindelsen til databasen! \n Se detaljer i konsollet");
        out.println("Klarte ikke å lukke forbindelsen til databasen " + url + "\n" + e.toString());
      }
    }
  }
 
}