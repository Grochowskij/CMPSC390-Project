/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class RecordWorkouts extends Application {

        private static ArrayList<Workout> workoutList = new ArrayList<>();
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
     @Override
    public void start(Stage stage) throws Exception {
    stage.setTitle("Record Workouts");
    
      clearFile();

      manuallyEnterWorkouts();
              
         getFileData();
         
         System.out.println(workoutList.toString());

     
    /**Group root = new Group();
    Scene scene = new Scene(root, 520, 520);
    Button btn = new Button();
    btn.setText("Don't click here!!!");
    root.getChildren().add(btn);
      
    stage.setScene(scene);
    stage.show();
    **/


    }
    
        public static void writeWorkout(Workout w) throws IOException 
        {
                String str = w.fileFormat();
                File file = new File("test.txt"); 
                BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                writer.append(str);
                writer.append("\n");
                writer.close(); 
        }
        public static void getFileData() throws IOException
        {
            File file = new File("test.txt"); 
            Scanner sc = new Scanner(file); 
            
            while (sc.hasNext())
            {
                Workout newWorkout = new Workout(sc.next(), sc.nextDouble(), sc.nextInt());
                workoutList.add(newWorkout);
            }

        }
        public static void clearFile() throws IOException {
        FileWriter fwOb = new FileWriter("test.txt", false); 
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        fwOb.close();
    }
        public static void manuallyEnterWorkouts() throws IOException
        {
            Scanner scan = new Scanner(System.in);
            String input = " ";
            
            while(!input.equals("n"))
            {
                String tempName ="";
                 double tempWeight=0;
                 int tempReps = 0;
                 
                 
                System.out.println("Enter the name of workout:");
                tempName=scan.next();
                System.out.println("Enter the weight:");
                tempWeight = scan.nextDouble();
                System.out.println("Enter amount of reps:");
                tempReps = scan.nextInt();
                
                Workout temp = new Workout(tempName,tempWeight,tempReps);
                writeWorkout(temp);
                
                System.out.println("Do you want to add another? (y/n)");
                input = scan.next();
                
                
            }
        }

    
}
