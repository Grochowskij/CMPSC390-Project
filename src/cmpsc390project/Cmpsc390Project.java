package cmpsc390project;

import java.io.BufferedWriter;
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
import java.util.ArrayList;
import javafx.scene.layout.HBox;
import java.io.File; 
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner; 
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Cmpsc390Project extends Application
{
    private static ArrayList<Workout> workoutList = new ArrayList<>();
    
    public static void main(String[] args) 
    {
        Application.launch(args);
    }
         @Override
    public void start(Stage stage) throws IOException
    {
         showRecordWorkoutScene(stage);
    }
    
    //main screen for recording workout data
        public static void showRecordWorkoutScene(Stage stage) throws IOException
        {     
             getFileData();
             
            GridPane pane = new GridPane();
            pane.setAlignment(Pos.CENTER);
            pane.setHgap(5.5);
            pane.setVgap(10);
            
            ListView listView = new ListView();
            listView =updateListView();
            pane.add(listView, 0, 0);
            
            Button btn = new Button();
            btn.setText("Add workout");
            pane.add(btn,0,1);

            Scene scene = new Scene(pane);
            stage.setTitle("Record Workouts");
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
            
            stage.setScene(scene);
            
            btn.setOnAction(e -> 
            {
            try 
            {
            addWorkoutDialog(stage, scene);
            } 
            catch (IOException ex) { }}); 
        }
        //end primary screen for recording workouts
        
         //this will update the list view
        public static ListView updateListView()
        {
            ObservableList<Workout> wOuts = FXCollections.observableArrayList(workoutList);
            ListView<Workout> wrkOut = new ListView<Workout>(wOuts);
            return wrkOut;
        }
        //end the list view updating
        
        //Create the add workout page, where user can enter in workouts
        public static void addWorkoutDialog(Stage primaryStage, Scene scene2) throws IOException
        {
            GridPane pane = new GridPane();
            pane.setAlignment(Pos.CENTER);
            pane.setHgap(10);
            pane.setVgap(10);
            pane.setPadding(new Insets(11,12,13,14));
            
            final ComboBox workouts2 = new ComboBox();
            workouts2.getItems().addAll(
                    "Bench","Curls","Squats","Deadlift"
            );
            
            pane.add(new Label("Workout Name:"), 0, 0);
            TextField wName= new TextField();
            pane.add(wName, 1, 0);
            //pane.add(workouts2, 1, 0);
            pane.add(new Label("Weight:"), 0, 1);
            TextField weight = new TextField();
            pane.add(weight, 1, 1);
            pane.add(new Label("Reps:"), 0, 2);
            TextField reps = new TextField();
            pane.add(reps, 1, 2);
            
            Button but = new Button("Add Workout");
            pane.add(but, 1, 3);
            
            GridPane.setHalignment(but, HPos.RIGHT);
            
            Scene scene = new Scene(pane);
            primaryStage.setTitle("Add Workout");
            primaryStage.setScene(scene);
            primaryStage.show();
         
            //Sets listener for add workout button
             but.setOnAction(e -> 
{
            String tempString = wName.getText();
            double tempWeight = new Double(weight.getText()).doubleValue();
            int tempReps = new Integer(reps.getText());
            
            pane.add(new Label("Success!"), 0, 3);
            wName.clear();
            weight.clear();
            reps.clear();
            
            Workout newWorkout = new Workout(tempString, tempWeight,tempReps);
                try 
                {
                    writeWorkout(newWorkout);
                    showRecordWorkoutScene(primaryStage);
                } 
                catch (IOException ex) {}
        }
);
             //End of "add workout" button
        }
        //End of addWorkoutDialog method
    
        //This method will write a workout to the text file in order to save it for future use
        public static void writeWorkout(Workout w) throws IOException 
        {
                String str = w.fileFormat();
                File file = new File("test.txt"); 
                BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                writer.append(str);
                writer.append("\n");
                writer.close(); 
        }
        //End writeWorkout method
        
        //This will get data from the text file and store it into an arrayList
        public static void getFileData() throws IOException
        {
            File file = new File("test.txt"); 
            Scanner sc = new Scanner(file); 
            workoutList.clear();
            
            while (sc.hasNext())
            {
                Workout newWorkout = new Workout(sc.next(), sc.nextDouble(), sc.nextInt());
                workoutList.add(newWorkout);
            }
        }
        //End getFileData method
        
        //This will clear the file of old workouts (for testing purposes)
        public static void clearFile() throws IOException 
        {
        FileWriter fwOb = new FileWriter("test.txt", false); 
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        fwOb.close();
        }
        //End clearFile method
}
