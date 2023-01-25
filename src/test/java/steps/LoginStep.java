package steps;

import Base.BaseUtil;
import com.aventstack.extentreports.GherkinKeyword;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import pages.LoginPage;

import java.util.List;
import java.util.Map;

/**
 * Created by Karthik on 31/01/2019.
 */
public class LoginStep extends BaseUtil {

    public LoginStep(BaseUtil base) {
    }

    @DataTableType(replaceWithEmptyString = "[blank]")
    public User convert(Map<String, String> entry) {
        return new User(
                entry.get("username"),
                entry.get("password").concat("$$$$$"));
    }

    @Then("^I should see the userform page$")
    public void iShouldSeeTheUserformPage() throws Throwable {
        scenarioDef.createNode(new GherkinKeyword("Then"), "I should see the userform page");

        Assert.assertEquals("Its not displayed", BaseUtil.Driver.findElement(By.id("Initial")).isDisplayed(), true);
    }

    @Given("^I navigate to the login page$")
    public void iNavigateToTheLoginPage() throws Throwable {
        BaseUtil.scenarioDef.createNode(new GherkinKeyword("Given"), "I navigate to the login page");
        System.out.println("Navigate Login Page");
        BaseUtil.Driver.navigate().to("http://www.executeautomation.com/demosite/Login.html");
    }

    @And("^I click login button$")
    public void iClickLoginButton() throws Throwable {
        BaseUtil.scenarioDef.createNode(new GherkinKeyword("And"), "I click login button");
        LoginPage page = new LoginPage(BaseUtil.Driver);
        page.ClickLogin();
    }

    @And("^I enter the following for Login$")
    public void iEnterTheFollowingForLogin(List<User> table) throws Throwable {
        BaseUtil.scenarioDef.createNode(new GherkinKeyword("And"), "I enter the following for login");
        // Create an ArrayList
        // List<User> users = new ArrayList<User>();
        // Store all the users
        // List<User> users = table.asList(User.class);

        LoginPage page = new LoginPage(BaseUtil.Driver);

        page.Login(table.get(0).username, table.get(0).password);

        // page.Login(users.get(2), users.get(3));

    }

    @And("^I enter ([^\"]*) and ([^\"]*)$")
    public void iEnterUsernameAndPassword(String userName, String password) throws Throwable {
        BaseUtil.scenarioDef.createNode(new GherkinKeyword("And"), "I enter username and password");
        System.out.println("UserName is : " + userName);
        System.out.println("Password is : " + password);
    }

    @Then("^I should see the userform page wrongly$")
    public void iShouldSeeTheUserformPageWrongly() throws Throwable {
        BaseUtil.scenarioDef.createNode(new GherkinKeyword("Then"), "I should see  the useform page wrongly");
        // Assert.assertEquals("Its not displayed",
        // base.Driver.findElement(By.id("sdfgdsfsd")).isDisplayed(), true);
    }

    public class User {
        public String username;
        public String password;

        public User(String userName, String passWord) {
            username = userName;
            password = passWord;
        }
    }

}
