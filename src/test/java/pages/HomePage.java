package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;


public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
        //driver.get("https://www.n11.com");
    }

    public HomePage closePopups() throws InterruptedException {
        clickById("myLocation-close-info");
        clickByXpath("//*[@id='cookieUsagePopIn']/span");
        clickByClassname("dn-slide-deny-btn");
        Thread.sleep(2000);
        //clickByClassname("closeBtn");
        return this;
    }

}
