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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.layout.StackPane; // Controls are stacked on top of eachother (overlapping)
import javafx.scene.layout.FlowPane;  // Controls are next to eachother, and wrap at boundary
import javafx.scene.layout.GridPane;  // Controls are placed in a grid, with position and span

import javafx.stage.Stage;
import javafx.geometry.*;


public class JavaFXOppgave extends Application { // Extends javafx application window
    Button beregning;
    Button blankUt;
    TextField lengdeInput;
    TextField høydeInput;
    TextField dybdeInput;
    TextField restOutput;

    public void start(Stage window) {
        FlowPane root = new FlowPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);

        root.getChildren().add(new Label("Oppgi målene på oljetanken i feltene under!:"));
        
        lengdeInput = new TextField();
        root.getChildren().add(new Label("Tanklengde L i cm:"));
        lengdeInput.setPrefColumnCount(10);
        root.getChildren().add(lengdeInput);

        høydeInput = new TextField();
        root.getChildren().add(new Label("Tankhøyde H i cm:"));
        høydeInput.setPrefColumnCount(10);
        root.getChildren().add(høydeInput);


        dybdeInput = new TextField();
        root.getChildren().add(new Label("Tankdybde D i cm:"));
        dybdeInput.setPrefColumnCount(10);
        root.getChildren().add(dybdeInput);


        restOutput = new TextField();
        root.getChildren().add(new Label("Oljerestvolum i liter:"));
        restOutput.setPrefColumnCount(10);
        restOutput.setEditable(false);
        root.getChildren().add(restOutput);


        beregning = new Button("Beregn restvolum");
        root.getChildren().add(beregning);
        beregning.setOnAction(e -> behandleKlikk(e));

        blankUt = new Button("Blank ut");
        root.getChildren().add(blankUt);
        blankUt.setOnAction(e -> behandleKlikk(e));



        Scene scene = new Scene(root, 300, 200);

        window.setTitle("Kalkulator");
        window.setScene(scene);
        window.setResizable(false);
        window.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }

    public void handleButtonPress(ActionEvent event) {
        System.out.println("Hello World!");
    }

    private static double mengdeRestolje(int høyde, int dybde, int lengde){
        double radius = høyde / 2;
        double del1 = lengde;
        double del2 = Math.pow(radius, 2) * Math.acos((radius - dybde) / radius);
        double del3 = radius - dybde;
        double del4 = Math.sqrt(dybde * (2 * radius - dybde));

        double volum = del1 * (del2 - del3 * del4);

        return volum / 1000;
    }

    public void behandleKlikk(ActionEvent e) {  
        if ( e.getSource() == blankUt) {  
            blankUtGUI();
        }
        else if ( e.getSource() == beregning ) {  
            gjennomførBeregning();
        }
    }

    private void blankUtGUI(){
        lengdeInput.setText("");
        høydeInput.setText("");
        dybdeInput.setText("");
        restOutput.setText("");
    }

    private void gjennomførBeregning(){
        try{
            int høyde = Integer.parseInt(høydeInput.getText());
            int dybde = Integer.parseInt(dybdeInput.getText());
            int lengde = Integer.parseInt(lengdeInput.getText());

            restOutput.setText("" + (int)mengdeRestolje(høyde, dybde, lengde));
        }catch(Exception e){
            restOutput.setText("Feil ved innlesing!");
        }

        
    }
}