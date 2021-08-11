/*
 * Name : Vincent Huynh
 * ID 	: 500902518
 */

import java.util.ArrayList;

public class Student implements Comparable<Student>
{
  private String name;
  private String id;
  public  ArrayList<CreditCourse> courses;
  
  
  /**
   * Constructs student object
   * @param name the name of student
   * @param id the id of student
   */
  public Student(String name, String id)
  {
	 this.name = name;
	 this.id   = id;
	 courses   = new ArrayList<CreditCourse>();
  }
  
  // Returns student id
  public String getId()
  {
	  return id;
  }
  // Returns student name
  public String getName()
  {
	  return name;
  }
  
  
  // Adds a credit course to list of courses for this student
  public void addCourse(String courseName, String courseCode, String descr, String format,String sem, double grade)
  {
	  
	  CreditCourse a = new CreditCourse(courseName, courseCode, descr, format, sem, grade);
	  a.setActive();
	  courses.add(a);
	  
  }
  
  
  // Prints student transcript of completed courses (non-active) includes course code, name, sem, grade
  public void printTranscript()
  {
	  for (CreditCourse c: courses) {
		  if (!c.getActive()) {
			  System.out.println(c.displayGrade());
		  }
	  }
  }
  
  
  // Prints all active courses student is enrolled in
  public void printActiveCourses()
  {
	 for (int i = 0; i < courses.size(); i++) {
		 if (courses.get(i).getActive()) {
			 System.out.println(courses.get(i).getDescription());
		 }
	 }
  }
  
  // Drops an active course 
  public void removeActiveCourse(String courseCode)
  {
	 for (int i = 0; i < courses.size(); i++) {
		 if (courses.get(i).getActive() && (courses.get(i).getCode().equalsIgnoreCase(courseCode))) {
			 courses.remove(i);
		 }
	 }
  }
  
  // Returns the id and name of student
  public String toString()
  {
	  return "Student ID: " + id + " Name: " + name;
  }
  
  
  //Identifies if student name and student id are the same 
  //Returns true if equal, otherwise false
  public boolean equals(Object other)
  {
	  Student studentother = (Student) other;
	  if (this.name.equalsIgnoreCase(studentother.getName()) && this.id.equalsIgnoreCase(studentother.getId())) {
		  return true;
	  }
	  return false;
  }

/**
 * compareTo method used to sort by Name
 */
public int compareTo(Student o) {
	return this.name.compareToIgnoreCase(o.getName());
}

}
