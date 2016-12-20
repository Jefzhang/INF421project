
public class Main {
	
	public static void main(String[] args){
		int n =8;
		int m = 4;
		int q[]={2,2,3,3};
		int schoolPrefs[][] = {{1,2,3,4,5,6,7,8},
		                   {3,5,4,8,7,2,1,6},
		                   {5,3,1,7,2,8,6,4},
		                   {6,8,7,4,2,3,5,1}};
		int stuPrefs[][] = {{2,1,3,4},{1,2,3,4},{3,2,1,4},{3,4,1,2},
		                   {1,3,4,2},{4,1,2,3},{1,2,3,4},{1,2,4,3}};
		SchoolChoice s = new SchoolChoice();
		//int[] result = s.Bostonmech(n,m,stuPrefs,schoolPrefs,q);
		//int[] result = s.Sosmmech(n,m,stuPrefs,schoolPrefs,q);
		int [] result = s.Ttcmmech(n,m,stuPrefs,schoolPrefs,q);
		for(int i=0;i<n;i++){
		System.out.println(result[i]);
		}
	}

}
