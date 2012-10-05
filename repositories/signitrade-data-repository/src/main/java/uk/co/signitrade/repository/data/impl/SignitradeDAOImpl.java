package uk.co.signitrade.repository.data.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import uk.co.signitrade.repository.data.api.SignitradeDAO;
import uk.co.signitrade.repository.data.model.SecurityDetailsEOD;

public class SignitradeDAOImpl extends HibernateDaoSupport implements SignitradeDAO {

	public List<SecurityDetailsEOD> listSecurityDetailsEOD() {
		return getHibernateTemplate().find("from SecurityDetailsEOD");
	}

	
}