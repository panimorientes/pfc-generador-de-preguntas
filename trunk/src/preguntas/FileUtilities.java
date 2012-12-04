package preguntas;
import java.io.*;
public class FileUtilities {
	
	public static File createFile(String filename)
	{
		File file;
	
		file = new File(filename);
		return file;
	
	}
	
	public static void writeFile(File f,String question)
	{
		try{
		
			FileWriter w = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(w);
			bw.write(question);
			bw.close();
		}catch(IOException e){};
	}
	
	
	
	
	 

}
