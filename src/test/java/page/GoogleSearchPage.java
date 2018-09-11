package page;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class GoogleSearchPage  extends page.BasePage {

    @FindBy(xpath = "//input[@id='lst-ib']")
    private WebElement searchField;

    @FindBy(xpath = "//input[@name=\"btnK\"]")
    private WebElement searchButton;

    public  GoogleSearchPage(WebDriver browser){
        this.browser = browser;
        PageFactory.initElements(browser,this);
        waitUntilElementIsVisible(searchField,10);
    }

    public boolean isLoaded(){
        return searchField.isDisplayed()
                && getCurrentPageTitle().contains("selenium - Поиск в Google")
                && getCurrentPageUrl().equals("http://www.google.com.ua/");
    }
    public ResultsGoogleSearch resultsGoogleSearch(String searchValue) {
        searchField.sendKeys(searchValue);
        searchField.sendKeys(Keys.ENTER);
        return new ResultsGoogleSearch(browser);

    }


}

