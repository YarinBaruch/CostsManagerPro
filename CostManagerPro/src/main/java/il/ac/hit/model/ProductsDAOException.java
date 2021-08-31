/*
 Assigned by:
 Yarin baruch, ID: 313204380
 Shimi Rokach, ID: 312493323
 */

package il.ac.hit.model;

/**
 * The ProductsDAOException class is custom exception class
 * for CostManagerDao class
 */
public class ProductsDAOException extends Exception{

  	
	private static final long serialVersionUID = 1L;
	/**
	 * Class Constructor for message 
	 * @param message
	 */
	public ProductsDAOException(String message) {
        super(message);
    }
	/**
	 * Class Constructor for message and Throwable cause
	 * @param message
	 * @param cause
	 */
    public ProductsDAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
