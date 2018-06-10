/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga_classscheduling;

import ga_classscheduling.domain.Room;
import ga_classscheduling.domain.Instructor;
import ga_classscheduling.domain.Course;
import ga_classscheduling.domain.Department;
import ga_classscheduling.domain.MeetingTime;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 *
 * @author Nanduni
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Data {

    private  static final String FILE_NAME = "College1.xlsx";
    private ArrayList<Room> rooms;
    private ArrayList<Instructor> instructors;
    private ArrayList<Course> courses;
    private ArrayList<Department> depts;
    private ArrayList<MeetingTime> meetingTimes;
    private int numberOfClasses=0;
    
    public Data(){initialize();}
    private Data initialize(){

        System.out.println("++++++++++++++++++++++++++++");


        FileInputStream excelFile = null;
        try {
            excelFile = new FileInputStream(new File(FILE_NAME));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Workbook workbook = null;
        try {
            workbook = new XSSFWorkbook(excelFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet datatypeSheet = workbook.getSheetAt(5);
        Sheet lecSheet = workbook.getSheetAt(0);
        Sheet coursesSheet = workbook.getSheetAt(1);
        Iterator<Row> iterator = datatypeSheet.iterator();
        rooms = new ArrayList<Room>();
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            if(currentRow.getRowNum() > 0){
                Iterator<Cell> cellIterator = currentRow.iterator();
                try {
                    Room room = new Room(currentRow.getCell(0).toString() , (int)currentRow.getCell(1).getNumericCellValue());
                    rooms.add(room);

                } catch (NullPointerException e){
                    e.printStackTrace();
                }

            }

        }

       /* Room room1 = new Room("R1",25);
        Room room2 = new Room("R2",45);
        Room room3 = new Room("R3",35);
        rooms = new ArrayList<Room>(Arrays.asList(room1,room2,room3));*/
        MeetingTime meetingTimeM1 = new MeetingTime("MMT1","M 09:00-10:00");
        MeetingTime meetingTimeM2 = new MeetingTime("MMT2","M 10:00-11:00");
        MeetingTime meetingTimeM3 = new MeetingTime("MMT3","M 11:00-12:30");
        MeetingTime meetingTimeM4 = new MeetingTime("MMT4","M 01:30-03:00");
        MeetingTime meetingTimeTu1 = new MeetingTime("TuT1","Tu 09:00-10:00");
        MeetingTime meetingTimeTu2 = new MeetingTime("TuT2","Tu 10:00-11:00");
        MeetingTime meetingTimeTu3 = new MeetingTime("TuT3","Tu 11:00-12:30");
        MeetingTime meetingTimeTu4 = new MeetingTime("TuT4","Tu :01:30-03:00");
        MeetingTime meetingTimeW1 = new MeetingTime("WT1","W 09:00-10:00");
        MeetingTime meetingTimeW2 = new MeetingTime("WT2","W 10:00-11:00");
        MeetingTime meetingTimeW3 = new MeetingTime("WT3","W 11:00-12:30");
        MeetingTime meetingTimeW4 = new MeetingTime("WT4","W 01:30-03:00");
        MeetingTime meetingTimeTh1 = new MeetingTime("ThT1","Th 09:00-10:00");
        MeetingTime meetingTimeTh2 = new MeetingTime("ThT2","Th 10:00-11:00");
        MeetingTime meetingTimeTh3 = new MeetingTime("ThT3","Th 11:00-12:30");
        MeetingTime meetingTimeTh4 = new MeetingTime("ThT4","Th 01:30-03:00");
        MeetingTime meetingTimeF1 = new MeetingTime("FT1","F 09:00-10:00");
        MeetingTime meetingTimeF2 = new MeetingTime("FT2","F 10:00-11:00");
        MeetingTime meetingTimeF3 = new MeetingTime("FT3","F 11:00-10:30");
        MeetingTime meetingTimeF4 = new MeetingTime("FT4","F 01:30-03:00");
        meetingTimes= new ArrayList<MeetingTime>(Arrays.asList(meetingTimeF1, meetingTimeF2 , meetingTimeF3 , meetingTimeF4,
                meetingTimeM1 , meetingTimeM2 , meetingTimeM3 , meetingTimeM4 , meetingTimeTh1 , meetingTimeTh2 , meetingTimeTh3, meetingTimeTh4,
                meetingTimeW1 , meetingTimeW2 , meetingTimeW3, meetingTimeW4 , meetingTimeTu1 , meetingTimeTu2 , meetingTimeTu3 , meetingTimeTu4));
        
        Instructor instructor1 = new Instructor("I1","Dr James Copper");
        Instructor instructor2 = new Instructor("I2","Dr. Michael Night");
        Instructor instructor3 = new Instructor("I3","Dr. Peiris");
        Instructor instructor4 = new Instructor("I3","Dr.De Silva");
        instructors = new ArrayList<Instructor>(Arrays.asList(instructor1,instructor2,instructor3,instructor4));
        Course course1 = new Course("C1","325K", new ArrayList<Instructor>(Arrays.asList(instructor1, instructor2)),25);
        Course course2 = new Course("C2","319K", new ArrayList<Instructor>(Arrays.asList(instructor1, instructor2,instructor3)),35);
        Course course3 = new Course("C3","462K", new ArrayList<Instructor>(Arrays.asList(instructor1, instructor2)),25);
        Course course4 = new Course("C4","464K", new ArrayList<Instructor>(Arrays.asList(instructor3, instructor4)),30);
        Course course5 = new Course("C5","360C", new ArrayList<Instructor>(Arrays.asList(instructor4)),35);
        Course course6 = new Course("C6","303K", new ArrayList<Instructor>(Arrays.asList(instructor1, instructor3)),45);
        Course course7 = new Course("C7","303L", new ArrayList<Instructor>(Arrays.asList(instructor2, instructor4)),45);
        courses = new ArrayList<Course>(Arrays.asList(course1,course2,course3,course4,course5,course6,course7));
        
        Department dept1= new Department("MATH",new ArrayList<Course>(Arrays.asList(course1, course3)));
        Department dept2= new Department("EE",new ArrayList<Course>(Arrays.asList(course2,course4, course5)));
        Department dept3= new Department("PHY",new ArrayList<Course>(Arrays.asList(course6, course7)));
        depts = new ArrayList<Department>(Arrays.asList(dept1,dept2,dept3));
        depts.forEach( x-> numberOfClasses += x.getCourses().size());
        
        return this;
    }
    
    
    public ArrayList<Room> getRooms(){return rooms;}
    public ArrayList<Instructor> getInstructors(){return instructors;}
    public ArrayList<Course> getCourse(){return courses;}
    public ArrayList<Department> getDepartment(){return depts;}
    public ArrayList<MeetingTime> getMeetingTime(){return meetingTimes;}
    public int getNumberOfClasses(){return this.numberOfClasses;}
}
