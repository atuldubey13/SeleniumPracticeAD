package adlearning.SeleniumPractice;

import java.beans.Visibility;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocatorsScreenshot {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/locatorspractice/");
		driver.findElement(By.linkText("Forgot your password?")).click();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		Thread.sleep(1000);
		//driver.findElement(By.cssSelector("input[placeholder='Name']")).sendKeys("Atul");
		//driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("atul@gmail.com");
		//driver.findElement(By.xpath("//input[@type ='text' and @placeholder='Phone Number']")).sendKeys("12345");
		
		driver.findElement(By.cssSelector(".reset-pwd-btn")).click();
		
		String passwordText = driver.findElement(By.xpath("//p[@class='infoMsg']")).getText();
		String[] passwordArray = passwordText.split("'");
		String password = passwordArray[1].trim();
		System.out.println(password);
		
		
		driver.findElement(By.cssSelector(".go-to-login-btn")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement userName = driver.findElement(By.id("inputUsername"));
		
		userName.sendKeys("Atul");
		driver.findElement(By.name("inputPassword")).sendKeys(password);
		driver.findElement(By.className("signInBtn")).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login-container")));
		WebElement loginInfo = driver.findElement(By.className("login-container"));
		
		File src = loginInfo.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("logo.png"));
		File src1=	((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src1,new File("screenshot.png"));
		driver.close();
		

	}

}
