package cmpsc390project;

import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Cmpsc390Project extends Application{
    
    public static void main(String[] args) {
        
        launch(args);
        
    }

    @Override
    public void start(Stage stage) throws Exception {
        createHomepage();
    }
    
    public void createModSchedPage(){
        Stage stage = new Stage();
        stage.setTitle("Modify Schedule Page");
        stage.setMaximized(true);


        Group root = new Group();
        Scene scene = new Scene(root, 320, 240);
        Button btn = new Button();
        btn.setText("You clicked the butt!!!");
        btn.set
        root.getChildren().add(btn);
        
        

        stage.setScene(scene);
        stage.show();
        
        btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evt){ 
                createHomepage();
                stage.close();
            }

        });
    }
    
    public void createHomepage(){
        Stage stage = new Stage();
        stage.setMaximized(true);
        
        stage.setTitle("Home Page");

        Group root = new Group();
        Scene scene = new Scene(root, 320, 240);
        Button btn = new Button();
        btn.setText("Don't click here!!!");
        root.getChildren().add(btn);
        
        

        stage.setScene(scene);
        stage.show();
        
        btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evt){ 
                stage.close();
                createModSchedPage();
            }

        });
    }
    
}
