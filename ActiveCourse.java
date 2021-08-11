/*
 * Name : Vincent Huynh
 * ID 	: 500902518
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ActiveCourse extends Course
{
	private ArrayList<Student> students; 
	private String             semester;
	private int lectureStart;
	private int lectureDuration;
	private String lectureDay;
	  
   /**
    * Constructs ActiveCourse
    * @param name the course name 
    * @param code the course code
    * @param descr the course description
    * @param fmt the course format
    * @param semester the semester it is in
    * @param students the arrayList of students in course
    */
   public ActiveCourse(String name, String code, String descr, String fmt,String semester,ArrayList<Student> students)
   {
	   super(name, code, descr, fmt);
	   this.semester = semester;
	   this.students = new ArrayList<Student>(students); 
   }
   

   //Returns lecture start time
   public int getLectureStart() {
	   return lectureStart;
   }
   
   //Returns lecture duration
   public int getLectureDuration() {
	   return lectureDuration;
   }
   
   //Returns lecture day
   public String getLectureDay() {
	   return lectureDay;
   }
   
   //Sets lecture start
   public void setLectureStart(int start) {
	   lectureStart = start;
   }
   
   //Sets lecture duration
   public void setLectureDuration(int duration) {
	   lectureDuration = duration;
   }
   
   //Sets lecture day
   public void setLectureDay(String day) {
	   lectureDay = day;
   }
   
   
   
   
   
   
   // Returns semester
   public String getSemester()
   {
	   return semester;
   }
   
   
   // Prints the students in this course  (name and student id)
   public void printClassList()
   {
	for (Student c: students) {
		System.out.println("Student ID: " + c.getId() + " Name: " + c.getName());
	}
   }
   
   
   // Prints the grade of each student in this course (along with name and student id)
   public void printGrades()
   {	
	   for (Student c: students) {
		   System.out.println(c.getId()+ " " + c.getName() + " " + getGrade(c.getId()));
	   } 
	   
	   }
	   

   /**
    * Returns numeric grade in course for specified student
    * @param studentId identifies student
    * @return grade, returns grade, if student not found returns 0
    */
   public double getGrade(String studentId)
   {
	  for (Student s: students) {
		  if (s.getId().equalsIgnoreCase(studentId)) {				//Finds student in students arrayList
			  for (CreditCourse e: s.courses) {						
				  if (e.getCode().equalsIgnoreCase(getCode())) {	//Checks in student's credit course to find course
					  return e.grade;
				  }
			  }
		  }
	  } 
	  return 0;
   }
   
   
   // Returns a String containing the course information, semester, number of students 
   // enrolled in the course
   public String getDescription()
   {
	   return super.getDescription();
	   
   }
   
   
   // Method to add student used in addCourse method in registry
   public void addStudent(Student x) {
	   students.add(x);
   }
   
  // Method to remove student used in dropCourse method in registry
   public void removeStudent(Student y) {
	   students.remove(y);
   }
   
   
   // Sorts the students in course by name using the Collections.sort() method
   public void sortByName()
   {
	  
	  if (students.size() == 0) {
		  System.out.println("Nothing to sort!");
	  }  
	  else {
		  Collections.sort(students, new NameComparator()); 
	  }
   }
   
   // This class is used to compare two Student objects based on student name
   private class NameComparator implements Comparator<Student>
   {

	public int compare(Student s1, Student s2) {
		return s1.getName().compareTo(s2.getName());
	}
   }
   
   
   // Sort the students in the course by student id using the Collections.sort() method
   public void sortById()
   {
	   if (students.size() == 0) {
		   System.out.println("Nothing to sort!");
	   } 
	   else {
		   Collections.sort(students, new IdComparator()); 
	   }
   }
   
   // This class is used to compare two Student objects based on student id
   private class IdComparator implements Comparator<Student>
   {
   	public int compare(Student s1, Student s2) {
   		return s1.getId().compareToIgnoreCase(s2.getId());
   	}
   }
}
