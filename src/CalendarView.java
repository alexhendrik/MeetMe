import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;


/**
 * This class is the primary initializing GUI class.
 */
public class CalendarView extends Application {
    AppManager manager;


    /**
     * This is the main starting method which creates the GUI and initializes AppManager.  It serves as the starting point for the entire software.
     * @param primaryStage  The main display screen.
     */
    @Override
    public void start(Stage primaryStage) {
        manager = new AppManager();
    primaryStage.setTitle("Meet.me");
    //StackPane stk = new StackPane();
    BorderPane border = new BorderPane();
    GridPane grid = new GridPane();
    VBox vbox = new VBox();
    vbox.setSpacing(10);
    grid.setPadding(new Insets(10,10,10,10));
    grid.setHgap(30);
    grid.setVgap(10);
     //TODO Tutorial messages


    //name label
        Label mondayLabel = createLabel("Monday");
        Label tuesdayLabel = createLabel("Tuesday");
        Label wednesdayLabel = createLabel("Wednesday");
        Label thursdayLabel = createLabel("Thursday");
        Label fridayLabel = createLabel("Friday");
        Label saturdayLabel = createLabel("Saturday");
        Label sundayLabel = createLabel("Sunday");

    GridPane.setConstraints(mondayLabel, 3, 0);
    GridPane.setConstraints(tuesdayLabel, 4, 0);
    GridPane.setConstraints(wednesdayLabel, 5, 0);
    GridPane.setConstraints(thursdayLabel, 6, 0);
    GridPane.setConstraints(fridayLabel, 7, 0);
    GridPane.setConstraints(saturdayLabel, 8, 0);
    GridPane.setConstraints(sundayLabel, 9, 0);



    Rectangle mondayRect =  createRect();
        Rectangle tuesdayRect = createRect();
        Rectangle wednesdayRect = createRect();
        Rectangle thursdayRect = createRect();
        Rectangle fridayRect = createRect();
        Rectangle saturdayRect = createRect();
        Rectangle sundayRect = createRect();


    Button newCustomEvent = createButton("Add Custom Event");
    Button resetSchedule = createButton("Reset Schedule");
    Button loadSchedule = createButton("Load Schedule");
    Button exitProgram = createButton("Exit");



    exitProgram.setOnAction(e -> {
        Platform.exit();
        System.exit(1);});

    loadSchedule.setOnAction(event -> {selectFileLoad();});//TODO Catch index out of bounds exception

       try{ resetSchedule.setOnAction(event -> {manager.resetState();}); } catch (NullPointerException e) {}
    newCustomEvent.setOnAction(event -> {newEvent();});//TODO Use the constructor to create a custom event from a window

    GridPane.setConstraints(mondayRect, 3, 1);
    GridPane.setConstraints(tuesdayRect, 4, 1);
    GridPane.setConstraints(wednesdayRect, 5, 1);
    GridPane.setConstraints(thursdayRect, 6, 1);
    GridPane.setConstraints(fridayRect, 7, 1);
    GridPane.setConstraints(saturdayRect, 8, 1);
    GridPane.setConstraints(sundayRect, 9, 1);


    border.setCenter(grid);
    border.setLeft(vbox);

		    
		    
		   /* Label passLabel = new Label("Password: ");
		    GridPane.setConstraints(passLabel, 0, 1);
		    
		    TextField passInput = new TextField();
		    passInput.setPromptText("password");
		    GridPane.setConstraints(passInput, 1, 1);
		    
		    Button loginButton = new Button("Log in");
		    GridPane.setConstraints(loginButton, 1, 2);
		    */
        grid.getChildren().addAll(mondayLabel, mondayRect, tuesdayRect, wednesdayRect, thursdayRect, fridayRect, saturdayRect, sundayRect,
                tuesdayLabel, wednesdayLabel, thursdayLabel, fridayLabel, saturdayLabel, sundayLabel);
        // grid.setGridLinesVisible(true);

    vbox.getChildren().addAll(newCustomEvent,resetSchedule,loadSchedule,exitProgram);

    Scene scene = new Scene(border, 1366, 768);

    primaryStage.setScene(scene);
        primaryStage.show();




    }


    /**
     * This is a generic constructor method for the rectangles in the GUI.
     * @return This returns the created rectangle.
     */

    public Rectangle createRect()	{

        Rectangle rect = new Rectangle();
        rect.setWidth(200);
        rect.setHeight(900);
        rect.setFill(Color.WHITE);
        return rect;




    }

    /**
     * This is a generic constructor method for a label.
     * @param day This attribute is used for the label text.
     * @return This returns the created label.
     */
    public Label createLabel(String day) {

        Label label = new Label(day);
        label.setFont(new Font("Arial", 40));

        return label;
    }

    /**
     * Generic constructor.
     */
    public CalendarView(){
    }

    /**
     * This method creates the file selection window and passes it to the AppManager.
     */

