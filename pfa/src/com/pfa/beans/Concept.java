package com.pfa.beans;

public class Concept {
	
	private String name;
	private String parentName;
	private Concept parent;
	
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

	public Concept(String name) {
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
