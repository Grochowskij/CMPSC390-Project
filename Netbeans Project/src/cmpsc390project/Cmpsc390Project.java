package cmpsc390project;

import java.time.LocalDate;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Cmpsc390Project extends Application{
    
    public static void main(String[] args) {
        
        launch(args);
        
    }

    @Override
    public void start(Stage stage) throws Exception {
        createModSchedPage();
    }
    
    public void createModSchedPage(){
        Stage stage = new Stage();
        stage.setTitle("Modify Schedule Page");
        stage.setMaximized(true);

        Group root = new Group();
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("cmpsc390project/Styling.css");
        
        Button homePage = new Button();
        homePage.setText("Home Page");
        root.getChildren().add(homePage);
        
        //create date box
        DatePicker datePicker = new DatePicker();
        
        //makes it impossible to select days before todays date
        final Callback<DatePicker, DateCell> dayCellFactory = 
            new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);
                           
                            if (item.isBefore(
                                    LocalDate.now())
                                ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                            }   
                    }
                };
            }
            };
        
        //basic datepicker setup
        datePicker.setDayCellFactory(dayCellFactory);
        HBox date = new HBox(new Label("Date: "), datePicker);
        date.setLayoutX(50);
        date.setLayoutY(50);
        root.getChildren().add(date);
        
        //create workout box
        final ComboBox workouts = new ComboBox();
        workouts.getItems().addAll(
            "bench",
            "squat",
            "dumbbell curls",
            "jump squats with dumbbell",
            "tricep extensions"  
        );
        //   how to get value in box ->          workouts.getValue();
        
        //add more workouts to same day  (not sure how to implement yet)
        //Button workoutAdd = new Button("Add workout");
        
        //formatting
        HBox workoutBox = new HBox(new Label("Select a workout from the list: "), workouts);
        workoutBox.setSpacing(10);
        workoutBox.setLayoutX(50);
        workoutBox.setLayoutY(100);
        root.getChildren().add(workoutBox);
        
        TextField time1 = new TextField();
        TextField time2 = new TextField();
        HBox timeBox = new HBox(new Label("Enter the start time and end time (xx:xx): "), time1, new Label(" - "), time2);
        timeBox.setLayoutX(50);
        timeBox.setLayoutY(150);
        root.getChildren().add(timeBox);
        //    how to get text in box  ->    time1/time2.getText();
        
        //submission button setup
        Button submit = new Button("Submit");
        submit.setLayoutX(50);
        submit.setLayoutY(200);
        root.getChildren().add(submit);
        
        stage.setScene(scene);
        stage.show();
        
        //submit button storage code
        submit.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evt){ 
                LocalDate ld = datePicker.getValue();
                String workout = (String)workouts.getValue();
                String startTime = time1.getText();
                String endTime = time2.getText();
            }

        });
        
        
        //homepage return code
        homePage.setOnAction(new EventHandler<ActionEvent>(){
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
        Scene scene = new Scene(root);
        scene.getStylesheets().add("cmpsc390project/Styling.css");
        
        Button btn = new Button();
        btn.setText("To Modify Schedule");
        btn.setLayoutX(100);
        btn.setLayoutY(100);
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
