package uk.co.signitrade.app.ui.controller;

import java.util.ArrayList;
import java.util.List;

import uk.co.signitrade.repository.data.filtermodel.MarketScanFilter;
import uk.co.signitrade.repository.data.model.SecurityDetailsEOD;
import uk.co.signitrade.service.data.api.SignitradeService;

import com.opensymphony.xwork2.ModelDriven;
 
public class MarketScanAction implements ModelDriven{

	MarketScanFilter marketScanFilter=new MarketScanFilter();

	public MarketScanFilter getMarketScanFilter() {
		return marketScanFilter;
	}

	public void setMarketScanFilter(MarketScanFilter marketScanFilter) {
		this.marketScanFilter = marketScanFilter;
	}


	List<SecurityDetailsEOD> securityDetailsEODList = new ArrayList<SecurityDetailsEOD>();
	
	SignitradeService signitradeService;
	public SignitradeService getSignitradeService() {
		return signitradeService;
	}

	public void setSignitradeService(SignitradeService signitradeService) {
		this.signitradeService = signitradeService;
	}

	public Object getModel() {
		return marketScanFilter;
	}
	
	public List<SecurityDetailsEOD> getSecurityDetailsEODList() {
		return securityDetailsEODList;
	}

	public void setSecurityDetailsEOD(List<SecurityDetailsEOD> securityDetailsEODList) {
		this.securityDetailsEODList = securityDetailsEODList;
	}

		
	public String listSecurityDetailsEOD() throws Exception{
		securityDetailsEODList = signitradeService.listSecurityDetailsEOD(marketScanFilter);
		
		return "success";
	
	}

}