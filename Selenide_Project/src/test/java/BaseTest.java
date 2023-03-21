import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    @BeforeMethod
    public void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10;
        open("https://www.yakaboo.ua/");
    }

    @AfterMethod
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
