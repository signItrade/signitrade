package uk.co.signitrade.repository.data.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import uk.co.signitrade.persistance.AbstractDao;
import uk.co.signitrade.repository.data.api.SignitradeDAO;
import uk.co.signitrade.repository.data.filtermodel.MarketScanFilter;
import uk.co.signitrade.repository.data.model.SecurityDetailsEOD;

public class SignitradeDAOImpl extends AbstractDao implements SignitradeDAO {

	public List<SecurityDetailsEOD> listSecurityDetailsEOD(MarketScanFilter marketScanFilter) {
		StringBuffer query=new StringBuffer("from SecurityDetailsEOD");
		String finalQuery = createQueryWithFilter(query.toString(), marketScanFilter);
		return find(finalQuery);
	}

	public List<SecurityDetailsEOD> listSecurityDetailsEOD() {
		return find("from SecurityDetailsEOD");
	}

}