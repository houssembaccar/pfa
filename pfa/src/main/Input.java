package main;

import java.io.*;
import java.util.*;

public class Input{
	
	private Scanner scanner;
	public void openFile(String fileName){
		
		try {
			scanner = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void readFile (){
		
		while(scanner.hasNext()){
			String currentLine=scanner.nextLine();
			if (currentLine.contains("<rdfs:Class rdf:ID"))
			System.out.printf("%s \n",currentLine);
			
		}
	}
	public void closeFile(){
		
		scanner.close();
	}
}