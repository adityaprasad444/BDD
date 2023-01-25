package steps;

import Base.BaseUtil;

//import cucumber.api.PickleStepTestStep;
//import cucumber.api.TestCase;
//import gherkin.pickles.PickleStep;
//import io.cucumber.core.api.Scenario;

import io.cucumber.java.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Created by Karthik on 31/01/2019.
 */

public class Hook extends BaseUtil {

    public Hook(BaseUtil base) {
    }

    @Before
    public void InitializeTest(Scenario scenario) {
        BaseUtil.scenarioDef = BaseUtil.features.createNode(scenario.getName());
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        // chromeOptions.addArguments("--headless");
        BaseUtil.Driver = new ChromeDriver(chromeOptions);
    }

    @After
    public void TearDownTest(Scenario scenario) {
        if (scenario.isFailed()) {
            // Take screenshot logic goes here
            System.out.println(scenario.getName());
        }
        System.out.println("Closing the browser : MOCK");
        BaseUtil.Driver.quit();
    }

    @BeforeStep
    public void BeforeEveryStep(Scenario scenario) {
        System.out.println("Before every step " + scenario.getId());
    }

    @AfterStep
    public void AfterEveryStep(Scenario scenario) throws NoSuchFieldException, IllegalAccessException {
        // System.out.println("Before every step " + stepTestStep.getId());
    }

}
