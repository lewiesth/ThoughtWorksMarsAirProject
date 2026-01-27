package stepdefinitions;

import com.microsoft.playwright.Page;
import io.cucumber.java.en.Given;
import pages.SearchPage;

public class CommonSteps {
    @Given("user navigates to {string} page")
    public void user_navigates_to_page(String pageName) {
        Page page=Hooks.getPage();
        if(pageName.equals("MarsAirSearch")) {
            SearchPage searchPage=new SearchPage(page);
            searchPage.assertWelcomeMessage();

        } else
            throw new RuntimeException("Undefined page name in feature file");
    }


}
