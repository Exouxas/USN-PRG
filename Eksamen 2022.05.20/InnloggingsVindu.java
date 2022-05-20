import static java.lang.System.*;
import static javax.swing.JOptionPane.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Math.*;
import java.util.*;
import java.io.*;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene; 

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;

import javafx.stage.Stage;

public class InnloggingsVindu extends Application {
    private Label etikettInfo = new Label("Skriv inn ditt brukernavn og passord!");

    private TextField brukernavn = new TextField();
    private TextField passord = new TextField();

    private Button knappLoggInn = new Button("Logg inn");
    private Button knappGlemt = new Button("Glemt passord");

    private final String GLEMT_PASSORD = "Du har trykket på \"Glemt passord\"";
    private final String RIKTIG_PASSORD = "Innloggingen er godkjent og du sendes videre.";
    private final String FEIL_PASSORD = "Du har ikke gitt korrekte innloggingsdata!";

    private final String FILNAVN = "login.txt";

    public void start(Stage window) {
        FlowPane root = new FlowPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);
        
        root.getChildren().add(etikettInfo);

        Label brukernavnLabel = new Label("Brukernavn:");
        brukernavnLabel.setPrefWidth(70);
        root.getChildren().add(brukernavnLabel);
        brukernavn.setPrefColumnCount(12);
        root.getChildren().add(brukernavn);

        Label passordLabel = new Label("Passord:");
        passordLabel.setPrefWidth(70);
        root.getChildren().add(passordLabel);
        passord.setPrefColumnCount(12);
        root.getChildren().add(passord);
        
        knappLoggInn.setOnAction(e -> behandleKlikk(e));
        root.getChildren().add(knappLoggInn);

        knappGlemt.setOnAction(e -> behandleKlikk(e));
        root.getChildren().add(knappGlemt);
        
        Scene scene = new Scene(root, 250, 150);
        
        window.setTitle("Innlogging");
        window.setScene(scene);
        window.setResizable(false);
        window.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }

    public void behandleKlikk(ActionEvent e) {
        if (e.getSource() == knappLoggInn)
            utførLoggInn();
        else if (e.getSource() == knappGlemt) {
            etikettInfo.setText(GLEMT_PASSORD);
            endreRegistrering();
        }
    }

    public String kode(String input){
        return ""; // Dummy metode
    }

    public void utførLoggInn(){
        File fil = new File(FILNAVN);
        Scanner reader = null;
        boolean innloggingVellykket = false;

        try{
            reader = new Scanner(fil);
            while(reader.hasNextLine()){
                String[] linjedel = reader.nextLine().split(";");
                if(linjedel[0].equals(brukernavn.getText())){
                    if(linjedel[1].equals(kode(passord.getText()))){
                        innloggingVellykket = true;
                        break;
                    }
                }
            }
            // Trenger ikke lukke siden dette skjer i "finally".
        }catch(FileNotFoundException e){
            out.println("Finner ikke filen '" + FILNAVN + "', feilmelding:" + e.toString());
        }catch(IOException e){
            out.println("Feil med å åpne filen '" + FILNAVN + "', feilmelding:" + e.toString());
        }catch(Exception e){
            out.println("Generell feil oppstått med filen '" + FILNAVN + "', feilmelding:" + e.toString());
        }finally{
            if(reader != null){
                try{
                    reader.close();
                }catch(Exception e){
                    out.println("Kunne ikke lukke filen '" + FILNAVN + "', feilmelding:" + e.toString());
                }
            }
        }

        if(innloggingVellykket){
            etikettInfo.setText(RIKTIG_PASSORD);
        }else{
            etikettInfo.setText(FEIL_PASSORD);
        }
    }

    public void endreRegistrering(){
        File fil = new File(FILNAVN);
        File backupFil = new File(FILNAVN + ".BAK");
        Scanner reader = null;
        FileWriter writer = null;
        boolean innloggingVellykket = false;

        try{
            reader = new Scanner(fil);
            while(reader.hasNextLine()){
                String[] linjedel = reader.nextLine().split(";");
                if(linjedel[0].equals(brukernavn.getText())){
                    if(linjedel[1].equals(kode(passord.getText()))){
                        innloggingVellykket = true;
                        break;
                    }
                }
            }
            // Trenger ikke lukke siden dette skjer i "finally".
        }catch(FileNotFoundException e){
            out.println("Finner ikke filen '" + FILNAVN + "', feilmelding:" + e.toString());
        }catch(IOException e){
            out.println("Feil med å åpne filen '" + FILNAVN + "', feilmelding:" + e.toString());
        }catch(Exception e){
            out.println("Generell feil oppstått med filen '" + FILNAVN + "', feilmelding:" + e.toString());
        }finally{
            if(reader != null){
                try{
                    reader.close();
                }catch(Exception e){
                    out.println("Kunne ikke lukke filen '" + FILNAVN + "', feilmelding:" + e.toString());
                }
            }
        }

        
        if(innloggingVellykket){
            etikettInfo.setText(GLEMT_PASSORD);
            // Vet ikke hvilket kriterie passordet har, så jeg sier minimum 8 tegn
            String nyttPassord = "";
            while(nyttPassord.length() < 8){
                nyttPassord = showInputDialog("Nytt passord(min. 8 tegn): ");
            }

            if(backupFil.exists()){
                out.println("Backupfil eksisterer allerede. Overskriver...");
                backupFil.delete();
            }

            try{
                fil.renameTo(backupFil);

                reader = new Scanner(backupFil);
                writer = new FileWriter(fil);

                while(reader.hasNextLine()) {
                    String[] linjedel = reader.nextLine().split(";");
                    writer.write(linjedel[0] + ";");
                    if (linjedel[0].equals(brukernavn.getText())) {
                        writer.write(kode(nyttPassord));
                    }else{
                        writer.write(linjedel[1]);
                    }

                    if(reader.hasNextLine()){
                        writer.write("\n");
                    }
                }
            }catch(FileNotFoundException e){
                out.println("Finner ikke filen '" + FILNAVN + ".BAK', feilmelding:" + e.toString());
            }catch(IOException e){
                out.println("Feil med å åpne en av filene, feilmelding:" + e.toString());
            }catch(SecurityException e){
                out.println("Klarte ikke å opprette backup fil '" + FILNAVN + ".BAK'" + e.toString());
            }catch(Exception e){
                out.println("Endring av passord feilet med feilmelding: " + e.toString());
            }finally{
                if(reader != null){
                    try{
                        reader.close();
                    }catch(Exception e){
                        out.println("Kunne ikke lukke filen '" + FILNAVN + ".BAK', feilmelding:" + e.toString());
                    }
                }

                if(writer != null){
                    try{
                        writer.close();
                    }catch(Exception e){
                        out.println("Kunne ikke lukke filen '" + FILNAVN + "', feilmelding:" + e.toString());
                    }
                }
            }
        }else{
            etikettInfo.setText(FEIL_PASSORD);
        }
    }
}