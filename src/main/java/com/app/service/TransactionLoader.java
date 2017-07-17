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
		
		OBTransactionResponse response = null;
//		try{
			 response = restTemplate.getForObject("https://apisandbox.openbankproject.com/obp/v1.2.1/banks/rbs/accounts/savings-kids-john/public/transactions", OBTransactionResponse.class);
			
//	        log.debug("GARBAGE IN RESPONSE:" + "\n\n" + response.substring(0, 5) + "\n\n");
//	        String toBeParsed = response.substring(6, response.length());
//	        atmDataPopulatorLogger.debug("TO BE PARSED RESPONSE:" + "\n\n" + toBeParsed + "\n\n");
//	        ObjectMapper objectMapper = new ObjectMapper();
//	        try {
//				OBTransactionResponse obTransactionResponse = objectMapper.readValue(toBeParsed, OBTransactionResponse.class);
//				System.out.println(obTransactionResponse);

//	        atmDataPopulatorLogger.debug("PARSED RESPONSE:" + "\n\n" + ingAtmLocations.toString() + "\n\n");

//	        return Arrays.asList(ingAtmLocations);
//			} catch (Exception e) {
//			
//				e.printStackTrace();
//			}
		return response;
	}
}
