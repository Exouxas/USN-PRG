/*
  Programforklaring
*/

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;

import javafx.scene.layout.StackPane; // Controls are stacked on top of eachother (overlapping)
import javafx.scene.layout.FlowPane;  // Controls are next to eachother, and wrap at boundary
import javafx.scene.layout.GridPane;  // Controls are placed in a grid, with position and span

import javafx.stage.Stage;


public class Romadmin extends Application { // Extends javafx application window
    TextArea printOmråde = null;
    ComboBox romTypeMeny = null;
    String[] romtyper = null;

    TextField romNr = null;
    TextField romtype = null;
    TextField plasser = null;

    final String FILNAVN = "nyrom.txt";
    
    public void start(Stage window) {

        romtyper = new String[4];
        romtyper[0] = "Auditorium";
        romtyper[1] = "Seminarrom";
        romtyper[2] = "Lab";
        romtyper[3] = "Grupperom";
        
        window.setResizable(false);
        FlowPane root = new FlowPane();


        Label romTypeMenyLabel = new Label("Velg romtype:   ");
        root.getChildren().add(romTypeMenyLabel);


        romTypeMeny = new ComboBox();
        romTypeMeny.getItems().add(romtyper[0]);
        romTypeMeny.getItems().add(romtyper[1]);
        romTypeMeny.getItems().add(romtyper[2]);
        romTypeMeny.getItems().add(romtyper[3]);
        romTypeMeny.setValue(romtyper[0]);
        romTypeMeny.setOnAction(e -> håndterNedtrekksliste(e));
        root.getChildren().add(romTypeMeny);


        printOmråde = new TextArea();
        printOmråde.setEditable(false);
        printOmråde.setPrefRowCount(8);
        root.getChildren().add(printOmråde);


        Label romNrLabel = new Label("Gi romnr:   ");
        root.getChildren().add(romNrLabel);

        romNr = new TextField();
        romNr.setPrefColumnCount(6);
        root.getChildren().add(romNr);

        Button redigerRomNr = new Button("Rediger");
        redigerRomNr.setOnAction(e -> håndterRedigering(e));
        root.getChildren().add(redigerRomNr);


        Label romtypeLabel = new Label("Romtype:   ");
        root.getChildren().add(romtypeLabel);

        romtype = new TextField();
        romtype.setPrefColumnCount(6);
        root.getChildren().add(romtype);

        Button angreKnapp = new Button("Angre");
        angreKnapp.setOnAction(e -> håndtereAngre(e));
        root.getChildren().add(angreKnapp);

        Label plassLabel = new Label("Plasser:      ");
        root.getChildren().add(plassLabel);

        plasser = new TextField();
        plasser.setPrefColumnCount(6);
        root.getChildren().add(plasser);


        Button lagreEndringer = new Button("Lagre endringer");
        lagreEndringer.setOnAction(e -> håndtereLagre(e));
        root.getChildren().add(lagreEndringer);

        Button avsluttProgram = new Button("Avslutt program");
        avsluttProgram.setOnAction(e -> håndtereAvslutt(e));
        root.getChildren().add(avsluttProgram);
        


        Scene scene = new Scene(root, 225, 300);

        window.setTitle("Romadmin");
        window.setScene(scene);
        window.show();

        settInnhold(romtyper[0]);
    }
    
    public static void main(String[] args){
        launch(args);
    }

    public void handleButtonPress(ActionEvent event){
        System.out.println("Hello World!");
    }

    public void håndtereAngre(ActionEvent event){
        romNr.setText("");
        romtype.setText("");
        plasser.setText("");
    }

    public void håndtereLagre(ActionEvent event){
        String[] linjer = hentInfo(FILNAVN);

        String funnetVerdi = hentLinje(linjer, romNr.getText());

        if(funnetVerdi != ""){
            String[] nyeLinjer = new String[linjer.length];
            for(int i = 0; i < linjer.length; i++){
                if(funnetVerdi == linjer[i]){
                    nyeLinjer[i] = romtype.getText() + ";" + romNr.getText() + ";" + plasser.getText();
                }else{
                    nyeLinjer[i] = linjer[i];
                }
            }

            FileWriter skriver = null;
            try{
                skriver = new FileWriter(new File(FILNAVN), false);
                for(int i = 0; i < nyeLinjer.length; i++){
                    skriver.write(nyeLinjer[i] + "\n");
                }
            }catch(Exception e){
                out.println("Feilet med å skrive fil: " + e.toString());
            }
            finally{
                if(skriver != null){
                    try{
                        skriver.close();
                    }catch(Exception e){
                        out.println("Klarte ikke å lukke fila");
                    }
                }
                
                settInnhold(romTypeMeny.getValue().toString());
            }
        }
        else{
            out.println("Romnr ikke funnet!");
        }
    }

    public void håndtereAvslutt(ActionEvent event){
         System.exit(0);
    }

    public void håndterRedigering(ActionEvent event){
        String[] linjer = hentInfo(FILNAVN);

        String funnetVerdi = hentLinje(linjer, romNr.getText());

        if(funnetVerdi != ""){
            String[] dataTab = funnetVerdi.split(";");
            romtype.setText(dataTab[0]);
            plasser.setText(dataTab[2]);
        }else{
            out.println("Romnr ikke funnet!");
        }
    }

    public String hentLinje(String[] linjer, String søkeord){
        String funnetVerdi = "";

        for(int i = 0; i < linjer.length; i++){
            String[] dataTab = linjer[i].split(";");
            if(dataTab[1].equals(søkeord)){
                funnetVerdi = linjer[i];
            }
        }

        return funnetVerdi;
    }

    public void håndterNedtrekksliste(Event event){
        settInnhold(romTypeMeny.getValue().toString());
    }

    public void settInnhold(String romtype){
        
        out.println("Setter til: " + romtype);

        String[] linjer = hentInfo(FILNAVN);

        String innhold = "";


        for(int i = 0; i < linjer.length; i++){
            String[] dataTab = linjer[i].split(";");
            if(dataTab[0].equals(romtype)){
                if(innhold != ""){
                    innhold += "\n";
                }

                innhold += dataTab[0] + "   " + dataTab[1] + "   " + dataTab[2];
            }
        }

        printOmråde.setText(innhold);
    }

    public static String[] hentInfo(String filBane){
        Scanner leser = null;
        String[] romTabell = null;

        try{
            leser = new Scanner(new File(filBane));
            int antallLinjer = 0;
            while(leser.hasNextLine()){
                antallLinjer++;
                leser.nextLine();
            }
            leser.close();

            romTabell = new String[antallLinjer];
            leser = new Scanner(new File(filBane));
            for(int i = 0; i < antallLinjer; i++){
                romTabell[i] = leser.nextLine();
            }
            leser.close();
        }
        catch(FileNotFoundException e){
            out.println("Filen '" + filBane + "' ikke funnet!");
        }
        catch(NumberFormatException e){
            out.println("Feil format på fil!");
        }
        catch(Exception e){
            out.println("Lesing av fil feilet!");
        }
        finally{
            if(leser != null){
                try{
                    leser.close();
                }catch(Exception e){
                    out.println("Klarte ikke å lukke filen!");
                }
            }
        }

        return romTabell;
    }
}