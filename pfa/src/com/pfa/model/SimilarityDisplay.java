package com.pfa.model;

import java.util.ArrayList;

import com.pfa.beans.Concept;

public class SimilarityDisplay {

	double sim=0;
	double simObjectProperty;
	double simDataProperty;
	Concept concept;
	
	public SimilarityDisplay(double sim, Concept concept) {
		super();
		this.sim = sim;
		this.concept = concept;
	}
	public SimilarityDisplay() {
		// TODO Auto-generated constructor stub
	}
	public double getSim() {
		return sim;
	}
	public void setSim(double sim) {
		this.sim = sim;
	}
	public Concept getConcept() {
		return concept;
	}
	public void setConcept(Concept concept) {
		this.concept = concept;
	}

	public ArrayList<SimilarityDisplay> listSimilarityConcepts (Concept concept){

		ArrayList <SimilarityDisplay> simList=new ArrayList<SimilarityDisplay>();
		Similarity similarity = new Similarity();
		for (int i=0;i<ConceptManagement.getConceptList().size();i++){
			Concept iterConcept= ConceptManagement.getConceptList().get(i);
			if (iterConcept!=null && concept !=null)
			{// we compare only the similarity between individuals 
				if (iterConcept.isIndividual()){
					SimilarityDisplay element= new SimilarityDisplay(similarity.conceptSim(concept, iterConcept.getParent()),iterConcept);
					simList.add(element);
				}
			}
		}
		return sort(simList);
	}
	
	public ArrayList<SimilarityDisplay> listSimilarityObjectProperty (Concept concept){

		ArrayList <SimilarityDisplay> simList=new ArrayList<SimilarityDisplay>();
		Similarity similarity = new Similarity();
		for (int i=0;i<ConceptManagement.getConceptList().size();i++){
			Concept iterConcept= ConceptManagement.getConceptList().get(i);
			if (iterConcept!=null && concept !=null)
			{// we compare only the similarity between individuals 
				if (iterConcept.isIndividual()){
					SimilarityDisplay element= new SimilarityDisplay(similarity.objectPropertySim(concept, ConceptManagement.getConceptList().get(i)),iterConcept);
					simList.add(element);
				}
			}
		}
		return sort(simList);
	}
	
	public ArrayList<SimilarityDisplay> listSimilarityDataProperty (Concept concept){

		ArrayList <SimilarityDisplay> simList=new ArrayList<SimilarityDisplay>();
		Similarity similarity = new Similarity();
		for (int i=0;i<ConceptManagement.getConceptList().size();i++){
			Concept iterConcept= ConceptManagement.getConceptList().get(i);
			if (iterConcept!=null && concept !=null)
			{// we compare only the similarity between individuals 
				if (iterConcept.isIndividual()){
					SimilarityDisplay element= new SimilarityDisplay(similarity.dataPropertySim(concept, ConceptManagement.getConceptList().get(i)),iterConcept);
					simList.add(element);
				}
			}
		}
		return sort(simList);
	}
	
	@Override
	public String toString() {
		return "SimilarityDisplay [sim=" + sim + ", concept=" + concept.getName() + "]";
	}
	private ArrayList<SimilarityDisplay> sort(ArrayList<SimilarityDisplay> simList){
		int max= 0;
		SimilarityDisplay aux=new SimilarityDisplay();
		for(int i=0;i<simList.size()-1;i++){
			max=i;
			for(int j=i+1;j<simList.size();j++){
				if (simList.get(max).getSim()<simList.get(j).getSim())
					max=j;
			}
			aux=simList.get(max); simList.set(max, simList.get(i));simList.set(i, aux);
		}
				
		
		return simList;
	}
	public void displayList(ArrayList<SimilarityDisplay> list){		
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).toString());
		}
	}
}
