import java.lang.reflect.Array;
import java.util.*;
import java.lang.Math;

public class SchoolChoice implements SchoolChoiceInterface {

	@Override
	public int[] Bostonmech(int n, int m, int[][] stuPrefs, int[][] schoolPrefs, int[] quota) {
		// TODO Auto-generated method stub
		int[] choice = new int[n];    //store the final choice of every student
		Queue<Integer> stuNonAllocated = new LinkedList<>();// students who are not allocated
		int[][] schoolPrefsInver = new int[m][n];                //matrix which store the preference to some student,facilitate the search 
		int[] quotaRemain = quota;     // store the remain seats of every school after a round

		for (int i = 1; i <=n; i++) {       //initialization 
			stuNonAllocated.add(i);
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
			int sizeQueue = stuNonAllocated.size();
			for(int i=0;i<sizeQueue;i++){
			//while (!stuNonAllocated.isEmpty()) {
				int stu = stuNonAllocated.poll();        //student id
				int school = stuPrefs[stu-1][k];        // his k-th choice 
				if (quotaRemain[school-1] > 0) {        //if there is still seat for his school chose, we add it to the list 
					if (!stubyschool.containsKey(school)) { 
						stubyschool.put(school, new ArrayList<Student>());
					}
					stubyschool.get(school).add(new Student(stu, schoolPrefsInver[school-1][stu-1]));
				}
				else stuNonAllocated.add(stu);         //if not, return back it to the stack for the next round
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
					stuNonAllocated.add(schlist.remove(0).id);
				}
			}
			if (num == n)
				break;
		}

