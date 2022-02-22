import base.BasePage;
import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.HomePage;

public class LoginTest extends BaseTest {

    String validEmail = "test.janedoe9@gmail.com";
    String validPassword = "Test_1234";
    String invalidEmail = "ajsdsajdhadjahs@gmail.com";
    String invalidPassword = "test12345";
    String gsm;

    //Opens https://www.n11.com home page and closes popups before all tests
    @BeforeAll
    public void openPage() throws InterruptedException {
        new BasePage(driver)
                .callHomePage()
                .closePopups();
    }

    /**
     * Test Scenario ID: 1
     * Header: Successful login with e-mail
     * Pre-condition: User has an account with valid e-mail and password
     * STEPS:
     * 1. Go to login page
     * 2. Type e-mail address
     * 3. Type password
     * 4. Click login button
     * Expected Result: User successfully logs in
     */
    @Test
    public void login() throws InterruptedException {
        Assertions.assertTrue(new HomePage(driver)
                .callLoginPage()
                .loginWithEmail(validEmail, validPassword)
                .isLoggedIn());
    }

    /**
     * Test Scenario ID: 2
     * Header: Login with invalid password
     * Pre-condition: User has an account with valid e-mail and password
     * STEPS:
     * 1. Go to login page
     * 2. Type e-mail address
     * 3. Type invalid password
     * 4. Click login button
     * Expected Result: User cannot login and displays error message: "E-posta adresiniz veya şifreniz hatalı"
     */
    @Test
    public void loginWrongPassword() throws InterruptedException {
        Assertions.assertFalse(new HomePage(driver)
                .callLoginPage()
                .loginWithEmail(validEmail, invalidPassword)
                .isErrorMessageTrue("//*[@id='loginForm']/div[2]/div[2]/div", "E-posta adresiniz veya şifreniz hatalı"));
    }

    /**
     * Test Scenario ID: 3
     * Header: Login with invalid e-mail
     * Pre-condition:
     * STEPS:
     * 1. Go to login page
     * 2. Type invalid e-mail address
     * 3. Type some password
     * 4. Click login button
     * Expected Result: User cannot login and displays error message: "E-posta adresiniz veya şifreniz hatalı"
     */
    @Test
    public void loginWrongEmail() throws InterruptedException {
        Assertions.assertTrue(new HomePage(driver)
                .callLoginPage()
                .loginWithEmail(invalidEmail, invalidPassword)
                .isErrorMessageTrue("//*[@id='loginForm']/div[2]/div[2]/div", "E-posta adresiniz veya şifreniz hatalı"));
    }

    /**
     * Test Scenario ID: 4
     * Header: Login with leaving e-mail space empty
     * Pre-condition:
     * STEPS:
     * 1. Go to login page
     * 2. Leave email space empty
     * 3. Type some password
     * 4. Click login button
     * Expected Result: User cannot login and displays error message: "Lütfen e-posta adresinizi girin."
     */
    @Test
    public void loginEmptyEmail() throws InterruptedException {
        Assertions.assertTrue(new HomePage(driver)
                .callLoginPage()
                .loginWithEmail("", invalidPassword)
                .isErrorMessageTrue("//*[@id='loginForm']/div[1]/div/div", "Lütfen e-posta adresinizi girin."));
    }

    /**
     * Test Scenario ID: 5
     * Header: Login with leaving password space empty
     * Pre-condition: User has a valid account with e-mail and password
     * STEPS:
     * 1. Go to login page
     * 2. Type a valid e-mail
     * 3. Leave password space empty
     * 4. Click login button
     * Expected Result: User cannot login and displays error message: "Bu alanın doldurulması zorunludur."
     */
    @Test
    public void loginEmptyPassword() throws InterruptedException {
        Assertions.assertTrue(new HomePage(driver)
                .callLoginPage()
                .loginWithEmail(validEmail, "")
                .isErrorMessageTrue("//*[@id='loginForm']/div[2]/div[2]/div", "Bu alanın doldurulması zorunludur."));
    }

    /**
     * Test Scenario ID: 6
     * Header: Forgot password
     * Pre-condition: User has a valid account with e-mail and password
     * STEPS:
     * 1. Go to login page
     * 2. Click forgot my password button
     * 3. Type e-mail address to the window that is opened
     * Expected Result: Password change e-mail is sent"
     */
    @Test void forgotPassword() throws InterruptedException {
        Assertions.assertTrue(new HomePage(driver)
                .callLoginPage()
                .forgotPassword(validEmail)
                .isEmailSent());
    }

    /**
     * Test Scenario ID: 7
     * Header: Successful quick login
     * Pre-condition: User has a valid account also defined with valid gsm
     * STEPS:
     * 1. Go to login page
     * 2. Click quick login button
     * 3. Type gsm to the window that is opened
     * 4. Click login button
     * Expected Result: User successfully logs in"
     */
    @Test
    public void quickLogin() throws InterruptedException {
        Assertions.assertTrue(new HomePage(driver)
                .callLoginPage()
                .quickLogin(gsm)
                .isLoggedIn());
    }
}
