
/*
 *  Leser personopplysninger fra en SQLite-database, og
 *  skriver ut en navneliste sortert på etternavn.
 *
 *  Forutsetter at databasetabellen Person er opprettet
 *  i databasen person.db, se SQL-skript nederst i filen. * 
 */
import static javax.swing.JOptionPane.*;
import java.util.*;
import java.io.*;
import java.sql.*;

public class PersonRegister {
    private static String url = "jdbc:sqlite:person.db";
    private static Connection conn = null;

    public static void main(String[] args) {
        String utTxt = "";
        kobleOpp();

        sendMelding("DROP TABLE IF EXISTS Person");
        sendMelding("create table Person( "+
        "Nr integer primary key," +
        "Fornavn varchar(50)," +
        "Etternavn varchar(50)," +
        "Alder integer," +
        "Stilling varchar(50)" +
        ");");
        sendMelding("insert into Person values(1, 'Per', 'Olsen', 24, 'Selger');");

        String sql = "SELECT * FROM Person ORDER BY Etternavn, Fornavn";
        utTxt = hentPersonData(sql);
        showMessageDialog(null, utTxt);
        kobleNed();
    }

    // Henter personopplysninger fra databasen
    private static void sendMelding(String sql) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            showMessageDialog(null, "Melding til databasen feilet." + e.toString());
        }
    }

    // Henter personopplysninger fra databasen
    private static String hentPersonData(String sql) {
        String svar = "";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            String fornavn, etternavn, stilling;
            int alder;
            while (rs.next()) {
                fornavn = rs.getString("Fornavn");
                etternavn = rs.getString("Etternavn");
                alder = rs.getInt("Alder");
                stilling = rs.getString("Stilling");

                svar += etternavn + ", " + fornavn + " (" + alder + ") - " + stilling + "\n";
            }
        } catch (SQLException e) {
            showMessageDialog(null, "Spørring mot databasen feilet." + e.toString());
        }
        return svar;
    }

    // Kobler opp til databasen.
    private static void kobleOpp() {
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            showMessageDialog(null, "Oppkobling til databasen " + url + " feilet.\n" + e.toString());
        }
    }

    // Lukker forbindelsen til databasen.
    private static void kobleNed() {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            showMessageDialog(null, "Klarte ikke å lukke forbindelsen til databasen " + url + "\n" + e.toString());
        }
    }

}

/*
 * 
 * create table Person
 * (
 * Nr integer primary key,
 * Fornavn varchar(50),
 * Etternavn varchar(50),
 * Alder integer,
 * Stilling varchar(50)
 * );
 * 
 * insert into Person values(1, 'Per', 'Olsen', 24, 'Selger');
 * insert into Person values(2, 'Anne', 'Hansen', 31, 'Mellomleder');
 * insert into Person values(3, 'Jon Ola', 'Bakken', 41, 'Reparatør');
 * insert into Person values(4, 'Oda Lise', 'Li', 28, 'Saksbehandler');
 * 
 */