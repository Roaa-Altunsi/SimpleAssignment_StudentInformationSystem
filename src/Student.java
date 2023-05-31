/*
Name: Roaa Hatim Altunsi ,, ID: 1914946
Class: IAR  ,, Subject: CPCS-203
*/
public class Student {
    // Data Fields
    private String studName;
    private String studID;
    private float studGPA;
    private double studGrade;
    private static int studentsCounter; //**
    
    // Constructors
    public Student(){
        studentsCounter++;
    }
    
    public Student(String studName, String studID, float studGPA){
        this.studName = studName;
        this.studID = studID;
        this.studGPA = studGPA;
        studentsCounter++;
    }
    
    // Getters & Setters & Service Methods
    public String GetStudName(){
        return studName;
    }
    
    public String GetStudID(){
        return studID;
    }
    
    public float GetStudGPA(){
        return studGPA;
    }
    
    public double GetStudGrade(){ //**
        return studGrade;
    }
    
    public void SetStudName(String studName){
        this.studName = studName;
    }
    
    public void SetStudID(String studID){
        this.studID = studID;
    }
    
    public void SetStudGPA(float studGPA){
        this.studGPA = studGPA;
    }
    
    public void SetStudGrade(double studGrade){
        this.studGrade = studGrade;
    }
    
    public static int GetStudentsCounter(){ //**
        return studentsCounter;
    }
}
