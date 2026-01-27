package stepdefinitions;

import com.microsoft.playwright.*;
import io.cucumber.java.*;

public class Hooks {
    private static Playwright playwright;
    private static Browser browser;
    private static Page page;
    private static BrowserContext context;

    @BeforeAll
    public static void setup() {
        playwright = Playwright.create();
        String browserName = System.getProperty("BROWSER","default");
        Boolean headless= Boolean.valueOf(System.getProperty("HEADLESS"));
        switch (browserName) {
            case "chromium" ->
                    browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
            case "firefox" ->
                    browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless));
            case "webkit" ->
                    browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless));
            default ->
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        }
    }
    @Before
    public void setupEach() {
        String URL="https://marsair.recruiting.thoughtworks.net/LewiesYun";
        context = browser.newContext();
        page = context.newPage();
        page.navigate(URL);
    }
    @After
    public void tearDownEach(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = page.screenshot(new Page.ScreenshotOptions()
                    .setPath(null));
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        if (page != null) page.close();
        if (context != null) context.close();
    }
    @AfterAll
    public static void tearDown() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
    public static Page getPage() {
        return page;
    }
}
