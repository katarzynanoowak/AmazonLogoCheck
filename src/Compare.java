import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert; 
import org.testng.annotations.Test;   

public class Compare {
	
	static WebDriver driver;
	
		
	@Test public void CompareLogo() throws InterruptedException, IOException {
		
			
			System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Selenium\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
			WebDriverWait wait;
			wait = new WebDriverWait(driver, 30);
			
			driver.get("https://www.amazon.com/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
			WebElement currentImage = driver.findElement(By.cssSelector("#nav-logo > a"));
		
			wait.until(ExpectedConditions.visibilityOf(currentImage));
			
			
			BufferedImage expectedImage = ImageIO.read(new File(System.getProperty("user.dir")+ "\\Logo\\amazonLogo.png"));
			
			
			
			Screenshot currentImageScreenshot = new AShot().takeScreenshot(driver, currentImage);
			ImageIO.write(currentImageScreenshot.getImage(), "PNG", new File(System.getProperty("user.dir")+ "\\test-output\\MyScreenshott.png"));
			BufferedImage actualImage = currentImageScreenshot.getImage();
			
			
			
			ImageDiffer imgDiff = new ImageDiffer();
			ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
			
			 if(diff.hasDiff()==true)
		        {
		         System.out.println("Logo different than original");
		        }
		        else {
		         System.out.println("Logo as specified");
		        }
			
			driver.close();
		}
		
}
