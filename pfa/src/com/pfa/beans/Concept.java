package com.pfa.beans;

import java.util.ArrayList;

public class Concept {
	
	private String name;
	private String parentName;
	private Concept parent;
	private boolean individual;
    private ArrayList <Property> propertyList=new ArrayList<Property>();
	
	public ArrayList<Property> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(ArrayList<Property> propertyList) {
		this.propertyList = propertyList;
	}

	public Concept(String name) {
		this.name=name;
		this.individual=false;
	}
	
	public Concept() {
		// TODO Auto-generated constructor stub
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
	
	public Property getProperty(String propertyName){
		ArrayList<Property> propertyList=getPropertyList();
		int size=propertyList.size();
		for(int i=0;i<size;i++){
			if(propertyList.get(i).getName().equals(propertyName))
				return propertyList.get(i);
		}
		return null;
	}

}
