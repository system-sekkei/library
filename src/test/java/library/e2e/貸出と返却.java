package library.e2e;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Duration;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class 貸出と返却 {
    WebDriver driver;

    @LocalServerPort
    int port;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }



    @Test
    @Timeout(10)
    public void 図書の貸出と返却() {
        // 図書の貸出
        driver.get("http://localhost:%d/loan/register".formatted(port));
        WebElement 会員番号入力欄 = driver.findElement(By.id("memberNumber.value"));
        WebElement 所蔵品番号入力欄 = driver.findElement(By.id("itemNumber.value"));
        WebElement 登録ボタン = driver.findElement(By.id("button-register-loan"));

        会員番号入力欄.sendKeys("1");
        所蔵品番号入力欄.sendKeys("2-A");
        登録ボタン.click();


        // 貸出図書の一覧
        WebElement 貸出リスト表 = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(driver -> driver.findElement(By.id("loan-list")));

        assertThat(driver.getTitle(), is("貸出完了"));
        WebElement 貸出リスト = 貸出リスト表.findElement(By.tagName("tbody"));
        List<WebElement> 貸出図書の一覧 = 貸出リスト.findElements(By.tagName("tr"));

        assertThat(貸出図書の一覧.size(), is(1));

        // 貸出図書の返却画面へ遷移
        WebElement 返却リンク = driver.findElement(By.id("link-return"));
        返却リンク.click();

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(driver -> driver.findElement(By.id("title-return")));

        // 貸出図書の返却
        WebElement 返却所蔵品番号入力欄 = driver.findElement(By.id("itemNumber.value"));
        WebElement 返却登録ボタン = driver.findElement(By.id("button-return"));

        返却所蔵品番号入力欄.sendKeys("2-A");
        返却登録ボタン.click();
    }
}
