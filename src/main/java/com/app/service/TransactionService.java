package com.app.service;

import java.util.List;
import java.util.Map;

import com.app.bb.model.BBTransaction;

/**
 * Transaction service implemention.
 * @author skuna
 *
 */
public interface TransactionService {

	/**
	 * Retrieve all transactions.
	 * 
	 * @return all transactions.
	 * @throws Exception
	 */
	public List<BBTransaction> getTransactionsFromOB() throws Exception;

	/**
	 * Retrieve transactions of specific type.
	 * 
	 * @param type Transaction type.
	 * @return transactions of specific type.
	 * @throws Exception
	 */
	public List<BBTransaction> getTransactionsFromOB(String type) throws Exception;

	/**
	 * Retrieve transaction total amount of specific transaction type.
	 * 
	 * @param type Transaction type.
	 * @return a map of transaction total amount of specific transaction type.
	 * @throws Exception
	 */
	public Map<String, Float> getTransactionsAmountByTypeFromOB(String type)
			throws Exception;

}
