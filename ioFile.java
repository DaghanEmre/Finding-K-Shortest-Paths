import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ioFile {
	
	static ArrayList<String> inputList = new ArrayList<String>();
	
	public static void readingInputTxt(File inputFile){
		BufferedReader input = null;
		
		try{
			input = new BufferedReader(new FileReader(inputFile));
			
			String line;
			/****That function loads each line to an array list****/
			while((line = input.readLine()) != null)
				inputList.add(line);
			input.close();
			
		}catch (IOException e){
			e.printStackTrace();
		}	
	}
	
}
