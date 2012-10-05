package uk.co.signitrade.app.ui.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uk.co.signitrade.repository.data.model.Customer1;
import uk.co.signitrade.service.data.api.CustomerBo;
 
import com.opensymphony.xwork2.ModelDriven;
 
public class CustomerAction implements ModelDriven{

	Customer1 customer = new Customer1();
	
	public Customer1 getCustomer() {
		return customer;
	}

	public void setCustomer(Customer1 customer) {
		this.customer = customer;
	}

	List<Customer1> customerList = new ArrayList<Customer1>();
	
	CustomerBo customerBo;
	public CustomerBo getCustomerBo() {
		return customerBo;
	}

	//DI via Spring
	public void setCustomerBo(CustomerBo customerBo) {
		this.customerBo = customerBo;
	}

	public Object getModel() {
		return customer;
	}
	
	public List<Customer1> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customer1> customerList) {
		this.customerList = customerList;
	}

	//save customer
	public String addCustomer() throws Exception{
		
		//save it
		customer.setCreatedDate(new Date());
		customerBo.addCustomer(customer);
	 
		//reload the customer list
		customerList = null;
		customerList = customerBo.listCustomer();
		
		return "success";
	
	}
	
	//list all customers
	public String listCustomer() throws Exception{
		
		customerList = customerBo.listCustomer();
		
		return "success";
	
	}

}