package temp;
import course_map.*;
public class MajorCreator {
	
	public static Planner addCsMajor(Planner planner)
	{
		Major csMajor = planner.getMajorObject();
		
		// Create courses
		Course cs101 = new Course(Subject.CS, "101", true, true);
		Course cs149 = new Course(Subject.CS, "149", true, true);	
		Course cs159 = new Course(Subject.CS, "159", true, true);
		Course cs227 = new Course(Subject.CS, "227", true, true);
		Course cs240 = new Course(Subject.CS, "240", true, true);
		Course cs260 = new Course(Subject.CS, "260", true, true);
		Course cs261 = new Course(Subject.CS, "261", true, true);
		Course cs280 = new Course(Subject.CS, "280", true, true,1);
		Course cs327 = new Course(Subject.CS, "327", true, true);
		Course cs330 = new Course(Subject.CS, "330", true, true );
		Course cs345 = new Course(Subject.CS, "345", true, true);
		Course cs347 = new Course(Subject.CS, "347", true, false);
		Course cs349 = new Course(Subject.CS, "349", true, true);
		Course cs354 = new Course(Subject.CS, "354", true, false);
		Course cs361 = new Course(Subject.CS, "361", true, true);
		Course cs430 = new Course(Subject.CS, "430", false, true);
		Course cs432 = new Course(Subject.CS, "432", true, false);
		Course cs442 = new Course(Subject.CS, "442", false, true);
		Course cs444 = new Course(Subject.CS, "444", false, true);
		Course cs446 = new Course(Subject.CS, "446", false, true);
		Course cs447 = new Course(Subject.CS, "447", true, false);
		Course cs450 = new Course(Subject.CS, "450", true, false);
		Course cs452 = new Course(Subject.CS, "452", false, true);
		Course cs456 = new Course(Subject.CS, "456", false, true);
		Course cs457 = new Course(Subject.CS, "457", true, false);
		Course cs458 = new Course(Subject.CS, "458", false, true);
		Course cs461 = new Course(Subject.CS, "461", false, true);
		Course cs462 = new Course(Subject.CS, "462", false, true);
		Course cs470 = new Course(Subject.CS, "470", false, true);
		Course cs474 = new Course(Subject.CS, "474", false, true);
		Course cs475 = new Course(Subject.CS, "475", false, true);
		Course cs476 = new Course(Subject.CS, "476", false, true);
		Course cs480 = new Course(Subject.CS, "480", true, true);
		Course cs482 = new Course(Subject.CS, "482", false, true);
		Course cs488 = new Course(Subject.CS, "442", true, true);
		
		// Add requirements
		cs149.addPostreq(cs159);
		
		cs159.addPostreq(cs227);
		cs159.addPostreq(cs240);
		cs159.addPostreq(cs261);
		cs159.addPostreq(cs345);
		cs159.addPostreq(cs474);
		cs159.addPostreq(cs480);
		cs159.addCoreq(cs227);
		
		cs227.addPostreq(cs240);
		cs227.addPostreq(cs327);
		
		cs240.addPostreq(cs327);
		cs240.addPostreq(cs349);
		cs240.addPostreq(cs361);
		cs240.addPostreq(cs354);
		cs240.addPostreq(cs430);
		cs240.addPostreq(cs444);
		cs240.addPostreq(cs446);
		cs240.addPostreq(cs452);
		cs240.addPostreq(cs482);
		cs240.addPostreq(cs488);
		
		cs261.addPostreq(cs361);
		cs261.addPostreq(cs430);
		cs261.addPostreq(cs482);
		cs261.addPostreq(cs488);
		
		cs327.addPostreq(cs432);
		cs327.addPostreq(cs452);
		
		cs345.addPostreq(cs347);
		cs345.addPostreq(cs446);
		cs345.addPostreq(cs447);
		cs345.addPostreq(cs474);
		
		cs361.addPostreq(cs432);
		cs361.addPostreq(cs450);
		cs361.addPostreq(cs456);
		cs361.addPostreq(cs457);
		cs361.addPostreq(cs458);
		cs361.addPostreq(cs461);
		cs361.addPostreq(cs462);
		cs361.addPostreq(cs470);
		cs361.addCoreq(cs457);
		
		cs457.addPostreq(cs458);

		cs474.addPostreq(cs475);
		cs474.addPostreq(cs476);
		
		// add courses to major
		csMajor.addCourse(cs101);
		csMajor.addCourse(cs149);
		csMajor.addCourse(cs159);
		csMajor.addCourse(cs227);
		csMajor.addCourse(cs240);
		csMajor.addCourse(cs260);
		csMajor.addCourse(cs261);
		csMajor.addCourse(cs280);
		csMajor.addCourse(cs327);
		csMajor.addCourse(cs330);
		csMajor.addCourse(cs345);
		csMajor.addCourse(cs347);
		csMajor.addCourse(cs349);
		csMajor.addCourse(cs354);
		csMajor.addCourse(cs361);
		csMajor.addCourse(cs430);
		csMajor.addCourse(cs432);
		csMajor.addCourse(cs442);
		csMajor.addCourse(cs444);
		csMajor.addCourse(cs446);
		csMajor.addCourse(cs447);
		csMajor.addCourse(cs450);
		csMajor.addCourse(cs452);
		csMajor.addCourse(cs456);
		csMajor.addCourse(cs457);
		csMajor.addCourse(cs458);
		csMajor.addCourse(cs461);
		csMajor.addCourse(cs462);
		csMajor.addCourse(cs470);
		csMajor.addCourse(cs474);
		csMajor.addCourse(cs475);
		csMajor.addCourse(cs476);
		csMajor.addCourse(cs480);
		csMajor.addCourse(cs482);
		csMajor.addCourse(cs488);
		
		planner.setMajor(csMajor);
		
		return planner;
	}
	
	public static Planner addCisMajor(Planner planner)
	{
		
		
		
		
		return planner;
	}

}
