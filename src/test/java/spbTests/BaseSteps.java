package spbTests;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class BaseSteps {
    private final SelenideElement logInButton = $x("//button[@id='login-button']");
    private final SelenideElement userNameInput = $x("//input[@name='username']");
    private final SelenideElement passwordInput = $x("//input[@name='password']");
    private final SelenideElement smsInput = $x("//input[@id='otp-code']");
    private final SelenideElement codeButton = $x("//button[@id='login-otp-button']");
    private final SelenideElement appName = $x("//div[@class='environment print-hidden']");
    private final static SelenideElement logoImg = $x("//*[@id='logo']");
    private final SelenideElement curAva = $x("//a[@id='user-avatar']");
    private final SelenideElement newAva = $x("//div[@id='avatars']//img[@data-avatar='24.png']");
    private final SelenideElement labelAva = $x("//div[@id='avatars-form']/label");
    private final SelenideElement saveAva = $x("//div[@class='form-actions']/button");
    private final SelenideElement errorAv =  $x("//div[@class='alert alert-error']");

    private final SelenideElement cardClick  =  $x("//a[@id='cards-overview-index']");
    private final SelenideElement cardBlock =  $x("//a[@class='card-block']");
    private final SelenideElement labelCardBlock =  $x("//input[@onclick=\"$('#card-block-disclaimer').slideUp()\"]");
    private final SelenideElement buttonCardBlock =  $x("//button[@id='block-card']");
    private final SelenideElement smsInput1 = $x("//input[@id='otp-input']");
    private final SelenideElement confirm = $x("//button[@id='confirm']");
    private final SelenideElement alertBlock = $x("//div[@class='alert alert-success']");
    private final SelenideElement unlockcardClick = $x("//a[@class='card-unblock']");
    private final SelenideElement b = $x("//div[@class='alert alert-success']/b");
    ;
    private final SelenideElement alertUnlock = $x("//div[@class='alert alert-success']");

    @Step("Проверка наименования приложения")  // Анотация бибилиотеки Allure позволяет выделать в отдельный степ (Шаг) какое-то действие/проверку в автотесте
    // также библиотека Allure считает анотации Before, After, Description
    void appCheckNmae(){
    //appName.shouldHave(text("bank")); // матчеры интегрированные в селенид. Делает скриншот
    assertThat("Не соответствует текст", appName.getText(),containsString("Интернет-банк")); // матчеры как отдельная библиотека. Не делает скриншот
}
    @Step("Изменить аватар")  // Анотация бибилиотеки Allure позволяет выделать в отдельный степ (Шаг) какое-то действие/проверку в автотесте
        // также библиотека Allure считает анотации Before, After, Description
    void setNewAva(){
        curAva.click();
        switchTo().frame($x("(//div[@id='contentbar']/iframe)"));
        labelAva.shouldBe(visible);
        labelAva.shouldHave(text("Аватар"));
        newAva.click();
        saveAva.click();
    }
    @Step("Вход")
     void login() {
        userNameInput.shouldBe(visible).val("demo");
        passwordInput.shouldBe(visible).val("demo");
        logInButton.shouldBe(visible).click();
        smsInput.shouldBe(visible).val("0000");
        codeButton.shouldBe(visible).click();
        logoImg.shouldBe(visible);
        appName.shouldBe(visible, Duration.ofSeconds(10));
        appCheckNmae();
    }
    @Step("Проверка сообщения о невозмоности изменения настроек")
    void errorAva (){
        errorAv.shouldBe(visible);
        errorAv.shouldHave(text("Демо-пользователь не может менять настройки."));
    }
    @Step("Выйти из фрейма")  // Анотация бибилиотеки Allure позволяет выделать в отдельный степ (Шаг) какое-то действие/проверку в автотесте
    // также библиотека Allure считает анотации Before, After, Description
    void frameOut(){
        switchTo().defaultContent();
        logoImg.shouldBe(visible).click();
    }
    //@AfterEach
    //void after() {
    // closeWebDriver();
    //}
    @Step("Временная блокировка карты Travel")  // Анотация бибилиотеки Allure позволяет выделать в отдельный степ (Шаг) какое-то действие/проверку в автотесте
    // также библиотека Allure считает анотации Before, After, Description
    void blockCard(){
        cardClick.shouldBe(visible).click();
        cardBlock.shouldBe(visible).click();
        labelCardBlock.shouldBe(visible).click();
        buttonCardBlock.shouldBe(visible).click();
        switchTo().frame($x("(//div[@class='modal-footer confirmation']/iframe)"));
        smsInput1.shouldBe(visible).val("0000");
        confirm.shouldBe(visible).click();
        switchTo().defaultContent();
        alertBlock.shouldBe(visible);
        //String number = alertBlock.getText();

       alertBlock.shouldHave(text("Карта " + b.getText() +" успешно заблокирована.")); // b - извлечение номера карты, substring не помогает
    }
    @Step("Разблокировка карты")  // Анотация бибилиотеки Allure позволяет выделать в отдельный степ (Шаг) какое-то действие/проверку в автотесте
        // также библиотека Allure считает анотации Before, After, Description
    void unlockCard(){
        unlockcardClick.shouldBe(visible).click();
        switchTo().frame($x("(//div[@class='modal-footer']/span/iframe)"));
        smsInput1.shouldBe(visible).val("0000");
        confirm.shouldBe(visible).click();
        alertBlock.shouldBe(visible);
        alertBlock.shouldHave(text("Карта " + b.getText() + " успешно разблокирована."));
    }
}
