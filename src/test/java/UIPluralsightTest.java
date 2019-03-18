import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UIPluralsightTest {
    WebDriver driver;

    @BeforeMethod
    public void startUpBrowser(){
        System.setProperty("webdriver.chrome.driver","C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");

        driver = new ChromeDriver();
        driver.get("http://www.vezuvian.com/");

        doBasicCheck();
    }

    @Test(description = "No exception thrown by findElement considered a successful test")
    public void checkLoginButtonIsPresent() {
        driver.findElement(By.className("nav-logo"));
    }

    @AfterMethod()
    public void closeBrowser(){
        System.out.println("Closing down the web browser.");
        driver.close();
    }

    private void doBasicCheck(){
        driver.findElement(By.className("nav-logo"));
    }
}
