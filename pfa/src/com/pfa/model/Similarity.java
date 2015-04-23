package com.pfa.model;

import java.util.ArrayList;

import com.pfa.beans.Concept;
import com.pfa.beans.Property;

public class Similarity {
	public  int depth(Concept concept){
		if (concept==null)
			return 1;
		int depth=2;
		while(concept.getParent()!=null){
			depth++;
			concept=concept.getParent();
		}
		return depth;
	}	
	
	public int depthInterDepth (Concept concept1 ,Concept concept2){
		
		int depth1=depth(concept1);
		int depth2=depth(concept2);
		//if the depth of one of the two concepts is equal to one the return value of depthInterDepth must be equal to one
		if (depth1==1 || depth2==1)
			return 1;
		//if the two concepts are equal then return the depth of one of them
		if (depth1==depth2){
			if (concept1.isEqualTo(concept2)){
				return depth1;
			}
			else {
				return depthInterDepth(concept1.getParent(),concept2.getParent());
			}
		}/* if the two concepts don't have the same depth we call recursively the method with the parent 
		    of the concept that has the greatest depth and the concept with lowest depth as arguments */
		else{
			if (depth1>depth2){
				return depthInterDepth(concept1.getParent(),concept2);
			}else {
				return depthInterDepth(concept1,concept2.getParent());
			}
		}
		
	}
	//******************************************************************object property similarity****************************************************************************** 
	public float conceptSim(Concept userConcept,Concept concept){
		return (float)depthInterDepth(userConcept,concept)/(float)depth(userConcept);
	}
	public int numberOfObjectProperty (Concept concept){
		ArrayList<Property> propertyList=concept.getPropertyList();
		int numberOfObjectProperty=0;
		for(int i=0;i<propertyList.size();i++){
			if(propertyList.get(i).getType().equals("object"))
				numberOfObjectProperty++;
		}
		return numberOfObjectProperty;
	}
	
	public double objectPropertySim(Concept concept1, Concept concept2){
		double similaritySum=0;
		int numberOfObjectPropertyOfConcept1=numberOfObjectProperty(concept1);
		int numberOfPropertyOfConcept1=concept1.getPropertyList().size();
		int numberOfPropertyOfConcept2=concept2.getPropertyList().size();
		ArrayList<Property> propertyListOfConcept1=concept1.getPropertyList();
		ArrayList<Property> propertyListOfConcept2=concept2.getPropertyList();
		Property property1= new Property();
		Property property2= new Property();
		for(int i=0;i<numberOfPropertyOfConcept1;i++){
			property1= propertyListOfConcept1.get(i);
			for(int j=0;j<numberOfPropertyOfConcept2;j++){
				property2=propertyListOfConcept2.get(j);
				if (property1.getName().equals(property2.getName())&& property1.getType().equals("object")){
					similaritySum+=conceptSim(concept1.getParent(),concept2.getParent());
				}
			}			
		}
		return similaritySum/numberOfObjectPropertyOfConcept1;
	}
	//*****************************************************************************data property similarity**********************************************************************
	public int numberOfDataProperty (Concept concept){
		ArrayList<Property> propertyList=concept.getPropertyList();
		int numberOfDataProperty=0;
		for(int i=0;i<propertyList.size();i++){
			if(propertyList.get(i).getType().equals("data"))
				numberOfDataProperty++;
		}
		return numberOfDataProperty;
	 }
	public double dataPropertySim(Concept concept1, Concept concept2){
		double similaritySum=0;
		int numberOfDataPropertyOfConcept1=numberOfDataProperty(concept1);
		int numberOfPropertyOfConcept1=concept1.getPropertyList().size();
		int numberOfPropertyOfConcept2=concept2.getPropertyList().size();
		ArrayList<Property> propertyListOfConcept1=concept1.getPropertyList();
		ArrayList<Property> propertyListOfConcept2=concept2.getPropertyList();
		Property property1= new Property();
		Property property2= new Property();
		double value1=0;
		double value2=0;
		for(int i=0;i<numberOfPropertyOfConcept1;i++){
			property1= propertyListOfConcept1.get(i);
			for(int j=0;j<numberOfPropertyOfConcept2;j++){
				property2=propertyListOfConcept2.get(j);
				if (property1.getName().equals(property2.getName()) && property1.getType().equals("data") && property2.getType().equals("data")){
					value1=Double.parseDouble( property1.getValues());
					value2=Double.parseDouble(property2.getValues());
					similaritySum+=1-Math.abs(value1-value2)/Math.max(value1,value2);
				}
			}			
		}
		return similaritySum/numberOfDataPropertyOfConcept1;
	}
		
	}
	

