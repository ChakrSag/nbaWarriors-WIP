package com.qa.nbaWarriors.Reporting;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManagerAssociateFunctions {
    /**
     * Function to return the Current date as a string
     * @return
     */
    public static String getCurrentDate(){
        DateFormat dateFormat=new SimpleDateFormat("MM-dd-yyyy");
        Date date=new Date();
        String dateString = dateFormat.format(date);
        //System.out.println("****************Sagar**************");
        return dateString;
    }

    /**
     * function to create directory
     * @param directoryName
     * @return
     */
    public static File createDirectory(String directoryName){

        File directoryfortheDay=new File(directoryName);

        if (!directoryfortheDay.exists()) {
            directoryfortheDay.mkdir();

            //System.out.println("****************Created*****************");
        }

        else{
            /**
             * Do Nothing
             */
            //System.out.println("****************Already created*****************");
        }

        return directoryfortheDay;
    }

    /**
     * Function to return the Current date time stamp as a string
     * @return
     */
    public static String getCurrentDateandTime(){
        DateFormat dateFormat=new SimpleDateFormat("MM-dd-yyyy_hh.mm.ss a");
        Date date=new Date();
        String dateString = dateFormat.format(date);

        return dateString;
    }
}
