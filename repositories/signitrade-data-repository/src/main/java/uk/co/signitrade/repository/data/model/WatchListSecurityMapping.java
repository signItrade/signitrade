package uk.co.signitrade.repository.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "T_WATCHLIST_SECURITY_MAPPING", schema = "stratos")
public class WatchListSecurityMapping implements java.io.Serializable {

	private Long id;
	private String highAlarm;
	private String lowAlarm;
	private SectorInformationReference sectorInformationReference;
	private WatchList watchList;

	@Id
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "highAlarm")
	public String getHighAlarm() {
		return highAlarm;
	}
	public void setHighAlarm(String highAlarm) {
		this.highAlarm = highAlarm;
	}
	@Column(name = "lowAlarm")
	public String getLowAlarm() {
		return lowAlarm;
	}
	public void setLowAlarm(String lowAlarm) {
		this.lowAlarm = lowAlarm;
	}
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=SectorInformationReference.class) 
	@JoinColumn(name="securityId", updatable=false, insertable=false)
	public SectorInformationReference getSectorInformationReference() {
		return sectorInformationReference;
	}
	public void setSectorInformationReference(
			SectorInformationReference sectorInformationReference) {
		this.sectorInformationReference = sectorInformationReference;
	}
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=WatchList.class) 
	@JoinColumn(name="watchListId", updatable=false, insertable=false)
	public WatchList getWatchList() {
		return watchList;
	}
	public void setWatchList(WatchList watchList) {
		this.watchList = watchList;
	}
	
	

}
