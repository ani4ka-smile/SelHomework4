import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeSelect {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Ani\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("http://shop.pragmatic.bg/admin");

    }


    @Test
    public void longTest() {
        WebElement userButton = driver.findElement(By.id("input-username"));
        userButton.sendKeys("admin1");
        WebElement passButton = driver.findElement(By.id("input-password"));
        passButton.sendKeys("parola123!");
        WebElement logButton = driver.findElement(By.xpath("(//button[contains(@type,'submit')])[1]"));
        logButton.click();
        WebElement menuSale = driver.findElement(By.id("menu-sale"));
        menuSale.click();
        WebElement orders = driver.findElement(By.id("collapse4"));
        orders.click();
        WebElement dropDown = driver.findElement(By.id("input-order-status"));
        Select status = new Select(dropDown);
        //status.selectByValue("0");

        //Assert.assertEquals(status.getOptions().size(), 16);


        List<String> expect = Arrays.asList(new String[]{"", "Missing Orders", "Canceled", "Canceled Reversal", "Chargeback", "Complete", "Denied", "Expired", "Failed", "Pending", "Processed", "Processing", "Refunded", "Reversed", "Shipped", "Voided"});
        List<String> real = new ArrayList<String>();

        List<WebElement> allOptions = status.getOptions();


        for (WebElement option : allOptions) {
            real.add(option.getText());
        }

        Assert.assertEquals(real, expect);

    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

