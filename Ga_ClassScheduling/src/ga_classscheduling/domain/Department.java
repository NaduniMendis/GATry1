/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga_classscheduling.domain;

import java.util.ArrayList;

/**
 *
 * @author Nanduni
 */
public class Department {
    private String name;
    private ArrayList<Course> courses;
    public Department(String name,ArrayList<Course> courses){
        this.name=name;
        this.courses=courses;
    
    }
    public String getName(){ return name;}
    public ArrayList<Course> getCourses(){ return courses;}
    
   
    
}
