package uk.co.signitrade.service.data.api;

import java.util.List;

import uk.co.signitrade.repository.data.filtermodel.MarketScanFilter;
import uk.co.signitrade.repository.data.model.SecurityDetailsEOD;

public interface SignitradeService {

	List<SecurityDetailsEOD> listSecurityDetailsEOD();
	List<SecurityDetailsEOD> listSecurityDetailsEOD(MarketScanFilter marketScanFilter);

}