/*
 * Name : Vincent Huynh
 * ID 	: 500902518
 */

public class CreditCourse extends Course
{
	private String semester;
	public double grade;
	public boolean active;
	
	// Constructs CreditCourse object
	public CreditCourse() {
		super();
		this.semester = "";
		this.grade = 0;
		this.active = true;
		
	}
	
	/**
	 * Constructs CreditCourse object with specified parameters
	 * @param name the course name
	 * @param code the course code 
	 * @param descr the course description
	 * @param fmt the format of course
	 * @param semester the semester course is in
	 * @param grade the grade 
	 */
	public CreditCourse(String name, String code, String descr, String fmt,String semester, double grade)
	{
		super(name, code, descr, fmt);
		this.semester = semester;
		this.grade = grade;
		this.active = true;
		
	}
	
	// Gets state of course (active or inactive)
	public boolean getActive()
	{

		return active;
	}
	// Sets course to active
	public void setActive()
	{

		this.active = true;
	}
	// Sets course to inactive
	public void setInactive()
	{
	
		this.active = false;
	}
	
	// Displays grade of course utilizing super inherited class
	public String displayGrade()
	{
		return super.getDescription() + " " + semester + " Grade " + Course.convertNumericGrade(grade);
	}
	
}