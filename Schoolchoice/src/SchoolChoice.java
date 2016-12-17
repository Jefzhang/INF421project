import java.util.*;
import java.lang.Math;

public class SchoolChoice implements SchoolChoiceInterface {

	@Override
	public int[] Bostonmech(int n, int m, int[][] stuPrefs, int[][] schoolPrefs, int[] quota) {
		// TODO Auto-generated method stub
		int[] choice = new int[n];    //store the final choice of every student
		Stack<Integer> stuNonAllocated = new Stack<Integer>();   // students who are not allocated
		int[][] schoolPrefsInver = new int[m][n];                //matrix which store the preference to some student,facilitate the search 
		int[] quotaRemain = quota;     // store the remain seats of every school after a round

		for (int i = 1; i <=n; i++) {       //initialization 
			stuNonAllocated.push(i);
			choice[i-1] = -1;
		}
		for (int i = 0; i < m; i++) {      //calculate the inverse matrix
			for (int j = 0; j < n; j++)
				schoolPrefsInver[i][schoolPrefs[i][j]-1] = j+1;
		}
		int num = 0;      //number of students allocated

		for (int k = 0;; k++) {                      //loop
			//we create a map to store informations: 
			//***************key is some school id, and the corresponding value is a student class
			Map<Integer, ArrayList<Student>> stubyschool = new HashMap<Integer, ArrayList<Student>>();
			while (!stuNonAllocated.isEmpty()) {
				int stu = stuNonAllocated.pop();        //student id
				int school = stuPrefs[stu-1][k];        // his k-th choice 
				if (quotaRemain[school-1] > 0) {        //if there is still seat for his school chose, we add it to the list 
					if (!stubyschool.containsKey(school)) { 
						stubyschool.put(school, new ArrayList<Student>());
					}
					stubyschool.get(school).add(new Student(stu, schoolPrefsInver[school-1][stu-1]));
				}
				else stuNonAllocated.push(stu);         //if not, return back it to the stack for the next round
			}
			Iterator<Integer> keyiter = stubyschool.keySet().iterator(); 
			while (keyiter.hasNext()) {
				int schoolid = keyiter.next();
				ArrayList<Student> schlist = stubyschool.get(schoolid);
				if (schlist.size() > quotaRemain[schoolid-1]) {   //if the number of applicants is more of the quota of this school, 
					                                              //this school has to rank them according to the preference and then choose
					Collections.sort(schlist, new Comparator<Student>() {            //in fact, k-th biggest algorithm can do better here 
						@Override
						public int compare(Student s1, Student s2) {
							int numbera = s1.PrefforSchool;
							int numberb = s2.PrefforSchool;
							if (numberb > numbera) {
								return -1;
							} else if (numberb < numbera) {
								return 1;
							} else {
								return 0;
							}
						}
					});
				}
				int numseat = Math.min(quotaRemain[schoolid-1], schlist.size());
				for (int i = 0; i < numseat; i++) {        //select
					choice[schlist.remove(0).id-1] = schoolid;
					num++;
				}
				quotaRemain[schoolid-1] -= numseat;
				while (!schlist.isEmpty()) {
					stuNonAllocated.push(schlist.remove(0).id);
				}
			}
			if (num == n)
				break;
		}

		return choice;
	}

}
