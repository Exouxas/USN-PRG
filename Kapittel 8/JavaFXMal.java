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


public class JavaFXMal extends Application { // Extends javafx application window
    
    public void start(Stage window) {

        // One way of subbing to event
        Button btn1 = new Button();
        btn1.setText("Say 'Hello World'");
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        // Other way of subbing to event
        Button btn2 = new Button();
        btn2.setText("Say 'Hello World'");
        btn2.setOnAction(e -> handleButtonPress(e));
        

        StackPane root = new StackPane();
        root.getChildren().add(btn1, btn2);


        Scene scene = new Scene(root, 300, 250);

        window.setTitle("Hello World!");
        window.setScene(scene);
        window.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }

    public void handleButtonPress(ActionEvent event) {
        System.out.println("Hello World!");
    }
}