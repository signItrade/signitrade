package uk.co.signitrade.repository.data.api;

import java.util.List;

import uk.co.signitrade.repository.data.model.Customer1;
 
public interface CustomerDAO{
	
	void addCustomer(Customer1 customer);
	
	List<Customer1> listCustomer();
	
}