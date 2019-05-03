package course_map;

public enum Semester {
	FR_FA, FR_SP, SO_FA, SO_SP, JU_FA, JU_SP, SE_FA, SE_SP;

	public String toString() {
		String s = "";
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
		return s;

	}
}
