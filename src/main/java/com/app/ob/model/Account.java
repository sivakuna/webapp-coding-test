package com.app.ob.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {

	private String id;
	private String number;
	private List<Holder> holders;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public List<Holder> getHolders() {
		return holders;
	}
	public void setHolders(List<Holder> holders) {
		this.holders = holders;
	}
	
	
}
