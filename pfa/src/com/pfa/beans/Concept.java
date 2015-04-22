package com.pfa.beans;

public class Concept {
	
	private String name;
	private String parentName;
	private Concept parent;
	private boolean individual;
	
	public Concept(String name) {
		this.name=name;
		this.individual=false;
	}
	
	public boolean isEqualTo(Concept concept){
		if (this.name==concept.getName() && this.parentName==concept.getParentName()){
			return true;
		}
		return false;
	}
	
	public Concept getParent() {
		return parent;
	}

	public void setParent(Concept parent) {
		this.parent = parent;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isIndividual() {
		return individual;
	}

	public void setIndividual(boolean individual) {
		this.individual = individual;
	}

}
