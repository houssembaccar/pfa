package main;

import com.pfa.beans.*;
import com.pfa.model.ConceptManagement;

public class Main {

	public static void main(String[] args) {
		Input inputFile=new Input();
		inputFile.openFile("ontology.owl");
		inputFile.readFile();
		inputFile.closeFile();
		ConceptManagement.displayConceptList();
	}

}
