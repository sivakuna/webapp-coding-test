package com.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.app.ob.model.OBTransactionResponse;

@Component
public class TransactionLoader {
	 private Logger log = LoggerFactory.getLogger(TransactionLoader.class);
	
	@Autowired
    private RestTemplate restTemplate;
	
	public OBTransactionResponse getTransactionsFromOB() throws Exception{
		log.info("Rest call to https://apisandbox.openbankproject.com/obp/v1.2.1/banks/rbs/accounts/savings-kids-john/public/transactions");
		OBTransactionResponse response = null;
		response = restTemplate.getForObject("https://apisandbox.openbankproject.com/obp/v1.2.1/banks/rbs/accounts/savings-kids-john/public/transactions", OBTransactionResponse.class);
		return response;
	}
}
