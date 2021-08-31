/*
 Assigned by:
 Yarin baruch, ID: 313204380
 Shimi Rokach, ID: 312493323
 */

package il.ac.hit.model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * The CostManagerDAO class implements the IProductsDAO interface This class
 * manages all the communication with the DB through the design patter DAO using
 * Hibernate Framework We use here the design pattern singleton
 * 
 * 
 */
public class CostManagerDAO implements IProductsDAO {

	private static CostManagerDAO instance;
	private SessionFactory factory;

	static {
		CostManagerDAO.instance = new CostManagerDAO();
	}

	/**
	 * Class Constructor
	 */
	private CostManagerDAO() {
		// creating factory for getting sessions

		try {
			// create a SessionFactory object
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}

	}

	/**
	 * This method return instance of the CostManagerDAO class
	 */
	public static CostManagerDAO getInstance() {
		return CostManagerDAO.instance;
	}

	/**
	 * The addCostItem method add new product to the Data Base
	 */
	@Override
	public void addCostItem(CostItem costItem) throws ProductsDAOException {
		// open a session
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			// begin a unit of work and return the associated Transaction object
			tx = session.beginTransaction();
			// stores the object into the database
			session.save(costItem);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ProductsDAOException("failed to add new product", e);
		} finally {
			if (session != null) {
				try {
					// end the session
					session.close();
				} catch (HibernateException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * deleteCostItem method will delete product from products table
	 */
	@Override
	public void deleteCostItem(int id) throws ProductsDAOException {
		// open a session
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			// begin a unit of work and return the associated Transaction object
			tx = session.beginTransaction();
			// get the match cost item from the DB
			CostItem costItem = (CostItem) session.get(CostItem.class, id);
			// delete the item from the database
			session.delete(costItem);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			throw new ProductsDAOException("failed to delete product", e);
		} finally {
			if (session != null) {
				try {
					// end the session
					session.close();
				} catch (HibernateException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * getCostItem method return a Product by id
	 *
	 */
	@Override
	public CostItem getCostItem(int id) throws ProductsDAOException {
		// open a session
		Session session = factory.openSession();
		Transaction tx = null;
		CostItem costItem = null;

		try {
			// begin a unit of work and return the associated Transaction object
			tx = session.beginTransaction();
			// get the match cost item from the DB
			costItem = (CostItem) session.get(CostItem.class, id);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			throw new ProductsDAOException("Error Finding the product", e);
		} finally {
			if (session != null) {
				try {
					// end the session
					session.close();
				} catch (HibernateException e) {
					e.printStackTrace();
				}
			}
		}
		// return the item
		return costItem;
	}

	/**
	 * getCostItems() return List of all products
	 * 
	 */
	@Override
	public List<CostItem> getCostItems() throws ProductsDAOException {
		// open a session
		Session session = factory.openSession();
		Transaction tx = null;
		// will hold all the items
		List<CostItem> costItems = null;
		try {
			// begin a unit of work and return the associated Transaction object
			tx = session.beginTransaction();
			// create a query to get list of all the costs from the DB
			costItems = session.createQuery("FROM CostItem").list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			throw new ProductsDAOException("failed to print products list", e);
		} finally {
			if (session != null) {
				try {
					// end the session
					session.close();
				} catch (HibernateException e) {
					e.printStackTrace();
				}
			}
		}
		// return the list
		return costItems;

	}

	/**
	 * generateReport method will return a list of cost items in range of the input
	 * date
	 */
	@Override
	public List<CostItem> generateReport(LocalDate startDate, LocalDate endDate) throws ProductsDAOException {

		// check if the input dates is valid
		if (startDate.isAfter(endDate)) {
			throw new ProductsDAOException("Date Range Error!");
		}
		// get all the costItems
		List<CostItem> list = getCostItems();
		// report will contain all the items in the input dates range
		List<CostItem> report = new ArrayList<CostItem>();

		for (CostItem costItem : list) {
			// check if the CostItem date is in the input dates range and add it to the
			// report if yes
			LocalDate localDate = stringToDate(costItem.getDate());
			if (localDate.isAfter(startDate.minusDays(1)) && localDate.isBefore(endDate.plusDays(1))) {
				report.add(costItem);
			}
		}
		// sorting the report by date
		report.sort((o1, o2) -> o1.getDate().compareTo(o2.getDate()));
		return report;
	}

	/**
	 * This method return the list of all the usernames and password
	 * 
	 */
	@Override
	public List<Login> getUsers() throws ProductsDAOException {

		// open a session
		Session session = factory.openSession();
		Transaction tx = null;
		// store all the users
		List<Login> credentials = null;
		try {
			// begin a unit of work and return the associated Transaction object
			tx = session.beginTransaction();
			// create a query to get list of all the users from the DB
			credentials = session.createQuery("FROM Login").list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			throw new ProductsDAOException("failed to retrive products list", e);
		} finally {
			if (session != null) {
				try {
					// end the session
					session.close();
				} catch (HibernateException e) {
					e.printStackTrace();
				}
			}
		}
		// return the list of users
		return credentials;
	}

	/**
	 * This method add a new user to the DB
	 * 
	 */
	@Override
	public void addUser(Login login) throws ProductsDAOException {
		// open a session
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			// begin a unit of work and return the associated Transaction object
			tx = session.beginTransaction();
			// stores the user into the database
			session.save(login);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ProductsDAOException("failed to add new user", e);
		} finally {
			if (session != null) {
				try {
					// end the session
					session.close();
				} catch (HibernateException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * getTotalCosts() method return the sum of all the costs.
	 * 
	 * @param items
	 * @return
	 */
	public double getTotalCosts(List<CostItem> items) {
		double sum = 0;
		// iterate over the list and sum all the costs
		for (CostItem cost : items) {
			sum += cost.getPrice();
		}
		return sum;

	}

	/**
	 * stringToDate method will convert the input String into LocalDate
	 * 
	 * @param date
	 * @return LocalDate
	 */
	public LocalDate stringToDate(String date) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(date, formatter);

	}

}
