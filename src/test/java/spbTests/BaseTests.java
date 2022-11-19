package spbTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class BaseTests {
    public  BaseSteps baseSteps;
    private final static SelenideElement logoImg = $x("//*[@id='logo']");
    private final static String baseUrl = "https://idemo.bspb.ru/";
    @BeforeAll // перед выполнением всех тестов
    static void beforeConfig(){
        Configuration.timeout = 3000;
        Configuration.browserSize = "1920x1080";
    }
    @BeforeEach // перед выполнением каждого теста, @ -аннотация
    public void before(){
        open(baseUrl);
        logoImg.shouldBe(visible);
        baseSteps = new BaseSteps();
        baseSteps.login();
    };
    @AfterEach
    void after() {
        closeWebDriver();
    }
}
