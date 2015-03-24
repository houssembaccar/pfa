package main;


import com.pfa.model.ConceptManagement;

public class Main {

	public static void main(String[] args) {
		Input inputFile=new Input();
		inputFile.openFile("cloudle.owl");
		inputFile.readFile();
		inputFile.closeFile();
		ConceptManagement.displayConceptList();
		System.out.println(ConceptManagement.searchConcept(ConceptManagement.getConceptList().get(2).getName()).getName());
	}

}
