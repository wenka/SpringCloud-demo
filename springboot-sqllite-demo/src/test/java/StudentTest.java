import com.wenka.sqlite.demo.SqliteApplication;
import com.wenka.sqlite.demo.dao.StudentDao;
import com.wenka.sqlite.demo.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

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

    private int total = 10000;
    @Autowired
    private StudentDao studentDao;

    @Test
    public void test() {

        List<Student> studentList = new LinkedList<>();
        for (int i = 0; i < total; i++) {
            studentList.add(new Student(UUID.randomUUID().toString(), "AA" + i, 22, new Date()));
        }
        long begin = System.currentTimeMillis();
        this.studentDao.saveAll(studentList);
        System.out.println("---------------------------");
        System.out.println(System.currentTimeMillis() - begin);
    }

    @Test
    public void testSelect() {
        long begin = System.currentTimeMillis();
        List<Student> all = this.studentDao.findAll();
        System.out.println("---------------------------" + all.size());
        System.out.println(System.currentTimeMillis() - begin);
//        for (Student student : all) {
//            System.out.println(student);
//        }
    }
}
