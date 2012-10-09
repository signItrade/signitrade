package uk.co.signitrade.repository.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
@Entity
@SqlResultSetMapping(name = "userModel", entities = @EntityResult(entityClass = uk.co.signitrade.repository.data.model.UserModel.class))
@Table(name = "users", schema = "stratos")
public class UserModel implements java.io.Serializable {

	private long loginId;
	private String userName;
	private String password;
	private String createdDate;

	public UserModel() {
	}

	public UserModel(long loginId, String userName, String password,
			String createdDate) {
		this.loginId = loginId;
		this.userName = userName;
		this.password = password;
		this.createdDate = createdDate;
	}
	@Id
	@Column(name = "user_id")
	public long getLoginId() {
		return loginId;
	}

	public void setLoginId(long loginId) {
		this.loginId = loginId;
	}
	@Column(name = "login_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name = "login_password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "created_date")
	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}


}
