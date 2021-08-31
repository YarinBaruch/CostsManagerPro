/*
 Assigned by:
 Yarin baruch, ID: 313204380
 Shimi Rokach, ID: 312493323
 */

package il.ac.hit.model;

import java.time.LocalDate;
import java.util.List;

/**
 * IProductsDAO Interface used for implement the DAO pattern
 * which will help us to communicate with the DB using Hibernate
 */
public interface IProductsDAO {
	/**
	 * The addCostItem method add new product to the Data Base
	 * @param costItem
	 * @throws ProductsDAOException
	 */
    public void addCostItem(CostItem costItem) throws ProductsDAOException;
    /**
     * deleteCostItem method will delete product from products table
     * @param id
     * @throws ProductsDAOException
     */
    public void deleteCostItem(int id) throws ProductsDAOException;
    /**
     * getCostItem method return a Product by id
     * @param id
     * @return Product
     * @throws ProductsDAOException
     */
    public CostItem getCostItem(int id) throws ProductsDAOException;
    /**
     * getCostItems() return List of all products  
     * @return List<>
     * @throws ProductsDAOException
     */
    public List<CostItem> getCostItems()throws ProductsDAOException;
    /**
     * getUsers method return List that stores the usernames and passwords
     * @return List<>
     * @throws ProductsDAOException
     */
	public List<Login> getUsers() throws ProductsDAOException;
	/**
	 * This method add a new user to the DB
	 * @param login
	 * @throws ProductsDAOException
	 */
	public void addUser(Login login) throws ProductsDAOException;
	/**
	 * generateReport method will return a list of cost items in range of the input date
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ProductsDAOException
	 */
	public List<CostItem> generateReport(LocalDate startDate, LocalDate endDate) throws ProductsDAOException;
	/**
	 *  getTotalCosts() method return the sum of all the costs.
	 * @param items
	 * @return
	 */
	public double getTotalCosts(List<CostItem> items);
}
