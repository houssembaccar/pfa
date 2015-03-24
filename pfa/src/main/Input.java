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
		String conceptName;
		String parentOfConceptName;
		Concept concept = null;
		while(scanner.hasNext()){
			String currentLine=scanner.nextLine();
			if (currentLine.contains("<rdfs:subClassOf rdf:resource=")) {
				indexOfFirstQuote=currentLine.indexOf("\"");
				indexOfSecondQuote=currentLine.lastIndexOf("\"");
				parentOfConceptName=currentLine.substring(indexOfFirstQuote+40, indexOfSecondQuote);
				ConceptManagement.addParentName(parentOfConceptName,concept);
			}
			if (currentLine.contains("<owl:Class rdf:about"))
			{
				indexOfFirstQuote=currentLine.indexOf("\"");
				indexOfSecondQuote=currentLine.lastIndexOf("\"");	
				conceptName=currentLine.substring(indexOfFirstQuote+40, indexOfSecondQuote);
				concept=new Concept(conceptName);
				ConceptManagement.addConcept(concept);
			}		
		}
	}
	public void closeFile(){		
		scanner.close();
	}
}