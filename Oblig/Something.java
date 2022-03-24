/*
  Obligatorisk innleveringsoppgave

  Christoffer Aske, Mars 2022
*/

// Standard imports
import static java.lang.System.*;
import static javax.swing.JOptionPane.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Math.*;
import java.util.*;
import java.sql.*;
import java.io.*;

// JavaFX imports
import javafx.application.Application;

import javafx.stage.Stage;
import javafx.scene.Scene; 
import javafx.scene.Node; 

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.geometry.Insets; 

import javafx.stage.Stage;

// Stjerne (*) betyr at knappen krever verifisering
// Elementer uten sub-elementer er funksjonelle

// Full oversikt over vinduer:
// + Main window
// +  |- Administrer medlemmer
//    |   |- Legg til              -|
//    |   |- *Slett                 |- Disse 3 vil ha sine egne spesielle input dialoger
//    |   |- Endre telefonnummer   -|
// +  |   |- Gå tilbake
//    |
// +  |- Vis medlemmer
// +  |   |- Fornavn          -|
// +  |   |- Etternavn         |- Disse 3 vil åpne et nytt vindu med resultatet
// +  |   |- Telefonnummer    -|
// +  |   |- Gå tilbake
//    |
// +  |- Ta backup        -|
// +  |- *Hent backup     -|- Bekreftelse på at oppgaven er utført
// +  |- Avslutt


public class Something extends Application { // Extends javafx application window
    Stage mainWindow; // Contains first set of buttons
    Stage confirmationWindow; // Confirm yes/no if the user wants to continue

    Stage adminWindow; // Used to add/remove members and change phone numbers
    Stage addMemberWindow; // Used to add a new member
    Stage removeMemberWindow; // Used to remove a selected member
    Stage modifyNumberWindow; // Used to modify the phone number of a member

    Stage viewMembersWindow; // Used to select how to sort members before displaying
    Stage sqlResultWindow; // Result of member sorting

    Stage activeWindow; // Currently visible window

    private static final double BUTTON_HEIGHT = 30;
    private static final String DB_NAME = "medlemmer.db";

    public void start(Stage window) { // Using this to set up all windows, and display the main window.
        // Check if database exists, and create if it doesn't. Read from file if db doesn't exist, but leave empty if file doesn't exist



        mainWindow = window;
        confirmationWindow = new Stage();
        adminWindow = new Stage();
        viewMembersWindow = new Stage();
        sqlResultWindow = new Stage();

        // Main window
        Button[] mainButtons = new Button[5];
        mainButtons[0] = makeMenuButton("Administrer medlemmer", 280, e -> openWindow(adminWindow)); // To admin window
        mainButtons[1] = makeMenuButton("Vis medlemmer", 280, e -> openWindow(viewMembersWindow));   // To overview window
        mainButtons[2] = makeMenuButton("Ta backup", 280, e -> writeBackup());                       // Make backup
        mainButtons[3] = makeMenuButton("Hent backup", 280, e -> readBackup());                      // Use backup file
        mainButtons[4] = makeMenuButton("Avslutt", 280, e -> exit());                                // Close program
        makeButtonList(mainWindow, "Medlemsregister", mainButtons);                                  // Compose window

        // Administration window
        Button[] adminButtons = new Button[4];
        adminButtons[0] = makeMenuButton("Legg til medlem", 280, e -> openWindow(addMemberWindow));        // To add member window
        adminButtons[1] = makeMenuButton("Slett medlem", 280, e -> openWindow(removeMemberWindow));        // To remove member window
        adminButtons[2] = makeMenuButton("Endre telefonnummer", 280, e -> openWindow(modifyNumberWindow)); // To modify phone number window                    
        adminButtons[3] = makeMenuButton("Gå tilbake", 280, e -> openWindow(mainWindow));                  // Back to main window
        makeButtonList(adminWindow, "Administrer medlemmer", adminButtons);                                // Compose window

        // View members window
        Button[] sortButtons = new Button[4];
        sortButtons[0] = makeMenuButton("Sorter etter fornavn", 280, e -> viewSortBy("Fornavn"));      // To admin window
        sortButtons[1] = makeMenuButton("Sorter etter etternavn", 280, e -> viewSortBy("Etternavn"));  // To overview window
        sortButtons[2] = makeMenuButton("Sorter etter telefonnummer", 280, e -> viewSortBy("TlfNr"));  // Make backup              
        sortButtons[3] = makeMenuButton("Gå tilbake", 280, e -> openWindow(mainWindow));               // Back to main window
        makeButtonList(viewMembersWindow, "Medlemsregister", sortButtons);                             // Compose window



        mainWindow.show();
        activeWindow = mainWindow;
    }
    
    public static void main(String[] args) {
        File t = new File(DB_NAME);
        if(!t.exists()){
            out.println("Database doesn't exist, attempting to create...");
            makeDB();
            if(t.exists()){
                out.println("Successfully created database!");
            }
        }
        launch(args);
    }


    private void openWindow(Stage window){
        if(activeWindow != null){
            activeWindow.hide();
        }
        activeWindow = window;
        clearWindow(activeWindow);
        activeWindow.show();
    }

