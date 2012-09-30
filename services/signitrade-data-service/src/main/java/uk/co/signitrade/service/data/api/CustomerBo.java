package uk.co.signitrade.service.data.api;

import java.util.List;

import uk.co.signitrade.repository.data.model.Customer;

public interface CustomerBo {

	void addCustomer(Customer customer);

	List<Customer> listCustomer();

}