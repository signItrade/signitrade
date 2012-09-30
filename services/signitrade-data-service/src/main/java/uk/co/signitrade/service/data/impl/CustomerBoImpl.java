package uk.co.signitrade.service.data.impl;

import java.util.List;

import uk.co.signitrade.service.data.api.CustomerBo;
import uk.co.signitrade.repository.data.api.CustomerDAO;
import uk.co.signitrade.repository.data.model.Customer;
 
public class CustomerBoImpl implements CustomerBo{
	
	CustomerDAO customerDAO;

	//DI via Spring
	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	//call DAO to save customer
	public void addCustomer(Customer customer){
		
		customerDAO.addCustomer(customer);
		
	}
	
	//call DAO to return customers
	public List<Customer> listCustomer(){
		
		return customerDAO.listCustomer();
		
	}
	
}