package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class ResultsGoogleSearch  extends BasePage {

    @FindBy(xpath = "//div[@class='srg']/div[@class='g']")
    private List<WebElement> searchResults;

    @FindBy(xpath = "//div[@id='resultStats']")
    private WebElement searchResultsTotal;

    private String linkXPath(int numberpage){
        return "//a[@aria-label='Page "+ numberpage + "']";
    }


    public ResultsGoogleSearch(WebDriver browser){
        this.browser = browser;
        PageFactory.initElements(browser,this);
        waitUntilElementIsVisible(searchResultsTotal,10);
    }


    public List<String> getResultsGoogleSearchText(){
        List<String> searchResultsText = new ArrayList<>();
        for (WebElement searchResult:searchResults){
            searchResultsText.add(searchResult.getText());
        }
        //List<String> searchResultsText = searchResults.stream().map((el) -> el.getText().toLowerCase()).collect(Collectors.toList());
        return searchResultsText;
    }
    public boolean searchResultsTextContainsPattern(String pattern){
        return getResultsGoogleSearchText().stream().allMatch((el) -> el.contains(pattern));
    }
    public boolean isLoaded() {
        return searchResults.size() != 0
                && getCurrentPageTitle().contains("selenium - Поиск в Google")
                && getCurrentPageUrl().contains("/search");

    }
    public static int getSearchResultsCount(){
        return searchResults.size();
    }

    private String getSearchTotalResultsText(){
        return searchResultsTotal.getText();
    }

    public ResultsGoogleSearch goToNextPage(int numberpage){
        searchResultsTotal.click();
        return new ResultsGoogleSearch(browser);
    }


}
