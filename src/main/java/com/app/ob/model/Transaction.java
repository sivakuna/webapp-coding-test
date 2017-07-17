package com.app.ob.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {

	private String id;
	
	@JsonProperty("this_account")
	private Account account;
	
	@JsonProperty("other_account")
	private OtherAccount otherAccount;
	
	@JsonProperty("details")
	private Details details;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Details getDetails() {
		return details;
	}

	public void setDetails(Details details) {
		this.details = details;
	}

	public OtherAccount getOtherAccount() {
		return otherAccount;
	}

	public void setOtherAccount(OtherAccount otherAccount) {
		this.otherAccount = otherAccount;
	}
	
	
}
