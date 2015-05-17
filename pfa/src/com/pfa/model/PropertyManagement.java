package com.pfa.model;

import java.util.ArrayList;

import main.UserInputHandler;
import main.UserInputHandler.InputType;

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
	public Property associateWordToProperty(String word){
		UserInputHandler userInputHandler=new UserInputHandler();
		Property property = null;
		int indexOfEqual=0;
		String propertyValue;
		String propertyName;
		if (userInputHandler.isOfType(word).equals(InputType.dataProperty)){
			property=new Property(word,"","data");
		}else if (userInputHandler.isOfType(word).equals(InputType.dataPropertyWithValue)){
			indexOfEqual=word.indexOf("=");
			propertyValue=word.substring(indexOfEqual+1);
			propertyName=word.substring(0,indexOfEqual);
			property=new Property(propertyName,propertyValue,"data");
		}else if (userInputHandler.isOfType(word).equals(InputType.objectProperty)){
			property=new Property(word,"","object");
		}else if (userInputHandler.isOfType(word).equals(InputType.objectPropertyWithValue)){
			indexOfEqual=word.indexOf("=");
			propertyValue=word.substring(indexOfEqual+1);
			propertyName=word.substring(0,indexOfEqual);
			property=new Property(propertyName,propertyValue,"object");
		}
		return property;
	}
	
	public ArrayList<Property> associateQueryForListOfProperties(String[] arrayOfWords){
		ArrayList<Property> propertyList = new ArrayList<Property>();
		UserInputHandler userInputhandler=new UserInputHandler();
		int i=0;
		InputType firstWord=userInputhandler.isOfType(arrayOfWords[0]);
		if((firstWord.equals(InputType.dataProperty))||(firstWord.equals(InputType.dataPropertyWithValue))||(firstWord.equals(InputType.objectProperty))||(firstWord.equals(InputType.objectPropertyWithValue))){
			propertyList.add(associateWordToProperty(arrayOfWords[0]));
		}
		for( i=1; i<arrayOfWords.length;i++){
			propertyList.add(associateWordToProperty(arrayOfWords[i]));
		}
		return propertyList;
	}
		

}
