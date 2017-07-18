package com.rest.test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.apache.commons.codec.binary.Base64;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.app.bb.model.BBTransaction;

/**
 * Junit for Transactions rest api.
 * 
 * @author skuna
 *
 */

public class TransactionsRestTest {
	
	private static final Logger logger = LoggerFactory
			.getLogger(TransactionsRestTest.class);

	private RestTemplate restTemplate = new RestTemplate();
	private static String url;
	private static HttpEntity<String> request;
	
	/**
	 * Initialize the url.
	 */
	@BeforeClass
	public static void init() {
		
		String plainCreds = "rest:rest";
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);
		url = "http://localhost:8080/webapp-coding-test/rest/";
		request = new HttpEntity<String>(headers);
		
	}

	/**
	 * Collecting sequence of operations for testing apis.
	 */
	@Test
	public void testAll(){
		testTotalAmount();
		testTransactions();
		testTransactionsWithType();
	}

	public void testTotalAmount(){
		logger.info("Testing - testTotalAmount");
		ResponseEntity<Map> response = restTemplate.exchange(url + "transactions/total/sandbox-payment", HttpMethod.GET, request, Map.class);
		Map<String,Double> amount = response.getBody();
		Assert.assertEquals(amount.get("GBP").equals(73.76), true);
		logger.info(amount.toString());
	}
	
	public void testTransactions(){
		logger.info("Testing - testTotalAmount");
		ResponseEntity<List> response = restTemplate.exchange(url + "transactions", HttpMethod.GET, request, List.class);
		List<BBTransaction> transactions = response.getBody();
		Assert.assertEquals(transactions.size(), 50);
		logger.info(transactions.toString());
	}
	
	public void testTransactionsWithType(){

		ResponseEntity<List> response = restTemplate.exchange(url + "transactions/sandbox-payment", HttpMethod.GET, request, List.class);
		List<LinkedHashMap<String,String>> transactions = response.getBody();
		for (int i = 0; i < transactions.size(); i++) {
			LinkedHashMap<String, String>  bb = transactions.get(i);
			Assert.assertEquals(bb.get("transactionType"),"sandbox-payment");
		}
		logger.info(transactions.toString());
	}
	
}
