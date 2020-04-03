import com.wenka.sqlite.demo.SqliteApplication;
import com.wenka.sqlite.demo.dao.StudentDao;
import com.wenka.sqlite.demo.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
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

    @Autowired
    private StudentDao studentDao;

    @Test
    public void test() {
        this.studentDao.save(new Student(UUID.randomUUID().toString(), "C", 22, new Date()));
    }

    @Test
    public void testSelect() {
        List<Student> all = this.studentDao.findAll();
        for (Student student : all) {
            System.out.println(student);
        }
    }
}
