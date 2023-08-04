package library.e2e;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Duration;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class 予約の管理 {
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
    public void 図書の予約_取置_貸出_返却() {
        // メニュー画面
        driver.get("http://localhost:%d".formatted(port));
        {
            // 予約画面 (図書の検索)
            WebElement 本の予約リンク = driver.findElement(By.id("link-reservation-for-member"));
            本の予約リンク.click();
        }

        // 予約画面 (図書の検索)
        {
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(driver -> driver.findElement(By.id("header-search-result")));

            WebElement キーワード入力欄 = driver.findElement(By.id("value"));
            キーワード入力欄.sendKeys("現場");

            WebElement 検索ボタン = driver.findElement(By.id("button-search"));
            検索ボタン.click();
        }

        // 予約画面 (図書の検索結果)
        {
            WebElement 検索結果表 = new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(driver -> driver.findElement(By.id("table-search-result")));

            List<WebElement> 検索結果一覧 = 検索結果表.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
            assertThat(検索結果一覧.size(), is(1));
            WebElement 予約リンク = 検索結果一覧.get(0).findElements(By.tagName("td")).get(0).findElement(By.tagName("a"));

            予約リンク.click();
        }

        // 予約画面
        {
            WebElement 予約ボタン = new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(driver -> driver.findElement(By.id("button-reserve")));

            WebElement 会員番号入力欄 = driver.findElement(By.id("value"));
            会員番号入力欄.sendKeys("1");
            予約ボタン.click();
        }

        // 予約完了
        {
            WebElement 完了メッセージ = new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(driver -> driver.findElement(By.className("notification")));

            assertThat(完了メッセージ.getText(), is("本を予約しました。"));

            WebElement ホームリンク = driver.findElement(By.id("link-to-home"));
            ホームリンク.click();
        }

        // ホーム画面
        {
            WebElement 予約の管理リンク = new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(driver -> driver.findElement(By.id("link-reservations")));

            予約の管理リンク.click();
        }

        // 予約の管理画面
        {
            var 予約表 = new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(driver -> driver.findElement(By.id("table-reservations")));

            List<WebElement> 予約の一覧 = 予約表.findElement(new By.ByTagName("tbody")).findElements(By.tagName("tr"));

            assertThat(予約の一覧.size(), is(1));

            WebElement 取置ボタン = 予約の一覧.get(0).findElements(By.tagName("button")).get(0);

            取置ボタン.click();
        }

        // 取置き画面
        {
            WebElement 取置き実行ボタン = new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(driver -> driver.findElement(By.id("button-retention")));

            WebElement 所蔵品番号入力欄 = driver.findElement(By.id("item"));
            所蔵品番号入力欄.sendKeys("1-A");

            取置き実行ボタン.click();
            // ホーム画面
            WebElement ホームリンク = driver.findElement(By.id("link-to-home"));
            ホームリンク.click();
        }


        // メニュー画面
        {
            WebElement 取置きの管理リンク = new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(driver -> driver.findElement(By.id("link-retentions")));

            取置きの管理リンク.click();
        }

        // 取置き画面
        {
            WebElement 取置き表 = new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(driver -> driver.findElement(By.id("table-retentions")));
            List<WebElement> 取置一覧 = 取置き表.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
            assertThat(取置一覧.size(), is(1));

            WebElement 貸出ボタン = 取置一覧.get(0).findElement(By.tagName("button"));
            貸出ボタン.click();

            // ホーム画面
            WebElement ホームリンク = driver.findElement(By.id("link-to-home"));
            ホームリンク.click();
        }

        // メニュー画面
        {
            WebElement 貸出と返却リンク = new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(driver -> driver.findElement(By.id("link-loan-return")));
            貸出と返却リンク.click();
        }

        // 貸出と返却画面
        {
            WebElement 返却リンク = new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(driver -> driver.findElement(By.id("link-return")));
            返却リンク.click();
        }

        // 貸出図書の返却
        {
            WebElement 返却登録ボタン = new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(driver -> driver.findElement(By.id("button-return")));
            WebElement 返却所蔵品番号入力欄 = driver.findElement(By.id("itemNumber.value"));

            返却所蔵品番号入力欄.sendKeys("1-A");
            返却登録ボタン.click();
        }

    }
}
