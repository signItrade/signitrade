package uk.co.signitrade.app.ui.controller;

import java.util.ArrayList;
import java.util.List;

import uk.co.signitrade.repository.data.model.SecurityDetailsEOD;
import uk.co.signitrade.service.data.api.SignitradeService;

import com.opensymphony.xwork2.ModelDriven;
 
public class MarketScanAction implements ModelDriven{

	SecurityDetailsEOD securityDetailsEOD = new SecurityDetailsEOD();
	
	public SecurityDetailsEOD getSecurityDetailsEOD() {
		return securityDetailsEOD;
	}

	public void setSecurityDetailsEOD(SecurityDetailsEOD securityDetailsEOD) {
		this.securityDetailsEOD = securityDetailsEOD;
	}

	List<SecurityDetailsEOD> securityDetailsEODList = new ArrayList<SecurityDetailsEOD>();
	
	SignitradeService signitradeService;
	public SignitradeService getSignitradeService() {
		return signitradeService;
	}

	//DI via Spring
	public void setSignitradeService(SignitradeService customerBo) {
		this.signitradeService = signitradeService;
	}

	public Object getModel() {
		return securityDetailsEOD;
	}
	
	public List<SecurityDetailsEOD> getSecurityDetailsEODList() {
		return securityDetailsEODList;
	}

	public void setSecurityDetailsEOD(List<SecurityDetailsEOD> securityDetailsEODList) {
		this.securityDetailsEODList = securityDetailsEODList;
	}

		
	//list all customers
	public String listSecurityDetailsEOD() throws Exception{
		
		securityDetailsEODList = signitradeService.listSecurityDetailsEOD();
		
		return "success";
	
	}

}