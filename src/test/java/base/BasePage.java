package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LoginPage;

import java.time.Duration;

public class BasePage {

    public WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public HomePage callHomePage(){
        driver.get("https://www.n11.com");
        return new HomePage(driver);
    }

    public LoginPage callLoginPage(){
        clickByClassname("btnSignIn");
        return new LoginPage(driver);
    }

    public void clickById(String id){
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.id(id)));
        driver.findElement(By.id(id)).click();
    }

    public void clickByClassname(String classname){
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.className(classname)));
        driver.findElement(By.className(classname)).click();
    }

    public void clickByXpath(String xpath){
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        driver.findElement(By.xpath(xpath)).click();
    }

    public void typeById(String id, String value){
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        element.clear();
        element.sendKeys(value);
    }

    public boolean isDisplayedByClassname(String classname){
        return driver.findElement(By.className(classname)).isDisplayed();
    }

    public boolean isErrorMessageTrue(String xpath, String errorMessage){
        String msg = driver.findElement(By.xpath(xpath)).getText();
        if(msg.equals(errorMessage)){
           return true;
        }
        return false;
    }

    public boolean isLoggedIn(){
        return isDisplayedByClassname("user");
    }
}
