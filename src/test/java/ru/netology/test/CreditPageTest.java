package ru.netology.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.CardInfo;
import ru.netology.data.DbUtils;
import ru.netology.page.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataGenerator.*;


public class CreditPageTest {


    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        Configuration.headless = true;
        open("http://localhost:8080");
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
       DbUtils.deleteTables();
    }

    @Test
    void shouldPositiveAnswerByApprovedCard() {
        val startPage = new StartPage();
        CardInfo card = new CardInfo(
                getFirstCardNumber(),
                getMonthCard(1),
                getYearCard(0),
                getCvc(),
                getOwnerCard());
        var paymentPage = startPage.payment();
        paymentPage.getFillCardDetails(card);
        paymentPage.successfulPaymentDebitCardNotification();
        var actual = DbUtils.getStatusCredit();
        assertEquals("APPROVED", actual);
    }
}
