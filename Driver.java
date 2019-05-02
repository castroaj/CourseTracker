import java.io.IOException;
import java.util.HashSet;

public class Driver {
	/**
	 * 1 planner = [ x Programs [ y Clusters [ z Courses ] ] ] ===================
	 * IE: zeru = [ 2 (CS,GENED) [ 20 (Writing,Intro to CS) [CS101,WRTC103]]]
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		HashSet<Program> degree = new HashSet<Program>();

		Program CS = ZGenerator.makeCSMajor(); //CS Major with 7 clusters
		Program GenED = ZGenerator.makeGenED(); //Gen Eds with 13 (?) clusters
		
		degree.add(CS);
		degree.add(GenED);

		ZPlanner planner = new ZPlanner("Zeru Tadesse", Semester.SO_SP, degree, 120);

		System.out.println(planner.toString(false)); //short version (change boolean)

	}
}
