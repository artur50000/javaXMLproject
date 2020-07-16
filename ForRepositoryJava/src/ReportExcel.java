import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReportExcel {
	
	static String subStringArr="";

	public static void xmlParsing() throws IOException {
	
    	//building arraylists of xml tags content
	ArrayList<String> update = new ArrayList<>();
	ArrayList<String> symptom = new ArrayList<>();    
	ArrayList<String> problem = new ArrayList<>();  
	ArrayList<String> natureoffix = new ArrayList<>(); 
	ArrayList<String> dataupdate = new ArrayList<>(); 
		 
	//checking all xml files in the folder
	File folder = new File("C:\\Users\\SotsenkoArtur\\updates\\testfolder\\");
	File[] listOfFiles = folder.listFiles();

	for (File file : listOfFiles) {
		    if (file.isFile()) {
		        System.out.println(file.getName());
		
		String filename=file.getName().replace(".xml", "");
		
		//retreaving the content from xml files
		Scanner scanner = new Scanner( new File(folder+"\\"+file.getName()));
        String text = scanner.next();
      
        scanner.close(); 
        
        File text2 = new File(folder+"\\"+file.getName());
        
        //Creating another Scanner instance to read File in Java
        Scanner scnr = new Scanner(text2);
     
        //Reading each line of file using Scanner class
       
        ArrayList<String> listref = new ArrayList<>();
     
        while(scnr.hasNextLine()){
             {
        	listref.add(scnr.nextLine());
        	       	       	
            }
        } 
        
        scnr.close();
        
        //extracting the content of xml tags in Arraylists
        //tag reference
        String[] str = text.split("\\n");
        
        ArrayList<String> listref2 = new ArrayList<>();
        
        for(String num: str) {
            
            if (num.indexOf("<Reference>")!=-1 &&num.indexOf("</Reference>")!=-1){
            String subStr = num.substring(num.indexOf("<Reference>")+11, num.indexOf("</Reference>"));
            listref2.add(subStr);
            //System.out.println(subStr);
            }
          
        }
        
        //tag symptom
       
        for (int i=0; i<listref.size();i++) {
        	if (listref.get(i).contains("<symptom>") && (listref.get(i-3).contains(filename)) ){
  		
        		String subStr1 = listref.get(i).substring(listref.get(i).indexOf("<symptom>")+9, listref.get(i).indexOf("</symptom>"));
  		
        		symptom.add(subStr1);
  		
       	}
        //tag createdate	
  	
  	    if (listref.get(i).contains("CreateDate=")) {
  		 subStringArr = listref.get(i).substring(listref.get(i).indexOf("CreateDate=")+12, listref.get(i).indexOf("\" T"));
  	
  	       }
  	   }
    
     //tag problem
        for (int i=0; i<listref.size();i++) {
        	if (listref.get(i).contains("<Problem>") && (listref.get(i-5).contains(filename)) ){
	  		
        		String subStr1 = listref.get(i).substring(listref.get(i).indexOf("<Problem>")+9, listref.get(i).indexOf("</Problem>"));
	  		
	  		problem.add(subStr1);
	  		
	  	 }
	    }
  
         //tag natureoffix 
        for (int i=0; i<listref.size();i++) {
        	if (listref.get(i).contains("<NatureOfFix>") && (listref.get(i-6).contains(filename)) ){
	  		
        		String subStr1 = listref.get(i).substring(listref.get(i).indexOf("<NatureOfFix>")+13, listref.get(i).indexOf("</NatureOfFix>"));
	  		
        		natureoffix.add(subStr1);
        		update.add(filename);
        		dataupdate.add(subStringArr);
	  		
	   	      }
	  	   }
	      }
 	 
	}
//printing the arraylists with tag content	 
 System.out.println(symptom);
 System.out.println(natureoffix);
 System.out.println(problem);
 System.out.println(update);
 System.out.println(dataupdate);
 
 //filling the excel workbook with data from arraylists
 File file = new File("C:\\Users\\SotsenkoArtur\\updates\\test.xltm");
 
 // Read XSL file
 FileInputStream inputStream = new FileInputStream(file);

 ZipSecureFile.setMinInflateRatio(0);
 XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
 XSSFSheet mySheet = workbook.getSheetAt(0);

 //setting excel styles
 CellStyle cellStyle = workbook.createCellStyle();
 cellStyle.setAlignment(HorizontalAlignment.CENTER);
 cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
 
 //filling excel rows with data
 for (int k1=0; k1<natureoffix.size(); k1++) {
	 Row row = mySheet.createRow(1+k1);
	 
	 row.createCell(0).setCellValue(dataupdate.get(k1));
	 row.createCell(1).setCellValue(update.get(k1));
	 row.createCell(2).setCellValue(symptom.get(k1));
	 row.createCell(3).setCellValue(problem.get(k1));
	 row.createCell(4).setCellValue(natureoffix.get(k1));
 }

 inputStream.close();
 FileOutputStream out = new FileOutputStream(file);
 workbook.write(out);
 out.close();
workbook.close();
 
    }
}
