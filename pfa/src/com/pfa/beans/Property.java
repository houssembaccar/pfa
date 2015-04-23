package com.pfa.beans;

public class Property {
	private String name;
	private String values;
	private String type;
	public Property(String name, String values,String type) {
		super();
		this.name = name;
		this.values = values;
		this.type= type;
	}
	public Property() {
		// TODO Auto-generated constructor stub
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValues() {
		return values;
	}
	public void setValues(String values) {
		this.values = values;
	}	
	@Override
	public String toString() {
		return "Property [name= " + name + ", values= " + values + ", type= " + type + "]\n";
	}

}
