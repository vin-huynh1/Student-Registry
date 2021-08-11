/*
 * Name : Vincent Huynh
 * ID 	: 500902518
 */

public class Course 
{
	private String code;
	private String name;
	private String description;
	private String format;
	
	//Constructs course object
	public Course()
	{
	  this.code        = "";
	  this.name        = "";
	  this.description = "";
	  this.format      = "";
	}
	/**
	 * Constructs Course object with specified name, course code, description, format
	 * @param name the name of course
	 * @param code the course code of course
	 * @param descr the course description of course
	 * @param fmt the format of course
	 */
	public Course(String name, String code, String descr, String fmt)
	{
	  this.code        = code;
	  this.name        = name;
	  this.description = descr;
	  this.format      = fmt;
	}
	
	// Returns the course code
	public String getCode()
	{
	   return code;
	}
	
	// Returns the course name
	public String getName()
	{
	  return name;
	}
	
	// Returns format of class
	public String getFormat()
	{
	  return format;
	}
	// Return description of class
	public String getDescription()
	{
	  return code +" - " + name + "\n" + description + "\n" + format;
	}
	
	
	
	// Getter to only get description (used for addCourse in Registry class)
	public String getterDescription() 
	{
		return description;
	}
	
	
	// Returns info about course
	 public String getInfo()
	 {
	   return code +" - " + name;
	 }
	 
	 // static method to convert numeric score to letter grade string 
	 // e.g. 91 --> "A+"
	 public static String convertNumericGrade(double score)
	 {
		 if (score >= 90) return "A+";
		 else if (score >= 85 && score <= 89) return "A";
		 else if (score >= 80 && score <= 84) return "A-";
		 else if (score >= 77 && score <= 79) return "B+";
		 else if (score >= 73 && score <= 76) return "B";
		 else if (score >= 70 && score <= 72) return "B-";
		 else if (score >= 67 && score <= 69) return "C+";
		 else if (score >= 63 && score <= 66) return "C";
		 else if (score >= 60 && score <= 62) return "C-";
		 else if (score >= 57 && score <= 59) return "D+";
		 else if (score >= 53 && score <= 56) return "D";
		 else if (score >= 50 && score <= 52) return "D-";
		 else return "F";
	 }
	 
}
