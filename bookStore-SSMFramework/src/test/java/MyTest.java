import com.quan.entity.Books;
import com.quan.service.BookService;
import com.quan.service.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @ClassName: MyTest
 * @Description:
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2021/11/9 19:09
 */
public class MyTest {
    @Test
    public void test01() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookServiceImpl = context.getBean("bookServiceImpl", BookService.class);
        List<Books> booksList = bookServiceImpl.queryAllBook();

        for (Books books : booksList) {
            System.out.println(books);
        }
    }
}
