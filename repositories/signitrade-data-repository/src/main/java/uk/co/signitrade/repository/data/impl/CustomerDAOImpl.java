package uk.co.signitrade.repository.data.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import uk.co.signitrade.repository.data.api.CustomerDAO;
import uk.co.signitrade.repository.data.model.Customer;

public class CustomerDAOImpl extends HibernateDaoSupport implements CustomerDAO {

	// add the customer
	public void addCustomer(Customer customer) {

		getHibernateTemplate().save(customer);

	}

	// return all the customers in list
	public List<Customer> listCustomer() {

		return getHibernateTemplate().find("from Customer");

	}

}