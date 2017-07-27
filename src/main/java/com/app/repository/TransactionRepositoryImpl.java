package com.app.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.app.ob.model.OBTransactionResponse;

/**
 * Transaction repository implementation.
 * @author skuna
 *
 */
@Repository
public class TransactionRepositoryImpl implements TransactionRepository {
	 private Logger log = LoggerFactory.getLogger(TransactionRepositoryImpl.class);
	
	@Autowired
    private RestTemplate restTemplate;
	
	private static String OP_TRANS_URL = "https://apisandbox.openbankproject.com/obp/v1.2.1/banks/rbs/accounts/savings-kids-john/public/transactions"; 
	
	@Override
	public OBTransactionResponse getTransactionsFromOB() throws Exception{
		log.info("Rest call to " + OP_TRANS_URL);
		OBTransactionResponse response = null;
		response = restTemplate.getForObject(OP_TRANS_URL, OBTransactionResponse.class);
		return response;
	}

}
