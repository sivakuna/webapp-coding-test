package com.rest.test;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
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
		ParameterizedTypeReference<Map<String, Float>> typeRef = new ParameterizedTypeReference<Map<String, Float>>() {};
		ResponseEntity<Map<String, Float>> response = restTemplate.exchange(url + "transactions/total/sandbox-payment", HttpMethod.GET, request, typeRef);
		Map<String,Float> amount = response.getBody();
		assertTrue(amount.get("GBP").equals( 73.76f));
		logger.info(amount.toString());
	}
	
	public void testTransactions(){
		logger.info("Testing - testTransactions");
		ParameterizedTypeReference<List<BBTransaction>> typeRef = new ParameterizedTypeReference<List<BBTransaction>>() {};
		ResponseEntity<List<BBTransaction>> response = restTemplate.exchange(url + "transactions", HttpMethod.GET, request, typeRef);
		List<BBTransaction> transactions = response.getBody();
		Assert.assertEquals(transactions.size(), 50);
		logger.info(transactions.toString());
	}
	
	public void testTransactionsWithType(){
		logger.info("Testing - testTransactionsWithType");
		ParameterizedTypeReference<List<BBTransaction>> typeRef = new ParameterizedTypeReference<List<BBTransaction>>() {};
		ResponseEntity<List<BBTransaction>> response = restTemplate.exchange(url + "transactions/sandbox-payment", HttpMethod.GET, request, typeRef);
		List<BBTransaction> transactions = response.getBody();
		for (int i = 0; i < transactions.size(); i++) {
			BBTransaction bb = transactions.get(i);
			Assert.assertEquals(bb.getTransactionType(),"sandbox-payment");
		}
		logger.info(transactions.toString());
	}
	
}
