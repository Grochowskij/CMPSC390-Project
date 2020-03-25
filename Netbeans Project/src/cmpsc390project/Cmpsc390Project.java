package cmpsc390project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Cmpsc390Project extends Application{
    
    public static void main(String[] args) {
        
        launch(args);
        
    }
    
    public class Record {
 
        public SimpleStringProperty Date, Time, Workout;
 
        public String getDate () {
            return Date.get();
        }
 
        public String getTime() {
            return Time.get();
        }
 
        public String getWorkout() {
            return Workout.get();
        }
 
        Record(String f1, String f2, String f3) {
            this.Date = new SimpleStringProperty(f1);
            this.Time = new SimpleStringProperty(f2);
            this.Workout = new SimpleStringProperty(f3);
      
        }
    }
      
           private final TableView<Record> tableView = new TableView<>();
 
    private final ObservableList<Record> dataList
            = FXCollections.observableArrayList();
   
    
    public void createHomepage(){
        
        Stage stage = new Stage();
        stage.setMaximized(true);


        stage.setTitle("Home Page");
 
        Group root = new Group();
 
        VBox vBox = new VBox();
        vBox.minWidth(200);
        vBox.getChildren().add(tableView);
 
        root.getChildren().add(vBox);
        
        Button btn = new Button();
        btn.setText("To Modify Schedule");
        btn.setLayoutX(300);
        btn.setLayoutY(100);
        root.getChildren().add(btn);
 
        stage.setScene(new Scene(root, 700, 250));
        stage.show();
        
        btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evt){ 
                stage.close();
                createModSchedPage();
            }

        });
    }

    @Override
    public void start(Stage stage) throws Exception {
        TableColumn columnF1 = new TableColumn("Date");
        columnF1.setCellValueFactory(
                new PropertyValueFactory<>("Date"));
 
        TableColumn columnF2 = new TableColumn("Time");
        columnF2.setCellValueFactory(
                new PropertyValueFactory<>("Time"));
 
        TableColumn columnF3 = new TableColumn("Workout");
        columnF3.setCellValueFactory(
                new PropertyValueFactory<>("Workout"));
        
        //file reading and storing to list
        String FieldDelimiter = ",";
                BufferedReader br;

            br = new BufferedReader(new FileReader("Scheduled.txt"));
 
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(FieldDelimiter,-2);
                dataList.add(new Record(fields[0],fields[2],fields[1]));

 
            }
 
        tableView.setItems(dataList);
        tableView.getColumns().addAll(
                columnF1, columnF2, columnF3);
        
        createHomepage();
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
        date.setLayoutX(350);
        date.setLayoutY(100);
        root.getChildren().add(date);
        
        ArrayList workoutList = new ArrayList();
        try {
            File myObj = new File("WorkoutList.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String workout = myReader.nextLine();
                workoutList.add(workout);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }

        //listview workouts
        ListView workouts = new ListView();

        for(int i = 0; i < workoutList.size();++i){
            workouts.getItems().add(workoutList.get(i));
        }
        workouts.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        //formatting
        VBox workoutBox = new VBox(new Label("Select a workout from the list: "),new Label("You can select multiple workouts"),new Label("by pressing control and clicking"), workouts);
        workoutBox.setSpacing(10);
        workoutBox.setLayoutX(50);
        workoutBox.setLayoutY(100);
        root.getChildren().add(workoutBox);
        
        ObservableList<String> numberTime = 
            FXCollections.observableArrayList(
            "1","2","3","4","5","6","7","8","9","10","11","12"
        );
        
        ObservableList<String> AMPM =
                FXCollections.observableArrayList(
            "AM","PM"
        );
        
        final ComboBox time1 = new ComboBox(numberTime);
        final ComboBox time2 = new ComboBox(numberTime);
        
        final ComboBox day = new ComboBox(AMPM);
        final ComboBox night = new ComboBox(AMPM);
        
        HBox timeBox = new HBox(new Label("Enter the start time and end time: "), time1, day, new Label(" - "), time2, night);
        timeBox.setLayoutX(350);
        timeBox.setLayoutY(150);
        root.getChildren().add(timeBox);
        //    how to get text in box  ->    time1/time2.getText();
        
        //submission button setup
        Button submit = new Button("Submit");
        submit.setLayoutX(350);
        submit.setLayoutY(200);
        root.getChildren().add(submit);
        
        stage.setScene(scene);
        stage.show();
        
        //submit button storage code
        submit.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evt){ 
                if(time1.getValue() == null || time2.getValue() == null || day.getValue() == null || night.getValue() == null){
                    Label error = new Label("Enter a value in all boxes!");
                    error.setLayoutX(stage.getWidth()/2);
                    root.getChildren().add(error);
                } else {
                LocalDate ld = datePicker.getValue();
                ObservableList selectedIndices = workouts.getSelectionModel().getSelectedIndices();
                String List = "";
                for(Object o : selectedIndices){
                    List = List + workoutList.get((int)o) + "+";
                }
                
                if(List.equals("")){
                    Label error2 = new Label("Enter a value in all boxes!");
                    error2.setLayoutX(stage.getWidth()/2);
                    root.getChildren().add(error2);
                } else {
                
                String startTime = (String)time1.getValue() + ":00" + (String)day.getValue();
                String endTime = (String)time2.getValue() + ":00" + (String)night.getValue();
                
                dataList.add(new Record(ld.toString(), startTime+ "-" + endTime, List));
                
                File scheduleF = new File("Scheduled.txt");
                //Write info to file and create if needed
                try{
                    if(!scheduleF.exists()){
                    System.out.println("We had to make a new file.");
                    scheduleF.createNewFile();
                    }

                    FileWriter fileWriter = new FileWriter(scheduleF, true);

                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(ld + "," + List + "," + startTime + "-" + endTime + "\n");
                    bufferedWriter.close();

                    System.out.println("Done");
                } catch(IOException e) {
                    System.out.println("COULD NOT LOG!!");
                }
                stage.close();
                createHomepage();
                }
                }
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
    
//    public void createHomepage(){
//        Stage stage = new Stage();
//        stage.setMaximized(true);
//        
//        stage.setTitle("Home Page");
//
//        Group root = new Group();
//        Scene scene = new Scene(root);
//        scene.getStylesheets().add("cmpsc390project/Styling.css");
//        
//        Button btn = new Button();
//        btn.setText("To Modify Schedule");
//        btn.setLayoutX(100);
//        btn.setLayoutY(100);
//        root.getChildren().add(btn);
//        
//        
//
//        stage.setScene(scene);
//        stage.show();
//        
//        btn.setOnAction(new EventHandler<ActionEvent>(){
//            @Override
//            public void handle(ActionEvent evt){ 
//                stage.close();
//                createModSchedPage();
//            }
//
//        });
//    }
    
}
