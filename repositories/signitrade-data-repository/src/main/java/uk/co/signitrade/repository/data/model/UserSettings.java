package uk.co.signitrade.repository.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
@Entity
@SqlResultSetMapping(name = "UserSettings", entities = @EntityResult(entityClass = uk.co.signitrade.repository.data.model.UserSettings.class))
@Table(name = "user_settings", schema = "stratos")
public class UserSettings implements java.io.Serializable {

	private long Id;
	private String propertyFileName;
	private String visibleProperies;
	private UserModel UserId;
	@Id
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	@Column(name="property_file_name")
	public String getPropertyFileName() {
		return propertyFileName;
	}
	public void setPropertyFileName(String propertyFileName) {
		this.propertyFileName = propertyFileName;
	}
	@Column(name="visible_properties")
	public String getVisibleProperies() {
		return visibleProperies;
	}
	public void setVisibleProperies(String visibleProperies) {
		this.visibleProperies = visibleProperies;
	}
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=UserModel.class) 
	@JoinColumn(name="user_id", updatable=false, insertable=false)
	public UserModel getUserId() {
		return UserId;
	}
	public void setUserId(UserModel userId) {
		UserId = userId;
	}

	
}
