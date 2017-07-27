package com.app.repository;

import com.app.ob.model.OBTransactionResponse;

/**
 * Transaction repository interface.
 * @author skuna
 *
 */

public interface TransactionRepository {

	/**
	 * Invokes rest api and retrieves the transactions from the remote service
	 * @return list of transactions
	 * @throws Exception
	 */
	public OBTransactionResponse getTransactionsFromOB() throws Exception;
}
