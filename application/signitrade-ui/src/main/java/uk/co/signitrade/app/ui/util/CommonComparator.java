package uk.co.signitrade.app.ui.util;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;

public class CommonComparator implements java.util.Comparator
{
	static Logger LOG = Logger.getLogger(CommonComparator.class);
	public static final String ASCENDING = "ascending";
	public static final String DESCENDING = "descending";
	private String sortField;
	private String sortOrder;
	private Class dataType;

	public CommonComparator()
	{}

	/**
	 * This is the constructor of class CommonComparator
	 * 
	 * @param sortField is argument of type String. 
	 * @param sortOrder is argument of type String. 
	 * @param dataType is argument of type Class. 
	 * 
	 */
	public CommonComparator(String sortField, String sortOrder, Class dataType)
	{
		this.setSortField(sortField);
		this.setSortOrder(sortOrder);
		this.setDataType(dataType);
	}

	/**
	 * This is the method which compares two objects.
	 * 
	 * @param ob1 is argument of type Object. 
	 * @param ob2 is argument of type Object.
	 *  
	 * @return result in the form of int.
	 * 
	 */
	public int compare(Object ob1, Object ob2)
	{
		Object data1 = null;
		Object data2 = null;

		Object dataType = this.getDataType();
		sortField = sortField.substring(0, 1).toUpperCase() + sortField.substring(1);
		try
		{
			Method method = ob1.getClass().getMethod("get" + sortField, new Class[] {});
			data1 = method.invoke(ob1, new Object[] {});
			data2 = method.invoke(ob2, new Object[] {});
			if (sortOrder.equalsIgnoreCase(ASCENDING))
			{
				return compareData(data1, data2);
			} else
			{
				return compareData(data2, data1);
			}
		} catch (Exception e)
		{
			LOG.error(e.getMessage(), e);
		}
		return 0;

	}

	/**
	 * This is the method which compares two Strings.
	 * 
	 * @param compareStr1 is argument of type String. 
	 * @param compareStr2 is argument of type String.
	 *  
	 * @return result in the form of int.
	 * 
	 */
	public int compareData(Object data1, Object data2)
	{
		if (dataType == String.class)
			return ((String)data1).compareTo((String)data2);
		else if (dataType == Integer.class)
			return ((Integer)data1).compareTo((Integer)data2);
		else if (dataType == Date.class)
			return ((Date)data1).compareTo((Date)data2);
		else if (dataType == Timestamp.class)
			return ((Timestamp)data1).compareTo((Timestamp)data2);
		else if (dataType == Long.class)
			return ((Long)data1).compareTo((Long)data2);
		else if (dataType == Double.class)
			return ((Double)data1).compareTo((Double)data2);
		else if (dataType == Float.class)
			return ((Float)data1).compareTo((Float)data2);
		else
			return 0;
	}

	/**
	 * @param dataType the dataType to set
	 */
	public void setDataType(Class dataType)
	{
		this.dataType = dataType;
	}

	/**
	 * @return the dataType
	 */
	public Class getDataType()
	{
		return dataType;
	}

	/**
	 * @return the sortField
	 */
	public String getSortField()
	{
		return sortField;
	}

	/**
	 * @param sortField the sortField to set
	 */
	public void setSortField(String sortField)
	{
		this.sortField = sortField;
	}

	/**
	 * @return the sortOrder
	 */
	public String getSortOrder()
	{
		return sortOrder;
	}

	/**
	 * @param sortOrder the sortOrder to set
	 */
	public void setSortOrder(String sortOrder)
	{
		this.sortOrder = sortOrder;
	}
}
