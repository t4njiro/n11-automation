package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public HomePage loginWithEmail(String email, String password) throws InterruptedException {
        typeById("email", email);
        typeById("password", password);
        clickById("loginButton");
        Thread.sleep(2000);
        return new HomePage(driver);
    }

    public HomePage quickLogin(String gsm) throws InterruptedException {

        String mainWindow = driver.getWindowHandle();
        clickByClassname("quickLogin");

        Set<String> allWindowHandles = driver.getWindowHandles();
        for(String window : allWindowHandles){
            if(!mainWindow.equals(window)){
                driver.switchTo().window(window);
            }
        }

        typeById("gsm-input", gsm);
        clickById("loginSubmit");
        TimeUnit.SECONDS.sleep(20);
        driver.switchTo().window(mainWindow);
        return new HomePage(driver);
    }

    public LoginPage forgotPassword(String email) throws InterruptedException {
        clickById("forgotPassword");
        typeById("forgottenUserEmail", email);
        Thread.sleep(1000);
        clickById("sendLinkForPasswordBtn");
        Thread.sleep(1000);
        return this;
    }

    public boolean isEmailSent(){
        return isDisplayedByClassname("confirmIcon");
    }

}
