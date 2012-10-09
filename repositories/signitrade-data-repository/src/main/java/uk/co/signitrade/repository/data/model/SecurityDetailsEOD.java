package uk.co.signitrade.repository.data.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "T_SECURITY_DETAILS_EOD", schema = "stratos")
public class SecurityDetailsEOD implements java.io.Serializable {

	private Long id;
	private SecurityDefinationReference securityDefinationReference;
	private Timestamp businessDate;
	private double eodPrice;
	private String signal;
	private String signalCount;
	private Long count;
	private String ma;
	private String macd;
	private String sto;
	private String cha;
	private String cci;
	private String pr;
	private String rsi;
	private String dmi;
	private String fi;
	private Long nintyDayChange;
	private Long nintyDayAvgTradedAmount;

	@Id
	@Column(name="id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@OneToOne(fetch=FetchType.LAZY, targetEntity=SecurityDefinationReference.class) 
	@JoinColumn(name="securityCode",referencedColumnName="code", updatable=false, insertable=false)
	public SecurityDefinationReference getSecurityDefinationReference() {
		return securityDefinationReference;
	}
	public void setSecurityDefinationReference(
			SecurityDefinationReference securityDefinationReference) {
		this.securityDefinationReference = securityDefinationReference;
	}
	@Column(name="businessDate")
	public Timestamp getBusinessDate() {
		return businessDate;
	}
	public void setBusinessDate(Timestamp businessDate) {
		this.businessDate = businessDate;
	}
	@Column(name="eodPrice")
	public double getEodPrice() {
		return eodPrice;
	}
	public void setEodPrice(double eodPrice) {
		this.eodPrice = eodPrice;
	}
	@Column(name="signal")
	public String getSignal() {
		return signal;
	}
	public void setSignal(String signal) {
		this.signal = signal;
	}
	@Column(name="signalCount")
	public String getSignalCount() {
		return signalCount;
	}
	public void setSignalCount(String signalCount) {
		this.signalCount = signalCount;
	}
	@Column(name="count")
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	@Column(name="ma")
	public String getMa() {
		return ma;
	}
	public void setMa(String ma) {
		this.ma = ma;
	}
	@Column(name="macd")
	public String getMacd() {
		return macd;
	}
	public void setMacd(String macd) {
		this.macd = macd;
	}
	@Column(name="sto")
	public String getSto() {
		return sto;
	}
	public void setSto(String sto) {
		this.sto = sto;
	}
	@Column(name="cha")
	public String getCha() {
		return cha;
	}
	public void setCha(String cha) {
		this.cha = cha;
	}
	@Column(name="cci")
	public String getCci() {
		return cci;
	}
	public void setCci(String cci) {
		this.cci = cci;
	}
	@Column(name="pr")
	public String getPr() {
		return pr;
	}
	public void setPr(String pr) {
		this.pr = pr;
	}
	@Column(name="rsi")
	public String getRsi() {
		return rsi;
	}
	public void setRsi(String rsi) {
		this.rsi = rsi;
	}
	@Column(name="dmi")
	public String getDmi() {
		return dmi;
	}
	public void setDmi(String dmi) {
		this.dmi = dmi;
	}
	@Column(name="fi")
	public String getFi() {
		return fi;
	}
	public void setFi(String fi) {
		this.fi = fi;
	}
	@Column(name="90DayChange")
	public Long getNintyDayChange() {
		return nintyDayChange;
	}
	public void setNintyDayChange(Long nintyDayChange) {
		this.nintyDayChange = nintyDayChange;
	}
	@Column(name="90DayAvgTradedAmount")
	public Long getNintyDayAvgTradedAmount() {
		return nintyDayAvgTradedAmount;
	}
	public void setNintyDayAvgTradedAmount(Long nintyDayAvgTradedAmount) {
		this.nintyDayAvgTradedAmount = nintyDayAvgTradedAmount;
	}

}
