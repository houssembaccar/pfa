package main;


import java.util.Scanner;

import com.pfa.beans.Concept;
import com.pfa.beans.Property;
import com.pfa.model.PropertyManagement;
import com.pfa.model.Similarity;
import com.pfa.model.ConceptManagement;
import com.pfa.model.SimilarityDisplay;

public class Main {
	
	public static void main(String[] args) {
		/*UserInputHandler userInputHadler=new UserInputHandler();
		Scanner userScanner = new Scanner(System.in);
		String inputString= userScanner.nextLine();
		userInputHadler.userInputSplit(inputString);*/
		Scanner scanner = new Scanner(System.in);
		String serviceName=null;
		Input inputFile=new Input();
		inputFile.openFile("cloudle.owl");
		inputFile.readFile();
		inputFile.closeFile();
		System.out.println("this is the list of our services\n");
		ConceptManagement.displayConceptList();
		PropertyManagement propertyManager=new 		PropertyManagement();
		Property property = new Property("hasFileSystem","","");
		
		System.out.println(propertyManager.associateConceptToPropertyWithValue(property).getName());
		
		SimilarityDisplay simDisp=new SimilarityDisplay();
		System.out.println("\n\n");
		simDisp.displayList(simDisp.listSimilarity(propertyManager.associateConceptToPropertyWithValue(property)));
		
		/*System.out.println("please type in the name of the service you are looking for :\n ");
		serviceName =scanner.nextLine();
		Concept concept = ConceptManagement.searchConcept(serviceName);
		Similarity  similarity=new Similarity();
		SimilarityDisplay simDisp=new SimilarityDisplay();
		
		System.out.println("\n\n");
		simDisp.displayList(simDisp.listSimilarity(concept));
		
		//System.out.println(similarity.conceptSim(concept, ConceptManagement.searchConcept("i7")));
		/*if (concept.isIndividual()){
			
			simDisp.displayList(simDisp.listSimilarityConcepts(concept.getParent()));
			System.out.println("\n\n");
			simDisp.displayList(simDisp.listSimilarityObjectProperty(concept));
			System.out.println("\n\n");
			simDisp.displayList(simDisp.listSimilarityDataProperty(concept));
		}else{
			simDisp.displayList(simDisp.listSimilarityConcepts(concept));
		}	*/	
		/*System.out.println("\n");
		for(int i=0;i<ConceptManagement.getConceptList().size();i++)
		System.out.println(similarity.dataPropertySim(concept, ConceptManagement.getConceptList().get(i)));
*/
}
}