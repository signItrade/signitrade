package uk.co.signitrade.service.data.impl;

import java.util.List;

import uk.co.signitrade.repository.data.api.SignitradeDAO;
import uk.co.signitrade.repository.data.filtermodel.MarketScanFilter;
import uk.co.signitrade.repository.data.model.SecurityDetailsEOD;
import uk.co.signitrade.service.data.api.SignitradeService;
 
public class SignitradeServiceImpl implements SignitradeService{
	
	SignitradeDAO signitradeDAO; 

	//DI via Spring
	public void setSignitradeDAO(SignitradeDAO signitradeDAO) {
		this.signitradeDAO = signitradeDAO;
	}


	public List<SecurityDetailsEOD> listSecurityDetailsEOD() {
		return signitradeDAO.listSecurityDetailsEOD();
	}


	public List<SecurityDetailsEOD> listSecurityDetailsEOD(MarketScanFilter marketScanFilter) {
		return signitradeDAO.listSecurityDetailsEOD(marketScanFilter);
	}
	
}