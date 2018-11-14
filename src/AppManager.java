import javafx.application.Application;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * This is the main class that launches the PDF parsing algorithm and is initialized by the GUI.
 */

public class AppManager {

    public boolean isSetup = false;
    Loader loader;

    CalendarView gui;

    public ArrayList<Course> userCourseList = new ArrayList<Course>();
    public ArrayList<Course> groupCourseList = new ArrayList<>();

    String userName;
    String userID;

    File schedulePDF;


    int minimumLength = 1;


    /**
     * This method is called by the GUI to initialize an instance of the Loader class and proceed with identifying the user's schedule.
     * @param file This file is the PDF schedule passed in from the file selector window.
     * @throws IndexOutOfBoundsException Exception thrown in case the software is already set up and needs to be reset prior to usage.
     */

    public void loadSchedule(File file) throws IndexOutOfBoundsException{

            if (isSetup == true) {
                throw new IndexOutOfBoundsException("The software has already been activated, please reset it if you want to change the schedule");
            }
            loader = new Loader(file);
            //File file = new File("C:\\Users\\Alexh\\Desktop\\EclipseWorkspace\\SE 300\\ER_SCHED_PRT.pdf");
            userCourseList = loader.getCourseList();
            userName = loader.getUserName();
            userID = loader.getUserID();
            System.out.println("The student ID number is " + userID);
            System.out.println("The student's name is " + userName);
            for (Course x : userCourseList){
                x.timeConvert();
                //System.out.println("The military time is :" + x.startTime + " " + x.endTime);
            }

            findCommonTime(userCourseList);

            isSetup = true;

    }


    /**
     * This method is used to start the process of resetting the software settings to default.
     */
    public void resetState(){
        userCourseList.clear();
        groupCourseList.clear();
        userID = "";
        userName = "";
        loader.reset();
        System.out.println("The program status has been reset");
    }

    public void findCommonTime(ArrayList<Course> courseList){
        int [][] timeTable = new int[7][24];

        for (Course course : courseList){

            int startCoef;
            int midCoef = 0;
            int endCoef;

            startCoef = course.startTime / 100;
            endCoef = course.endTime / 100;
            if (endCoef - startCoef > 1){
                midCoef = (endCoef + startCoef)/2;
            }

            for (String day : course.Days){
                switch (day){
                    case "Monday": timeTable[0][startCoef] = 1;
                        if (midCoef != 0){timeTable[0][midCoef] = 1;}
                        timeTable[0][endCoef] = 1; break;
                    case "Tuesday":timeTable[1][startCoef] = 1;
                        if (midCoef != 0){timeTable[1][midCoef] = 1;}
                        timeTable[1][endCoef] = 1; break;
                    case "Wednesday":timeTable[2][startCoef] = 1;
                        if (midCoef != 0){timeTable[2][midCoef] = 1;}
                        timeTable[2][endCoef] = 1; break;
                    case "Thursday":timeTable[3][startCoef] = 1;
                        if (midCoef != 0){timeTable[3][midCoef] = 1;}
                        timeTable[3][endCoef] = 1; break;
                    case "Friday":timeTable[4][startCoef] = 1;
                        if (midCoef != 0){timeTable[4][midCoef] = 1;}
                        timeTable[4][endCoef] = 1; break;
                    case "Saturday":timeTable[5][startCoef] = 1;
                        if (midCoef != 0){timeTable[5][midCoef] = 1;}
                        timeTable[5][endCoef] = 1; break;
                    case "Sunday":timeTable[6][startCoef] = 1;
                        if (midCoef != 0){timeTable[6][midCoef] = 1;}
                        timeTable[6][endCoef] = 1; break;
                    default: break;
                }
            }

        }

        for (int[] x : timeTable)
        {

            for (int y : x)
            {
                System.out.print(y + " ");
            }
            System.out.println();
        }

        //return new Course("blank", 1);
    }



}
