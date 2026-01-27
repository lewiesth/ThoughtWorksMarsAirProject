package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;

import java.util.concurrent.ThreadLocalRandom;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SearchPage {
    private final Page page;
    private final Locator titleHeader;
    private final Locator secondTitleHeader;
    private final Locator departingDropdown;
    private final Locator returningDropdown;
    private final Locator promoCodeInput;
    private final Locator searchButton;
    private static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public SearchPage(Page page) {
        this.page = page;
        this.titleHeader = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Welcome to MarsAir!"));
        this.secondTitleHeader = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Book a ticket to the red planet now!"));
        this.departingDropdown = page.locator("#departing");
        this.returningDropdown = page.locator("#returning");
        this.promoCodeInput = page.locator("#promotional_code");
        this.searchButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("search"));
    }
    public void assertWelcomeMessage() {
        assertThat(titleHeader).isVisible();
        assertThat(secondTitleHeader).isVisible();
    }
    public void selectDepartureDate(String departureDate) {
        departingDropdown.selectOption(new SelectOption().setLabel(departureDate));
    }
    public void selectReturnDate(String returnDate) {
        returningDropdown.selectOption(new SelectOption().setLabel(returnDate));
    }
    public void clickOnSearch() {
        searchButton.click();
    }
    public String createAndEnterValidPromoCode() {
        String initialPromoCode=createInitialCharacters();
        int checkSum=(Character.getNumericValue(initialPromoCode.charAt(2))+
                Character.getNumericValue(initialPromoCode.charAt(8))+
                Character.getNumericValue(initialPromoCode.charAt(9)))%10;
        String finalPromoCode=initialPromoCode+checkSum;
        promoCodeInput.fill(finalPromoCode);
        return finalPromoCode;
    }
    public String createAndEnterInvalidPromoCode() {
        String initialPromoCode=createInitialCharacters();
        int checkSum=(Character.getNumericValue(initialPromoCode.charAt(2))+
                Character.getNumericValue(initialPromoCode.charAt(8))+
                Character.getNumericValue(initialPromoCode.charAt(9)))%10;
        int intToAppend=randomDigitExcluding(checkSum);
        String finalPromoCode=initialPromoCode+intToAppend;
        promoCodeInput.fill(finalPromoCode);
        return finalPromoCode;
    }
    private String createInitialCharacters() {
        StringBuilder sb = new StringBuilder();
        sb.append(randomChar()).append(randomChar()).append(randomDigit(true));
        sb.append("-");
        sb.append(randomChar()).append(randomChar()).append(randomChar());
        sb.append("-");
        sb.append(randomDigit(false)).append(randomDigit(false));
        return sb.toString();
    }
    private char randomChar() {
        return ALPHA.charAt(ThreadLocalRandom.current().nextInt(ALPHA.length()));
    }

    private int randomDigit(Boolean withoutZero) {
        if(withoutZero)
            return ThreadLocalRandom.current().nextInt(1,10);
        else
            return ThreadLocalRandom.current().nextInt(10);
    }
    private int randomDigitExcluding(int toExclude) {
        int randomDigit;

        do {
            randomDigit = ThreadLocalRandom.current().nextInt(10);
        } while (randomDigit == toExclude);

        return randomDigit;
    }
}
