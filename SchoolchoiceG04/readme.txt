How to run this program ?

Method 1(Terminal, .jar)
1,In ./bin directory, we have provided the Schoolchoice.jar file, you can run it in the terminal with
  the command like:

  java -jar Schoolchoice.jar 8 4 data.txt boston

  Interpretation of the arguments:
  8 : the number of students
  4 : the number of schools
  data.txt : give the preference information and schools' quotas,
             it should be constructed like:

             Number of seats for schools:
             2，2，3，3;
             Preference of the students:
             2,1,3,4;
             1,2,3,4;
             3,2,1,4;
             3,4,1,2;
             1,3,4,2;
             4,1,2,3;
             1,2,3,4;
             1,2,4,3;
             Preference of the schools:
             1,2,3,4,5,6,7,8;
             3,5,4,8,7,2,1,6;
             5,3,1,7,2,8,6,4;
             6,8,7,4,2,3,5,1;

             We have given an exemple of data.txt in the bin/ directory. If data.txt is  
             not in the same directory of Schoolchoice.jar, you should give the fullpath    
             of it.
   boston : which mechanism you choose,
               boston for Boston mechanism;
               sosm for Gale-Shapley Student Optimal Stable Mechanism
               ttcm for Top Trading Cycles Mechanism

2, you can get a output.txt file in the same directory of Schoolchoice.jar, which give the final choice
   for all the students.
   It's like that:

   We use the Boston mechanism to calculate the matching
   The final school choice calculated for every student is:
   2,1,3,3,1,4,3,2,

Method 2(Eclipse):
   1,Off course you can utilize Eclipse to run the project;
   2,Notice that you need add four arguments before running it.
