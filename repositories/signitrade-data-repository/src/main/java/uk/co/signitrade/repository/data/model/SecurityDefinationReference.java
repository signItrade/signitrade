package uk.co.signitrade.repository.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "T_SECURITY_DEFN_REF", schema = "stratos")
public class SecurityDefinationReference implements java.io.Serializable {

	private Long id;
	private String name;
	private String code;
	private StockExchangeDefinationReference stockExchangeDefinationReference;
	private SectorInformationReference sectorInformationReference;
	
	@Id
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=StockExchangeDefinationReference.class) 
	@JoinColumn(name="exchangeCode", updatable=false, insertable=false)
	public StockExchangeDefinationReference getStockExchangeDefinationReference() {
		return stockExchangeDefinationReference;
	}
	public void setStockExchangeDefinationReference(
			StockExchangeDefinationReference stockExchangeDefinationReference) {
		this.stockExchangeDefinationReference = stockExchangeDefinationReference;
	}
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=SectorInformationReference.class) 
	@JoinColumn(name="sector", updatable=false, insertable=false)
	public SectorInformationReference getSectorInformationReference() {
		return sectorInformationReference;
	}
	public void setSectorInformationReference(
			SectorInformationReference sectorInformationReference) {
		this.sectorInformationReference = sectorInformationReference;
	}
	
	
}