    public void selectFileLoad() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Schedule PDF File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Portable Document Format", "*.pdf"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            manager.loadSchedule(selectedFile);
        }
    }

    /**
     * This is an unused method for showing an error dialog if the user tries to initialize the schedule parser twice.
     */

    public void showSetupError() {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Illegal Action");
        alert.setHeaderText("Your Account is already set up!");
        alert.setContentText("Please reset your information before uploading a new schedule.");

        alert.showAndWait();

    }

    /**
     * This is a generic method constructor for a button.
     * @param buttonName The text to be displayed in the button.
     * @return The method returns the created button.
     */

    public Button createButton (String buttonName) {

        Button button = new Button(buttonName);
        button.setFont(new Font ("Arial", 16));

        return button;
    }

    /**
     * This method is called from the GUI to open the dialog menu to select the parameters of a new custom event.  The custom event is then added to the list of courses for the given user.
     */

    public void newEvent(){
        GridPane customPane = new GridPane();
        customPane.setVgap(10);
        Stage customStage = new Stage();
        customStage.setTitle("Create a Custom Event");
        customStage.setScene(new Scene(customPane, 450,450));

        customPane.setPadding(new Insets(10,10,10,10));

        Label courseLabel = new Label("Event Name:");
        Label startTimeLabel = new Label("Start Time:");
        Label endTimeLabel = new Label("End Time:");
        Label daysLabel = new Label("Days:");

        GridPane.setConstraints(courseLabel, 2, 1);
        GridPane.setConstraints(startTimeLabel, 2, 2);
        GridPane.setConstraints(endTimeLabel, 2, 3);
        GridPane.setConstraints(daysLabel, 2, 4);

        TextField courseBox = new TextField("e.g. Baseball Practice, Sleep, ...");
        TextField startTimeBox = new TextField("e.g. 9:00 AM, 1:00 PM, ...");
        TextField endTimeBox = new TextField("e.g. 9:00 AM, 1:00 PM, ...");

        GridPane.setConstraints(courseBox, 4, 1);
        GridPane.setConstraints(startTimeBox, 4, 2);
        GridPane.setConstraints(endTimeBox, 4, 3);

        CheckBox monCheck = new CheckBox("Monday");
        CheckBox tueCheck = new CheckBox("Tuesday");
        CheckBox wedCheck = new CheckBox("Wednesday");
        CheckBox thuCheck = new CheckBox("Thursday");
        CheckBox friCheck = new CheckBox("Friday");
        CheckBox satCheck = new CheckBox("Saturday");
        CheckBox sunCheck = new CheckBox("Sunday");

        GridPane.setConstraints(monCheck, 4, 4);
        GridPane.setConstraints(tueCheck, 4, 5);
        GridPane.setConstraints(wedCheck, 4, 6);
        GridPane.setConstraints(thuCheck, 4, 7);
        GridPane.setConstraints(friCheck, 4, 8);
        GridPane.setConstraints(satCheck, 4, 9);
        GridPane.setConstraints(sunCheck, 4, 10);

        ArrayList<CheckBox> checkBoxes = new ArrayList<>();
        checkBoxes.add(monCheck);
        checkBoxes.add(tueCheck);
        checkBoxes.add(wedCheck);
        checkBoxes.add(thuCheck);
        checkBoxes.add(friCheck);
        checkBoxes.add(satCheck);
        checkBoxes.add(sunCheck);

        Button confirmButton = new Button("Submit");

        GridPane.setConstraints(confirmButton, 4, 12);

        customPane.getChildren().addAll(courseLabel, startTimeLabel, endTimeLabel, daysLabel);
        customPane.getChildren().addAll(courseBox, startTimeBox, endTimeBox, monCheck, tueCheck, wedCheck, thuCheck, friCheck, satCheck, sunCheck, confirmButton);

        customStage.show();


        /**
         * This handle opens a dialog window for the user to input the information of their custom event and creates it as a course object within the list of the user's courses.
         */
        confirmButton.setOnAction(event -> {
        ArrayList<String> days = new ArrayList<>();
        String name;
        String startTime;
        String endTime;

        //TODO This is pretty bad

         if(monCheck.isSelected()){ days.add("Monday"); }
         if(tueCheck.isSelected()){ days.add("Tuesday"); }
         if(wedCheck.isSelected()){ days.add("Wednesday"); }
         if(thuCheck.isSelected()){ days.add("Thursday"); }
         if(friCheck.isSelected()){ days.add("Friday"); }
         if(satCheck.isSelected()){ days.add("Saturday"); }
         if(sunCheck.isSelected()){ days.add("Sunday"); }

         name = courseBox.getText();
         startTime = startTimeBox.getText();
         endTime = endTimeBox.getText();

         Course customEvent = new Course(name, startTime, endTime, days);

         manager.userCourseList.add(customEvent);

         System.out.println("A custom event named " + customEvent.courseID + " was created and will take place between " + customEvent.Time.get(0) + " and " + customEvent.Time.get(1));

        customStage.hide();

        });


    }



}