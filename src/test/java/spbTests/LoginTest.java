package spbTests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.*;

public class LoginTest extends BaseTests {
   @Test
    @Description("Авторизация в интернет банке БСБП")
   void loginTest1(){
       baseSteps.setNewAva();
       baseSteps.errorAva();
       baseSteps.frameOut();
   }

    @Test
    @Description("Авторизация в интернет банке БСБП")
    void loginTest2(){
        baseSteps.blockCard();
        baseSteps.unlockCard();
    }
}



