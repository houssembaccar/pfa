package main;


import java.util.Scanner;

import com.pfa.model.ConceptManagement;

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
		serviceName=scanner.nextLine();
		if(ConceptManagement.searchConcept(serviceName)!=null)
			System.out.printf("%s is avalable",serviceName);
		else
			System.out.printf("%s dose not exist in our repository",serviceName);
	}

}
