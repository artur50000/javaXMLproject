import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class DownloadHtmlPage {
	

public static void DownloadPage() throws AWTException, InterruptedException {
	
//we will need robot package in places where selenium can not follow the html link due to security restrictions
//on site	
Robot r = new Robot();

System.setProperty("webdriver.chrome.driver","C:\\Users\\SotsenkoArtur\\Downloads\\chromedriver.exe");
WebDriver driver = new ChromeDriver();

//I will hide all urls and credentials under the common names 

//going to the url and entering credentials
driver.get("https URL");
driver.manage().window().maximize();

Thread.sleep(500);

driver.findElement(By.id("userNameInput")).sendKeys("USERNAME",Keys.ENTER);

Thread.sleep(500);

driver.findElement(By.name("Password")).sendKeys("PASSWORD",Keys.ENTER);
Thread.sleep(500);
 
driver.get("https targeted URL on the site");
 
Thread.sleep(2000);


//here comes the robot 
r.mouseMove(209,254);
r.mouseMove(202,299);

Thread.sleep(3000);

r.mousePress(InputEvent.BUTTON1_MASK);
r.delay(150);
r.mouseRelease(InputEvent.BUTTON1_MASK);

Thread.sleep(3000);	      

r.mouseMove(771,398);

r.mousePress(InputEvent.BUTTON2_MASK);
r.delay(150);
r.mouseRelease(InputEvent.BUTTON2_MASK);

Thread.sleep(3000);

r.mousePress(InputEvent.BUTTON1_MASK);
r.mouseRelease(InputEvent.BUTTON1_MASK);

Thread.sleep(1000); 

//I use robot to download xml page because selenium did not keep xml tags while
//downloading page with selenium driver

r.keyPress(KeyEvent.VK_CONTROL);
r.keyPress(KeyEvent.VK_S);
r.delay(150);
r.keyRelease(KeyEvent.VK_CONTROL);
r.keyRelease(KeyEvent.VK_S);
r.delay(150);
                
Thread.sleep(1000); 

r.keyPress(KeyEvent.VK_ENTER);
r.delay(150);
r.keyRelease(KeyEvent.VK_ENTER);
r.delay(150);

Thread.sleep(3000); 
	
	
}

}