    private void clearWindow(Stage window){
        // Clear all text fields and lists
        for(Node n : window.getScene().getRoot().getChildrenUnmodifiable()){
            // Stupid that switch can't be used for this
            if(n.getClass() == TextField.class){
                ((TextField)n).setText("");
            }
            else if(n.getClass() == ListView.class){
                ((ListView)n).getItems().clear();
            }
        }
    }

    private static Button makeMenuButton(String title, double width, EventHandler handler){
        Button btn = new Button(title);

        btn.setMinWidth(width);
        btn.setPrefWidth(width);
        btn.setMaxWidth(width);

        btn.setMinHeight(BUTTON_HEIGHT);
        btn.setPrefHeight(BUTTON_HEIGHT);
        btn.setMaxHeight(BUTTON_HEIGHT);

        btn.setOnAction(handler);

        return btn;
    }

    private static void makeButtonList(Stage stage, String title, Button[] options){
        stage.setTitle(title);
        stage.setResizable(false);

        GridPane grid = new GridPane();
        grid.setVgap(10); 
        grid.setHgap(10);  
        grid.setPadding(new Insets(10, 10, 10, 10)); 

        for(int i = 0; i < options.length; i++){
            grid.add(options[i], 0, i);
        }

        Scene scene = new Scene(grid, 300, options.length * (BUTTON_HEIGHT + 10) + 10);
        stage.setScene(scene);
    }

    private void writeBackup(){
        String[] newBackup = viewSortBy("MNr");
        File backupFile = new File("register.txt");
        FileWriter writer = null;

        boolean firstLineDone = false;
        try{
            writer = new FileWriter(backupFile, false);
            for(String line : newBackup){
                if(firstLineDone){
                    writer.write("\n");
                }
                writer.write(line);

                firstLineDone = true;
            }
            out.println("Successfully wrote backup file.");
        }catch(Exception e){
            out.println("Failed to write backup: " + e.toString());
        }finally{
            if(writer != null){
                try{
                    writer.close();
                }catch(Exception e){
                    out.println("Failed to close writer: " + e.toString());
                }
            }
        }
    }

    private void readBackup(){
        if(confirm("Er du sikker på at du vil overskrive databasen med ny backup?")){
            Scanner reader = null;
            try{
                reader = new Scanner(new File("register.txt"));
                makeDB();
                while(reader.hasNextLine()){
                    insertMember(new Medlem(reader.nextLine()));
                }


                out.println("Successfully used backup.");
            }catch(IOException e){
                out.println("Failed to read backup file: " + e.toString());
            }catch(Exception e){
                out.println("Failed to use backup: " + e.toString());
            }finally{
                if(reader != null){
                    try{
                        reader.close();
                    }catch(Exception e){
                        out.println("Failed to close backup file: " + e.toString());
                    }
                }
            }
        }
    }

    private static boolean confirm(String text){
        int result = showConfirmDialog(null, text);
        return result == YES_OPTION;
    }

    private static void makeDB(){
        editDB("DROP TABLE IF EXISTS Medlem;");
        editDB("CREATE TABLE Medlem(MNr integer primary key, Fornavn varchar(50), Etternavn varchar(50), Adresse varchar(50), Tlf integer);");
    }

    private static void editDB(String message){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(message);
        }catch(Exception e){
            out.println("Error with connection to db");
            if(message != ""){
                out.println("Message: " + message);
            }
            out.println(e.toString());
        }finally{
            if(conn != null){
                try{
                    conn.close();
                }catch(Exception e){
                    out.println("Failed to close connection: " + e.toString());
                }
            }
        }
    }

    private static void editDB(String[] messages){
        for(String message : messages){
            editDB(message);
        }
    }

    private static void insertMember(Medlem member){
        String valueBuilder = "";
        valueBuilder += member.getMNr() + ",";
        valueBuilder += "'" + member.getFornavn() + "',";
        valueBuilder += "'" + member.getEtternavn() + "',";
        valueBuilder += "'" + member.getAdresse() + "',";
        valueBuilder += member.getTlf();
        editDB("INSERT INTO Medlem values(" + valueBuilder + ");");
    }

    private String[] viewSortBy(String sortBy){
        String[] members = new String[0];
        String message = "SELECT * FROM Medlem ORDER BY " + sortBy + " ASC";

        Connection conn = null;
        try{
            conn = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);
            Statement stmt = conn.createStatement();

            // Count results
            ResultSet rs = stmt.executeQuery(message);
            int counter = 0;
            while(rs.next()){
                counter++;
            }
            members = new String[counter];
            
            // Re-do query to get back to the top
            rs = stmt.executeQuery(message);
            
            int i = 0;
            while(rs.next()){
                Medlem member = new Medlem();
                member.setMNr(rs.getInt("MNr"));
                member.setFornavn(rs.getString("Fornavn"));
                member.setEtternavn(rs.getString("Etternavn"));
                member.setAdresse(rs.getString("Adresse"));
                member.setTlf(rs.getInt("Tlf"));

                members[i] = member.toString();
                i++;
            }

        }catch(Exception e){
            out.println("Error with connection to db");
            if(message != ""){
                out.println("Message: " + message);
            }
            out.println(e.toString());
        }finally{
            if(conn != null){
                try{
                    conn.close();
                }catch(Exception e){
                    out.println("Failed to close connection: " + e.toString());
                }
            }
        }
        return members;
    }

    private void exit(){
        mainWindow.close();
    }
}