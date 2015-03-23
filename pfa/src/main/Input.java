package main;

import java.io.*;
import java.util.*;
import com.pfa.beans.*;
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
		int indexOfFirstQuote;
		int indexOfSecondQuote;
		ArrayList<Concept> conceptList= new ArrayList<Concept>();
		
		while(scanner.hasNext()){
			String currentLine=scanner.nextLine();
			if (currentLine.contains("<rdfs:Class rdf:ID"))
			{
				indexOfFirstQuote=currentLine.indexOf("\"");
				indexOfSecondQuote=currentLine.lastIndexOf("\"");
				
				conceptList.add(new Concept(currentLine.substring(indexOfFirstQuote+1, indexOfSecondQuote)));
			}
		
		}
		for(int i=0;i<conceptList.size();i++) {
			System.out.println(conceptList.get(i).getName());
		}
	}
	public void closeFile(){
		
		scanner.close();
	}
}