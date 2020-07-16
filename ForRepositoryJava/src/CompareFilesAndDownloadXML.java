import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CompareFilesAndDownloadXML {

	public static void compareFiles () throws FileNotFoundException, InterruptedException, AWTException  {
	//creating robot object
	Robot r = new Robot();
		 
	//reading html file in string variable
	 Scanner scanner = new Scanner( new File("C:\\Users\\SotsenkoArtur\\Downloads\\T24 Update Service.html") );
         String text = scanner.useDelimiter("\\A").next();
         scanner.close(); 
         
        //making array of strings     
         String[] str = text.split(" ");
         
       //creating Arraylist to store html links to xml files        
         ArrayList<String> listref = new ArrayList<>();
         
         for(String num: str) {
             
             if (num.indexOf("https")!=-1 &&num.indexOf(".xml")!=-1){
             String subStr = num.substring(num.indexOf("https"), num.indexOf("xml")+3);
             listref.add(subStr);
            
             }
          
         }
         
        //writing the Arraylist to file
         
         try {
             FileWriter writer = new FileWriter("C:\\Users\\SotsenkoArtur\\MyFile.txt", true);
             for (int i=0; i<listref.size();i++){
             writer.write(listref.get(i));
             writer.write(",");   // write new line
            
             }
             writer.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
         
         //reading old file with previously stored links to compare with new
         Scanner scanner1 = new Scanner( new File("C:\\Users\\SotsenkoArtur\\MyFile2.txt") );
         String text1 = scanner1.useDelimiter("\\A").next();
         scanner1.close(); 
     
       //making array of strings
         String[] str1 = text1.split(",");
         
         //creating arraylist to store links for comparison
         ArrayList<String> listref1 = new ArrayList<>();
                
         
         for(String num1: str1) {
             
             if (num1.indexOf("https")!=-1 &&num1.indexOf(".xml")!=-1){
             String subStr1 = num1.substring(num1.indexOf("https"), num1.indexOf("xml")+3);
             listref1.add(subStr1);
             
             }
         }
         
         //removing duplicated links
         for (int i=0; i<listref.size();i++){
             for (int j=0; j<listref1.size();j++){
                 if(listref1.get(j).contentEquals(listref.get(i))){
                     listref.remove(i);
                 }
             
             }
                         
         }
         
     	//going to new urls and downloading new files 
         
         System.setProperty("webdriver.chrome.driver","C:\\Users\\SotsenkoArtur\\Downloads\\chromedriver.exe");
	     WebDriver driver = new ChromeDriver();
	     driver.get("https URL");
	     driver.manage().window().maximize();
	     
	     Thread.sleep(500);
	     
	     driver.findElement(By.id("userNameInput")).sendKeys("username",Keys.ENTER);
	     Thread.sleep(500);
	     
	     driver.findElement(By.name("Password")).sendKeys("password",Keys.ENTER);
	     Thread.sleep(500);
	     
	     //printing list of files
         System.out.println(listref);
         
         //downloading all files with new links
         for (int i=0; i<listref.size();i++){
       
        	  
        	  driver.get(listref.get(i));
        	  
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
}

