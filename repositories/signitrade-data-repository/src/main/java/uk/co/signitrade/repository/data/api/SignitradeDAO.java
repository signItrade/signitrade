package uk.co.signitrade.repository.data.api;

import java.util.List;

import uk.co.signitrade.repository.data.filtermodel.MarketScanFilter;
import uk.co.signitrade.repository.data.model.SecurityDetailsEOD;
 
public interface SignitradeDAO{
	
	List<SecurityDetailsEOD> listSecurityDetailsEOD();
	List<SecurityDetailsEOD> listSecurityDetailsEOD(MarketScanFilter marketScanFilter);
	
}