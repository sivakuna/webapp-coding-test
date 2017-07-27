package com.app.rest;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.app.bb.model.BBTransaction;
import com.app.service.TransactionServiceImpl;

/**
 * Transaction rest api.
 * 
 * @author skuna
 *
 */
@RestController
@EnableWebMvc
public class TransactionController {

	private Logger log = LoggerFactory.getLogger(TransactionController.class);
	 
	@Autowired
	private TransactionServiceImpl transactionService;
	
	/**
	 * Retrieve all transactions.
	 * 
	 * @return all transactions.
	 * @throws Exception
	 */
	@RequestMapping(value = "/transactions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<BBTransaction> getTransactions() throws Exception {
		log.info("invoking...getTransactions");
		List<BBTransaction> transactions =  transactionService.getTransactionsFromOB();
		return transactions;
	}

	/**
	 * Retrieve transactions of specific type.
	 * 
	 * @param type Transaction type.
	 * @return transactions of specific type.
	 * @throws Exception
	 */
	@RequestMapping(value = "/transactions/{type}", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<BBTransaction> getTransactionsByType(@PathVariable("type") String type) throws Exception {
		log.info("invoking...getTransactionsByType");
		List<BBTransaction> transactions =  transactionService.getTransactionsFromOB(type);
		return transactions;
	}
	
	/**
	 * Retrieve transaction total amount of specific transaction type.
	 * 
	 * @param type Transaction type.
	 * @return a map of transaction total amount of specific transaction type.
	 * @throws Exception
	 */
	@RequestMapping(value = "/transactions/total/{type}", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Float>  getTransactionAmountByType(@PathVariable("type") String type) throws Exception {
		log.info("invoking...getTransactionAmountByType");
		Map<String, Float>  totalAmount =  transactionService.getTransactionsAmountByTypeFromOB(type);
		return totalAmount;
	}
	
}
