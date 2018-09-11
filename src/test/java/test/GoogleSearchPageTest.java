package test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.GoogleSearchPage;
import page.ResultsGoogleSearch;

import java.util.List;

public class GoogleSearchPageTest extends  BaseTest {

    private GoogleSearchPage googleSearchPage;
    //private ResultsGoogleSearch resultsGoogleSearch;


    //@BeforeMethod
    //public void beforeMethod() {
    //    browser = new FirefoxDriver();
    //    browser.get("https://www.google.com/");
    //    ResultsGoogleSearch = new resultsGoogleSearch(browser);
    //}

    //@AfterMethod
    //public void afterMethod() {
    //    browser.close();
    //}

    @DataProvider
    public  Object[][] valieddate(){
        return new Object[][]{
                {"selenium"},
                {"SELENIUM"},
                {"Selenium"}
        };
    }
    @Test(dataProvider = "valieddate")
    public void searchResultsOnSearchPage( String searchValue) {
        Assert.assertTrue(googleSearchPage.isLoaded(),"GoogleSearchPage is not loaded");
        Assert.assertTrue (ResultsGoogleSearch.getSearchResultsCount() >= 10, "Not enough search results on search page");

        List<String> searchResults = ResultsGoogleSearch.getResultsGoogleSearchText();
        for (String searchResultsText: searchResults){
            Assert.assertTrue(searchResultsText.toLowerCase().contains(searchValue.toLowerCase()),
                    "search value:" + searchValue+ " not found in:\n"+ searchResult();
        }
        ResultsGoogleSearch = ResultsGoogleSearch.goToNextPage(2);
        Assert.assertTrue(ResultsGoogleSearch.getSearchResultsCount() >= 10, "Not enough search results on second search page");

        List<String> searchResultsSecondPage = ResultsGoogleSearch.getResultsGoogleSearchText();
        for (String searchResult: searchResultsSecondPage){
            Assert.assertTrue(searchResult.toLowerCase().contains(searchValue.toLowerCase()),
                    "For second page search term: "+searchValue+" not found in: \n"+searchResult);
        }

    }
}
