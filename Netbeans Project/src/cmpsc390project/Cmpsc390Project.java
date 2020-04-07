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
import javafx.scene.text.Text;
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
        
        public void setWorkout(String s){
            Workout = new SimpleStringProperty(s);
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
    
    public void createStatPage(){
        
    }
    
    public void createWorkoutPage(){
        
    }
    
    public void createHomepage(){
        Stage stage = new Stage();
        stage.setTitle("Home Page");
        stage.setMaximized(true);
        
       
        Group root = new Group();
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("cmpsc390project/Styling.css");
        
        Button homePage = new Button();
        homePage.setText("Home Page");
        homePage.setStyle("-fx-background-color: #730b6b");

        homePage.setMinWidth(100);
        root.getChildren().add(homePage);
        
        //Takes to mod schedule
        Button btn = new Button();
        btn.setStyle("-fx-background-color: #A62662");
        btn.setText("Add Workout");
        btn.setLayoutX(100);
        btn.setLayoutY(0);
        btn.setMinWidth(100);
        root.getChildren().add(btn);
        
        //takes to list of workouts
        Button WorkoutBtn = new Button();
        WorkoutBtn.setText("Workout List");
        WorkoutBtn.setStyle("-fx-background-color: #e84f64");

        WorkoutBtn.setLayoutX(200);
        WorkoutBtn.setMinWidth(100);
        root.getChildren().add(WorkoutBtn);
        //MAIN NAV
        //button to stats
        Button StatBtn = new Button();
        StatBtn.setStyle("-fx-background-color: #ff7a8c");

        StatBtn.setText("Statistics");
        StatBtn.setLayoutX(300);
        StatBtn.setMinWidth(100);
        root.getChildren().add(StatBtn);
        
        btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evt){ 
                stage.close();
                createModSchedPage(null);
            }

        });
        
        WorkoutBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evt){ 
                stage.close();
                createWorkoutPage();
            }

        });
        
        StatBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evt){ 
                stage.close();
                createStatPage();
            }

        });
        
        homePage.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evt){ 
                stage.close();
                createHomepage();
            }

        });
        
        // Create the first Text Node
        Text welcome = new Text("Welcome Back, Homie!");
        // Create the VBox
        VBox messageBox = new VBox(8);
       //messageBox.setPadding(new Insets(5, 50, 50, 25));
       //welcome.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
       welcome.setId("fancytext");

       messageBox.setSpacing(5);
        // Add the Text Nodes to the VBox
        messageBox.getChildren().add(welcome);    
        messageBox.setSpacing(30);
        messageBox.setLayoutY(40);
        root.getChildren().add(messageBox);
        
        //Display of workouts
        VBox vBox = new VBox();
        vBox.setLayoutX(50);
        vBox.setLayoutY(100);
        vBox.minWidth(200);
        vBox.getChildren().add(tableView);
        
        Button delete = new Button("Delete selected workout");
        Button complete = new Button("Completed selected workout");
        delete.setLayoutX(100);
        delete.setLayoutY(510);
        complete.setLayoutX(90);
        complete.setLayoutY(540);
        
        root.getChildren().add(delete);
        root.getChildren().add(complete);
        
        delete.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evt){
                Record item = tableView.getSelectionModel().getSelectedItem();
                if(item!= null){
                    try {
                        deleteHomeWorkout(item);
                    } catch (IOException ex) {
                        System.out.println("Error");
                    }
                    stage.close();
                    Stage stage = new Stage();
                    try {
                        start(stage);
                    } catch (Exception ex) {
                        System.out.println("Error at start stage");
                    }
                }
            }

        });
        
        complete.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evt){
                Record item2 = tableView.getSelectionModel().getSelectedItem();
                if(item2 != null){
                    if(LocalDate.parse(item2.getDate()).compareTo(LocalDate.now()) <= 0){
                        try {
                            stage.close();
                            completeHome(item2);
                        } catch (IOException ex) {
                            System.out.println("Error at complete Home call");
                        }
                    } else {
                        Label temporalError = new Label("You can't complete a workout before the date scheduled!");
                        temporalError.setLayoutX(50);
                        temporalError.setLayoutY(640);
                        root.getChildren().add(temporalError);
                    }
                }
            }

        });
 
        root.getChildren().add(vBox);
        
        Button editWorkout = new Button("Edit selected workout");
        editWorkout.setLayoutX(110);
        editWorkout.setLayoutY(570);
        root.getChildren().add(editWorkout);
        
        editWorkout.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evt){
                stage.close();
                createModSchedPage(tableView.getSelectionModel().getSelectedItem());
            }

        });
 
        stage.setScene(scene);
        stage.show();
        
        
    }
    
    public void deleteHomeWorkout(Record item) throws IOException{
        try{
            File inputFile = new File("Scheduled.txt");
            File tempFile = new File("myTempFile.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = item.getDate() + "," + item.getWorkout() + "," + item.getTime();
            String currentLine;

            while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
                
                String trimmedLine = currentLine.trim();
                if(trimmedLine.equals(lineToRemove)) continue;
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close(); 
            reader.close();
            
        } catch(FileNotFoundException e){
            System.out.println("Error");
        }
        
        try{
            File inputFile = new File("myTempFile.txt");
            File tempFile = new File("Scheduled.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String currentLine;

            while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
                
                String trimmedLine = currentLine.trim();
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close(); 
            reader.close();
            
        } catch(FileNotFoundException e){
            System.out.println("Error");
        }
    }
    
    public void completeHome(Record item) throws IOException{
        String workouts = item.getWorkout();
        ArrayList workout = new ArrayList();
        
        //seperate multiple workouts
        while(!workouts.equals("")){
            workout.add(workouts.substring(0,workouts.indexOf('+')));
            workouts = workouts.substring(workouts.indexOf('+')+1);
        }
        
        Stage stage = new Stage();
        stage.setTitle("Completed Workout");
        stage.setMaximized(true);

        Group root = new Group();
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("cmpsc390project/Styling.css");
        
        Label title = new Label("Select a workout");
        title.setLayoutX(50);
        title.setLayoutY(25);
        root.getChildren().add(title);
        
        ComboBox workoutBox = new ComboBox();
        for(int i = 0; i < workout.size(); ++i){
            workoutBox.getItems().add(workout.get(i));
        }
        workoutBox.setLayoutX(150);
        workoutBox.setLayoutY(25);
        root.getChildren().add(workoutBox);
        
        Label weight = new Label("Enter weight in lbs:");
        weight.setLayoutX(50);
        weight.setLayoutY(75);
        root.getChildren().add(weight);
        
        TextField weightInput = new TextField();
        weightInput.setLayoutX(200);
        weightInput.setLayoutY(75);
        root.getChildren().add(weightInput);
        
        Label reps = new Label("Enter number of reps:");
        reps.setLayoutX(50);
        reps.setLayoutY(125);
        root.getChildren().add(reps);
        
        TextField repInput = new TextField();
        repInput.setLayoutX(200);
        repInput.setLayoutY(125);
        root.getChildren().add(repInput);
        
        Label sets = new Label("Enter number of sets:");
        sets.setLayoutX(50);
        sets.setLayoutY(175);
        root.getChildren().add(sets);
        
        TextField setInput = new TextField();
        setInput.setLayoutX(200);
        setInput.setLayoutY(175);
        root.getChildren().add(setInput);
        
        Button submit = new Button("Submit");
        submit.setLayoutX(50);
        submit.setLayoutY(225);
        root.getChildren().add(submit);
        
        submit.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evt){
                String repVal = repInput.getText();
                String weightVal = weightInput.getText();
                String setVal = setInput.getText();
                
                //if makes sure the value is an int
                if(checkInt(repVal) == false || checkInt(weightVal) == false || checkInt(setVal) == false){
                    //create error code
                    Label error = new Label("Enter only numbers in the text boxes!");
                    error.setLayoutX(50);
                    error.setLayoutY(275);
                    root.getChildren().add(error);
                } else {
                    //preparation for file. If it doesn't exist it creates the file
                    File WorkoutInputF = new File("WorkoutInput.txt");
                    try{
                        if(!WorkoutInputF.exists()){
                        System.out.println("We had to make a new file.");
                        WorkoutInputF.createNewFile();
                        }

                        FileWriter fileWriter = new FileWriter(WorkoutInputF, true);

                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        bufferedWriter.write(workoutBox.getValue() + " " + weightVal + " " + repVal + " " + setVal + "\n");
                        bufferedWriter.close();
                    
                    } catch(IOException e) {
                        System.out.println("COULD NOT LOG!!");
                    }
                    //if there's only 1 item in the list then after submission we can delete the scheduled workout and return to the home page
                    if(workoutBox.getItems().size() == 1){
                        try {
                            deleteHomeWorkout(item);
                        } catch (IOException ex) {
                            System.out.println("Error deleting completed workout after loop");
                        }
                        stage.close();
                        Stage newStage = new Stage();
                        try {
                            start(newStage);
                        } catch (Exception ex) {
                            System.out.println("Error returning home after completing workout");
                        }
                        
                    
                    } else {
                        workoutBox.getItems().remove(workoutBox.getSelectionModel().getSelectedIndex());
                        workoutBox.getSelectionModel().clearSelection();
                    }
                }
            }

        });
        
        stage.setScene(scene);
        stage.show();
    }
    
    public boolean checkInt(String s){
        try{
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex){
            return false;
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        tableView.getItems().clear();
        tableView.getColumns().clear();
        dataList.clear();
        
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
    
    public void createModSchedPage(Record modify){
        Stage stage = new Stage();
        stage.setTitle("Modify Schedule Page");
        stage.setMaximized(true);

        Group root = new Group();
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("cmpsc390project/Styling.css");
        
        Button homePage = new Button();
        homePage.setText("Home Page");
        homePage.setMinWidth(100);
        root.getChildren().add(homePage);
        
        //Takes to mod schedule
        Button btn = new Button();
        btn.setText("Add Workout");
        btn.setLayoutX(100);
        btn.setLayoutY(0);
        btn.setMinWidth(100);
        root.getChildren().add(btn);
        
        //takes to list of workouts
        Button WorkoutBtn = new Button();
        WorkoutBtn.setText("Workout List");
        WorkoutBtn.setLayoutX(200);
        WorkoutBtn.setMinWidth(100);
        root.getChildren().add(WorkoutBtn);
        
        //button to stats
        Button StatBtn = new Button();
        StatBtn.setText("Statistics");
        StatBtn.setLayoutX(300);
        StatBtn.setMinWidth(100);
        root.getChildren().add(StatBtn);
        
        btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evt){ 
                stage.close();
                createModSchedPage(null);
            }

        });
        
        WorkoutBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evt){ 
                stage.close();
                createWorkoutPage();
            }

        });
        
        StatBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evt){ 
                stage.close();
                createStatPage();
            }

        });
        
        homePage.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evt){ 
                stage.close();
                createHomepage();
            }

        });
        
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
        
        if(modify!= null){
            LocalDate localDate = LocalDate.parse(modify.getDate());
            String tyme1 = modify.getTime().substring(0,modify.getTime().indexOf('-'));
            String tyme2 = modify.getTime().substring(modify.getTime().indexOf('-')+1);
            String AMPM1 = tyme1.substring(tyme1.length()-2);
            String AMPM2 = tyme2.substring(tyme2.length()-2);
            tyme1 = tyme1.substring(0,tyme1.length()-2);
            tyme2 = tyme2.substring(0,tyme2.length()-2);
            String givenWorkouts = modify.getWorkout();
            ArrayList workouts1 = new ArrayList();
            while(!givenWorkouts.equals("")){
                workouts1.add(givenWorkouts.substring(0,givenWorkouts.indexOf("+")));
                givenWorkouts = givenWorkouts.substring(givenWorkouts.indexOf("+")+1);
            }
            
            time1.getSelectionModel().select(tyme1);
            time2.getSelectionModel().select(tyme2);
            day.getSelectionModel().select(AMPM1);
            night.getSelectionModel().select(AMPM2);
            if(localDate.isBefore(LocalDate.now())){
                datePicker.setValue(LocalDate.now());
            } else {
                datePicker.setValue(localDate);
            }
            for(int i = 0; i < workouts1.size(); ++i){
                workouts.getSelectionModel().select(workouts1.get(i));
            }
        }
        
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
    }
    
}
