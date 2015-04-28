package main;

import java.util.ArrayList;

import com.pfa.beans.Concept;
import com.pfa.beans.Property;
import com.pfa.model.ConceptManagement;
import com.pfa.model.PropertyManagement;

public class UserInputHandler {
	
	private String[] arrayOfWords;
	
	
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

}
