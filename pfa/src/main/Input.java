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
				parentOfConceptName=currentLine.substring(indexOfFirstQuote+41, indexOfSecondQuote).trim();
				ConceptManagement.addParentName(parentOfConceptName,concept);
			}
			if (currentLine.contains("<owl:Class rdf:about"))
			{
				indexOfFirstQuote=currentLine.indexOf("\"");
				indexOfSecondQuote=currentLine.lastIndexOf("\"");	
				conceptName=currentLine.substring(indexOfFirstQuote+41, indexOfSecondQuote).trim();
				concept=new Concept(conceptName);
				ConceptManagement.addConcept(concept);
			}
			//add individuals to the tree
			if (currentLine.contains("<rdf:type rdf:resource=")){
				indexOfFirstQuote=currentLine.indexOf("\"");
				indexOfSecondQuote=currentLine.lastIndexOf("\"");
				parentOfConceptName=currentLine.substring(indexOfFirstQuote+41, indexOfSecondQuote).trim();
				ConceptManagement.addParentName(parentOfConceptName,concept);
			}
			if (currentLine.contains("<owl:NamedIndividual rdf:about")){
				
				indexOfFirstQuote=currentLine.indexOf("\"");
				indexOfSecondQuote=currentLine.lastIndexOf("\"");	
				conceptName=currentLine.substring(indexOfFirstQuote+41, indexOfSecondQuote).trim();
				concept=new Concept(conceptName);
				concept.setIndividual(true);
				ConceptManagement.addConcept(concept);
			}
			//finish adding individuals
			if (currentLine.contains("General axioms"))
				break;
		}
		for(int j=0;j<ConceptManagement.getConceptList().size();j++) {
			Concept currentConcept=ConceptManagement.getConceptList().get(j);
			if (currentConcept.getParentName()!=null) {
				currentConcept.setParent(ConceptManagement.searchConcept(ConceptManagement.getConceptList().get(j).getParentName()));
			}
		}
	}
	public void closeFile(){		
		scanner.close();
	}
}