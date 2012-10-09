package uk.co.signitrade.persistance;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.transform.Transformers;
import org.hibernate.type.TimestampType;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


@SuppressWarnings("deprecation")
public class AbstractDao<T> extends HibernateDaoSupport implements Serializable
{

	// MEMBERS

	private Class<?> persistentClass;

	// CONSTRUCTORS

	public AbstractDao()
	{
		super();
	}

	protected AbstractDao(Class<?> dataClass)
	{
		super();
		this.persistentClass = dataClass;
	}

	public void flush()
	{
		getSession().flush();
	}

	// ***************************queries**********************/
	@SuppressWarnings("unchecked")
	public T getById(java.io.Serializable id)
	{
		getSession().flush();
		return (T)getHibernateTemplate().get(persistentClass, id);
	}

	@SuppressWarnings("unchecked")
	public T getById(Long id)
	{
		getSession().flush();
		return (T)getHibernateTemplate().get(persistentClass, (Serializable)id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findByNamedQuery(String queryName)
	{
		getSession().flush();
		return (List<T>)getHibernateTemplate().findByNamedQuery(queryName);
	}

	@SuppressWarnings("unchecked")
	public List<T> findByNamedQueryAndNamedParam(String queryName, String param, Object val)
	{
		getSession().flush();
		return (List<T>)getHibernateTemplate().findByNamedQueryAndNamedParam(queryName, param, val);
	}

	@SuppressWarnings("unchecked")
	public List<T> findByNamedQueryAndNamedParam(String queryName, String[] params, Object[] vals)
	{
		getSession().flush();
		return (List<T>)getHibernateTemplate().findByNamedQueryAndNamedParam(queryName, params, vals);
	}

	@SuppressWarnings("unchecked")
	public List<T> find(String query, Object[] params)
	{
		getSession().flush();
		return (List<T>)getHibernateTemplate().find(query, params);
	}

	@SuppressWarnings("unchecked")
	public List<T> get(Criterion... criterion) throws DAOException
	{
		getSession().flush();
		Criteria criteria = createCriteria();

		for (Criterion c : criterion)
		{
			criteria.add(c);
		}

		try
		{
			return new ArrayList<T>(criteria.list());
		} catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}

	public T loadClass(Class object, Long id)
	{
		return (T)getHibernateTemplate().get(object, (Serializable)id);
	}

	@SuppressWarnings("unchecked")
	public List<T> getFilteredList(Map criteriaFieldsValues)
	{
		getSession().flush();
		return getCriteria(criteriaFieldsValues).list();
	}

	@SuppressWarnings("unchecked")
	public T getEntity(Map criteriaFieldsValues)
	{
		getSession().flush();
		return (T)getCriteria(criteriaFieldsValues).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public T findUniqueByNamedQuery(String query, String[] params, Object[] values)
	{
		return (T)getSQLQuery(query, "x", params, values, 0).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<T> find(String query, String alias, String[] params, Object[] values, int maxResults)
	{
		return getSQLQuery(query, alias, params, values, maxResults).list();
	}

	private SQLQuery getSQLQuery(String query, String alias, String[] params, Object[] values, int maxResults)
	{
		getSession().flush();

		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(query);

		if (alias != null)
		{
			sqlQuery.addEntity(alias, persistentClass);
		}
		
		setParameters(params, values, sqlQuery);

		return sqlQuery;
	}

	/**
	 * 
	 * @param queryName
	 * @param params Parameter name passed in query
	 * @param values Parameter Values
	 * @param maxResults Max record at one time
	 * @return List of require Entity
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByNamedQueryAndNamedParam(String queryName, String[] params, Object[] values, int maxResults)
	{
		getSession().flush();
		Query namedQuery = getSession().getNamedQuery(queryName);

		setParameters(params, values, namedQuery);

		return namedQuery.list();
	}

	/**
	 * 
	 * @param queryName
	 * @param params Parameter name passed in query
	 * @param values Parameter Values
	 * @param maxResults Max record at one time
	 * @param startIndex Start Index of record for pagination
	 * @return List of require Entity
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByNamedQueryAndNamedParamForList(String queryName, String[] params, Object[] values, int maxResults, int startIndex)
	{
		getSession().flush();
		Query namedQuery = getSession().getNamedQuery(queryName);
		namedQuery.setFirstResult(startIndex);
		namedQuery.setMaxResults(maxResults);
		setParameters(params, values, namedQuery);
		return namedQuery.list();
	}

	/**
	 * return a single instance that matches the query, or null if the query
	 * returns no results
	 */
	public T uniqueResult(String[] fields, Object[] values)
	{
		return uniqueResult(fields, values, true);
	}

	@SuppressWarnings("unchecked")
	public T uniqueResult(String[] fields, Object[] values, boolean isFlush)
	{
		if (isFlush)
			getSession().flush();

		Criteria c = createCriteria();
		for (int i = 0; i < fields.length; i++)
		{
			c.add(Expression.eq(fields[i], values[i]));
		}

		return (T)c.uniqueResult();
	}

	public List<T> list(String[] fields, Object[] values)
	{
		return list(fields, values, null, false);
	}

	@SuppressWarnings("unchecked")
	public List<T> list(String[] fields, Object[] values, String[] orderFields, boolean isAscending)
	{
		getSession().flush();
		Criteria c = createCriteria();
		for (int i = 0; i < fields.length; i++)
		{
			c.add(Expression.eq(fields[i], values[i]));
		}

		if (orderFields != null)
		{
			for (int i = 0; i < orderFields.length; i++)
			{
				if (isAscending)
				{
					c.addOrder(Order.asc(orderFields[i]));
				} else
				{
					c.addOrder(Order.desc(orderFields[i]));
				}
			}
		}

		return (List<T>)c.list();
	}

	// ***************************load/update**********************/
	@SuppressWarnings("unchecked")
	public T merge(T entity)
	{
		return (T)getHibernateTemplate().merge(entity);
	}

	public void evict(Object entity)
	{
		getHibernateTemplate().evict(entity);
	}
	
	public void evictCollection(List entityList)
	{
		for(Object obj : entityList){
			getSession().evict(obj);
		}
	}

	@SuppressWarnings("unchecked")
	public T load(Object id)
	{
		return (T)getHibernateTemplate().load(persistentClass, (Serializable)id);
	}

	@SuppressWarnings("unchecked")
	public Object load(Class object, Long id)
	{
		getSession().flush();
		return (Object)getHibernateTemplate().get(object, (Serializable)id);

	}

	@SuppressWarnings("unchecked")
	public List<T> loadAll()
	{
		return (List<T>)getHibernateTemplate().loadAll(persistentClass);
	}

	public void saveOrUpdate(T entity) throws DataAccessException
	{
		getHibernateTemplate().saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(List<T> entities) throws DataAccessException
	{
		getHibernateTemplate().saveOrUpdateAll(entities);
	}

	public void delete(List<T> entities)
	{
		getHibernateTemplate().delete(entities);
	}

	public void update(T record) throws DataAccessException
	{
		getHibernateTemplate().update(record);
	}

	public Object save(T record) throws DataAccessException
	{
		Object retVal = getHibernateTemplate().save(record);
		return retVal;
	}

	public void saveRecord(T record) throws DataAccessException
	{
		getHibernateTemplate().saveOrUpdate(record);
	}

	public void removeRecord(Long id)
	{
		Object record = getHibernateTemplate().load(persistentClass, id);
		getHibernateTemplate().delete(record);
	}

	public void delete(T entity)
	{
		getHibernateTemplate().delete(entity);
	}

	public void deleteEntity(T entity)
	{
		getHibernateTemplate().delete(entity);
	}

	public Session getCurrentSession()
	{
		return getSession();
	}

	public Criteria createCriteria()
	{
		Session session = getSession();
		return session.createCriteria(persistentClass);
	}

	public Criteria createCriteria(String alias)
	{
		Session session = getSession();
		return session.createCriteria(persistentClass, alias);
	}

	public void deleteAll(Collection<T> entities)
	{
		getHibernateTemplate().deleteAll(entities);
	}

	public void deleteAll(Set<T> entities)
	{
		getHibernateTemplate().deleteAll(entities);
	}

	public void deleteAll(List<T> entities)
	{
		getHibernateTemplate().deleteAll(entities);
	}

	public void deleteAllEntity(List<T> entities)
	{
		getHibernateTemplate().deleteAll(entities);
	}

	// Generic methods
	@SuppressWarnings("unchecked")
	public List genericFind(String query, Object[] params)
	{
		getSession().flush();
		return getHibernateTemplate().find(query, params);
	}

	@SuppressWarnings("unchecked")
	public <K> K findUnique(String query, Object[] values)
	{
		getSession().flush();

		Query q = getSession().createQuery(query);
		setParameters(values, q);

		return (K)q.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List find(String query)
	{
		getSession().flush();
		return getHibernateTemplate().find(query);
	}

	public List findSql(String query)
	{
		getSession().flush();
		Query q = getSession().createSQLQuery(query);
		return q.list();
	}

	@SuppressWarnings("unchecked")
	public Map findMap(String query)
	{
		getSession().flush();
		return (Map)getHibernateTemplate().find(query);
	}

	@SuppressWarnings("unchecked")
	public List findQuery(String query)
	{

		getSession().flush();
		return getHibernateTemplate().getSessionFactory().openSession().createQuery(query).list();
	}

	@SuppressWarnings("unchecked")
	public List findQuery(String query, int maxSize)
	{
		getSession().flush();
		Session session = getSession();
		Query queryExecute = session.createQuery(query);
		queryExecute.setCacheable(true);
		queryExecute.setCacheRegion("query.findQuery");
		queryExecute.setMaxResults(maxSize);
		return queryExecute.list();
	}

	/**
	 * This is a generic method that need NOT return entities and hence the
	 * return type is NOT templated.
	 */
	@SuppressWarnings("unchecked")
	public List findByNamedParam(String query, String[] paramNames, Object[] values)
	{
		//		getSession().flush();
		return getHibernateTemplate().findByNamedParam(query, paramNames, values);
	}

	public int execute(String query)
	{
		return getHibernateTemplate().getSessionFactory().openSession().createQuery(query).executeUpdate();
	}

	@SuppressWarnings("unused")
	private String getCountQuery(String query, String[] params, Object[] values)
	{
		int fromIndex = query.indexOf("FROM");
		String replaceString = query.substring(0, fromIndex);

		query = query.replace(replaceString, "SELECT COUNT(*) ");

		query = replaceParameters(params, values, query);

		return query;
	}

	

	@SuppressWarnings("unchecked")
	private Criteria getCriteria(Map criteriaFieldsValues)
	{
		Criteria retVal = createCriteria();
		Set<String> s = criteriaFieldsValues.keySet();
		for (String key : s)
		{
			if (criteriaFieldsValues.get(key) != null)
			{
				retVal.add(Expression.eq(key, criteriaFieldsValues.get(key)));
			}
		}

		return retVal;
	}

	
	/* returns the List of specified Alias beans */
	@SuppressWarnings("unchecked")
	public <C> List<C> findWithAliasBean(String query, Class<C> clazz, final String[] params, final Object[] values)
	{
		final String queryString = query;
		final Class<C> clazzz = clazz;

		return (List<C>)getHibernateTemplate().execute(new HibernateCallback()
		{

			public Object doInHibernate(Session session) throws HibernateException, SQLException
			{

				Query query = session.createQuery(queryString);
				if (params != null || values != null)
				{
					for (int i = 0; i < params.length; i++)
					{
						query.setParameter(params[i], values[i]);
					}
				}

				query.setResultTransformer(Transformers.aliasToBean(clazzz));

				return query.list();
			}
		});
	}

	/* eOffice */
	/**
	 * Method defined for getting the list by criteria
	 * 
	 * @param criteria
	 * @return List<T>
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(Criteria criteria) throws HibernateException
	{
		getSession().flush();
		return (List<T>)criteria.list();
	}

	/**
	 * This method's purpose is to generate the select queries automatically
	 * based on the criteria map supplied. If criteria map is null or it does
	 * not have any keys result without any criteria will be returned.
	 * 
	 * @author Chitrabhanu
	 * @param cls
	 * @param criterion
	 * @return List of Model class objects
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByCriterion(Class cls, Map<String, Object> criterion) throws HibernateException
	{

		String entityName = cls.getName();
		ArrayList<String> paramNames = new ArrayList<String>();
		ArrayList<Object> paramValues = new ArrayList<Object>();
		String query = "from " + entityName;

		try
		{
			Iterator<String> keys = criterion.keySet().iterator();
			if (keys.hasNext())
			{
				query = "from " + entityName + " entity where ";
			}
			while (keys.hasNext())
			{
				String key = keys.next();
				paramNames.add(key);
				paramValues.add(criterion.get(key));
				query = query + "entity." + key + "=:" + key + " ";
				if (keys.hasNext())
				{
					query = query + "and ";
				}
			}
		} catch (NullPointerException e)
		{}

		return findByNamedParam(query, (String[])paramNames.toArray(new String[0]), paramValues.toArray());
	}

	

	/**
	 * @throws DAOException
	 */
	public void beginTransaction() throws DAOException
	{
		getCurrentSession().beginTransaction();
	}

	/**
	 * @throws DAOException
	 */
	public void commitTransaction() throws DAOException
	{
		getCurrentSession().getTransaction().commit();
	}

	/**
	 * @throws DAOException
	 */
	public void rollBackTransaction() throws DAOException
	{
		getCurrentSession().getTransaction().rollback();
	}

	

	public void deleteAll(final String entityName, final List<T> entities)
	throws DataAccessException {

		getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException {
				for (Object entity : entities) {
					session.delete(entityName, entity);
				}
				return null;
			}
		});
	}


	public void saveOrUpdateAll(final String entityName,final List<T> entities) throws DataAccessException
	{    
		getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException {                    
				for (Object object : entities) {
					session.saveOrUpdate(entityName,object);
				}
				return null;
			}
		});

	}
	protected void setParameters(String[] params, Object[] values, Query query)
	{
		for (int i = 0; i < params.length; i++)
		{
			if (values[i] instanceof Timestamp)
			{
				query.setParameter(params[i], values[i], new TimestampType());
			} else if (values[i] instanceof Collection)
			{
				query.setParameterList(params[i], (Collection)values[i]);
			} else
			{
				query.setParameter(params[i], values[i]);
			}
		}
	}

	private void setParameters(Object[] values, Query query)
	{
		for (int i = 0; i < values.length; i++)
		{
			query.setParameter(i, values[i]);
		}
	}
	private String replaceParameters(String[] params, Object[] values, String queryString)
	{
		String param;
		String value;
		for (int i = 0; i < params.length; i++)
		{
			param = ":" + params[i];
			value = (values[i] == null) ? "" + values[i] : "'" + values[i] + "'";
			queryString = queryString.replaceAll(param, value);
		}

		queryString = queryString.replaceAll("\\n", " ");
		queryString = queryString.replaceAll("\\t", " ");

		return queryString;
	}
	/**
	 * 
	 * @param query
	 * @param filterMap
	 * @return
	 */
	public String createQueryWithFilter(String query,Object obj)
	{
		StringBuffer filter = new StringBuffer();
		filter.append(query);
		Map filterMap=convertBeanToMap(obj);
		if(null!=filterMap)
		{
			Set set = filterMap.keySet();
			Iterator itr = set.iterator();
			while(itr.hasNext())
			{
				String key=(String)itr.next();
				if(null!=filterMap.get(key) && ""!=filterMap.get(key))
				{
					if(!StringUtils.contains(filter.toString()," where "))
					{
						filter.append(" where "+key+"='"+filterMap.get(key)+"'");
					}
					else if(StringUtils.contains(filter.toString()," where "))
					{
						filter.append(" and "+key+"='"+filterMap.get(key)+"'");
					}
				}
			}
		}

		return filter.toString();
	}
	/**
	 * 
	 * @param obj
	 * @return
	 */
	public Map convertBeanToMap(Object obj)
	{
		try {
			Map map = BeanUtils.describe(obj);
			BeanUtils.populate(obj, map);
			System.out.println("map : "+map.get("ma"));
			map.remove("class");
			return map;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}

}
