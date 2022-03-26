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
//   Main window
//    |- Administrer medlemmer
//    |   |- Legg til              -|
//    |   |- *Slett                 |- Disse 3 vil ha sine egne spesielle input dialoger
//    |   |- Endre telefonnummer   -|
//    |   |- Gå tilbake
//    |
//    |- Vis medlemmer
//    |   |- Fornavn          -|
//    |   |- Etternavn         |- Disse 3 vil åpne et nytt vindu med resultatet
//    |   |- Telefonnummer    -|
//    |   |- Gå tilbake
//    |
//    |- Ta backup        -|
//    |- *Hent backup     -|- Bekreftelse på at oppgaven er utført
//    |- Avslutt


public class Medlemsregister extends Application { // Extends javafx application window
    Stage mainWindow; // Contains first set of buttons

    Stage adminWindow; // Used to add/remove members and change phone numbers
    Stage addMemberWindow; // Used to add a new member

    TextField firstnameInput;
    TextField lastnameInput;
    TextField addressInput;
    TextField phoneNumberInput;

    Stage removeMemberWindow; // Used to remove a selected member
    TextField memberNumberInput;
    Stage modifyNumberWindow; // Used to modify the phone number of a member
    TextField memberNumberInput2;
    TextField phoneNumberInput2;

    Stage viewMembersWindow; // Used to select how to sort members before displaying
    Stage sqlResultWindow; // Result of member sorting
    ListView resultList;

    Stage activeWindow; // Currently visible window

    private static final double BUTTON_HEIGHT = 30;
    private static final double ELEMENT_WIDTH = 280;
    private static final String DB_NAME = "medlemmer.db";

