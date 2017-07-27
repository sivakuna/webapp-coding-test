package com.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.bb.model.BBTransaction;
import com.app.ob.model.Details;
import com.app.ob.model.Holder;
import com.app.ob.model.Metadata;
import com.app.ob.model.OBTransactionResponse;
import com.app.ob.model.OtherAccount;
import com.app.ob.model.Transaction;
import com.app.ob.model.Value;
import com.app.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public List<BBTransaction> getTransactionsFromOB() throws Exception {
		OBTransactionResponse obTransactionResponse = transactionRepository.getTransactionsFromOB();
		List<Transaction> obTransactions =  obTransactionResponse.getTransactions();
		List<BBTransaction> bbTransactions = new ArrayList<>();
		for (Transaction opTransaction : obTransactions) {
			BBTransaction bbTransaction = new BBTransaction();
			bbTransaction.setId(opTransaction.getId());
			if (opTransaction.getAccount() != null){
				bbTransaction.setAccountId(opTransaction.getAccount().getId());
			}
			OtherAccount oac =  opTransaction.getOtherAccount();
			if (oac != null) {
				bbTransaction.setCounterpartyAccount(oac.getNumber());
				Holder holder = oac.getHolder();
				if (holder != null){
				  bbTransaction.setCounterpartyName(holder.getName());
				}
				Metadata metadata = oac.getMetadata();
				if (metadata != null){
				  bbTransaction.setCounterPartyLogoPath(metadata.getImage_URL());
				}
			}
			Details details = opTransaction.getDetails();
			if(details != null) {
				bbTransaction.setTransactionType(details.getType());
				bbTransaction.setDescription(details.getDescription());
				Value val = details.getValue() ;
				if (val != null){
					bbTransaction.setTransactionAmount(val.getAmount());
					bbTransaction.setTransactionCurrency(val.getCurrency());
					bbTransaction.setInstructedAmount(val.getAmount());
					bbTransaction.setInstructedCurrency(val.getCurrency());
				}
			}
			bbTransactions.add(bbTransaction);
		}
		return bbTransactions;
	}
	
	@Override
	public List<BBTransaction> getTransactionsFromOB(String type) throws Exception {
		List<BBTransaction> bbAllTransactions =  getTransactionsFromOB();
		List<BBTransaction> bbTransactions = new ArrayList<>();
		
		for (BBTransaction bbTransaction : bbAllTransactions) {
			if ((bbTransaction.getTransactionType() != null && bbTransaction.getTransactionType().equalsIgnoreCase(type)) || ("null".equals(type) && bbTransaction.getTransactionType() == null)){
				bbTransactions.add(bbTransaction);
			}
		}
		return bbTransactions;
	}

	@Override
	public Map<String, Float>  getTransactionsAmountByTypeFromOB(String type) throws Exception {
		List<BBTransaction> bbAllTransactions =  getTransactionsFromOB();
		Map<String, Float> totalAmountMap = new HashMap<>();
		for (BBTransaction bbTransaction : bbAllTransactions) {
			if ((bbTransaction.getTransactionType() != null && bbTransaction.getTransactionType().equalsIgnoreCase(type)) || ("null".equals(type) && bbTransaction.getTransactionType() == null)){
				Float totalamount = totalAmountMap.get(bbTransaction.getTransactionCurrency());
				if (totalamount== null){
					totalAmountMap.put(bbTransaction.getTransactionCurrency(),new Float(bbTransaction.getTransactionAmount()));
				}else{
					totalamount += new Float(bbTransaction.getTransactionAmount());
					totalAmountMap.put(bbTransaction.getTransactionCurrency(),totalamount);
				}
			}
		}
		return totalAmountMap;
	}

	public TransactionRepository getTransactionRepository() {
		return transactionRepository;
	}

	public void setTransactionRepository(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

}
