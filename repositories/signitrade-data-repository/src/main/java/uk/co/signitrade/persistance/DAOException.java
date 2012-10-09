package uk.co.signitrade.persistance;

/**
 * @author Bikram
 * 
 */
public class DAOException extends Exception
{


	public DAOException()
	{
	}

	/**
	 * @param message
	 */
	public DAOException(String message)
	{
		super(message);
	}

	/**
	 * @param cause
	 */
	public DAOException(Throwable cause)
	{
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DAOException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
