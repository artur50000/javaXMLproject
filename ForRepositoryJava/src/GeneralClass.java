import java.awt.AWTException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class GeneralClass {

	public static void main(String[] args) throws AWTException, InterruptedException, IOException {
		
		 GeneralClass general=new GeneralClass();
		 		 
		 //download html page with links to xml files
		 Class<DownloadHtmlPage> downloadpage=DownloadHtmlPage.class;
		
		 DownloadHtmlPage.DownloadPage();
		 
		 general.MethodName(downloadpage);
		 
                 general.pressEnterKeyToContinue();
		 
		 //compare links in html with links in previously saved file. Download files with new
         	//links
         	Class<CompareFilesAndDownloadXML> compareXML=CompareFilesAndDownloadXML.class;
         
         	CompareFilesAndDownloadXML.compareFiles();
		 
		 general.MethodName(compareXML);
		 
         	general.pressEnterKeyToContinue();
         
        	//parse xml tags for data and fill cells in excel workbook with these data     
         	Class<ReportExcel> xmlparsing=ReportExcel.class;
         
         	ReportExcel.xmlParsing();
		  
		general.MethodName(xmlparsing);
			 
	     	general.pressEnterKeyToContinue();
	     
             //compare new xml files with the old files where the filename differs with periodic 
	     //number and delete files with old numbers in the filename
	     	Class<deleteOldFiles> deletefiles = deleteOldFiles.class;
         
         	deleteOldFiles.deleteFiles();
		  
		general.MethodName(deletefiles);
			 
	     	general.pressEnterKeyToContinue();
	
	}
	
	//wait for user decision to continue running the application
	public void pressEnterKeyToContinue()
	{ 
	        System.out.println("Press Enter key to continue...");
	        Scanner s = new Scanner(System.in);
	        s.nextLine();
	        s.close();
	        
	}
	
	//get the invoked method name
	public void MethodName(Class<?> t) {
		
		Method [] methods = t.getDeclaredMethods();
		
		for (Method method:methods) {
			String str=method.getName();
			System.out.println("Method "+str+" was invoked");
		}
		
	}
}
