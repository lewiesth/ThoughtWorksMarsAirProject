package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SearchResultPage {
    private final Page page;
    private final Locator searchResultsTitleHeader;
    private final Locator seatsAvailable;
    private final Locator seatsAvailableNumber;
    private final Locator backButton;
    private final Locator seatsNotAvailable;
    private final Locator promotionalCodeMessage;
    private final Locator logoButton;
    private final Locator invalidSchedule;

    public SearchResultPage(Page page) {
        this.page = page;
        this.searchResultsTitleHeader = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Search Results"));
        this.seatsAvailable = page.getByText("Seats available!");
        this.seatsAvailableNumber = page.getByText("Call now on 0800 MARSAIR to book!");
        this.backButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Back"));
        this.seatsNotAvailable = page.getByText("Sorry, there are no more seats available.");
        this.promotionalCodeMessage=page.locator("p.promo_code");
        this.logoButton=page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("MarsAir"));
        this.invalidSchedule = page.getByText("Unfortunately, this schedule is not possible. Please try again.");
    }
    public void assertSeatsAvailable() {
        assertThat(searchResultsTitleHeader).isVisible();
        assertThat(seatsAvailable).isVisible();
        assertThat(seatsAvailableNumber).isVisible();
        assertThat(backButton).isVisible();
    }
    public void assertSeatsNotAvailable() {
        assertThat(searchResultsTitleHeader).isVisible();
        assertThat(seatsNotAvailable).isVisible();
        assertThat(backButton).isVisible();
    }
    public void assertPromoCodeValid(String promotionalCode) {
        assertThat(promotionalCodeMessage).isVisible();
        String discount=promotionalCode.charAt(2)+"0%";
        String validPromoCode="Promotional code "+promotionalCode+" used: "+discount+" discount!";
        assertThat(promotionalCodeMessage).hasText(validPromoCode);
    }
    public void assertPromoCodeInvalid(String promotionalCode) {
        assertThat(promotionalCodeMessage).isVisible();
        String invalidPromoCode="Sorry, code "+promotionalCode+" is not valid";
        assertThat(promotionalCodeMessage).hasText(invalidPromoCode);
    }
    public void clickOnLogo() {
        logoButton.click();
    }
    public void assertInvalidSchedule() {
        assertThat(invalidSchedule).isVisible();
    }
    public void clickOnBackButton() {
        backButton.click();
    }
}
