import static java.lang.System.*;
import static javax.swing.JOptionPane.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Math.*;
import java.util.*;
import java.io.*;


import javafx.application.Application;
import javafx.beans.Observable;
import javafx.stage.Stage;
import javafx.scene.Scene; 

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.layout.FlowPane;  // Controls are next to eachother, and wrap at boundary

import javafx.stage.Stage;


public class GokartAdmin extends Application { // Extends javafx application window
    private RadioButton visAktive = new RadioButton("vis aktive");
    private RadioButton visAlle = new RadioButton("vis alle");
    
    private TextArea tekstområde = new TextArea();

    private TextField kjøretøyId = new TextField();

    private Button settAktiv = new Button("Sett kjøretøy aktivt");
    private Button settService = new Button("Sett kjøretøy på service");

    public void start(Stage window) {
        FlowPane root = new FlowPane();
        root.setHgap(10);
        root.setVgap(10);

        ToggleGroup gruppe = new ToggleGroup();

        visAktive.setToggleGroup(gruppe);
        visAktive.setOnAction(e -> radioKnapper(e));
        root.getChildren().add(visAktive);

        visAlle.setToggleGroup(gruppe);
        visAlle.setOnAction(e -> radioKnapper(e));
        root.getChildren().add(visAlle);
        visAlle.fire();
        
        root.getChildren().add(tekstområde);
        
        root.getChildren().add(new Label("Kjøretøy-id:"));

        kjøretøyId.setPrefColumnCount(4);
        root.getChildren().add(kjøretøyId);

        settAktiv.setOnAction(e -> handleButtonPress(e));
        root.getChildren().add(settAktiv);

        settService.setOnAction(e -> handleButtonPress(e));
        root.getChildren().add(settService);
        

        Scene scene = new Scene(root, 500, 300);
        

        window.setTitle("Gokart Admin - Fart og Spenning AS");
        window.setScene(scene);
        window.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }

    public void radioKnapper(ActionEvent e) {
        Kjoretoy[] kjoretoyListe = lesInnRegister();

        tekstområde.setText("");
        for(Kjoretoy k : kjoretoyListe){
            if(e.getSource() == visAlle || 
            (e.getSource() == visAktive && k.getStatus() == Kjoretoy.STATUS_AKTIV)){
                tekstområde.setText(tekstområde.getText() + k.toString() + "\n");
            }
        }
        
    }

    public void handleButtonPress(ActionEvent e) {
        try{
            int id = Integer.parseInt(kjøretøyId.getText());

            
            Kjoretoy[] kjoretoyListe = lesInnRegister();
            for(Kjoretoy kart : kjoretoyListe){
                if(kart.getId() == id){
                    if ( e.getSource() == settAktiv) {  
                        kart.setStatus(Kjoretoy.STATUS_AKTIV);
                    }
                    else if ( e.getSource() == settService ) {  
                        kart.setStatus(Kjoretoy.STATUS_SERVICE);
                    }

                    break;
                }
            }

            lagreRegister(kjoretoyListe);
            
        }catch(NumberFormatException ex){
            out.println("Brukerern skrev feil nummer");
        }catch(Exception ex){
            out.println("Generell feil: " + e.toString());
        }
    }

    private Kjoretoy[] lesInnRegister(){
        // Er allerede implementert. Legger inn for kompileringens skyld.
        return null;
    }

    private void lagreRegister(Kjoretoy[] kjoretoy){
        // Er allerede implementert. Legger inn for kompileringens skyld.
    }
}