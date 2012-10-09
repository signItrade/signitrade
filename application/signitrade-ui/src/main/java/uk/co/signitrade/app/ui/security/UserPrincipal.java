package uk.co.signitrade.app.ui.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserPrincipal extends User
{
	private Object userAttributes;

	@SuppressWarnings("deprecation")
	public UserPrincipal(String userName, String password, Object userAttributes, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
			GrantedAuthority[] authorities)
	{
		/*
		 * calling constructor of super class.
		 */
		super(userName, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.userAttributes = userAttributes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode() all the hashcode, equals, and toString
	 * implementation is done as there was some issues in getting the customized
	 * information from the principal. Later type casting allows to retrieve the
	 * same(example in home.java).
	 * 
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((userAttributes == null) ? 0 : userAttributes.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (!super.equals(obj))
		{
			return false;
		}
		if (!(obj instanceof UserPrincipal))
		{
			return false;
		}
		UserPrincipal other = (UserPrincipal)obj;
	
		return true;
	}

	@Override
	public String toString()
	{
		return "UserPrincipal [userAttributes=" + userAttributes + ", getPassword()=" + getPassword()+ ", getUsername()=" + getUsername() + "]";
	}


	public Object getUserAttributes()
	{
		return userAttributes;
	}

}
