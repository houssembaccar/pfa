package com.pfa.model;

import java.util.ArrayList;

import com.pfa.beans.Concept;

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
		return sort(simList);
	}
	
	@Override
	public String toString() {
		return "SimilarityDisplay [simConcept=" + simConcept
				+ ", simObjectProperty=" + simObjectProperty
				+ ", simDataProperty=" + simDataProperty + ", similaritySum="
				+ similaritySum + ", concept=" + concept.getName() + "]";
	}
	// this method sort an arrayList given as parameter
	private ArrayList<SimilarityDisplay> sort(ArrayList<SimilarityDisplay> simList){
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
}
