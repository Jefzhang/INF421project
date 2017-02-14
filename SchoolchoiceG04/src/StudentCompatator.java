import java.util.Comparator;

/**
 * Created by jfzhang on 08/01/2017.
 */
public class StudentCompatator implements Comparator<Student> {
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

}
