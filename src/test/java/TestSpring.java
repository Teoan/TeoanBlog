/**
 * 　 @author Teoan
 *
 * @date 2020/1/16 12:56
 */


import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-mvc.xml","classpath:spring/spring-mybatis.xml"})
@WebAppConfiguration("src/main/webapp")
public class TestSpring {
}
