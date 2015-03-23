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
			System.out.println(conceptList.get(i).getName());
		}
	}
	

}
