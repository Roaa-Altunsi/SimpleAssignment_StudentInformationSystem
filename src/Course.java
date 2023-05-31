/*
Name: Roaa Hatim Altunsi ,, ID: 1914946
Class: IAR  ,, Subject: CPCS-203
*/
public class Course {
    // Data Fields
    private String courseName;
    private String courseDays;
    private String courseTime;
    private String courseRoom;
    private String courseBuilding;
    private String courseInstructor;
    private Student[] registeredStudents;
    
    // Constructors
    public Course(){
        
    }
    
    public Course(String courseName, String courseDays, 
            String courseTime, String courseRoom, String courseBuilding,
            String courseInstructor, int capacity){
        this.courseName = courseName;
        this.courseDays = courseDays;
        this.courseTime = courseTime;
        this.courseRoom = courseRoom;
        this.courseBuilding = courseBuilding;
        this.courseInstructor = courseInstructor;
        registeredStudents = new Student[capacity];
    }
    
    // Getters & Setters & Service Methods
    public String GetCourseName(){
        return courseName;
    }
    
    public String GetCourseDays(){
        return courseDays;
    }
    
    public String GetCourseTime(){
        return courseTime;
    }
    
    public String GetCourseRoom(){
        return courseRoom;
    }
    
    public String GetCourseBuilding(){
        return courseBuilding;
    }
    
    public String GetCourseInstructor(){
        return courseInstructor;
    }
    
    public Student GetStudent(int index){
        return registeredStudents[index];
    }
    
    public Student[] GetAllStudents(){
        return registeredStudents;
    }
    
    public void SetCourseName(String courseName){
        this.courseName = courseName;
    }
    
    public void SetCourseDays(String courseDays){
        this.courseDays = courseDays;
    }
    
    public void SetCourseTime(String courseTime){
        this.courseTime = courseTime;
    }
    
    public void SetCourseRoom(String courseRoom){
        this.courseRoom = courseRoom;
    }
    
    public void SetCourseBuilding(String courseBuilding){
        this.courseBuilding = courseBuilding;
    }
    
    public void SetCourseInstructor(String courseInstructor){
        this.courseInstructor = courseInstructor;
    }
    
    public void AddStudent(Student stud, int index){
        registeredStudents[index] = stud;
    }
}
