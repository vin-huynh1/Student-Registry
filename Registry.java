/*
 * Name : Vincent Huynh
 * ID 	: 500902518
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeMap;

public class Registry
{
	
   private TreeMap<String, Student> students = new TreeMap<String, Student>();												//Key is studentID string
   private TreeMap<String, ActiveCourse> courses  = new TreeMap<String, ActiveCourse>(String.CASE_INSENSITIVE_ORDER);		//Key is course code string
   
   public Registry() throws FileNotFoundException
   {
	   
	   ArrayList<Student> list = new ArrayList<Student>();
	   //CPS 209
	   String courseName = "Computer Science II";
	   String courseCode = "CPS209";
	   String descr = "Learn how to write complex programs!";
	   String format = "3Lec 2Lab";
	   courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
	   
	   //CPS 511
	   list.clear();
	   courseName = "Computer Graphics";
	   courseCode = "CPS511";
	   descr = "Learn how to write cool graphics programs";
	   format = "3Lec";
	   courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"F2020",list));
	   
	   //CPS 643 
	   list.clear();
	   courseName = "Virtual Reality";
	   courseCode = "CPS643";
	   descr = "Learn how to write extremely cool virtual reality programs";
	   format = "3Lec 2Lab";
	   courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
	   
	   // CPS706
	   list.clear();
	   courseName = "Computer Networks";
	   courseCode = "CPS706";
	   descr = "Learn about Computer Networking";
	   format = "3Lec 1Lab";
	   courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
	   
	   // CPS616
	   list.clear();
	   courseName = "Algorithms";
	   courseCode = "CPS616";
	   descr = "Learn about Algorithms";
	   format = "3Lec 1Lab";
	   courses.put(courseCode, new ActiveCourse(courseName,courseCode,descr,format,"W2020",list));
	   
	   
	   
	   if (!new File("students.txt").exists()) {
		   throw new FileNotFoundException();							//checks if student.txt exists else throws exception
	   } 
	   else {
		   Scanner reading = new Scanner(new File("students.txt"));		//Scanner reads each line in student.txt
		   while (reading.hasNextLine()) {					
			   String nameAndId = reading.nextLine(); 
			   readEachStudent(nameAndId); 
		   }
		   reading.close();
	   }
	   
   }
   
   
   /**
    * Helper method that that seperates student name and id, constructs student object, and adds it to TreeMap
    * @param a is the line that is read from file that contains name and id
    */
   public void readEachStudent(String a) {						
	   int i = 0;
	   while (!Character.isDigit(a.charAt(i)) && !Character.isWhitespace(a.charAt(i)) && i < a.length()-1) {
		   i++;
	   }
	   String studentsName = a.substring(0,i);						//Gets student name from line
	   String studentsId = a.substring(i+1);						//Gets student id from line
	   
	   if (studentsId.length() != 5) {								//Checks if id is valid length, else exception
		   throw new NoSuchElementException();
	   }
	   else {
		   for (char c: studentsId.toCharArray()) {					//Checks if id is all numbers, else exception
			   if (!Character.isDigit(c)) {
				   throw new NoSuchElementException();
			   }
		   }
		   students.put(studentsId, new Student(studentsName, studentsId));		//Adds student object with name and id from file's current line to TreeMap students
		   
	   }
   }
   
   
   /**
    * Method to return courses TreeMap for scheduler (used in StuRegSim)
    * @return the TreeMap of courses
    */
   public TreeMap<String, ActiveCourse> getCourses() {
	   return courses;
   }
   
   
   /**
    * Adds new student to the registry
    * @param name for student's name
    * @param id to identify student
    * @return true if student is added
    */
   public boolean addNewStudent(String name, String id)
   {
	   Student stuobj = new Student(name, id);													//New student object
	   for (String key: students.keySet()) {
		   Student stuobj2 = students.get(key);
		   if (stuobj.getId().equalsIgnoreCase(stuobj2.getId())) {								//Checks to see if student is already in registry
			   System.out.println("Student" + stuobj.getName() + " is already registered");
			   return false;
		   }
	   }
	   students.put(stuobj.getId(), stuobj);
	   return true;
   }
   
   
   /**
    * Removes student from registry
    * @param studentId to identify student
    * @return true if student is removed or false is not
    */
   public boolean removeStudent(String studentId)
   {
	
	   for (String key: students.keySet()) {
		   Student s = students.get(key);
		   if (s.getId().equalsIgnoreCase(studentId)) {			//Finds student in students arrayList
			   students.remove(s.getId());								//Removes student if found
			   return true;
		   }
	   }
	   return false;
   }
   
   
   // Prints all registered students
   public void printAllStudents()
   {
	   for (String key: students.keySet())
	   {
		   Student s = students.get(key);
		   System.out.println("ID: " + s.getId() + " Name: " + s.getName() );   
	   }
	    
   }
   
   
   /**
    * Adds student to active course
    * @param studentId identifies student
    * @param courseCode identifies the active course
    */
   public void addCourse(String studentId, String courseCode)
   {
	   
	   for (String key: students.keySet()) {
		   Student s = students.get(key);
		   if (s.getId().equalsIgnoreCase(studentId)) {										//Finds student in students arrayList
			   boolean takenb4 = false;											
			   for (int k = 0; k < s.courses.size(); k++) {
				   CreditCourse cc = s.courses.get(k);
				   if (cc.getCode().equalsIgnoreCase(courseCode)) {							//Checks if student has taken course before
					   takenb4 = true;
					   System.out.println("Student has already taken this course in past");
					   break;
				   }
			   }
			   if (!takenb4) {
				   for (String key1: courses.keySet()) {
					   ActiveCourse ac = courses.get(key1);
					   if (!s.courses.contains(ac)) {										//Checks if student is not already enrolled
						   ac.addStudent(s);												//Adds student to active course
						   s.addCourse(ac.getName(), ac.getCode(), ac.getterDescription(), ac.getFormat(), ac.getSemester(), 0);		//Adds course to student's list of credit courses
					   }
				   }
			   }
		   }
	   }
   }
   
   
   /**
    * Drops student from active course
    * @param studentId identifies student
    * @param courseCode identifies active course
    */
   public void dropCourse(String studentId, String courseCode)
   {
	   // Find the active course
	   // Find the student in the list of students for this course
	   // If student found:
	   //   remove the student from the active course
	   //   remove the credit course from the student's list of credit courses
	   
	   
	   for (String key: courses.keySet()) {
		   ActiveCourse ac = courses.get(key);
		   if (ac.getCode().equalsIgnoreCase(courseCode)) {							//Finds active course with courseCode
			   for(String key2: students.keySet()) {
				   Student c = students.get(key2);
				   if (c.getId().equalsIgnoreCase(studentId)) {						//Finds student in list of students for course
					   ac.removeStudent(c);											//Removes student from their credit course list
					   for (CreditCourse cc: c.courses) {
						   	if (cc.getCode().equalsIgnoreCase(courseCode)) {
						   		c.removeActiveCourse(courseCode);					//Removes student from active course
						   		break;
						   	}
					   }
				   }
			   }
		   }
	   }
   }
   
   
   // Prints all active courses
   public void printActiveCourses()
   {
	   for (String key: courses.keySet()) {
		   ActiveCourse ac = courses.get(key);
		   System.out.println(ac.getDescription() + " Current Enrollment: " + courses.size());
	   }
   }
   
   
   /**
    * Prints list of students in active course
    * @param courseCode identifies active course
    */
   public void printClassList(String courseCode)
   {
	  for (String key: courses.keySet()) { 
		  ActiveCourse ac = courses.get(key);
		  if (ac.getCode().equalsIgnoreCase(courseCode)) {
			  ac.printClassList();
		  }
	  }
   }
   
   
   /**
    * Finds course and sorts by student name
    * @param courseCode identifies active course
    */
   public void sortCourseByName(String courseCode)
   {

	   for (String key: courses.keySet()) {
		   ActiveCourse ac = courses.get(key);
		   if (ac.getCode().equalsIgnoreCase(courseCode)) {
			   ac.sortByName();
		   }
	   }
   }
   

   /**
    * Finds course and sorts by student id
    * @param courseCode identifies active course
    */
   public void sortCourseById(String courseCode)
   {
	   for (String key: courses.keySet()) {
		   ActiveCourse ac = courses.get(key);
		   if (ac.getCode().equalsIgnoreCase(courseCode)) {
			   ac.sortById();
		   }
	   }
   }
   
   /**
    * Prints student names and grades of given course
    * @param courseCode identifies active course
    */
   public void printGrades(String courseCode)
   {
	   for (String key: courses.keySet()) {
		   ActiveCourse ac = courses.get(key);
		   if (ac.getCode().equalsIgnoreCase(courseCode)) {
			   ac.printGrades();
		   }
	   }
   }
   
   
   /**
    * Prints all active courses of student
    * @param studentId identifies student
    */
   public void printStudentCourses(String studentId)
   {
	   boolean correct = false;
	   for (String key: students.keySet()) {
		   Student s = students.get(key);
		   if (s.getId().equalsIgnoreCase(studentId)) {			//Finds student in student arrayList
			   correct = true;
			   s.printActiveCourses();							//If student found, prints their active courses
		   }
	   }
	   if (!correct) {
		   System.out.println("Enter a valid id");		
	   }
	   
	   
   }
   
   
   /**
    * Prints all completed courses and grades of student
    * @param studentId identifies student
    */
   public void printStudentTranscript(String studentId)
   {
	   for (String key: students.keySet()) {
		   Student s = students.get(key);
		   if (s.getId().equalsIgnoreCase(studentId)) {			//Finds student in student arrayList
			   for (CreditCourse a: s.courses) {				//Access their credit courses
				   if (!a.getActive()) {						//Checks if course is completed
					   System.out.println(a.displayGrade());
				   }
			   }
		   }
	   }
   }
   

   /**
    * Sets final grade of student
    * @param courseCode identifies course
    * @param studentId identifies student
    * @param grade the grade of student
    */
   public void setFinalGrade(String courseCode, String studentId, double grade)
   { 
	   // find the active course
	   // If found, find the student in class list
	   // then search student credit course list in student object and find course
	   // set the grade in credit course and set credit course inactive
	   
	   for (String key: courses.keySet()) {
		   ActiveCourse a = courses.get(key);
		   if (a.getCode().equalsIgnoreCase(courseCode)) {						//Finds active course
			   for (String key2: students.keySet()) {
				   Student s = students.get(key);
				   if (s.getId().equalsIgnoreCase(studentId)) {					//Finds student in class list
					   for (CreditCourse e: s.courses) {						
						   if (e.getCode().equalsIgnoreCase(courseCode)) {		//Finds course in student's credit course list
							   e.grade = grade;									//Sets the grade in course for student
							   e.setInactive();									//Course is completed sets to inactive
						   }
					   }
				   } 
			   }
		   }
	   } 
   }
  
}
