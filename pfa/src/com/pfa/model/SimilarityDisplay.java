package com.pfa.model;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.UserInputHandler;
import main.UserInputHandler.InputType;

import com.pfa.beans.Concept;
import com.pfa.beans.Property;

public class SimilarityDisplay {

	private static final double alpha = .333;// used in the mathematical equation to calculate the sum of similarity
	private static final double beta = .333;// used in the mathematical equation to calculate the sum of similarity
	double simConcept;
	double simObjectProperty;
	double simDataProperty;
	double similaritySum;
	Concept concept;
	
	public SimilarityDisplay(double simConcept, double simObjectProperty,
			double simDataProperty, double similaritySum, Concept concept) {
		super();
		this.simConcept = simConcept;
		this.simObjectProperty = simObjectProperty;
		this.simDataProperty = simDataProperty;
		this.similaritySum = similaritySum;
		this.concept = concept;
	}
	public SimilarityDisplay() {
		// TODO Auto-generated constructor stub
	}
	public double getSimConcept() {
		return simConcept;
	}
	public void setSimConcept(double simConcept) {
		this.simConcept = simConcept;
	}
	public Concept getConcept() {
		return concept;
	}
	public void setConcept(Concept concept) {
		this.concept = concept;
	}
	public double getSimObjectProperty() {
		return simObjectProperty;
	}
	public void setSimObjectProperty(double simObjectProperty) {
		this.simObjectProperty = simObjectProperty;
	}
	public double getSimDataProperty() {
		return simDataProperty;
	}
	public void setSimDataProperty(double simDataProperty) {
		this.simDataProperty = simDataProperty;
	}
	public double getSimilaritySum() {
		return similaritySum;
	}
	public void setSimilaritySum(double similaritySum) {
		this.similaritySum = similaritySum;
	}
	
	
	public ArrayList<SimilarityDisplay> listSimilarity (Concept concept){

		ArrayList <SimilarityDisplay> simList=new ArrayList<SimilarityDisplay>();
		Similarity similarity = new Similarity();
		double simConcept;
		double simObjectProperty=0;
		double simDataProperty=0;
		double similaritySum=0;
		for (int i=0;i<ConceptManagement.getConceptList().size();i++){
			Concept iterConcept= ConceptManagement.getConceptList().get(i);
			if (iterConcept!=null && concept !=null)
			{// we compare only the similarity between individuals 
				if (iterConcept.isIndividual()){
					simConcept = similarity.conceptSim(concept, iterConcept.getParent());
					simObjectProperty = similarity.objectPropertySim(concept, iterConcept);
					simDataProperty = similarity.dataPropertySim(concept, iterConcept);
					//given alpha and beta we calculate the final similarity using this mathematical equation  
					similaritySum = alpha*simConcept + beta*simObjectProperty + (1-alpha-beta)*simDataProperty;
					SimilarityDisplay element= new SimilarityDisplay(simConcept,simObjectProperty,simDataProperty,similaritySum,iterConcept);
					simList.add(element);
				}
			}
		}
		return simList;
	}
	
	@Override
	public String toString() {
		return " [ concept=" + concept.getName() + "]";
	}
	// this method sort an arrayList given as parameter
	public ArrayList<SimilarityDisplay> sort(ArrayList<SimilarityDisplay> simList){
		int max= 0;
		SimilarityDisplay aux=new SimilarityDisplay();
		for(int i=0;i<simList.size()-1;i++){
			max=i;
			for(int j=i+1;j<simList.size();j++){
				if (simList.get(max).getSimilaritySum()<simList.get(j).getSimilaritySum())
					max=j;
			}
			aux=simList.get(max); simList.set(max, simList.get(i));simList.set(i, aux);
		}						
		return simList;
	}
	//this method displays an arrayList given as parameter
	public void displayList(ArrayList<SimilarityDisplay> list){		
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).toString());
		}
	}
	public ArrayList<SimilarityDisplay> listSimilaritySum (ArrayList<SimilarityDisplay> finalResult,ArrayList<SimilarityDisplay> simList){
		double sum=0;
		for(int i=0;i<finalResult.size();i++){
			sum=finalResult.get(i).getSimilaritySum()+simList.get(i).getSimilaritySum();
			finalResult.get(i).setSimilaritySum(sum);

			finalResult.get(i).setConcept(simList.get(i).getConcept());
		}		
		return finalResult;	
	}
	//this function eliminate idividuals that haven't a given property.
	public ArrayList<SimilarityDisplay> eliminateInappropriateIndividuals (ArrayList<SimilarityDisplay> simList,Property property){
		
		Concept individual;
		boolean hasProperty=false;
		for(int i=0;i<simList.size();i++){
			hasProperty=false;
			individual =simList.get(i).getConcept();
			if (individual.isIndividual()){
				ArrayList<Property> propertyList=individual.getPropertyList();
				if(propertyList.size()!=0){
					for (int j=0;j<propertyList.size();j++){
						if (property.getName().equals(propertyList.get(j).getName())){
							if (property.getValues().equals("")){
								hasProperty=true;
							}
							else if(property.getValues().equals(individual.getPropertyList().get(j).getValues())){
								hasProperty=true;

							}
						}
							
					}				
				}
				if (!hasProperty){
					System.out.println(individual.getName()+"   who has "+individual.getPropertyList().toString()+"  =====> remouved \n");
					simList.remove(i);
					i--;
				}
			}
		}
		return simList;
	}
	public ArrayList<SimilarityDisplay> search(String query){
		UserInputHandler userInputHandler=new UserInputHandler();
		userInputHandler.setArrayOfWords(query);
        ArrayList<SimilarityDisplay> resultList=new ArrayList<SimilarityDisplay>();
        userInputHandler.setArrayOfWords(query);
        String [] arrayOfWords = userInputHandler.getArrayOfWords();
        PropertyManagement propertyManager=new PropertyManagement();
        ArrayList<Property> propertyList=propertyManager.associateQueryForListOfProperties(arrayOfWords);
        
        if (userInputHandler.validInput()){
        	if (userInputHandler.isOfType(arrayOfWords[0]).equals(InputType.individual)){
        		resultList=listSimilarity(ConceptManagement.searchConcept(arrayOfWords[0]));
        		}
        	else{
        		resultList=listSimilarity(propertyManager.associateConceptToPropertyWithOutValue(propertyList.get(0)));
        	}
        	for (int i=0;i<propertyList.size();i++){
        		Property property=propertyList.get(i);
        		if (property!=null){
            		resultList=eliminateInappropriateIndividuals(resultList,property );
        		}
        	}
        }	
		else {
			JOptionPane.showMessageDialog(new JFrame(), "you must enter an individual followed or not by a sequence of properties.\n to see the list of individuals and properties you can consult the ontology.","unvalid input warning",JOptionPane.WARNING_MESSAGE);
			System.out.println("unvalid Input");
		}
		return resultList;
	}
}
