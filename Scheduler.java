/*
 * Name : Vincent Huynh
 * ID 	: 500902518
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

//Customized exception classes																											
class UnknownCourseException extends RuntimeException {
	public UnknownCourseException() {}
	public UnknownCourseException(String message) { super(message);}
}

class InvalidDayException extends RuntimeException {
	public InvalidDayException() {}
	public InvalidDayException(String message) { super(message);}
}

class InvalidTimeException extends RuntimeException {
	public InvalidTimeException() {}
	public InvalidTimeException(String message) { super(message);}
}

class InvalidDurationException extends RuntimeException {
	public InvalidDurationException() {}
	public InvalidDurationException(String message) { super(message);}
}

class LectureTimeCollisionException extends RuntimeException {
	public LectureTimeCollisionException() {}
	public LectureTimeCollisionException(String message) { super(message);}
}


public class Scheduler
{

	TreeMap<String,ActiveCourse> schedule;
	
	/**
	 * Constructor for Scheduler	
	 * @param courses the TreeMap of String (key) and ActiveCourse(value)
	 */
	public Scheduler(TreeMap<String,ActiveCourse> courses)
	{
	  schedule = courses;
	}
	
	
	/**
	 * Sets day and time of courses with appropriate parameters
	 * @param courseCode the course's code (either CPS209, CPS511, CPS643, CPS706, CPS616)
	 * @param day the day the course occurs (Mon, Tue, Wed, Thur, Fri)
	 * @param startTime the start time of course (0800 - 1700hrs)
	 * @param duration the duration of course
	 */
	public void setDayAndTime(String courseCode, String day, int startTime, int duration)
	{
		
		boolean found = false;
		boolean founddur = false;
		
		//Checks if course is offered
		for (String key: schedule.keySet()) {														
			if (key.equalsIgnoreCase(courseCode)) {
				found = true;
			}
		}
		if (!found) { throw new UnknownCourseException();}

		
		//Checks if day is valid
		List<String> validDays = Arrays.asList(new String[]{"Mon", "Tue", "Wed", "Thur", "Fri"});			
		if (!validDays.contains(day)) {
			throw new InvalidDayException();		
		}
		
		
		//Checks is duration is valid
		if (duration == 1 || duration == 2 || duration == 3) {											
			founddur = true;
		}
		if (!founddur) { throw new InvalidDurationException();}
		
		
		//Checks if start time is valid with respect to course duration
		if (startTime <= 800 || (startTime + (duration*100) > 1700)) { throw new InvalidTimeException(); }	
		
	
		//Checks if an existing course's runtime collides with another
		for(ActiveCourse ac: schedule.values()) {
			int courseEnd = ac.getLectureStart() + (ac.getLectureDuration() * 100);
			if (!(ac.getLectureDay() == null) && !(ac.getLectureDay().isEmpty()) && ac.getLectureDay().equalsIgnoreCase(day)) {
				if ( (ac.getLectureStart() == startTime) || ((ac.getLectureStart() <= startTime) && (courseEnd >= startTime) ) || ((ac.getLectureStart() >= startTime) && (courseEnd >= ac.getLectureStart()))) {
					throw new LectureTimeCollisionException();
				}
			}
		}
		
		
		
		//Adds course to schedule with alloted start time, end time
		ActiveCourse ac = schedule.get(courseCode);
		ac.setLectureDay(day);
		ac.setLectureDuration(duration);
		ac.setLectureStart(startTime);
		
	}
	
	
	/**
	 * Clears existing schedule for course
	 * @param courseCode the course code of course
	 */
	public void clearSchedule(String courseCode)
	{

		for (String key: schedule.keySet()) {
			ActiveCourse ac = schedule.get(key);
			if (ac.getCode().equalsIgnoreCase(courseCode)) {
				ac.setLectureDay("");
				ac.setLectureDuration(0);
				ac.setLectureStart(0);
				System.out.println(courseCode + " has been cleared from schedule");
			}
		}
	}
		
	
	/**
	 * Prints schedule with courses that have been added
	 */
	public void printSchedule()
	{
		
		String blank = "        ";
		String[][] board = new String[10][5];
		int k = 800;
			
		for (int i = 0; i < board.length; i++) {															
			for (int y = 0; y < board[i].length; y++) {				//Blank board creation
				board[i][y] = blank;
			}
		}
		
		System.out.println("     " + "  Mon " + "     Tue " + "      Wed " + "      Thur " + "      Fri " );
		for (int i = 0; i < board.length; i++) {
			if (k < 1000) {
				System.out.print("0"+k);}							//Hours on left
			else {
				System.out.print(k);
			}
			boolean found = false;
			for (int x = 0; x < board[i].length; x++) {
				
				for (ActiveCourse ac: schedule.values()) {							
					if (ac.getLectureStart() == k) {										//Checks if Active course's start time is equal to hour on left 
						if (ac.getLectureDay().equalsIgnoreCase("Mon") && x == 0) {			//If hour matches then location on 2D array is updated with course's code
							found = true;
							board[i][x] = ac.getCode();

						}
						if (ac.getLectureDay().equalsIgnoreCase("Tue") && x == 1) {
							found = true;
							board[i][x] = ac.getCode();

						}
						if (ac.getLectureDay().equalsIgnoreCase("Wed") && x == 2) {
							found = true;
							board[i][x] = ac.getCode();

						}
						if (ac.getLectureDay().equalsIgnoreCase("Thur") && x == 3) {
							found = true;
							board[i][x] = ac.getCode();

						}
						if (ac.getLectureDay().equalsIgnoreCase("Fri") && x == 4) {
							found = true;
							board[i][x] = ac.getCode();

						}
					}
					if (found) {															//If Active course time matches then prints course code for its duration (1-3hrs)
						int o = ac.getLectureDuration();
						for (int u = 1; u < o; u++) {
							board[i+u][x] = ac.getCode();
						}
					}
				}
				found = false;
				System.out.print(" " + board[i][x]+ " ");									//Prints board at the index and row
				
			}
			System.out.println("");
			k += 100;																		//Hour on left increases so next row can be printed
		}		
	
	}
	
	
}

