package main;


import java.util.Scanner;

import com.pfa.beans.Concept;
import com.pfa.model.Similarity;
import com.pfa.model.ConceptManagement;
import com.pfa.model.SimilarityDisplay;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String serviceName=null;
		Input inputFile=new Input();
		inputFile.openFile("cloudle.owl");
		inputFile.readFile();
		inputFile.closeFile();
		System.out.println("this is the list of our services\n");
		ConceptManagement.displayConceptList();
		System.out.println("please type in the name of the service you are looking for :\n ");
		serviceName =scanner.nextLine();
		Concept concept = ConceptManagement.searchConcept(serviceName);
		Similarity  similarity=new Similarity();
		SimilarityDisplay simDisp=new SimilarityDisplay();
		//System.out.println(similarity.conceptSim(concept, ConceptManagement.searchConcept("i7")));
		if (concept.isIndividual()){
			
			simDisp.displayList(simDisp.listSimilarityConcepts(concept.getParent()));
		}else{
			simDisp.displayList(simDisp.listSimilarityConcepts(concept));
		}
		
		

}
}