package Assignment11.Test_Scenario;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class testCleartrip extends invokeDriver {

	public WebDriver driver;

	@BeforeTest
	public void initializer() {
		driver = getBrowser();

	}

	@Test
	public void accessBrowser()   {
		report.createTest("accessbrowser");
		driver.get("https://www.cleartrip.com/");
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();

		System.out.println(driver.getTitle());

	}

	@Test()
	public void searchFlight() throws InterruptedException{
		report.createTest("searchflight");
		driver.findElement(By.xpath("//a[contains (@title,'international destinations')]")).click();
		String validateSearchFlight = driver.findElement(By.xpath("//form[@id='SearchForm']/h1")).getText();
		AssertJUnit.assertEquals(validateSearchFlight, "Search flights");
		
		driver.findElement(By.cssSelector("#OneWay")).click();

		driver.findElement(By.id("FromTag")).sendKeys("ban");
		Thread.sleep(5000);
		driver.findElement(By.id("FromTag")).sendKeys(Keys.ENTER);
		driver.findElement(By.id("ToTag")).sendKeys("del");
		Thread.sleep(5000);
		driver.findElement(By.id("ToTag")).sendKeys(Keys.ENTER);
		
		int totalCount = driver.findElements(By.xpath("//td[contains(@data-handler,'selectDay')]")).size();
		for (int i = 0; i < totalCount; i++) {
			if (i == 30) {
				driver.findElements(By.xpath("//td[contains(@data-handler,'selectDay')]")).get(i).click();
			}
		}
		Select adults = new Select(driver.findElement(By.id("Adults")));
		adults.selectByValue("1");
		Select Children = new Select(driver.findElement(By.name("childs")));
		Children.selectByValue("1");
		Select Infants = new Select(driver.findElement(By.id("Infants")));
		Infants.selectByIndex(1);

		driver.findElement(By.cssSelector("#SearchBtn")).click();
		

	}

	@Test
	public void selectFlight() throws InterruptedException {
		report.createTest("selectflight");
		driver.findElement(By.xpath("//p[text()='Non-stop']")).click();
		
		Actions s = new Actions(driver);
		WebElement slider = driver.findElement(By.className("input-range__slider"));

		s.dragAndDropBy(slider, -180, 0).build().perform();
		
		driver.findElement(By.xpath("//p[text()='Show multi-airline itineraries' ]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(@class,'primary-500')]")).click();
	}

	@AfterTest
	public void reviewIternary() throws InterruptedException {

		report.createTest("reviewIternary");
		Set<String> parentWindow = driver.getWindowHandles();
		Iterator<String> it = parentWindow.iterator();
		String searchWindow = it.next();
		String iternaryWindow = it.next();
		
		driver.switchTo().window(iternaryWindow);
		
		System.out.println(driver.getTitle());

		
		System.out.println(driver.findElement(By.xpath("//p[text()='Review your flight details']")).getText());
		
		driver.findElement(By.className("booking")).click();
		
		driver.findElement(By.id("username")).sendKeys("TeamB@moolya.com");

		driver.findElement(By.id("LoginContinueBtn_1")).click();
		Thread.sleep(5000);
	}

	@AfterSuite

	public void userDetails() {

		report.createTest("userDetails");
		Select adultTitle = new Select(driver.findElement(By.cssSelector("#AdultTitle1")));
		adultTitle.selectByVisibleText("Mr");
		driver.findElement(By.id("AdultFname1")).sendKeys("Praveen");
		driver.findElement(By.id("AdultLname1")).sendKeys("Borawar");

		Select childTitle = new Select(driver.findElement(By.cssSelector("#ChildTitle1")));
		childTitle.selectByVisibleText("Miss");
		driver.findElement(By.id("ChildFname1")).sendKeys("Swathy");
		driver.findElement(By.id("ChildLname1")).sendKeys("Venu");

		Select dayC = new Select(driver.findElement(By.cssSelector("#ChildDobDay1")));
		dayC.selectByValue("1");
		Select monthC = new Select(driver.findElement(By.cssSelector("#ChildDobMonth1")));
		monthC.selectByVisibleText("Jan");
		Select yearC = new Select(driver.findElement(By.cssSelector("#ChildDobYear1")));
		yearC.selectByValue("2010");

		Select infantTitle = new Select(driver.findElement(By.cssSelector("#InfantTitle1")));
		infantTitle.selectByIndex(1);
		driver.findElement(By.id("InfantFname1")).sendKeys("Team");
		driver.findElement(By.id("InfantLname1")).sendKeys("B");

		Select dayI = new Select(driver.findElement(By.cssSelector("#InfantDobDay1")));
		dayI.selectByValue("3");
		Select monthI = new Select(driver.findElement(By.cssSelector("#InfantDobMonth1")));
		monthI.selectByVisibleText("Sep");
		Select yearI = new Select(driver.findElement(By.cssSelector("#InfantDobYear1")));
		yearI.selectByValue("2020");

		driver.findElement(By.id("mobileNumber")).sendKeys("9998887776");
		driver.findElement(By.cssSelector("#travellerBtn")).click();

		System.out.println("-----Please Enter The Payment Details-----");
        driver.quit();
		report.flush();

	}

}
