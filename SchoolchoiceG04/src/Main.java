
import  java.io.*;
import java.util.Scanner;
public class Main {
	
	public static void main(String[] args){
		int n =Integer.parseInt(args[0]);
		//int n = args[0];
		int m =Integer.parseInt(args[1]);
		int [] quota = new int[m];
		int[][] stuPrefs = new int[n][m];
		int[][] schoolPrefs = new int[m][n];
		int [] choice = new int [n];
		SchoolChoice s = new SchoolChoice();
		String data = args[2];
		String mech = args[3];
		//String mech = args[2];
		//System.out.print(test);
		    try {
				String pathname = data;
				File filename = new File(pathname); //
				InputStreamReader reader = new InputStreamReader(
						new FileInputStream(filename)); //
				BufferedReader br = new BufferedReader(reader); //
				String line;
				line = br.readLine();
				while (line != null) {
					switch (line) {
						/*case "Number of students:":{
							n = Integer.parseInt(br.readLine());
							break;
						}
						case "Number of schools:": {
							m = Integer.parseInt((br.readLine()));
							break;
						}*/
						case "Number of seats for schools:":{
							//System.out.print(m);
							//int quota[] = new int[m];
							//System.out.println(br.readLine());
							Scanner scan = new Scanner(br.readLine());
							for(int i=0;i<m;i++){
								quota[i] = Integer.parseInt(scan.findInLine("\\d"));
								//System.out.println(quota[i]);
							}
							scan.close();
							break;

						}
						case "Preference of the students:":{
							//int stuPrefs[][] = new int[n][m];
							for(int i=0;i<n;i++){
								Scanner scan = new Scanner(br.readLine());
								for(int j=0;j<m;j++){
									stuPrefs[i][j] = Integer.parseInt(scan.findInLine("\\d"));
								}
								scan.close();
							}
							break;

						}

						case "Preference of the schools:":{
							//int schPrefs[][] = new int [m][n];
							for(int i=0;i<m;i++){
								Scanner scan = new Scanner(br.readLine());
								for(int j=0;j<n;j++){
									schoolPrefs[i][j] = Integer.parseInt((scan.findInLine("\\d")));
								}
								scan.close();
							}
							break;
						}
						default:break;
					}

					line = br.readLine(); //
				}
				br.close();

				/*File writename = new File("output.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件
				if(!writename.exists()) {
					writename.createNewFile();
				}*/
				BufferedWriter out = new BufferedWriter(new FileWriter("output.txt",true));
				switch (mech){
					case "boston":{
						choice = s.Bostonmech(n,m,stuPrefs,schoolPrefs,quota);
						out.write("We use the Boston mechanism to calculate the matching\r\n");
						out.write("The final school choice calculated for every student is:\r\n");
						for(int i=0;i<n;i++){
							out.write(String.valueOf(choice[i]));
							out.write(',');
						}
						out.write("\r\n");
						out.flush();
						out.close();
						break;
					}
					case "sosm":{
						choice = s.Sosmmech(n,m,stuPrefs,schoolPrefs,quota);
						out.write("We use the Gale-Shapley Mechanism to calculate the matching\r\n");
						out.write("The final school choice calculated for every student is:\r\n");
						for(int i=0;i<n;i++){
							out.write(String.valueOf(choice[i]));
							out.write(',');
						}
						out.write("\r\n");
						out.flush();
						out.close();
						break;
					}
					case "ttcm":{
						choice = s.Ttcmmech(n,m,stuPrefs,schoolPrefs,quota);
						out.write("We use the Top Trading Cycles Mechanism to calculate the matching\r\n");
						out.write("The final school choice calculated for every student is:\r\n");
						for(int i=0;i<n;i++){
							out.write(String.valueOf(choice[i]));
							out.write(',');
						}
						out.write("\r\n");
						out.flush();
						out.close();
						break;
					}
					default:{
						throw new Error("You should choose at least one of the three mechanisms: boston, sosm and ttcm");
					}
				}

            /* 写入Txt文件 */
				/*File writename = new File("output.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件
				writename.createNewFile(); // 创建新文件
				BufferedWriter out = new BufferedWriter(new FileWriter(writename));
				out.write("我会写入文件啦\r\n"); // \r\n即为换行
				out.flush(); // 把缓存区内容压入文件
				out.close(); // 最后记得关闭文件*/

			} catch (Exception e) {
				e.printStackTrace();
			}

		//System.out.println(n);

		//


		//int[] result = s.Sosmmech(n,m,stuPrefs,schoolPrefs,q);
		//int [] result = s.Ttcmmech(n,m,stuPrefs,schoolPrefs,q);
		/*for(int i=0;i<n;i++){
		System.out.println(result[i]);
		}*/
	}

}
