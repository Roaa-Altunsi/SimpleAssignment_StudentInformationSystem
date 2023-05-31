/*
Name: Roaa Hatim Altunsi ,, ID: 1914946
Class: IAR  ,, Subject: CPCS-203
*/
public class University {
    // Data Fields
    private String uniName;
    private String uniCity;
    private Course[] uniCourses;
    
    // Constructors
    public University(){
        
    }
    
    public University(String uniName, String uniCity, int numberOfCourses){ //**
        this.uniName = uniName;
        this.uniCity = uniCity;
        uniCourses = new Course[numberOfCourses];
    }
    
    // Setters & Getters & Service Methods
    public String GetUniName(){
        return uniName;  
    }
    
    public String GetUniCity(){
        return uniCity;
    }
    
    public Course GetUniCourse(int index){ //**
        return uniCourses[index];
    }
    
    public Course[] GetAllCourses(){ 
        return uniCourses;
    }
    
    public void SetUniName(String uniName){
        this.uniName = uniName;
    }
    
    public void SetUniCity(String uniCity){
        this.uniCity = uniCity;
    }
    
    public void AddCourse(Course uniCourses, int index){
        this.uniCourses[index] = uniCourses;
    }
    
}
