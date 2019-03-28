import java.io.File;

public class Main {
	
	public static void main(String[] args) {
		File inputFile = new File("input.txt");
		File outputFile = new File("output.txt");
		
		ioFile.readingInputTxt(inputFile);
		ProgramManager.programManager(outputFile);
		
	}
	
}
