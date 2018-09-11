
package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import page.GoogleSearchPage;

public class BaseTest {

    private WebDriver browser;
    public GoogleSearchPage googleSearchPage;

    @Parameters("nameBrowser")
    @BeforeMethod
    public void beforeMethod(@Optional("firefox") String nameBrowser) {
        if (nameBrowser.toLowerCase().equals("firefox")) {
            browser = new FirefoxDriver();
        }

        if (nameBrowser.toLowerCase().equals("chrome")) {
            browser = new ChromeDriver();
        } else {
            try {
                throw new Exception("Browser name " + nameBrowser + " is not supported");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        browser.get("https://www.google.com.ua/");
        googleSearchPage = new GoogleSearchPage(browser);
    }

    @AfterMethod
    public void afterMethod() {
        browser.close();
    }

}
