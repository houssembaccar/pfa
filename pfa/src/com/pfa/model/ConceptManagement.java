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
			System.out.println(conceptList.get(i).getName()+" subclass of    :"+conceptList.get(i).getParentName());
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
			condition= name==conceptName;
			if (condition)
				return conceptList.get(i);
		}
		return null;		
	}

}
