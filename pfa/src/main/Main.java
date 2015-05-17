package main;


import java.util.ArrayList;
import java.util.Scanner;

import com.pfa.beans.Concept;
import com.pfa.beans.Property;
import com.pfa.controller.Controller;
import com.pfa.model.PropertyManagement;
import com.pfa.model.Similarity;
import com.pfa.model.ConceptManagement;
import com.pfa.model.SimilarityDisplay;
import com.pfa.view.Window;

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
		
		Window window =new Window();
		SimilarityDisplay simDisp=new SimilarityDisplay();
		Controller controller=new Controller(simDisp,window);
		
		

		
		
}
}