package uk.co.signitrade.repository.data.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "T_CUSTOMER_ACCESS_MAPPING", schema = "stratos")
public class CustomerAccessMapping implements java.io.Serializable {

	private Long id;
	private Customer customer;
	private CustomerAccessReference customerAccessReference;

	@Id
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=Customer.class) 
	@JoinColumn(name="custId", updatable=false, insertable=false)
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne(fetch=FetchType.LAZY, targetEntity=CustomerAccessReference.class) 
	@JoinColumn(name="acessId", updatable=false, insertable=false)
	public CustomerAccessReference getCustomerAccessReference() {
		return customerAccessReference;
	}
	public void setCustomerAccessReference(
			CustomerAccessReference customerAccessReference) {
		this.customerAccessReference = customerAccessReference;
	}

}
