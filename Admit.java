//McKinley Melton
//4/25/17
/*
 * This program looks at the test scores and GPA of two
 * different students and calculates which student is the
 * better option for a college admission.
 */


import java.util.*;
public class Admit {

	//this asks the students about their test scores and calculates who is best
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		intro();
		System.out.println("Information for applicant #1:");
		double studentOne = testCheck(in);
		System.out.println("Information for applicant #2:");
		double studentTwo = testCheck(in);
		compare(studentOne, studentTwo);
	}
	
	//describes the program and states what is needed
	public static void intro(){
		System.out.println("This program compares two applicants to");
		System.out.println("determine which one seems like the stronger");
		System.out.println("applicant.  For each candidate I will need");
		System.out.println("either SAT or ACT scores plus a weighted GPA.");
		System.out.println();
	}
	
	
	/*accepts a scanner as a parameter and checks to 
	  see if the student took the SAT or ACT and returns
	  the students score based on test scores and GPA*/
	public static double testCheck(Scanner in){
		System.out.print("    do you have 1) SAT scores or 2) ACT scores? ");
		int option = in.nextInt();	
		double examScore = 0;
		if(option == 1){
			examScore = satOption(in);
		}else{
			examScore = actOption(in);
		}
		return examScore + gpaCalc(in); 
		
	}
	
	//asks for the students sat scores and returns their calculated score
	public static double satOption(Scanner in){
		System.out.print("    SAT math? ");
		int math = in.nextInt();
		System.out.print("    SAT critical reading? ");
		int reading = in.nextInt();
		System.out.print("    SAT writing? ");
		int write = in.nextInt();
		double exam = satCalc(math, reading, write);
		return exam; 
	}
	
	//asks for the students act scores and returns their calculated score
	public static double actOption(Scanner in){
		System.out.print("    ACT English? ");
		int english = in.nextInt();
		System.out.print("    ACT math? ");
		int math = in.nextInt();
		System.out.print("    ACT reading? ");
		int read = in.nextInt();
		System.out.print("    ACT science? ");
		int science = in.nextInt();
		double exam = actCalc(english, math, read, science);
		return exam;
	}
	
	// accepts each of the scores received on the SAT and calculates 
	//a score based on the students sat and returns the results
	public static double satCalc(int math, int reading, int write){
		double score = (((2.0 * math) + reading + write) / 32.0);
		score = Math.round(score * 10.0) / 10.0;
		System.out.println("    exam score = " + Math.round(score * 10.0) / 10.0);
		return score;
	}
	
	/*accepts each of the scores received on the ACT and 
	  calculates a score based on the students act scores and
	  returns the results */
	public static double actCalc(int english, int math, int reading, int science){
		double score = (english + (2 * math) + reading + science) / 1.8;
		score = Math.round(score * 10.0) / 10.0;
		System.out.println("    exam score = " + score);
		return score;
	}
	
	//asks the user what their GPA is and returns the calculated GPA score
	public static double gpaCalc(Scanner in){
		System.out.print("    overall GPA? ");
		double gpa = in.nextDouble();
		System.out.print("    max GPA? ");
		double max = in.nextDouble();
		System.out.print("    Transcript Multiplier? ");
		double multi = in.nextDouble();
		return gpaMultiplier(gpa, max, multi);
	}
	
	/* accepts the students gpa, what it was out of, the multiplier based on AP 
	  classes and calculates the GPA score based on the students GPA and 
	  returns the results*/
	public static double gpaMultiplier(double gpa, double max, double multi){
		double finalGPA = (gpa / max) * 100.0 * multi;
		finalGPA = Math.round(finalGPA * 10.0) / 10.0;
		System.out.println("    GPA score = " + finalGPA);
		System.out.println();
		return finalGPA;
	}
	
	//accepts the two students final scores and determines which applicant is stronger
	public static void compare(double studentOne, double studentTwo){
		System.out.println("First applicant overall score  = " + studentOne);
		System.out.println("Second applicant overall score = " + studentTwo);
		if(studentOne > studentTwo){
			System.out.println("The first applicant seems to be better");
		}else if(studentTwo > studentOne){
			System.out.println("The second applicant seems to be better");
		}else{
			System.out.println("The two applicants seem to be equal");
		}
	}
}
