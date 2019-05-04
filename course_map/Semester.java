package course_map;

/**
 * Semester enum
 * 
 * @author Zeru Tadesse
 *
 */
public enum Semester {
	FR_FA, FR_SP, SO_FA, SO_SP, JU_FA, JU_SP, SE_FA, SE_SP;
	/**
	 * Get the name of enum for readability (outputs)
	 */
	public String toString() {
		String s = "--";
		if (this != null) {
			switch (this) {
			case FR_FA:
				s = "Freshman Fall";
				break;
			case FR_SP:
				s = "Freshman Spring";
				break;
			case SO_FA:
				s = "Sophmore Fall";
				break;
			case SO_SP:
				s = "Sophmore Spring";
				break;
			case JU_FA:
				s = "Junior Fall";
				break;
			case JU_SP:
				s = "Junior Spring";
				break;
			case SE_FA:
				s = "Senior Fall";
				break;
			case SE_SP:
				s = "Senior Spring";
				break;
			}
		}
		return s;

	}

	/**
	 * 
	 * @param year Invalid = 0 Freshman = 1
	 * @param sem  Invalid = 0, FA = 1, SP=2
	 * @return
	 */
	public Semester getSemester(int year, int sem) {
		Semester semester = FR_FA;
		switch (year) {
		case 1:
			semester = sem == 1 ? FR_FA : FR_SP;
			break;
		case 2:
			semester = sem == 1 ? SO_FA : SO_SP;
			break;
		case 3:
			semester = sem == 1 ? JU_FA : JU_SP;
			break;
		case 4:
			semester = sem == 1 ? SE_FA : SE_SP;
			break;
		}
		return semester;
	}

}
