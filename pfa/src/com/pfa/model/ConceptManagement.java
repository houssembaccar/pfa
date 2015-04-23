package com.pfa.model;

import java.util.ArrayList;

import com.pfa.beans.Concept;

public class ConceptManagement {
	private static ArrayList<Concept> conceptList= new ArrayList<Concept>();

	public static ArrayList<Concept> getConceptList() {
		return conceptList;
	}

	public static void addConcept(Concept concept) {
		conceptList.add(concept);
	}
	
	public static void displayConceptList() {
		for(int i=0;i<conceptList.size();i++) {
			if (conceptList.get(i).getParent()!=null){
				System.out.println(conceptList.get(i).getName()+" subclass of    :"+conceptList.get(i).getParent().getName());
				if (conceptList.get(i).isIndividual())
					System.out.println("individual");
			}
			
			else
				System.out.println("\n"+conceptList.get(i).getName()+" subclass of    : Thing"+conceptList.get(i).getParentName());
			for(int j=0;j<conceptList.get(i).getPropertyList().size();j++)
			System.out.println(conceptList.get(i).getPropertyList().get(j).toString());

		    
		}
	}
	
	public static void addParentName(String parentName, Concept concept) {
		conceptList.get(conceptList.indexOf(concept)).setParentName(parentName);
	}
	public static Concept searchConcept(String conceptName) {
		String name;
		boolean condition;
		for(int i=0;i<conceptList.size();i++) {
			name=conceptList.get(i).getName();
			condition= name.equals(conceptName);
			if (condition)
				return conceptList.get(i);
		}
		return null;		
	}

}
