import com.wenka.sqlite.demo.SqliteApplication;
import com.wenka.sqlite.demo.dao.StudentDao;
import com.wenka.sqlite.demo.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * Created with IDEA
 *
 * @author wenka wkwenka@gmail.com
 * @date 2020/04/03  下午 03:32
 * @description:
 */
@SpringBootTest(classes = SqliteApplication.class)
@RunWith(SpringRunner.class)
public class StudentTest {

    private int total = 10;
    @Autowired
    private StudentDao studentDao;

    @Test
    public void test() {

        List<Student> studentList = new LinkedList<>();
        for (int i = 0; i < total; i++) {
            studentList.add(new Student(UUID.randomUUID().toString(), "AA" + i, 22, new Date(),null));
        }
        long begin = System.currentTimeMillis();
        this.studentDao.saveAll(studentList);
        System.out.println("---------------------------");
        System.out.println(System.currentTimeMillis() - begin);
    }

    @Test
    public void testSelect() {
        long begin = System.currentTimeMillis();
//        List<Student> all = this.studentDao.findAll();
        Optional<Student> optional = this.studentDao.findById("3bd951eb-b97a-4559-bd62-167318e14417");
        Student student = optional.get();
        System.out.println(System.currentTimeMillis() - begin);
//        for (Student student : all) {
//            System.out.println(student);
//        }
    }
}
