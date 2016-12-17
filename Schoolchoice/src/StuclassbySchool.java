import java.util.*;


public class StuclassbySchool {           //student set classfiled by school
	public int schoolid;
	public List<Student> students = new ArrayList<Student>();
	public StuclassbySchool(int school, Student s){
		this.schoolid=school;
		//this.students=l;
		this.students.add(s);
	}
}
