package uk.co.signitrade.app.ui.controller;

import uk.co.signitrade.repository.data.model.UserModel;

import com.opensymphony.xwork2.ModelDriven;
 
public class LoginAction implements ModelDriven{
	UserModel userModel = new UserModel();

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public String authenticateUser() throws Exception{
		return "success";
	
	}

	public Object getModel() {
		return userModel;
	}

}