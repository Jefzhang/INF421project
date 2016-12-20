
public interface SchoolChoiceInterface {
	//n the number of student
	//m the number of school
	//stuPrefs the students' preference about the school
	//students' priorites for a certain school
	//quota the number of place for the school
	int[] Bostonmech(int n, int m, int[][]stuPrefs, int[][]schoolPrefs, int[]quota);

	int[] Sosmmech(int n, int m, int[][]stuPrefs, int[][]schoolPrefs, int[]quota);

	int[] Ttcmmech(int n, int m, int[][]stuPrefs, int[][]schoolPrefs, int[]quota);

}
