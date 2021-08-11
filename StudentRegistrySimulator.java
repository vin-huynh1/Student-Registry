/*
 * Name : Vincent Huynh
 * ID 	: 500902518
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class StudentRegistrySimulator 
{
  public static void main(String[] args)
  {	
	  Registry registry = null;
	  Scheduler scheduler = null;
	  try {
		  registry = new Registry(); 
		  scheduler = new Scheduler(registry.getCourses());
	  }
	  catch (FileNotFoundException e) {
		  System.out.println("students.txt file is not found");
		  System.exit(0);
	  }
	  catch (NoSuchElementException e) {
		  System.out.println("Bad file format");
		  System.exit(0);
	  } 
	  
	  
	  Scanner scanner = new Scanner(System.in);
	  System.out.print(">");
	  
	  while (scanner.hasNextLine())
	  {
		  String inputLine = scanner.nextLine();
		  if (inputLine == null || inputLine.equals("")) continue;
		  
		  Scanner commandLine = new Scanner(inputLine);
		  String command = commandLine.next();
		  
		  if (command == null || command.equals("")) continue;
		  
		  else if (command.equalsIgnoreCase("L") || command.equalsIgnoreCase("LIST"))
		  {
			  registry.printAllStudents();
		  }
		  else if (command.equalsIgnoreCase("Q") || command.equalsIgnoreCase("QUIT"))
			  return;
		  
		  else if (command.equalsIgnoreCase("REG"))
		  {
			  // Registers a new student in registry
			  String studentName = commandLine.next();
			  String studentId = commandLine.next();
			  if (StudentRegistrySimulator.isStringOnlyAlphabet(studentName)) {			//Checks if name is all alphabetic
				  if (StudentRegistrySimulator.isNumeric(studentId)) {					//Checks if id is numeric
					  registry.addNewStudent(studentName, studentId);
				  }
			  }   
		  }
		  else if (command.equalsIgnoreCase("DEL"))
		  {
			  // Deletes a student from registry
			  String studentId = commandLine.next();
			  if (StudentRegistrySimulator.isNumeric(studentId)) registry.removeStudent(studentId);		//Checks if id is numeric then removes 
			  
		  }
		  
		  else if (command.equalsIgnoreCase("ADDC"))
		  {
			 // Adds a student to an active course 
			  String studentId = commandLine.next();
			  String courseCode = commandLine.next();
			  registry.addCourse(studentId, courseCode);
			  
		  }
		  else if (command.equalsIgnoreCase("DROPC"))
		  {
			  // Drops a student from course
			  String studentId = commandLine.next();
			  String courseCode = commandLine.next();
			  registry.dropCourse(studentId, courseCode);
			  
		  }
		  else if (command.equalsIgnoreCase("PAC"))
		  {
			  // Prints all active courses
			  registry.printActiveCourses();
			
		  }		  
		  else if (command.equalsIgnoreCase("PCL"))
		  {
			  // Prints the class list of students for specified course
			  String courseCode = commandLine.next();
			  registry.printClassList(courseCode);
			 
		  }
		  else if (command.equalsIgnoreCase("PGR"))
		  {
			  // Prints name, id and grade of all students in active course
			  String courseCode = commandLine.next();
			  registry.printGrades(courseCode);
			  
		  }
		  else if (command.equalsIgnoreCase("PSC"))
		  {
			  // Prints all credit courses of student
			  String studentId = commandLine.next();
			  registry.printStudentCourses(studentId);
			  
		  }
		  else if (command.equalsIgnoreCase("PST"))
		  {
			  // Prints student transcript 
			  String studentId = commandLine.next();
			  registry.printStudentTranscript(studentId);
			  
		  }
		  else if (command.equalsIgnoreCase("SFG"))
		  {
			  // Sets final grade of student
			  String courseCode = commandLine.next(); 
			  String studentId = commandLine.next();
			  double stuGrade = commandLine.nextDouble();
			  registry.setFinalGrade(courseCode, studentId, stuGrade); 
			  
		  }
		  else if (command.equalsIgnoreCase("SCN"))
		  {
			  // Sorts students alphabetically for a specified course
			  String courseCode = commandLine.next();
			  registry.sortCourseByName(courseCode);

		  }
		  else if (command.equalsIgnoreCase("SCI"))
		  {
			// Sorts students by ID (ascending) for specified course
			  String courseCode = commandLine.next();
			  registry.sortCourseById(courseCode);
			  
			  
		  }
		  else if (command.equalsIgnoreCase("SCH")) 
		  {
			  String courseCode = commandLine.next();
			  
			  String day = commandLine.next();
			  
			  String start = commandLine.next();
			  int start1 = Integer.parseInt(start);
			  
			  String duration = commandLine.next();
			  int duration1 = Integer.parseInt(duration);
			  
			  
			  try {				
			  	scheduler.setDayAndTime(courseCode, day, start1, duration1);								//Utilizes setDayAndTime method in Scheduler and catches possible exceptions
			  }
			  catch (UnknownCourseException e) {
				  System.out.println("UnknownCourseException: Course " + courseCode + " is not found");
			  }
			  catch (InvalidDayException e) {
				  System.out.println("InvalidDayException: Day " + day + " is invalid");
			  }
			  catch (InvalidTimeException e) {
				  System.out.println("InvalidTimeException: Time " + start1 + " is invalid");
			  }
			  catch (InvalidDurationException e) {
				  System.out.println("InvalidDurationException: Duration " + duration + " is invalid");
			  }
			  catch (LectureTimeCollisionException e) {
				  System.out.println("LectureTimeCollisionException: Lecture time collides with another class");
			  }
		  }
		  else if (command.equalsIgnoreCase("CSCH"))
		  {
			  String courseCode = commandLine.next();
			  scheduler.clearSchedule(courseCode);
			  
		  }
		  else if (command.equalsIgnoreCase("PSCH"))
		  {
			  scheduler.printSchedule();
		  }
		  System.out.print("\n>");
	  }
  }
  
  /**
   * Checks if string contains all alphabetical characters
   * @param str the input string
   * @return true if str contains all alphabetical characters, otherwise false
   */
  private static boolean isStringOnlyAlphabet(String str) 
  {
	  for (char i: str.toCharArray()) {
		  if (!Character.isAlphabetic(i)) {
			  System.out.println("Invalid characters in Name " + str); 
			  return false;
		  }
	  } 
	  return true;
  } 
  
  /**
   * Checks if string contains all numeric characters
   * @param str the input string
   * @return true if str contains all numeric characters, otherwise false
   */
  public static boolean isNumeric(String str)
  {
	  for (char x: str.toCharArray()) {
		  if (!Character.isDigit(x)) {
			  System.out.println("Invalid characters in ID " + str);
			  return false;
		  }
	  } 
	  return true;
	  
	  
  }
  
  
}