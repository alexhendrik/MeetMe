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
        int [][] timeTable = new int[24][7];

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
                    case "Monday": timeTable[startCoef][0] = 1;
                        if (midCoef != 0){timeTable[midCoef][0] = 1;}
                        timeTable[endCoef][0] = 1; break;
                    case "Tuesday":timeTable[startCoef][1] = 1;
                        if (midCoef != 0){timeTable[midCoef][1] = 1;}
                        timeTable[endCoef][1] = 1; break;
                    case "Wednesday":timeTable[startCoef][2] = 1;
                        if (midCoef != 0){timeTable[midCoef][2] = 1;}
                        timeTable[endCoef][2] = 1; break;
                    case "Thursday":timeTable[startCoef][3] = 1;
                        if (midCoef != 0){timeTable[midCoef][3] = 1;}
                        timeTable[endCoef][3] = 1; break;
                    case "Friday":timeTable[startCoef][4] = 1;
                        if (midCoef != 0){timeTable[midCoef][4] = 1;}
                        timeTable[endCoef][4] = 1; break;
                    case "Saturday":timeTable[startCoef][5] = 1;
                        if (midCoef != 0){timeTable[midCoef][5] = 1;}
                        timeTable[endCoef][5] = 1; break;
                    case "Sunday":timeTable[startCoef][6] = 1;
                        if (midCoef != 0){timeTable[midCoef][6] = 1;}
                        timeTable[endCoef][6] = 1; break;
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