    public void start(Stage window) { // Using this to set up all windows, and display the main window.
        mainWindow = window;
        adminWindow = new Stage();
        viewMembersWindow = new Stage();
        sqlResultWindow = new Stage();

        // Main window
        Button[] mainButtons = new Button[5];
        mainButtons[0] = makeMenuButton("Administrer medlemmer", ELEMENT_WIDTH, e -> openWindow(adminWindow)); // To admin window
        mainButtons[1] = makeMenuButton("Vis medlemmer", ELEMENT_WIDTH, e -> openWindow(viewMembersWindow));   // To overview window
        mainButtons[2] = makeMenuButton("Ta backup", ELEMENT_WIDTH, e -> writeBackup());                       // Make backup
        mainButtons[3] = makeMenuButton("Hent backup", ELEMENT_WIDTH, e -> readBackup());                      // Use backup file
        mainButtons[4] = makeMenuButton("Avslutt", ELEMENT_WIDTH, e -> exit());                                // Close program
        makeButtonList(mainWindow, "Medlemsregister", mainButtons);                                            // Compose window

        // Administration window
        Button[] adminButtons = new Button[4];
        adminButtons[0] = makeMenuButton("Legg til medlem", ELEMENT_WIDTH, e -> openWindow(addMemberWindow));        // To add member window
        adminButtons[1] = makeMenuButton("Slett medlem", ELEMENT_WIDTH, e -> openWindow(removeMemberWindow));        // To remove member window
        adminButtons[2] = makeMenuButton("Endre telefonnummer", ELEMENT_WIDTH, e -> openWindow(modifyNumberWindow)); // To modify phone number window                    
        adminButtons[3] = makeMenuButton("Gå tilbake", ELEMENT_WIDTH, e -> openWindow(mainWindow));                  // Back to main window
        makeButtonList(adminWindow, "Administrer medlemmer", adminButtons);                                          // Compose window

        // View members window
        Button[] sortButtons = new Button[4];
        sortButtons[0] = makeMenuButton("Sorter etter fornavn", ELEMENT_WIDTH, e -> resultWindow("Fornavn"));      // To admin window
        sortButtons[1] = makeMenuButton("Sorter etter etternavn", ELEMENT_WIDTH, e -> resultWindow("Etternavn"));  // To overview window
        sortButtons[2] = makeMenuButton("Sorter etter telefonnummer", ELEMENT_WIDTH, e -> resultWindow("Tlf"));  // Make backup              
        sortButtons[3] = makeMenuButton("Gå tilbake", ELEMENT_WIDTH, e -> openWindow(mainWindow));               // Back to main window
        makeButtonList(viewMembersWindow, "Medlemsregister", sortButtons);                                       // Compose window

        // Add member window
        addMemberWindow = new Stage();
        addMemberWindow.setTitle("Legg til");
        addMemberWindow.setResizable(false);
        GridPane addMemberGrid = new GridPane();
        addMemberGrid.setVgap(10); 
        addMemberGrid.setHgap(10);  
        addMemberGrid.setPadding(new Insets(10, 10, 10, 10)); 

        Label firstnameLabel = new Label("Fornavn:");
        addMemberGrid.add(firstnameLabel, 0, 0);

        firstnameInput = new TextField();
        addMemberGrid.add(firstnameInput, 1, 0);

        Label lastnameLabel = new Label("Etternavn:");
        addMemberGrid.add(lastnameLabel, 0, 1);

        lastnameInput = new TextField();
        addMemberGrid.add(lastnameInput, 1, 1);

        Label addressLabel = new Label("Adresse:");
        addMemberGrid.add(addressLabel, 0, 2);

        addressInput = new TextField();
        addMemberGrid.add(addressInput, 1, 2);

        Label phoneNumberLabel = new Label("Tlf:");
        addMemberGrid.add(phoneNumberLabel, 0, 3);

        phoneNumberInput = new TextField();
        addMemberGrid.add(phoneNumberInput, 1, 3);

        Button addMemberButton = makeMenuButton("Legg til", 220, e -> addMember()); 
        addMemberGrid.add(addMemberButton, 0, 4, 2, 1);

        Scene addMemberScene = new Scene(addMemberGrid, 240, 190);
        addMemberWindow.setScene(addMemberScene);

        

        // Remove member window
        removeMemberWindow = new Stage();
        removeMemberWindow.setTitle("Legg til");
        removeMemberWindow.setResizable(false);
        GridPane removeMemberGrid = new GridPane();
        removeMemberGrid.setVgap(10); 
        removeMemberGrid.setHgap(10);  
        removeMemberGrid.setPadding(new Insets(10, 10, 10, 10)); 

        Label memberNumberLabel = new Label("Medlemsnummer");
        removeMemberGrid.add(memberNumberLabel, 0, 0);

        memberNumberInput = new TextField();
        removeMemberGrid.add(memberNumberInput, 1, 0);

        Button removeMemberButton = makeMenuButton("Ta bort", 220, e -> removeMember()); 
        removeMemberGrid.add(removeMemberButton, 0, 1, 2, 1);

        Scene removeMemberScene = new Scene(removeMemberGrid, 240, 80);
        removeMemberWindow.setScene(removeMemberScene);


        // Modify phone number window
        modifyNumberWindow = new Stage();
        modifyNumberWindow.setTitle("Legg til");
        modifyNumberWindow.setResizable(false);
        GridPane modifyNumberGrid = new GridPane();
        modifyNumberGrid.setVgap(10); 
        modifyNumberGrid.setHgap(10);  
        modifyNumberGrid.setPadding(new Insets(10, 10, 10, 10)); 

        Label memberNumberLabel2 = new Label("Medlemsnummer");
        modifyNumberGrid.add(memberNumberLabel2, 0, 0);

        memberNumberInput2 = new TextField();
        modifyNumberGrid.add(memberNumberInput2, 1, 0);

        Label memberPhoneNumberLabel = new Label("Telefonnummer");
        modifyNumberGrid.add(memberPhoneNumberLabel, 0, 1);

        phoneNumberInput2 = new TextField();
        modifyNumberGrid.add(phoneNumberInput2, 1, 1);

        Button modifyNumberButton = makeMenuButton("Endre tlf", 220, e -> modifyNumber()); 
        modifyNumberGrid.add(modifyNumberButton, 0, 2, 2, 1);

        Scene modifyNumberScene = new Scene(modifyNumberGrid, 240, 120);
        modifyNumberWindow.setScene(modifyNumberScene);



        // Sort members window
        sqlResultWindow.setResizable(false);
        
        GridPane grid = new GridPane();
        grid.setVgap(10); 
        grid.setHgap(10);  
        grid.setPadding(new Insets(10, 10, 10, 10)); 
        resultList = new ListView();
        resultList.setPrefWidth(ELEMENT_WIDTH);
        resultList.setMaxWidth(ELEMENT_WIDTH);
        resultList.setMinWidth(ELEMENT_WIDTH);
        resultList.setPrefHeight(ELEMENT_WIDTH * 2);
        resultList.setMaxHeight(ELEMENT_WIDTH * 2);
        resultList.setMinHeight(ELEMENT_WIDTH * 2);
        grid.add(resultList, 0, 0);
        grid.add(makeMenuButton("Ferdig", ELEMENT_WIDTH, e -> openWindow(mainWindow)), 0, 1);

        Scene scene = new Scene(grid, ELEMENT_WIDTH + 20, 10 + BUTTON_HEIGHT + 10 + ELEMENT_WIDTH * 2 + 10);
        sqlResultWindow.setScene(scene);

        mainWindow.show();
        activeWindow = mainWindow;
    }
    
