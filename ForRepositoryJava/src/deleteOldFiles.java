import java.io.File;

public class deleteOldFiles {
public static void deleteFiles () {
	//declare two folders where we will compare files to delete
	File folder = new File("C:\\Users\\SotsenkoArtur\\updates\\victoriaXML\\");
	File folder2 = new File("C:\\Users\\SotsenkoArtur\\updates\\testfolder\\");
	
	//declare arrays with file names
	File[] listOfFiles = folder.listFiles();
	File[] listOfFiles2 = folder2.listFiles();
	
	int sum=0;

	//search for file with the particular string in the file name and delete it
	for (File file2: listOfFiles2) {
		String f=file2.getName();
	
		String f1=f.replace(f.substring(f.length()-6),"");
	
			for (File file: listOfFiles) {
				String f2=file.getName();
				if (f2.indexOf(f1)!=-1){
					System.out.println("found! "+ file.getAbsolutePath());
					file.delete();
					sum=sum+1;
				}
			}
		}

		System.out.println("deleted " +sum);
	}
}

