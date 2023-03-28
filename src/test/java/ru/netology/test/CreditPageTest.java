package ru.netology.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.CardInfo;
import ru.netology.data.DbInteractionDbUtils;
import ru.netology.page.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataGenerator.*;
import static ru.netology.data.DbInteractionDbUtils.*;



public class CreditPageTest {


    //@BeforeAll
    //static void setUpAll() {
//        SelenideLogger.addListener("allure", new AllureSelenide());
//    }

    @BeforeEach
    public void setUp() {
        Configuration.headless = false;
        open("http://localhost:8080");
    }

    @Test
    void shouldPositiveAnswerByApprovedCard() {
        var startPage = new StartPage();
        CardInfo card = new CardInfo(
                getFirstCardNumber(),
                getMonthCard(1),
                getYearCard(0),
                getCvc(),
                getOwnerCard());
        var paymentPage = startPage.payment();
        paymentPage.getFillCardDetails(card);
        paymentPage.successfulPaymentDebitCard();
        String actual = DbInteractionDbUtils.getStatus("2");
        assertEquals("APPROVED", actual);
    }
}
