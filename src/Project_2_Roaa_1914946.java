/*
Name: Roaa Hatim Altunsi ,, ID: 1914946
Class: IAR  ,, Subject: CPCS-203
*/
import java.io.*;
import java.util.*;
public class Project_2_Roaa_1914946 {
    public static void main(String[] args) throws Exception{
        
        // Create the file objects
        File inputf = new File("input.txt");
        File outputf = new File("output.txt");
        if(!inputf.exists()){
            System.out.println("The file doesn't exist, Check in " + inputf.getAbsolutePath());
            System.exit(0);
        }
        Scanner read = new Scanner(inputf);
        PrintWriter write = new PrintWriter(outputf);
        University[] universities = new University[read.nextInt()]; // Declare University array
        
        write.println("################################################");
        write.println("####### Students Information System v.2 ########");
        write.println("################################################");
        write.println("- Number of universities: " + universities.length);
        
        // Add Universities Command
        while(read.hasNext("add_universities")){
            for(int i=0; i<universities.length; i++){
                read.next();
                write.println("\n- Command: add_universities");
                String uniName = read.next();
                String uniCity = read.next();
                int numOfCourses = read.nextInt();
                universities[i] = new University(uniName, uniCity, numOfCourses);
                write.printf("\t\t+ %s\n", universities[i].GetUniName());
            }
        }
        
        // Add Courses Command
        int count = 0; // Get index of each Course Object
        while(read.hasNext("add_courses")){
            read.next();
            write.println("\n- Command: add_courses");
            int uniIndex = getUniversityIndex(read.next(), universities); 
            Course courseObject = new Course(read.next(), read.next(), read.next(), 
                            read.next(), read.next(), read.next(), read.nextInt());
            if(count == universities[uniIndex].GetAllCourses().length)
                count = 0;
            universities[uniIndex].AddCourse(courseObject, count);
            count++;
            write.printf("\t\t+ %s - %s\n", courseObject.GetCourseName(), universities[uniIndex].GetUniName());
        }
        
        // Add Students Command
        while(read.hasNext("add_students")){
            read.next();
            write.println("\n- Command: add_students");
            int uniIndex = getUniversityIndex(read.next(), universities);
            int courseIndex = getCourseIndex(read.next(), universities, uniIndex);
            int numberOfStudents = read.nextInt();
            
            for(int i=0; i<numberOfStudents; i++){
                String studentID = read.next();
                String studentName = read.next();
                float studentGPA = read.nextFloat();
                double studentGrade = read.nextDouble();
                Student studentObject = new Student(studentName, studentID, studentGPA);
                studentObject.SetStudGrade(studentGrade);
                Course courseObject = universities[uniIndex].GetUniCourse(courseIndex);
                if(i < courseObject.GetAllStudents().length)
                   courseObject.AddStudent(studentObject, i);
                else{
                    System.out.println("No more students can be added to " + courseObject.GetCourseName() + " course");
                    break;}
                write.printf("\t\t+ %s - %s - %s\n", courseObject.GetStudent(i).GetStudName().replace("_", " "),
                        courseObject.GetCourseName() ,universities[uniIndex].GetUniName());
            } 
        }
        
        // Print Result Command
        while(read.hasNext("print_result")){
            read.next();
            write.print("\n- Command: print_result");
            int uniIndex = getUniversityIndex(read.next(), universities);
            for(int i=0; i<universities[uniIndex].GetAllCourses().length; i++){
                write.println("\n	-> University: " + universities[uniIndex].GetUniName());
                write.println("	-> Course: " + universities[uniIndex].GetUniCourse(i).GetCourseName());
                write.println("	-> Number of students: " + 
                        universities[uniIndex].GetUniCourse(i).GetAllStudents().length);
                write.println("--------------------------------------------------------------------");
                write.println("ID             First Name     Last Name      GPA            Grade   ");
                write.println("--------------------------------------------------------------------");
                for(int j=0; j<universities[uniIndex].GetUniCourse(i).GetAllStudents().length; j++){
                    Student studentObject = universities[uniIndex].GetUniCourse(i).GetStudent(j);
                    String[] splitStudName = studentObject.GetStudName().split("_");
                    write.printf("%-15s%-15s%-15s%-15.2f%-15.2f\n", studentObject.GetStudID(),
                            splitStudName[0], splitStudName[1], studentObject.GetStudGPA(),
                            studentObject.GetStudGrade());
                }
                write.print("--------------------------------------------------------------------\n");
            } 
        }
        
        // Find Average && Max && Min Grade Commands
        while(read.hasNext("find_average_grade") || read.hasNext("find_max_grade")
                || read.hasNext("find_min_grade")){
            String command = read.next();
            int uniIndex = getUniversityIndex(read.next(), universities);
            int courseIndex = getCourseIndex(read.next(), universities, uniIndex);
            Course courseObject = universities[uniIndex].GetUniCourse(courseIndex);
            double sumGrades = 0; int countStud = 0; int maxIndex = 0; int minIndex = 0;
            double maxGrade = courseObject.GetStudent(0).GetStudGrade();
            double minGrade = courseObject.GetStudent(0).GetStudGrade();
            
            for(int i=0; i<courseObject.GetAllStudents().length; i++){
                sumGrades += courseObject.GetStudent(i).GetStudGrade();
                countStud++; 
                if(courseObject.GetStudent(i).GetStudGrade() > maxGrade){
                    maxGrade = courseObject.GetStudent(i).GetStudGrade();
                    maxIndex = i; }
                if(courseObject.GetStudent(i).GetStudGrade() < minGrade){
                    minGrade = courseObject.GetStudent(i).GetStudGrade();
                    minIndex = i; }}
            
            write.println("\n- Command: " + command);
            write.printf("%31s%1s\n","-> University: ", universities[uniIndex].GetUniName());
            write.printf("%27s%1s\n" ,"-> Course: ", courseObject.GetCourseName());
            if(command.equals("find_average_grade"))
                write.printf("%35s%.2f\n" ,"-> Average Grades: ", (sumGrades/countStud));
            if(command.equals("find_max_grade"))
                write.printf("%30s%.1f%s%s%s%s%s\n" ,"-> Max Grades:", maxGrade, " for Student ", 
                courseObject.GetStudent(maxIndex).GetStudName().replace("_"," "),
                " (", courseObject.GetStudent(maxIndex).GetStudID(), ")");
            if(command.equals("find_min_grade"))
                write.printf("%30s%.1f%s%s%s%s%s\n" ,"-> Min Grades:", minGrade, " for Student ", 
                courseObject.GetStudent(minIndex).GetStudName().replace("_"," "),
                " (", courseObject.GetStudent(minIndex).GetStudID(), ")");
        }
        
        // Find Avarage GPA
        int maxUniIndex = 0; int maxCourseIndex = 0; int maxStudIndex = 0;
        int minUniIndex = 0; int minCourseIndex = 0; int minStudIndex = 0;
        double maxGPA = universities[0].GetUniCourse(0).GetStudent(0).GetStudGPA();
        double minGPA = universities[0].GetUniCourse(0).GetStudent(0).GetStudGPA();
            
        if(read.next().equals("find_average_gpa")){
            double GPA = 0; 
            for (int i=0; i<universities.length; i++)
                for (int j = 0; j < universities[i].GetAllCourses().length; j++) 
                    for (int k = 0; k < universities[i].GetUniCourse(j).GetAllStudents().length; k++){
                        GPA += universities[i].GetUniCourse(j).GetStudent(k).GetStudGPA();
                        double studGPA = universities[i].GetUniCourse(j).GetStudent(k).GetStudGPA();
                        if(studGPA > maxGPA){
                           maxGPA = studGPA; 
                           maxUniIndex = i; maxCourseIndex = j; maxStudIndex = k; }
                        if(studGPA < minGPA){
                           minGPA = studGPA; 
                           minUniIndex = i; minCourseIndex = j; minStudIndex = k; } }
            write.println("\n- Command: find_average_gpa");
            write.println("	* The average GPA for all students in all universities is " + 
                    (GPA/Student.GetStudentsCounter()));          
        }
        
        // Find max , min GPA Commands
        int countForPrintMinGPA = 1;
        while(read.hasNext("find_max_gpa") || read.hasNext("find_min_gpa")){
            String command = read.next();
            Student stud; University uni;
            if(command.equals("find_max_gpa")){
                stud = universities[maxUniIndex].GetUniCourse(maxCourseIndex).GetStudent(maxStudIndex);
                uni = universities[maxUniIndex];}
            else{
                stud = universities[minUniIndex].GetUniCourse(minCourseIndex).GetStudent(minStudIndex);
                uni= universities[minUniIndex];
                countForPrintMinGPA = 2;}
            for(int i=0; i<countForPrintMinGPA; i++){
                write.println("\n- Command: " + command);
                String s = command.equals("find_max_gpa")? "Top ":"Worst ";
                write.println("	* The " + s + "Performing Student in all universities is:");
                write.println("--------------------------------------------------------------------\n" +
                              "ID             Full Name      GPA            Grade          \n" +
                              "--------------------------------------------------------------------");
                write.printf("%-15s%-15s%-15.2f%-15.2f\n", stud.GetStudID(),
                                stud.GetStudName().replace("_"," "), stud.GetStudGPA(),stud.GetStudGrade());
                write.println("--------------------------------------------------------------------");
                write.println("	-> University: " + uni.GetUniName()); 
                if(command.equals("find_min_gpa")) write.println(); //Get the same format as the output file
            }
        }
        
        // About_app Command
        if(read.next().equals("about_app")){
            write.println("- Command: about_app\n	-> Developed By: Roaa Hatim Altunsi\n" +
                    "	-> University ID: 1914946\n	-> Section: IAR");
        }
        
        // Exit Command
        if(read.next().equals("exit")){
            Date theDate = new Date();
            write.print("\nGoodbye :)\nThis file is generated on " + theDate);
            write.flush(); write.close();
        }
    }
    
    // Method to find the index of the university, given its name
    public static int getUniversityIndex(String uniName, University[] universities){
        for(int i=0; i<universities.length; i++)
            if(universities[i].GetUniName().equals(uniName))
                return i;
        return -1;
    }
    
    // Method to find the index of the course, given its name
    public static int getCourseIndex(String courseName, University[] universities, int uniIndex){
        for(int i=0; i<universities[uniIndex].GetAllCourses().length; i++)
            if(universities[uniIndex].GetUniCourse(i).GetCourseName().equals(courseName))
                    return i;
        return -1;
    }
}