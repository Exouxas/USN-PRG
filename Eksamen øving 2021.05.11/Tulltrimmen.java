/*
  Programforklaring
*/

import static java.lang.System.*;
import static javax.swing.JOptionPane.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Math.*;
import java.util.*;
import java.util.concurrent.Flow;
import java.io.*;


import javafx.application.Application;

import javafx.stage.Stage;
import javafx.scene.Scene; 

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;

import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.layout.StackPane; // Controls are stacked on top of eachother (overlapping)
import javafx.scene.layout.FlowPane;  // Controls are next to eachother, and wrap at boundary
import javafx.scene.layout.GridPane;  // Controls are placed in a grid, with position and span

import javafx.stage.Stage;


public class Tulltrimmen extends Application { // Extends javafx application window
    Button visPost = null;
    ComboBox<String> visning = null;
    Button blankUt = null;

    TextField postNr = null;
    Label aktuellPost = null;
    TextArea tekstområde = null;

    String[] alternativ = null;
    String[] registrering = null;

    public void start(Stage window) {
        alternativ = new String[4];
        alternativ[0] = "Ingen sortering";
        alternativ[1] = "Topp 5";
        alternativ[2] = "Alle besøk";
        alternativ[3] = "Alle A -> Å";

        // Eksempel data for kompilering av test
        registrering = new String[5];
        registrering[0] = "Linje 1";
        registrering[1] = "Linje 2";
        registrering[2] = "Linje 3";
        registrering[3] = "Linje 4";
        registrering[4] = "Linje 5";

        FlowPane panel = new FlowPane();

        Label postNrLabel = new Label("Gi postnr: ");
        panel.getChildren().add(postNrLabel);
        // alternativ: panel.getChildren().add(new Label("Gi postnr: "));

        postNr = new TextField();
        postNr.setPrefColumnCount(2);
        panel.getChildren().add(postNr);

        visPost = new Button("Vis registrering");
        visPost.setOnAction(e -> behandleKlikk(e));
        panel.getChildren().add(visPost);

        aktuellPost = new Label("");
        panel.getChildren().add(aktuellPost);

        tekstområde = new TextArea();
        tekstområde.setPrefColumnCount(15);
        tekstområde.setPrefRowCount(12);
        tekstområde.setEditable(false);
        panel.getChildren().add(tekstområde);

        visning = new ComboBox<String>();
        visning.getItems().add(alternativ[0]);
        visning.getItems().add(alternativ[1]);
        visning.getItems().add(alternativ[2]);
        visning.getItems().add(alternativ[3]);
        visning.getSelectionModel().select(alternativ[0]);
        visning.setOnAction(e -> behandleKlikk(e));
        panel.getChildren().add(visning);

        blankUt = new Button("Blank ut");
        blankUt.setOnAction(e -> behandleKlikk(e));
        panel.getChildren().add(blankUt);



        Scene scene = new Scene(panel, 225, 325);

        window.setTitle("Tulltrim21");
        window.setResizable(false);
        window.setScene(scene);
        window.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }

    public void behandleKlikk(ActionEvent e) {
        if (e.getSource() == visPost)
            hentRegistrerteBesøk();
        else if (e.getSource() == visning)
            sorterRegistrerteBesøk();
        else if (e.getSource() == blankUt)
            blankUtAlt();
    }

    private void hentRegistrerteBesøk(){
        // Fyll inn "registrering" variabel
    }

    private void sorterRegistrerteBesøk(){
        if(visning.getSelectionModel().getSelectedItem() == alternativ[3]){
            if(registrering != null){
                Arrays.sort(registrering);
                String tekst = "";
                for(int i = 0; i < registrering.length; i++){
                    if(!tekst.equals("")){
                        tekst += "\n";
                    }

                    tekst += registrering[i];
                }
                tekstområde.setText(tekst);
            }
        }
    }

    private void blankUtAlt(){
        postNr.setText("");
        tekstområde.setText("");
        aktuellPost.setText("");
        visning.getSelectionModel().select(alternativ[0]);
        registrering = null;
    }
}

