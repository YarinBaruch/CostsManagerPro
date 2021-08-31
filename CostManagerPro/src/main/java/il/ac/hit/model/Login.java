/*
 Assigned by:
 Yarin baruch, ID: 313204380
 Shimi Rokach, ID: 312493323
 */
package il.ac.hit.model;

/**
 * This class represent user that login to the system
 * @author Yarin
 *
 */
public class Login {
	
	private String username;
	private String password;
	
	/**
	 * Class Constructor
	 * @param username
	 * @param password
	 */
	public Login(String username, String password) {
		setUsername(username);
		setPassword(password);
	}
	/**
	 * default class constructor
	 */
	public Login() {
		
	}
	/**
	 * return the username 
	 * @return username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * set a username for user
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * return the user password
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * set password for the user
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * This method print the details about Login object
	 */
	@Override
	public String toString() {
		return "Login [username=" + username + ", password=" + password + "]";
	}

	/**
	 * Returns a hash code value for the object. 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	/**
	 * Indicates whether some other object is "equal to" this one.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Login other = (Login) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	
	
	
}
