/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmpsc390project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class RecordWorkouts extends Application {

  
    public static void main(String[] args) {
        Application.launch(args);
    }
    
     @Override
    public void start(Stage stage) {
    stage.setTitle("Record Workouts");

    Group root = new Group();
    Scene scene = new Scene(root, 520, 520);
    Button btn = new Button();
    btn.setText("Don't click here!!!");
    //root.getChildren().add(btn);
    
    Rectangle rec = new Rectangle(50, 50, 50, 50);
    rec.setFill(javafx.scene.paint.Color.BLUE);
    
    root.getChildren().add(rec);
    
         root.setOnMouseMoved(new EventHandler<MouseEvent>() {
             @Override
             public void handle(MouseEvent evt) 
             {
                 
                 System.out.println("x=" + (evt.getX()-rec.getX()) + " y=" + (evt.getY()-rec.getY()));
                 double xx = (evt.getX()-rec.getX());
                 double yy = (evt.getY()-rec.getY());
                 
                 if(xx<25 && yy<25)
                 {
                     rec.setX(rec.getX() + 2);
                     rec.setY(rec.getY() + 2);
                 }
                 if(xx>25 && yy<25)
                 {
                     rec.setX(rec.getX() - 2);
                     rec.setY(rec.getY() + 2);
                 }
                 if(xx>25 && yy>25)
                 {
                     rec.setX(rec.getX() - 2);
                     rec.setY(rec.getY() - 2);
                 }
                  if(xx<25 && yy>25)
                 {
                     rec.setX(rec.getX() + 2);
                     rec.setY(rec.getY() - 2);
                 }
             }
             
         });
    
    stage.setScene(scene);
    stage.show();
    

        // ButtonMover handler = new ButtonMover();
         //btn.setOnAction(handler);
         //MouseMoved mouse = new MouseMoved();
         //btn.setOnMouseMoved(mouse);
    }

    
}
