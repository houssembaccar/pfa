package com.pfa.model;

import java.util.ArrayList;

import com.pfa.beans.Concept;
import com.pfa.beans.Property;

public class PropertyManagement {
	
	public PropertyManagement() {
		super();
		// TODO Auto-generated constructor stub
	}
//this function associate return a concept that has a property given as argument if it exist else it return null
	public Concept associateConceptToPropertyWithOutValue(Property property){
		Concept associatedConcept=null;
		ArrayList<Concept> conceptList=ConceptManagement.getConceptList();
		ArrayList<Property> propertyList;
		Concept iterConcept=new Concept();
		Property iterProperty=new Property();
		int conceptCounter=0;
		int propertyCounter=0;
		while (associatedConcept==null && conceptCounter < conceptList.size()) {
			iterConcept=conceptList.get(conceptCounter);
			if (iterConcept.isIndividual()){
				propertyList=iterConcept.getPropertyList();
				propertyCounter=0;
				while(associatedConcept==null && propertyCounter<propertyList.size()){
					iterProperty=iterConcept.getPropertyList().get(propertyCounter);
					if (property.getName().equals(iterProperty.getName()))
						associatedConcept=iterConcept;
					propertyCounter++;
				}
			}		
			conceptCounter++;
		}
		return associatedConcept;
	}
	
	public Concept associateConceptToPropertyWithValue(Property property){
		Concept associatedConcept=null;
		ArrayList<Concept> conceptList=ConceptManagement.getConceptList();
		ArrayList<Property> propertyList;
		Concept iterConcept=new Concept();
		Property iterProperty=new Property();
		Concept closerConcept=new Concept();
		boolean closerConceptNotInitialized=true;
		int conceptCounter=0;
		int propertyCounter=0;
		double iterPropertyValue=0;
		double propertyValue=0;
		double closerConceptPropertyValue=0;
		// loop all indviduals 
		while (associatedConcept==null && conceptCounter < conceptList.size()) {
			iterConcept=conceptList.get(conceptCounter);
			if (iterConcept.isIndividual()){
				propertyList=iterConcept.getPropertyList();
				propertyCounter=0;
				//loop all the properties of the individual iterConcept
				while(associatedConcept==null && propertyCounter<propertyList.size()){
					iterProperty=iterConcept.getPropertyList().get(propertyCounter);
					if (property.getName().equals(iterProperty.getName())){
						// we have two cases : 1) treatment for data property 2) treatment for object property
						if (iterProperty.getType().equals("data")){
							//initialization of the closerConcept to the first concept witch have this property
							if (closerConceptNotInitialized){
								closerConcept=iterConcept;
								closerConceptNotInitialized=false;
							}
							iterPropertyValue=Double.parseDouble(iterProperty.getValues());
							propertyValue=Double.parseDouble(property.getValues());
							closerConceptPropertyValue=Double.parseDouble(closerConcept.getProperty(property.getName()).getValues());
							//we affect iterConcept to closerConcept if he has a closer property value
							if (Math.abs(iterPropertyValue-propertyValue) < Math.abs(closerConceptPropertyValue-propertyValue))
							    closerConcept=iterConcept;
						}		
						else if (iterProperty.getType().equals("object")){
							//initialization of the closerConcept to the first concept witch have this property
							if (closerConceptNotInitialized){
								closerConcept=iterConcept;
								closerConceptNotInitialized=false;
							}
							if (iterProperty.getValues().equals(property.getValues())){
								return iterConcept;
							}
						}
					}					
					propertyCounter++;
				}
			}		
			conceptCounter++;
		}
		return closerConcept;
	}

}
