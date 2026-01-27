package stepdefinitions;

import com.microsoft.playwright.Page;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SearchPage;
import pages.SearchResultPage;

public class FlightBookingSteps {
    Page page=Hooks.getPage();
    SearchPage searchPage=new SearchPage(page);
    SearchResultPage searchResultPage=new SearchResultPage(page);
    String promotionalCode=null;
    @When("user selects a departing date {string}")
    public void userSelectsADepartingDate(String departureDate) {
        searchPage.selectDepartureDate(departureDate);
    }

    @And("user selects a returning date {string}")
    public void userSelectsAReturningDate(String returnDate) {
        searchPage.selectReturnDate(returnDate);
    }

    @And("clicks on search")
    public void clicksOnSearch() {
        searchPage.clickOnSearch();
    }

    @Then("show seat available message")
    public void showSeatAvailableMessage() {
        searchResultPage.assertSeatsAvailable();
    }

    @Then("show no more seats available message")
    public void showNoMoreSeatsAvailableMessage() {
        searchResultPage.assertSeatsNotAvailable();
    }

    @When("user enters a valid promo code")
    public void userEntersAValidPromoCode() {
        promotionalCode=searchPage.createAndEnterValidPromoCode();
    }

    @And("promo code is valid message")
    public void promoCodeIsValidMessage() {
        searchResultPage.assertPromoCodeValid(promotionalCode);
    }

    @When("user enters a invalid promo code")
    public void userEntersAInvalidPromoCode() {
        promotionalCode=searchPage.createAndEnterInvalidPromoCode();
    }

    @And("promo code is invalid message")
    public void promoCodeIsInvalidMessage() {
        searchResultPage.assertPromoCodeInvalid(promotionalCode);
    }

    @And("clicks on logo")
    public void clicksOnLogo() {
        searchResultPage.clickOnLogo();
    }

    @Then("verify redirect to homepage")
    public void verifyRedirectToHomepage() {
        searchPage.assertWelcomeMessage();
    }

    @Then("show invalid schedule message")
    public void showInvalidScheduleMessage() {
        searchResultPage.assertInvalidSchedule();
    }

    @And("clicks back button")
    public void clicksBackButton() {
        searchResultPage.clickOnBackButton();
    }
}
