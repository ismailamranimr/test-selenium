package TestCases;

import static org.testng.Assert.assertEquals;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class TestCase1 {
	private static WebDriver driver;
	
	@BeforeClass
	public static void setUp() {
		String osName = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
		if (osName.indexOf("win") >= 0)
			System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
		else if (osName.indexOf("mac") >= 0)
			System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver");
		
	    driver = new ChromeDriver();
	}
	
	@BeforeTest
	public void beforeTest()
	{
		System.out.println("Before test");
	}
	
	@Test 
	public void canGoToHomePage() {
		driver.get("https://sites.google.com/a/chromium.org/chromedriver/downloads");
		
	}
	
	@Test(dependsOnMethods="canGoToHomePage")
	public void canGoToDownloadPage() {
		WebElement chromeDriverPath =  driver.findElement(By.xpath("//*[@id=\"sites-canvas-main-content\"]/table/tbody/tr/td/div/h2/b/a"));
		chromeDriverPath.click();
		String pageUrl = "https://chromedriver.storage.googleapis.com/index.html?path=2.37/";
	    String actualPageUrl = driver.getCurrentUrl();
	    	
        assertEquals(pageUrl, actualPageUrl);
	}
	
	@Test(dependsOnMethods="canGoToDownloadPage")
	public void canDownloadWindowsVersion() {
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement chromeDriverWindowsLink =  driver.findElement(By.linkText("chromedriver_win32.zip"));
		chromeDriverWindowsLink.click();
	}
	
	
	@AfterClass
	public static void tearDown() {
		driver.close();
	}

}