		return choice;
	}

	@Override
	public int[] Sosmmech(int n, int m, int[][] stuPrefs, int[][] schoolPrefs, int[] quota){
		int[] choice = new int[n];    //store the final choice of every student
		Stack<Integer> stuNonAllocated = new Stack<Integer>();   // students who are not allocated
		int[][] schoolPrefsInver = new int[m][n];                //matrix which store the preference to some student,facilitate the search
		//int[] quotaRemain = quota;     // store the remain seats of every school after a round
		int[] stuchoiceindex = new int[n];

		for (int i = 1; i <=n; i++) {       //initialization
			stuNonAllocated.push(i);
			choice[i-1] = -1;
			stuchoiceindex[i-1] = 0;

		}
		for (int i = 0; i < m; i++) {      //calculate the inverse matrix
			for (int j = 0; j < n; j++)
				schoolPrefsInver[i][schoolPrefs[i][j]-1] = j+1;
		}
		//int num = 0;      //number of students allocated
		Map<Integer, ArrayList<Student>> stubyschool = new HashMap<Integer, ArrayList<Student>>();
		/*Comparator<Student> stuorder = new Comparator<Student>() {
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
		};*/
		//Map<Integer, PriorityQueue(1,stuorder)> stubyschool = new HashMap<Integer, PriorityQueue(1,stuorder)>();
		while (true){                      //loop
			//we create a map to store informations:
			//***************key is some school id, and the corresponding value is a student class
			//Map<Integer, ArrayList<Student>> stubyschool = new HashMap<Integer, ArrayList<Student>>();
			int num=0;
			while (!stuNonAllocated.isEmpty()) {
				int stu = stuNonAllocated.pop();        //student id
				int school = stuPrefs[stu-1][stuchoiceindex[stu-1]];        // his k-th choice
				if (!stubyschool.containsKey(school)) {
					stubyschool.put(school, new ArrayList<Student>());
				}
				studentListInsert(stubyschool.get(school),new Student(stu, schoolPrefsInver[school-1][stu-1]));
			}
			Iterator<Integer> keyiter = stubyschool.keySet().iterator();
			while (keyiter.hasNext()) {
				int schoolid = keyiter.next();
				ArrayList<Student> schlist = stubyschool.get(schoolid);
				int numseat = Math.min(quota[schoolid-1], schlist.size());
				for (int i = 0; i < numseat; i++) {        //select
					choice[schlist.get(i).id-1] = schoolid;
				}
				if(numseat < schlist.size()){
					for(int i=numseat;i<schlist.size();i++){
						int stuid = schlist.remove(i).id;
						stuNonAllocated.push(stuid);
						stuchoiceindex[stuid-1] +=1;
					}
				}
				num = num +numseat;
				}

			if (num == n)
				break;
		}
		return choice;
	}

	@Override
	public int[] Ttcmmech(int n, int m, int[][] stuPrefs, int[][] schoolPrefs, int[] quota) {
		int[] choice = new int[n];
		int num = 0;     //keep track the num of students who have been allocated
		Map<Integer, ArrayList<Integer>> stuRemain = new HashMap<Integer, ArrayList<Integer>>();    //for storing the students who are not allocated
		Map<Integer, ArrayList<Integer>> schRemain = new HashMap<Integer, ArrayList<Integer>>();  //for storing schools who have seats remains
		int [] counter = new int[m];          //keep track how many seats are still available at this school

		for(int i=0;i<n;i++){                    //initialization
			choice[i] = -1;
			ArrayList<Integer> ls = new ArrayList<>();
			for(int j=0; j<m; j++){
				ls.add(stuPrefs[i][j]);
			}
			stuRemain.put(i+1,ls);
		}

		for(int i=0;i<m;i++){             //initialization
			counter[i] = quota[i];
			ArrayList<Integer> ls = new ArrayList<>();
			for(int j=0;j<n;j++){
				ls.add(schoolPrefs[i][j]);
			}
			schRemain.put(i+1,ls);
		}
		while (!stuRemain.isEmpty()){            //if there are still students who are not allocated
            HashMap<Integer,Integer> stuChoice = new HashMap<>();        //for storing the new matching pairs in this step
			findAllCircles(stuRemain,schRemain,stuChoice);              //find all the circles in this step and find the ner matching pairs
			num += stuChoice.keySet().size();
			Iterator<Integer> keyStu = stuChoice.keySet().iterator();    //use the stuChoice for updating the final choice table and the remaining schools and students
			while (keyStu.hasNext()){
				int stuId = keyStu.next();
				int schId = stuChoice.get(stuId);
				choice[stuId-1] = schId;       //update the student choice table
				if(num!=n){
					updateRemain(stuId,schId,stuRemain,schRemain,counter);   //updating the remaining schools and students
				}
			}


		}

		return choice;



	}

	public static void studentListInsert(ArrayList<Student> ls, Student s) {             //insert sorting
	    int index=0;
		for(int i=0;i<ls.size();i++) {
			if (s.PrefforSchool <= ls.get(i).PrefforSchool) {
				index = i;
				break;
			}
		}
		ls.add(index, s);

    }

    public static void findAllCircles(Map<Integer, ArrayList<Integer>> stuRemain,Map<Integer, ArrayList<Integer>>schRemain,HashMap<Integer, Integer> stuChoice){
		Map<Integer,Boolean>  schRead = new HashMap<>();                 //keep track if a certain school node has been read
		Iterator<Integer> schKey = schRemain.keySet().iterator();        //initialization
		while(schKey.hasNext()){
			int schId = schKey.next();
			schRead.put(schId,false);
		}
		Iterator<Integer> keyIter = schRemain.keySet().iterator();
		while(keyIter.hasNext()){                                     //ensure that every school node will be considered
			int schId = keyIter.next();
			Stack<Integer> schStack = new Stack<>();               //store the non-read nodes which can be reached from this school node
			strongConnected(schId,schRead,stuRemain,schRemain,stuChoice,schStack);      // find the circle which include this school node if exist
			while(!schStack.isEmpty()){                //update
				int term = schStack.pop();
				schRead.replace(term,true);
			}
		}


	}

	public static void strongConnected(int schId, Map<Integer,Boolean>schRead, Map<Integer, ArrayList<Integer>> stuRemain,Map<Integer, ArrayList<Integer>>schRemain,
									   Map<Integer,Integer> stuChoice, Stack<Integer> schStack){
    	if(!schRead.get(schId)){
    		if(!schStack.contains(schId)){     //if it's not read
    			schStack.push(schId);
    			//schRead[schId-1] = true;
    			int stuId = schRemain.get(schId).get(0);
    			int schNext = stuRemain.get(stuId).get(0);
    			strongConnected(schNext,schRead,stuRemain,schRemain,stuChoice,schStack);
			}
			else{       //if it is already in the stack, we can find a circle
    			int sch = schStack.pop();
    			int w = schId;
    			while(sch!=schId){
    				int stu = schRemain.get(sch).get(0);
    				stuChoice.put(stu,w);
    				schRead.replace(sch,true);
    				w=sch;
    				sch = schStack.pop();
				}
				schRead.replace(schId,true);
				stuChoice.put(schRemain.get(schId).get(0),w);
			}

		}

	}

	public  static void updateRemain(int stuId, int schId, Map<Integer, ArrayList<Integer>> stuRemain, Map<Integer, ArrayList<Integer>>schRemain, int [] counter){
		stuRemain.remove(stuId);
		counter[schId-1] -= 1;
		if(counter[schId-1]<= 0 ){            //remove this school from the list
			schRemain.remove(schId);
			Iterator<Integer> keyStu = stuRemain.keySet().iterator();
			while(keyStu.hasNext()){
				int stuKey = keyStu.next();
				ArrayList<Integer> schList = stuRemain.get(stuKey);
				int index = schList.indexOf(schId);
				schList.remove(index);
			}
		}
		Iterator<Integer> keySch = schRemain.keySet().iterator();
		while(keySch.hasNext()){
			int schKey = keySch.next();
			ArrayList<Integer> stuList = schRemain.get(schKey);
			int index = stuList.indexOf(stuId);
			stuList.remove(index);
		}
	}




}
