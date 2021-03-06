package cmpsc390project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.time.Clock.system;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
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
    
    public void createStatPage() throws NumberFormatException, IOException{
        try {
			
    		Stage stage = new Stage();
    		
			
			Text t = new Text();
			t.setText("Your Stats");
			t.setX(820); 
			t.setY(50);
			
                        ArrayList<entry> squats = new ArrayList<>();
                        ArrayList<entry> deadlift = new ArrayList<>();
                        
			File workouts = new File("WorkoutInput.txt");
			String FieldDelimiter = ",";
                        BufferedReader reader = new BufferedReader(new FileReader(workouts));
                        String line;
                        
                        
			while ((line = reader.readLine()) != null)
			{String[] fields = line.split(FieldDelimiter,-2);
			entry temp = new entry(fields[0],Integer.parseInt(fields[1]),Integer.parseInt(fields[2]),Integer.parseInt(fields[3]));
                        
                        
                        switch(temp.getName()){
                            case "Squat":
                                squats.add(temp);
                                break;
                            case "Barbell Deadlift":
                                deadlift.add(temp);
                                break;
                            default:
                                break;
                        }
                        }
			
			final NumberAxis xAxis = new NumberAxis();
                        final NumberAxis yAxis = new NumberAxis();
                        xAxis.setLabel("Last 10 Workouts");
                        yAxis.setLabel("1RM");
                        final LineChart<Number,Number> lineChart = 
                        new LineChart<Number,Number>(xAxis,yAxis);
	                
	        lineChart.setTitle("Progress");
			lineChart.setLayoutY(30);
	        
	        XYChart.Series series = new XYChart.Series();
	        series.setName("Squats");
                
	        int index = 0;
                
                if(squats.size()>10){
                    index = squats.size()-10;
                } else {
                    while(squats.size()<10){
                        squats.add(new entry());
                    }
                }
                
                for(int i  = 0; i < 10; i++){
                    series.getData().add(new XYChart.Data(i, squats.get(index).calculate1RM()));
                    index++;
                }
                
                index = 0;
                
                if(deadlift.size()>10){
                    index = deadlift.size()-10;
                } else {
                    while(deadlift.size() < 10){
                        deadlift.add(new entry());
                    }
                }
	        
	        XYChart.Series series2 = new XYChart.Series();
	        series2.setName("Deadlift");
                
                for(int i  = 0; i < 10; i++){
                    series2.getData().add(new XYChart.Data(i, deadlift.get(index).calculate1RM()));
                    index++;
                }
	        
	        lineChart.getData().add(series);
	        lineChart.getData().add(series2);
                
                ArrayList shoulderA = new ArrayList();
                ArrayList legA = new ArrayList();
                ArrayList backA = new ArrayList();
                
                File myObj = new File("input.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String workout = myReader.nextLine();
                    String type = myReader.nextLine();
                    switch(type){
                        case "S":
                            shoulderA.add(workout);
                            break;
                        case "B":
                            backA.add(workout);
                            break;
                        case "L":
                            legA.add(workout);
                            break;
                        default:
                            break;
                    }
                    myReader.nextLine();
                }
                myReader.close();
                
                    TableView workouts1 = new TableView();
                    TableColumn<String, cell> column1 = new TableColumn<>("Name");
		    column1.setCellValueFactory(new PropertyValueFactory<>("name"));
		    TableColumn<String, cell> column2 = new TableColumn<>("Amount");
		    column2.setCellValueFactory(new PropertyValueFactory<>("amount"));
		    workouts1.getColumns().add(column1);
		    workouts1.getColumns().add(column2);
                    
                    for(int i = 0; i < shoulderA.size(); ++i){
                        workouts1.getItems().add(new cell((String)shoulderA.get(i)));
                    }
			
			TableView workouts2 = new TableView();
			TableColumn<String, cell> column3 = new TableColumn<>("Name");
		    column3.setCellValueFactory(new PropertyValueFactory<>("name"));
		    TableColumn<String, cell> column4 = new TableColumn<>("Amount");
		    column4.setCellValueFactory(new PropertyValueFactory<>("amount"));
		    workouts2.getColumns().add(column3);
		    workouts2.getColumns().add(column4);
                    
                    for(int i = 0; i < legA.size(); ++i){
                        workouts2.getItems().add(new cell((String)legA.get(i)));
                    }
			
			TableView workouts3 = new TableView();
			TableColumn<String, cell> column5 = new TableColumn<>("Name");
		    column5.setCellValueFactory(new PropertyValueFactory<>("name"));
		    TableColumn<String, cell> column6 = new TableColumn<>("Amount");
		    column6.setCellValueFactory(new PropertyValueFactory<>("amount"));
		    workouts3.getColumns().add(column5);
		    workouts3.getColumns().add(column6);
                    
                    for(int i = 0; i < backA.size(); ++i){
                        workouts3.getItems().add(new cell((String)backA.get(i)));
                    }
		    
                    TitledPane shoulder = new TitledPane("Shoulder", workouts1);
                    shoulder.setLayoutX(500);
		    shoulder.setLayoutY(65);
		    TitledPane leg = new TitledPane("Leg", workouts2);
                    leg.setLayoutX(750);
		    leg.setLayoutY(65);
		    TitledPane back = new TitledPane("Back", workouts3);
                    back.setLayoutX(1000);
		    back.setLayoutY(65);
			
                    
        
        Button homePage = new Button();
        homePage.setText("Home Page");
        homePage.setMinWidth(100);
        
        //Takes to mod schedule
        Button btn = new Button();
        btn.setText("Add Workout");
        btn.setLayoutX(100);
        btn.setLayoutY(0);
        btn.setMinWidth(100);
        
        //takes to list of workouts
        Button WorkoutBtn = new Button();
        WorkoutBtn.setText("Workout List");
        WorkoutBtn.setLayoutX(200);
        WorkoutBtn.setMinWidth(100);
        
        //button to stats
        Button StatBtn = new Button();
        StatBtn.setText("Statistics");
        StatBtn.setLayoutX(300);
        StatBtn.setMinWidth(100);
        
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
                try {
                    createWorkoutPage();
                } catch (FileNotFoundException ex) {
                    
                }
            }

        });
        
        StatBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evt){ 
                stage.close();
                try {
                    createStatPage();
                } catch (IOException ex) {
                    Logger.getLogger(Cmpsc390Project.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        
        homePage.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evt){ 
                stage.close();
                try {
                    createHomepage();
                } catch (IOException ex) {
                    Logger.getLogger(Cmpsc390Project.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
			
		    Group root = new Group(t,shoulder,leg,back,lineChart,workouts1,homePage, StatBtn,WorkoutBtn,btn);
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add("cmpsc390project/Styling.css");
                        
                        
			stage.setScene(scene);
                        stage.setTitle("Stats");
                        
			stage.show();
			stage.setMaximized(true);
			
		
        } catch(Exception e) {
            e.printStackTrace();
	}
    }
    
    public void createWorkoutPage() throws FileNotFoundException{
        
        Stage stage = new Stage();
        stage.setMaximized(true);
        
        File data = new File("input.txt");
        Scanner read = new Scanner(data);
        
        
        Button btn = new Button();
        btn.setTranslateX(300);
        btn.setTranslateY(150);
        Button btn1 = new Button();
        btn1.setTranslateX(300);
        btn1.setTranslateY(-150);
        Rectangle backdrop = new Rectangle();
        backdrop.setHeight(500);
        backdrop.setWidth(1000);
        backdrop.setTranslateX(0);
        backdrop.setTranslateY(0);
        backdrop.setFill(Color.PINK);
        
        btn.setText("Go back");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                stage.close();
                try {
                    createHomepage();
                } catch (IOException ex) {
                    Logger.getLogger(Cmpsc390Project.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        btn1.setText("Add Workout");
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                stage.close();
                try {
                    addWorkout(data);
                } catch (IOException e) {
                    
                }
            }
        });
        
        List <Workout> sExcer = new ArrayList<>();
        Workout sPlaceholder = new Workout("Shoulder Excercies", "S", "Make sure to select your favorite shoulder workout!");
        sExcer.add(sPlaceholder);
        List <Workout> lExcer = new ArrayList<>();
        Workout lPlaceholder = new Workout("Leg Excercies", "L", "Make sure to select your favorite leg workout!");
        lExcer.add(lPlaceholder);
        List <Workout> bExcer = new ArrayList<>();
        Workout bPlaceholder = new Workout("Back Excercies", "B", "Make sure to select your favorite back workout!");
        bExcer.add(bPlaceholder);
        
        while(read.hasNext())
        {
            Workout temp = new Workout (read.nextLine(), read.nextLine(), read.nextLine());
            
            switch (temp.getBodyClass())
            {
                case "S":
                    sExcer.add(temp);
                    break;
                case "L":
                    lExcer.add(temp);
                    break;
                case "B":
                    bExcer.add(temp);
                    break;
                default:
                    System.out.println("Error");
                    break;
            }
        }
        
        ChoiceBox <Workout> sChoice = new ChoiceBox();
        sChoice.getItems().addAll(sExcer);
        sChoice.setTranslateX(-400);
        sChoice.setTranslateY(-200);
        sChoice.setValue(sPlaceholder);
        
        
        ChoiceBox <Workout> lChoice = new ChoiceBox();
        lChoice.getItems().addAll(lExcer);
        lChoice.setTranslateX(-365);
        lChoice.setTranslateY(0);
        lChoice.setValue(lPlaceholder);
        
        ChoiceBox <Workout> bChoice = new ChoiceBox();
        bChoice.getItems().addAll(bExcer);
        bChoice.setTranslateX(-385);
        bChoice.setTranslateY(200);
        bChoice.setValue(bPlaceholder);
        
        ImageView animation= new ImageView(new Image("pushUp.gif"));
        animation.setTranslateY(-50);
        
        
        Rectangle WOtext = new Rectangle();
        Text title = new Text("Select an Exercise to view!");
        title.setTranslateX(0);
        title.setTranslateY(-180);
        title.setFill(Color.BROWN);
        
        
        Rectangle WOinstruction = new Rectangle();
        Text instr = new Text("Using the menus on the left side, navigate to an exercise you'd"
                + " like to view!");
        instr.setTranslateX(0);
        instr.setTranslateY(120);
        instr.setWrappingWidth(200);
        instr.setFill(Color.BROWN);
        
        
        StackPane root = new StackPane();
        
        root.getChildren().add(backdrop);
        root.getChildren().addAll(btn, btn1);
        root.getChildren().add(animation);
        root.getChildren().add(title);
        root.getChildren().add(instr);
        root.getChildren().addAll(sChoice, lChoice, bChoice);
        sChoice.getSelectionModel().selectedItemProperty().addListener((v,old, New )-> swap(root, New, title, instr));
        lChoice.getSelectionModel().selectedItemProperty().addListener((v,old, New )-> swap(root, New, title, instr));
        bChoice.getSelectionModel().selectedItemProperty().addListener((v,old, New )-> swap(root, New, title, instr));
        
        Button homePage = new Button();
        homePage.setText("Home Page");
        homePage.setStyle("-fx-background-color: #730b6b");
        homePage.setTranslateX(-555);
        homePage.setTranslateY(-315);
        homePage.setMinWidth(100);
        root.getChildren().add(homePage);
        
        //takes to list of workouts
        Button WorkoutBtn = new Button();
        WorkoutBtn.setText("Workout List");
        WorkoutBtn.setStyle("-fx-background-color: #e84f64");

        WorkoutBtn.setTranslateX(200);
        WorkoutBtn.setMinWidth(100);
        WorkoutBtn.setTranslateX(-355);
        WorkoutBtn.setTranslateY(-315);        
        root.getChildren().add(WorkoutBtn);
        //MAIN NAV
        //button to stats
        Button StatBtn = new Button();
        StatBtn.setStyle("-fx-background-color: #ff7a8c");

        StatBtn.setText("Statistics");
        StatBtn.setTranslateX(-255);
        StatBtn.setTranslateY(-315);   
        StatBtn.setMinWidth(100);
        root.getChildren().add(StatBtn);
        
        Button nice= new Button();
        nice.setStyle("-fx-background-color: #A62662");
        nice.setText("Add Workout");
        nice.setTranslateX(-455);
        nice.setTranslateY(-315);  
        nice.setMinWidth(100);
        root.getChildren().add(nice);
        
        WorkoutBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evt){ 
                stage.close();
                try {
                    createWorkoutPage();
                } catch (FileNotFoundException ex) {
                    
                }
            }

        });
        
        nice.setText("Add Workout");
        nice.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent evt){ 
                stage.close();
                createModSchedPage(null);
            }
        });
        
        StatBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evt){ 
                stage.close();
                try {
                    createStatPage();
                }catch (IOException ex) {
                    Logger.getLogger(Cmpsc390Project.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        
        homePage.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evt){ 
                stage.close();
                try {
                    createHomepage();
                } catch (IOException ex) {
                    Logger.getLogger(Cmpsc390Project.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        Scene scene = new Scene(root, 300, 500);
        stage.setScene(scene);
        stage.show();
        
    }
    
    public void createHomepage() throws IOException{
        Stage stage = new Stage();
        stage.setTitle("Home Page");
        stage.setMaximized(true);
        
        Group root = new Group();
        Accordion accordion = new Accordion();
        accordion.setLayoutX(320);
        accordion.setLayoutY(100);
        TableView stats = new TableView();
        TableColumn<String, position> column1 = new TableColumn<>("Excercise");
        column1.setCellValueFactory(new PropertyValueFactory<>("excercise"));
        TableColumn<String, position> column2 = new TableColumn<>("Amount");
        column2.setCellValueFactory(new PropertyValueFactory<>("amount"));
        stats.getColumns().add(column1);
        stats.getColumns().add(column2);
        root.getChildren().add(stats);
       
        TitledPane faststats = new TitledPane("Your Favorites", stats);
        accordion.getPanes().add(faststats);

        faststats.setLayoutX(320);
        faststats.setLayoutY(100);
        //fixes the Favorite's Table's row size to 3
        stats.setFixedCellSize(25);
        stats.prefHeightProperty().bind(Bindings.size(stats.getItems()).multiply(stats.getFixedCellSize()).add(30));
        stats.prefWidthProperty().bind(Bindings.size(stats.getItems()).multiply(stats.getFixedCellSize()).add(60));
        

   for (int i = 1; i < 4; i ++)
   {stats.getItems().add(new position(i));}
   
    //1RM Stats
        TabPane tabPane = new TabPane();
        TitledPane oneRepMax = new TitledPane("1RM Stats", tabPane);
        accordion.getPanes().add(oneRepMax);
        oneRepMax.setLayoutX(320);
        oneRepMax.setLayoutY(250);
        
        double[] returns = createStatRecord("Squat");
        
        //Squats Tab
        Text squatInfo = new Text("Highest 1RM: " + returns[0] + "\n" + "Latest 1RM: " + returns[1]);
        // Create the VBox
        VBox squats = new VBox(2);
        squats.setId("Squats 1RM");
        squats.setSpacing(5);
        // Add the Text Nodes to the VBox
        squats.getChildren().add(squatInfo);    
        root.getChildren().add(squats);
        Tab squat = new Tab("Squats", squats);
        
        returns = createStatRecord("Bench");
        
        //Bench Tab
        Text benchInfo = new Text("Highest 1RM: " + returns[0] + "\n" + "Latest 1RM: " + returns[1]);
        VBox benches = new VBox(2); 
        benches.setId("Bench 1RM");
        benches.setSpacing(5);
        benches.getChildren().add(benchInfo);
        root.getChildren().add(benches);
        Tab bench = new Tab("Benches", benches);
        
        returns = createStatRecord("Sit-ups");
        
        //Deadlift Tab
        Text situpInfo = new Text("Highest 1RM: " + returns[0] + "\n" + "Latest 1RM: " + returns[1]);
        VBox situps = new VBox(2);
        situps.setId("Sit-up 1RM");
        situps.setSpacing(5);
        situps.getChildren().add(situpInfo);
        root.getChildren().add(situps);
        Tab situpTab = new Tab("Sit-up", situps);
        
        //add all three tabs to the tabpane
        tabPane.getTabs().add(squat);
        tabPane.getTabs().add(bench);
        tabPane.getTabs().add(situpTab);
        
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        
        root.getChildren().add(accordion);
        

        stage.setTitle("Home Page");
        stage.setMaximized(true);
       
        
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
                try {
                    createWorkoutPage();
                } catch (FileNotFoundException ex) {
                    
                }
            }

        });
        
        StatBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evt){ 
                stage.close();
                try {
                    createStatPage();
                } catch (IOException ex) {
                    Logger.getLogger(Cmpsc390Project.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }); 
        
        homePage.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evt){ 
                stage.close();
                try {
                    createHomepage();
                } catch (IOException ex) {
                    Logger.getLogger(Cmpsc390Project.class.getName()).log(Level.SEVERE, null, ex);
                }
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
    
    //create the stat record and sort
    //this method is called in the tab area of createHomepage
    //String name is the workout of each tab
    public double[] createStatRecord(String name) throws FileNotFoundException, IOException{
        File workoutStats = new File("workoutInput.txt");
        BufferedReader bf = new BufferedReader(new FileReader(workoutStats));
        double[] output = new double[2];
        
        //read file
            File data = new File("WorkoutInput.txt");
            String FieldDelimiter = ",";
            BufferedReader reader = new BufferedReader(new FileReader(data));
            String line;
            
            entry last = new entry();
            entry biggest = new entry();
            //read file and increment each time the workout repeats
            while ((line = reader.readLine()) != null){
                String[] fields = line.split(FieldDelimiter,-2);
                entry input = new entry(fields[0],Integer.parseInt(fields[1]),Integer.parseInt(fields[2]),Integer.parseInt(fields[3]));
            
                
                if(input.getName().equals(name)){
                    if(input.compare1RM(biggest)>0){
                        biggest = input;
                    }
                    last = input;
                }
                
            }
            
            
            bf.close();
            
            output[0] = biggest.calculate1RM();
            output[1] = last.calculate1RM();
            //return the biggest 1rm of that particular record
            return output;
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

                        try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                            bufferedWriter.write(workoutBox.getValue() + "," + weightVal + "," + repVal + "," + setVal + "\n");
                        }
                      
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
                try {
                    createWorkoutPage();
                } catch (FileNotFoundException ex) {
                    
                }
            }

        });
        
        StatBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evt){ 
                stage.close();
                try {
                    createStatPage();
                } catch (IOException ex) {
                    Logger.getLogger(Cmpsc390Project.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        
        homePage.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent evt){ 
                stage.close();
                try {
                    createHomepage();
                } catch (IOException ex) {
                    Logger.getLogger(Cmpsc390Project.class.getName()).log(Level.SEVERE, null, ex);
                }
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
        datePicker.setEditable(false);
        
        //basic datepicker setup
        datePicker.setDayCellFactory(dayCellFactory);
        HBox date = new HBox(new Label("Date: "), datePicker);
        date.setLayoutX(350);
        date.setLayoutY(100);
        root.getChildren().add(date);
        
        ArrayList workoutList = new ArrayList();
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String workout = myReader.nextLine();
                workoutList.add(workout);
                myReader.nextLine();
                myReader.nextLine();
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
                    try {
                        createHomepage();
                    } catch (IOException ex) {
                        Logger.getLogger(Cmpsc390Project.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                }
            }

        });
    }
    
    public void swap(StackPane s, Workout w, Text t, Text i)
    {
        s.getChildren().remove(t);
        s.getChildren().remove(i);
        t.setText(w.getTitle());
        i.setText(w.getInstr());
        s.getChildren().add(t);
        s.getChildren().add(i);
    }
    
    public void addWorkout(File f) throws IOException
    {
        Stage stage = new Stage();
        stage.setMaximized(true);
        
        Rectangle backdrop = new Rectangle();
        backdrop.setHeight(500);
        backdrop.setWidth(1000);
        backdrop.setTranslateX(0);
        backdrop.setTranslateY(0);
        backdrop.setFill(Color.PINK);
        
        Text title = new Text("Adding an Excercise");
        title.setFont(Font.font ("Verdana", 40));
        title.setTranslateX(0);
        title.setTranslateY(-180);
        title.setFill(Color.BLUEVIOLET);
        
        TextField wName = new TextField();
        wName.setPromptText("Name of Workout");
        wName.setMaxWidth(300);
        
        ChoiceBox <String> bodyPart = new ChoiceBox();
        bodyPart.getItems().addAll("Pick a Body Part of Focus", "Shoulder", "Legs", "Back");
        bodyPart.setValue("Pick a Body Part of Focus");
        bodyPart.setTranslateY(30);
        
        TextField wInstr = new TextField();
        wInstr.setPromptText("How to do the Workout");
        wInstr.setMinHeight(100);
        wInstr.setMaxWidth(300);

        wInstr.setTranslateY(bodyPart.getTranslateY()+70);
        
        
        Text emptyField = new Text("One or both of the text boxes have been left blank. Please make sure you've"
                + " filled them out throughly.");
        emptyField.setFont(Font.font ("Verdana", 15));
        emptyField.setWrappingWidth(200);
        emptyField.setTranslateX(-300);
        emptyField.setTranslateY(wInstr.getTranslateY()+25);
        emptyField.setFill(Color.RED); 
        emptyField.setOpacity(0);
        
        Text invalidBodyPart = new Text("You did not select one of the appropriate options given to you"
                + "for the body part in the drop down menu.");
        invalidBodyPart.setFont(Font.font ("Verdana", 15));
        invalidBodyPart.setWrappingWidth(200);
        invalidBodyPart.setTranslateX(300);
        invalidBodyPart.setTranslateY(wInstr.getTranslateY()+25);
        invalidBodyPart.setFill(Color.RED); 
        invalidBodyPart.setOpacity(0);
        
        
        
        Button cancel = new Button();
        cancel.setTranslateX(150);
        cancel.setTranslateY(200);
        cancel.setText("Cancel");
        cancel.setOnAction(new EventHandler<ActionEvent>() {    
            @Override
            public void handle(ActionEvent event) {
                stage.close();
                try {
                    createWorkoutPage();
                } catch (FileNotFoundException e) {

                }
            }
        });
        
        Button submit = new Button();
        submit.setTranslateX(-150);
        submit.setTranslateY(200);
        submit.setText("Submit");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
               if (wName.getText().equals("") || wInstr.getText().equals("") || "Pick a Body Part of Focus".equals(bodyPart.getValue()))
               {
                    if (wName.getText().equals("") || wInstr.getText().equals(""))
                    {
                        emptyField.setOpacity(100);
                    }
                    if ("Pick a Body Part of Focus".equals(bodyPart.getValue()))
                    {
                        invalidBodyPart.setOpacity(100);
                    }
               }
               
               else
               {
                   
                FileWriter myWriter;
                   try {
                       myWriter = new FileWriter(f, true);
                       myWriter.write("\n" + wName.getText());
                       
                       switch(bodyPart.getValue())
                       {
                           case "Shoulder":
                            myWriter.write("\nS");
                            break;
                           case "Legs":
                            myWriter.write("\nL");
                            break;
                           case "Back":
                            myWriter.write("\nB");
                            break;
                       }
                       myWriter.write("\n" + wInstr.getText());
                       myWriter.close();
                   } catch (IOException ex) {
                       Logger.getLogger(Cmpsc390Project.class.getName()).log(Level.SEVERE, null, ex);
                   }
                stage.close();
                try {
                    createWorkoutPage();
                } catch (FileNotFoundException e) {
                   
                }
               }
               
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(backdrop);
        root.getChildren().addAll(cancel, submit);
        root.getChildren().add(title);
        root.getChildren().addAll(wName, wInstr, bodyPart);
        root.getChildren().addAll(emptyField, invalidBodyPart);
       
        Scene scene = new Scene(root, 300, 500);
        
        stage.setScene(scene);
        stage.show();

    }
}
