package uk.co.signitrade.app.ui.security;

/**
 * This class retireves the user details from database and passes it
 * to the Authentication manager. The spring jdbc templates is used
 * for retrieving the user details from the database.
 *
 **/

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
public class DBAuthenticationService implements UserDetailsService
{
	private final static String LOGIN_ID = "loginid";
	private final static String PASSWORD = "password";
	

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	private String userQuery;
	

	private String userAttributeClass;
	

	public void setDataSource(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@SuppressWarnings("deprecation")
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException
	{
		System.out.println("-------------inside DBAuthenticationService.loadUserByUsername(String userName)-------------");
		UserPrincipal user = null;

		try
		{
			if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(userQuery))
			{
				return user;
			}

			List<Map<String, Object>> userDetailList = this.jdbcTemplate.queryForList(userQuery, new Object[] { userName });
			if (userDetailList == null || userDetailList.isEmpty())
				return null;

			Map<String, Object> userDetail = userDetailList.get(0);

			String password = (String)userDetail.get(PASSWORD);

			Object objUserAttribute = populateObjectAttribute(userDetail, userAttributeClass);


			GrantedAuthority[] authorities = getGrantedAuthorities();

			user = new UserPrincipal(userName, password, objUserAttribute, true, true, true, true, authorities);

		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return user;
	}


	/**
	 * 
	 * @param groups
	 * @return
	 */
	private GrantedAuthority[] getGrantedAuthorities()
	{
		GrantedAuthority[] authorities = null;
		authorities = new GrantedAuthority[1];
		authorities[0] = new GrantedAuthorityImpl("ROLE_USER");
		return authorities;
	}

	/**
	 * 
	 * @param data
	 * @param attributeClass
	 * @return
	 */
	private Object populateObjectAttribute(Map data, String attributeClass)
	{
		Object objAttributes = null;
		Class clsAttributes = null;
		Map fieldMap = new HashMap();
		Field[] fields = null;
		boolean isMap = false;
		if (attributeClass != null && !attributeClass.isEmpty())
		{
			try
			{
				clsAttributes = Class.forName(attributeClass);
				objAttributes = clsAttributes.getConstructor(new Class[] {}).newInstance(new Object[] {});
				isMap = (objAttributes instanceof Map);
			} catch (Exception ex)
			{
				ex.printStackTrace();
			}
		} else
		{
			objAttributes = new HashMap();
			isMap = true;
		}
		if (!isMap)
		{
			fields = clsAttributes.getDeclaredFields();
			for (int i = 0; fields != null && i < fields.length; i++)
			{
				fieldMap.put(fields[i].getName().toLowerCase(), fields[i].getName());
			}
		}

		Iterator itr = data.entrySet().iterator();
		while (itr.hasNext())
		{
			Map.Entry entry = (Map.Entry)itr.next();
			String key = (String)entry.getKey();
			Object objValue = entry.getValue();
			String value = null;
			if (objValue != null)
				value = entry.getValue().toString();

			if (objAttributes == null)
			{
				continue;
			}
			try
			{
				if (isMap)
				{
					((Map)objAttributes).put(key, value);
				} else
				{
					if (fieldMap.containsKey(key))
					{
						BeanUtils.setProperty(objAttributes, (String)fieldMap.get(key), value);
					}
				}
			} catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return objAttributes;
	}

	public JdbcTemplate getJdbcTemplate()
	{
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	public String getUserQuery()
	{
		return userQuery;
	}

	public void setUserQuery(String userQuery)
	{
		this.userQuery = userQuery;
	}

	public DataSource getDataSource()
	{
		return dataSource;
	}

	public void setUserAttributeClass(String userAttributeClass)
	{
		this.userAttributeClass = userAttributeClass;
	}

	public String getUserAttributeClass()
	{
		return userAttributeClass;
	}
}
