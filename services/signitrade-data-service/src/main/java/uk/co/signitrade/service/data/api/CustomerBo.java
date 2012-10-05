package uk.co.signitrade.service.data.api;

import java.util.List;

import uk.co.signitrade.repository.data.model.Customer1;

public interface CustomerBo {

	void addCustomer(Customer1 customer);

	List<Customer1> listCustomer();

}