    public static void main(String[] args) {
        // Check if database exists, and create if it doesn't.
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

        Scene scene = new Scene(grid, ELEMENT_WIDTH + 20, options.length * (BUTTON_HEIGHT + 10) + 10);
        stage.setScene(scene);
    }

    private void addMember(){
        Medlem newMember = new Medlem();
        newMember.setFornavn(firstnameInput.getText());
        newMember.setEtternavn(lastnameInput.getText());
        newMember.setAdresse(addressInput.getText());
        try{
            newMember.setTlf(Integer.parseInt(phoneNumberInput.getText()));
        }catch(Exception e){
            out.println("Failed to read phone number: " + e.toString());
        }

        newMember.setMNr(getLastMember() + 1);

        if(newMember.isValid()){
            insertMember(newMember);
            showMessageDialog(null, "Medlem lagt til!");
            openWindow(mainWindow);
        }else{
            showMessageDialog(null, "Vennligst fyll mer informasjon.");
        }
    }

    private void removeMember(){
        int memberNumber = -1;

        try{
            memberNumber = Integer.parseInt(memberNumberInput.getText());
        }catch(NumberFormatException e){
            out.println("Failed to read member number");
        }

        if(memberExists(memberNumber)){
            editDB("DELETE FROM Medlem WHERE MNr = " + memberNumber);
            showMessageDialog(null, "Medlem nr " + memberNumber + " er nå tatt bort.");
            openWindow(mainWindow);
        }else{
            showMessageDialog(null, "Finner ikke medlem nr " + memberNumber);
        }
    }

    private void modifyNumber(){
        int memberNumber = -1;
        int phoneNumber = -1;

        try{
            memberNumber = Integer.parseInt(memberNumberInput2.getText());
            phoneNumber = Integer.parseInt(phoneNumberInput2.getText());
        }catch(NumberFormatException e){
            out.println("Failed to read number");
        }

        if(memberExists(memberNumber) & phoneNumber > 0){
            editDB("UPDATE Medlem SET Tlf = " + phoneNumber + " WHERE MNr = " + memberNumber + ";");
            showMessageDialog(null, "Telefonnummer endret.");
            openWindow(mainWindow);
        }else{
            showMessageDialog(null, "Velligst fyll inn på nytt.");
        }
    }

    private boolean memberExists(int memberNumber){
        Connection conn = null;
        String message = "";
        boolean memberExists = false;

        try{
            conn = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);
            Statement stmt = conn.createStatement();

            message = "SELECT MNr FROM Medlem WHERE MNr = " + memberNumber + ";";
            ResultSet rs = stmt.executeQuery(message);
            memberExists = rs.next();
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

        return memberExists;
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
            showMessageDialog(null, "Backup er nå hentet ut. Database oppdatert.");
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

    private int getLastMember(){
        Connection conn = null;
        String message = "";
        int result = 0;

        try{
            conn = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);
            Statement stmt = conn.createStatement();

            message = "SELECT MNr FROM Medlem ORDER BY MNr DESC;";
            ResultSet rs = stmt.executeQuery(message);
            rs.next();
            result = rs.getInt("MNr");
            
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

        return result;
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
                showMessageDialog(null, "Backup er nå hentet ut. Database oppdatert.");
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
        String message = "SELECT * FROM Medlem ORDER BY " + sortBy + " ASC;";

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
                
                if(sortBy != "Tlf" | member.getTlf() > 0){
                    members[i] = member.toString();
                    i++;
                }
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

    private void resultWindow(String sortBy){
        sqlResultWindow.setTitle("Sortert: " + sortBy);
        openWindow(sqlResultWindow);

        for(String line : viewSortBy(sortBy)){
            if(line != null){
                resultList.getItems().add(line);
            }
        }
    }

    private void exit(){
        mainWindow.close();
    }
}