import com.zhou.DemoApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * zhousecurity
 * 2019/11/1 10:28
 *
 * @author ChristChou
 * @since
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)//该class是SpringBoot项目的Application
@ActiveProfiles("test")
@WebAppConfiguration
@TestPropertySource({//引用的配置文件
        "classpath:/application.properties"
})
public abstract class SpringBootTestAbstract {
}