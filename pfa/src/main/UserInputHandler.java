package main;

import java.util.ArrayList;

import com.pfa.beans.Concept;
import com.pfa.beans.Property;
import com.pfa.model.ConceptManagement;
import com.pfa.model.PropertyManagement;

public class UserInputHandler {
	
	private String[] arrayOfWords;
	
	public enum InputType {

	    individual, objectPropertyWithValue, dataPropertyWithValue, objectProperty, dataProperty, unknown

	}
	
	public String[] getArrayOfWords() {
		return arrayOfWords;
	}
	public void setArrayOfWords(String query) {
		this.arrayOfWords = query.split(" ");
	}
	//this function associate the appropriate concept to a word given as argument if the word isn't a concept or individual or don't contain a property it return null
	public Concept associateConceptToInputWord(String word){
		//if the word correspond to a concept we return this concept 
		Concept concept = ConceptManagement.searchConcept(word);
		if (concept!=null)
			return concept;
		// if the word isn't a concept and don't contain "=" we suppose that it is a property without a value. if it's not a property we return null
		else if (!word.contains("=")){
			Property property=new Property();
			property.setName(word);
			PropertyManagement propertyManager=new PropertyManagement();
			concept=propertyManager.associateConceptToPropertyWithOutValue(property);
			//if the word contains "==" we suppose that it's a DATA property with a value. but if it's not we return null
		}else if (word.contains("==")){
			int indexOfEqual=word.indexOf("=");
			String propertyName=word.substring(0,indexOfEqual);
			String propertyValue=word.substring(indexOfEqual+2);
			String PropertyType="data";
			Property property=new Property(propertyName,propertyValue,PropertyType);
			PropertyManagement propertyManager=new PropertyManagement();
			concept = propertyManager.associateConceptToPropertyWithValue(property);
		}
		//if the word contains "=" we suppose that it's a OBJECT property with a value. but if it's not we return null
		else if (word.contains("=")){
			int indexOfEqual=word.indexOf("=");
			String propertyName=word.substring(0,indexOfEqual);
			String propertyValue=word.substring(indexOfEqual+1);
			String PropertyType="object";
			Property property=new Property(propertyName,propertyValue,PropertyType);
			PropertyManagement propertyManager=new PropertyManagement();
			concept = propertyManager.associateConceptToPropertyWithValue(property);
		}
		return concept;	
	}
	public InputType isOfType(String word){
		
		String propertyName = null;
		String propertyValue = null;
		String PropertyType;
		
		
		 if (word.contains("=") ){
			int indexOfEqual=word.indexOf("=");
			propertyName=word.substring(0,indexOfEqual);
			propertyValue=word.substring(indexOfEqual+1);
		    PropertyType="object";
			Property property=new Property(propertyName,propertyValue,PropertyType);	
	}
		ArrayList<Concept> conceptList=ConceptManagement.getConceptList();
		ArrayList<Property> propertyList;
		Concept iterConcept=new Concept();
		Property iterProperty=new Property();
		int conceptCounter=0;
		int propertyCounter=0;
		double iterPropertyValue=0;
			    
		while (conceptCounter < conceptList.size()) {
			iterConcept=conceptList.get(conceptCounter);
			if (iterConcept.isIndividual()){
				if (iterConcept.getName().equals(word))
				   return InputType.individual;
				propertyList = iterConcept.getPropertyList();
				propertyCounter=0;
				//loop all the properties of the individual iterConcept
				while( propertyCounter<propertyList.size()){
					iterProperty=iterConcept.getPropertyList().get(propertyCounter);
					if (iterProperty.getType().equals("object")){
						if (iterProperty.getName().equals(word))
							return InputType.objectProperty;
						else if (iterProperty.getName().equals(propertyName))
							return InputType.objectPropertyWithValue;
					}
					else if (iterProperty.getType().equals("data")){
						if (iterProperty.getName().equals(word))
							return InputType.dataProperty;
						else if (iterProperty.getName().equals(propertyName)){
						try{
							Double.parseDouble(propertyValue);
							return InputType.dataPropertyWithValue;
						}catch(NumberFormatException e){
							return InputType.unknown;
						}
							
					}
					}
					propertyCounter++;
					}
					}					
			conceptCounter++;
				}
		return InputType.unknown;
			}	
	public boolean validInput(){
		boolean validInput=true;
		boolean unknownInput=true;
		int numberOfIndividual=0;
		for (int i=0;i<arrayOfWords.length;i++){
			if(isOfType(arrayOfWords[i]).equals(InputType.individual)){
				numberOfIndividual++;
			}
			if (unknownInput && !isOfType(arrayOfWords[i]).equals(InputType.unknown)){
				unknownInput=false;
			}
		}
		if (numberOfIndividual > 1)
			validInput= false;
		if ((numberOfIndividual ==1 && !isOfType(arrayOfWords[0]).equals(InputType.individual)) || unknownInput)
				validInput = false;
		return validInput;	
	}
			
		
}
