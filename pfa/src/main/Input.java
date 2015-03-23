package main;

import java.io.*;
import java.util.*;

import com.pfa.beans.*;
import com.pfa.model.ConceptManagement;
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
		while(scanner.hasNext()){
			String currentLine=scanner.nextLine();
			if (currentLine.contains("<rdfs:Class rdf:ID"))
			{
				indexOfFirstQuote=currentLine.indexOf("\"");
				indexOfSecondQuote=currentLine.lastIndexOf("\"");				
				ConceptManagement.addConcept(new Concept(currentLine.substring(indexOfFirstQuote+1, indexOfSecondQuote)));
			}		
		}
	}
	public void closeFile(){		
		scanner.close();
	}
}