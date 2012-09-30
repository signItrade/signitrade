package uk.co.signitrade.repository.data.api;

import java.util.List;

import uk.co.signitrade.repository.data.model.Customer;
 
public interface CustomerDAO{
	
	void addCustomer(Customer customer);
	
	List<Customer> listCustomer();
	
}