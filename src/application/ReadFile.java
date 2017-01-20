package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class ReadFile {
	
	//get the file and return a Scanner
	public static Scanner get(String fileName){
		try {
			Scanner input = new Scanner(new File(fileName));
			return input;
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			return null;
		}
		
	}
	
	
	//write to a file and replace what's already inside
	public static void write(String fileName, String content){
		try {
			PrintWriter writer = new PrintWriter(fileName, "UTF-8");
			writer.print(content);
			writer.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//write to a file and append the information
	public static void writeAppend(String fileName, String content){
		try {
			Files.write(Paths.get(fileName), (content+"\n").getBytes(), StandardOpenOption.APPEND);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
