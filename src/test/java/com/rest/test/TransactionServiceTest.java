package com.rest.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.app.bb.model.BBTransaction;
import com.app.ob.model.Account;
import com.app.ob.model.Details;
import com.app.ob.model.Holder;
import com.app.ob.model.Metadata;
import com.app.ob.model.OBTransactionResponse;
import com.app.ob.model.OtherAccount;
import com.app.ob.model.Transaction;
import com.app.ob.model.Value;
import com.app.repository.TransactionRepository;
import com.app.service.TransactionServiceImpl;

public class TransactionServiceTest  {

	TransactionServiceImpl transactionService;
	
	@Before
	public void setup() throws Exception{
		TransactionRepository mockito = mock(TransactionRepository.class);
		transactionService = new TransactionServiceImpl();
		transactionService.setTransactionRepository(mockito);
		
		OBTransactionResponse response  = new OBTransactionResponse();
		Transaction tran = new Transaction();
		tran.setId("dcb8138c-eb88-404a-981d-d4edff1086a6");
		tran.setAccount(new Account());
		tran.getAccount().setId("savings-kids-john");
		tran.setOtherAccount(new OtherAccount());
		tran.getOtherAccount().setNumber("13677980653");
		Holder holder = new Holder();
		holder.setName("ALIAS_CBCDE5");
		tran.getOtherAccount().setHolder(holder);
		Metadata metadata = new Metadata();
		tran.getOtherAccount().setMetadata(metadata);
		Details detail = new Details();
		Value val = new Value();
		val.setAmount("20.0");
		val.setCurrency("USD");
		detail.setValue(val);
		tran.setDetails(detail);
		
		Transaction tran2 = new Transaction();
		tran2.setId("dcb8138c-eb88-404a-981d-d4edff1086a2");
		tran2.setAccount(new Account());
		tran2.getAccount().setId("savings-kids-john");
		tran2.setOtherAccount(new OtherAccount());
		tran2.getOtherAccount().setNumber("13677980653");
		Holder holder2 = new Holder();
		holder2.setName("ALIAS_CBCDE5");
		tran2.getOtherAccount().setHolder(holder2);
		Metadata metadata2 = new Metadata();
		tran2.getOtherAccount().setMetadata(metadata2);
		Details detail2 = new Details();
		detail2.setType("sandbox-payment");
		Value val2 = new Value();
		val2.setAmount("20.0");
		val2.setCurrency("USD");
		detail2.setValue(val2);
		tran2.setDetails(detail2);
		
		
		response.getTransactions().add(tran);
		response.getTransactions().add(tran2);
		when(transactionService.getTransactionRepository().getTransactionsFromOB()).thenReturn(response);
		
	}
	
	@Test
	public void testTransactions() throws Exception{
		List<BBTransaction> transactions = transactionService.getTransactionsFromOB();
		Assert.assertTrue(transactions.size()==2);
		for (int i = 0; i < transactions.size(); i++) {
			BBTransaction bb = transactions.get(i);
			Assert.assertTrue(bb.getId().equals("dcb8138c-eb88-404a-981d-d4edff1086a6") ||
					bb.getId().equals("dcb8138c-eb88-404a-981d-d4edff1086a2") );
		}
	}
	
	@Test
	public void testTransactionsByType() throws Exception{
		List<BBTransaction> transactions = transactionService.getTransactionsFromOB("sandbox-payment");
		Assert.assertTrue(transactions.size()==1);
		for (int i = 0; i < transactions.size(); i++) {
			BBTransaction bb = transactions.get(i);
			Assert.assertEquals(bb.getTransactionType(),"sandbox-payment");
		}
	}
	
	@Test
	public void testTransactionsByTypeWithNull() throws Exception{
		List<BBTransaction> transactions = transactionService.getTransactionsFromOB("null");
		Assert.assertTrue(transactions.size()==1);
		for (int i = 0; i < transactions.size(); i++) {
			BBTransaction bb = transactions.get(i);
			Assert.assertNull(bb.getTransactionType());
		}
	}
	
	@Test
	public void testTransactionsTotalByType() throws Exception{
		Map<String,Float> amountMap = transactionService.getTransactionsAmountByTypeFromOB("sandbox-payment");
		Assert.assertTrue(amountMap.get("USD") == 20.0f);
	}
}
