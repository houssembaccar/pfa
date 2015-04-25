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
		int indexOfSharp,indexOfPropertyName,indexOfPropertyValue;
		int indexOfSecondQuote,indexOfEndPropertyName,indexOfEndPropertyValue;	
		String dataType = "data";
		String objectType="object";	
		String conceptName,propertyName,propertyValue;
		String parentOfConceptName;
		Concept concept = null;
		String currentLine;
		while(scanner.hasNext()){
			currentLine=scanner.nextLine();
			if (currentLine.contains("<rdfs:subClassOf rdf:resource=")) {
				indexOfSharp=currentLine.indexOf("#");
				indexOfSecondQuote=currentLine.lastIndexOf("\"");
				parentOfConceptName=currentLine.substring(indexOfSharp+1, indexOfSecondQuote).trim();
				ConceptManagement.addParentName(parentOfConceptName,concept);
			}
			if (currentLine.contains("<owl:Class rdf:about"))
			{
				indexOfSharp=currentLine.indexOf("#");
				indexOfSecondQuote=currentLine.lastIndexOf("\"");	
				conceptName=currentLine.substring(indexOfSharp+1, indexOfSecondQuote).trim();
				concept=new Concept(conceptName);
				ConceptManagement.addConcept(concept);
			}
			//add individuals to the tree
			if (currentLine.contains("<rdf:type rdf:resource=")){
				indexOfSharp=currentLine.indexOf("#");
				indexOfSecondQuote=currentLine.lastIndexOf("\"");
				parentOfConceptName=currentLine.substring(indexOfSharp+1, indexOfSecondQuote).trim();
				ConceptManagement.addParentName(parentOfConceptName,concept);
				currentLine=scanner.nextLine();
				while(!currentLine.contains("</owl:NamedIndividual>")){
					if (currentLine.contains("rdf:datatype")){
						indexOfPropertyName=currentLine.indexOf("<");
						indexOfEndPropertyName=currentLine.indexOf("rdf:datatype");
						propertyName=currentLine.substring(indexOfPropertyName+1, indexOfEndPropertyName-1).trim();
						indexOfPropertyValue=currentLine.indexOf(">");
						indexOfEndPropertyValue=currentLine.indexOf("</");
						propertyValue=currentLine.substring(indexOfPropertyValue+1,indexOfEndPropertyValue).trim();
						Property dataProperty=new Property(propertyName,propertyValue,dataType);
						concept.getPropertyList().add(dataProperty);
					}else if (currentLine.contains("<has")){//faute à corriger
						
						indexOfPropertyName=currentLine.indexOf("<");
						indexOfEndPropertyName=currentLine.indexOf("rdf:resource");
						propertyName=currentLine.substring(indexOfPropertyName+1, indexOfEndPropertyName-1).trim();
						indexOfSharp=currentLine.indexOf("#");
						indexOfSecondQuote=currentLine.lastIndexOf("\"");	
						propertyValue=currentLine.substring(indexOfSharp+1, indexOfSecondQuote).trim();
						Property objectProperty=new Property(propertyName,propertyValue,objectType);
						concept.getPropertyList().add(objectProperty);
					}						
						currentLine=scanner.nextLine();
				}
			}
			if (currentLine.contains("<owl:NamedIndividual rdf:about")){
				
				indexOfSharp=currentLine.indexOf("#");
				indexOfSecondQuote=currentLine.lastIndexOf("\"");	
				conceptName=currentLine.substring(indexOfSharp+1, indexOfSecondQuote).trim();
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