/*
 Assigned by:
 Yarin baruch, ID: 313204380
 Shimi Rokach, ID: 312493323
 */
package il.ac.hit.controller;

import il.ac.hit.model.*;
import il.ac.hit.utils.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The UserController class represents an user controller in order to perform
 * user actions
 * 
 */
public class UserController extends BasicController {

	private Utilities util = null;
	HttpSession session = null;

	/**
	 * Class Constructor
	 * 
	 * @param dao
	 */
	public UserController(IProductsDAO dao) {
		super(dao);
		util = new Utilities();
	}

	/**
	 * The products method get all the cost items from the model and send it to the
	 * view via the HttpServletRequest object
	 * 
	 * @param request
	 * @param response
	 * @throws ProductsDAOException
	 */
	public void products(HttpServletRequest request, HttpServletResponse response) throws ProductsDAOException {
		// get list of all the cost items
		List<CostItem> vec = dao.getCostItems();
		// create list that will hold all the costs that match the current user
		List<CostItem> list = new ArrayList<CostItem>();
		// get the session object associated with the request
		session = request.getSession();
		// get the user who is currently logged in
		Login login = (Login) session.getAttribute("match");

		if (login != null) {
			// iterate over the cost items list and check which cost item belong to the
			// current user
			for (CostItem costItem : vec) {
				if (costItem.getUser().equals(login.getUsername())) {
					// add the cost item to the list only if match to the user
					list.add(costItem);
				}
			}
			// get the total sum of all the costs
			double sum = dao.getTotalCosts(list);
			// set the attribute on the request 
			request.setAttribute("data", list);
			request.setAttribute("total", sum);
		}

	}

	/**
	 * The addproduct method gets all the inputs that the user fill in the form and
	 * add a new product to the DB
	 * 
	 * @param request
	 * @param response
	 * @throws ProductsDAOException
	 */
	public void addproduct(HttpServletRequest request, HttpServletResponse response) throws ProductsDAOException {

		double price = 0.0;
		int id = 0;
		// get the user input from the form
		String name = request.getParameter("name");
		String priceStr = request.getParameter("price");
		String idStr = request.getParameter("ID");
		String description = request.getParameter("description");
		String category = request.getParameter("category");
		String date = request.getParameter("date");

		// check if the input is null
		if (name != null && description != null && priceStr != null && idStr != null && category != null
				&& date != null) {
			// check if the input is valid using Utilities class
			if (util.validateItem(name, priceStr, description, category, date, idStr)) {
				// parsing the data to numbers
				price = Double.parseDouble(priceStr);
				id = Integer.parseInt(idStr);
				// get the current session associated with this request
				session = request.getSession();
				// get the user who is currently logged in
				Login login = (Login) session.getAttribute("match");
				if (login != null) {
					// get the username without the password for adding it to the DB
					// so in this way we can identify which user add this item
					String user = login.getUsername();
					CostItem item = new CostItem(id, name, description, price, date, category, user);
					// add the item to the DB
					dao.addCostItem(item);
					// set the attribute
					request.setAttribute("product", item);

				}

			} else {
				// if the user input is invalid set custom attribute
				request.setAttribute("erroradd", "");

			}
		}
	}

	/**
	 * The deleteproduct method gets from the user a product id to be deleted and
	 * delete it from the DB
	 * 
	 * @param request
	 * @param response
	 * @throws ProductsDAOException
	 */
	public void deleteproduct(HttpServletRequest request, HttpServletResponse response) throws ProductsDAOException {

		int id = 0;
		// get the user input from the form
		String idStr = request.getParameter("ID");
		if (idStr != null) {
			// check if the id is valid
			if (util.validateID(idStr)) {
				// get the current session associated with this request
				session = request.getSession();
				// get the user who is currently logged in
				Login login = (Login) session.getAttribute("match");
				if (login != null) {
					// parsing the data to number
					id = Integer.parseInt(idStr);
					// get the cost item by the id
					CostItem item = dao.getCostItem(id);
					// check if there is such cost
					if (item != null) {
						if (item.getUser().equals(login.getUsername())) {
							// delete the cost item
							dao.deleteCostItem(id);
							// set appropriate attribute with the delete item
							request.setAttribute("delete", item);
						}
					}

				}
			} else {
				// if the user input is invalid set custom attribute
				request.setAttribute("errordel", "");

			}
		}
	}

	/**
	 * The getproduct method get a product id from the user, find the right product
	 * from the DB and send it to the view via the HttpServletRequest object
	 * 
	 * @param request
	 * @param response
	 * @throws ProductsDAOException
	 */
	public void getproduct(HttpServletRequest request, HttpServletResponse response) throws ProductsDAOException {

		int id = 0;
		// get the user input from the form
		String idStr = request.getParameter("ID");
		if (idStr != null) {
			// check if the id is valid
			if (util.validateID(idStr)) {
				// get the current session associated with this request
				session = request.getSession();
				// get the user who is currently logged in
				Login login = (Login) session.getAttribute("match");
				if (login != null) {
					// parsing the data to number
					id = Integer.parseInt(idStr);
					// get the cost item by the id
					CostItem item = dao.getCostItem(id);
					// check if item is found
					if (item != null) {
						// check if the item belong to the current user
						if (item.getUser().equals(login.getUsername())) {
							request.setAttribute("idproduct", item);
						}
					}
				}

			} else {
				// if the user input is invalid set custom attribute
				request.setAttribute("errorid", "");
			}
		}
	}

	/**
	 * The login method get the username and the password from the form and check if
	 * there is a match with the data base.
	 * 
	 * @param request
	 * @param response
	 * @throws ProductsDAOException
	 * @throws IOException
	 * @throws ServletException
	 */
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ProductsDAOException, IOException, ServletException {
		// get the user input from the form
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (username != null && password != null) {
			// check if the user and password is valid
			if (util.validateUser(username, password)) {
				// create an instance of Login class
				Login user = new Login(username, password);
				// get list of all the users
				List<Login> vec = dao.getUsers();
				// get the current session associated with this request
				session = request.getSession();
				// check if the list contains this user(if the credentials are match)
				if (vec.contains(user)) {
					// set "match" attribute - success login
					session.setAttribute("match", user);

				} else {
					// set "no-match" attribute - fail login
					session.setAttribute("no-match", user);

				}
			} else {
				// if the user input is invalid set custom attribute
				request.setAttribute("errorlogin", username);

			}
		}
	}

	/**
	 * This method invalidate the current session
	 * 
	 * @param request
	 * @param response
	 * @throws ProductsDAOException
	 */
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ProductsDAOException {

		// get the current session associated with this request
		session = request.getSession();
		if (session != null) {
			// Invalidates this session
			session.invalidate();
		}

	}

	/**
	 * The register method used for creating a new user account
	 * 
	 * @param request
	 * @param response
	 * @throws ProductsDAOException
	 */
	public void register(HttpServletRequest request, HttpServletResponse response) throws ProductsDAOException {

		// get the user input from the form
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (username != null && password != null) {
			// check if the user and password is valid
			if (util.validateUser(username, password)) {
				// create an instance of Login class
				Login login = new Login(username, password);
				// add this user to the DB
				dao.addUser(login);
				// set appropriate attribute with "register"
				request.setAttribute("register", login);
			} else {
				// if the user input is invalid set custom attribute
				request.setAttribute("errorregister", "");

			}
		}
	}

	/**
	 * This method returns report of all the cost items in specific period
	 * 
	 * @param request
	 * @param response
	 * @throws ProductsDAOException
	 */
	public void report(HttpServletRequest request, HttpServletResponse response) throws ProductsDAOException {

		// get the start and end date from the user
		String start = request.getParameter("startdate");
		String end = request.getParameter("enddate");
		// create list for the report
		List<CostItem> list = new ArrayList<CostItem>();

		if (start != null && end != null) {
			// get the current session associated with this request
			session = request.getSession();
			// get the user who is currently logged in
			Login currentUser = (Login) session.getAttribute("match");
			if (currentUser != null) {
				// check the input dates
				if (util.validateDate(start, end)) {
					// convert the date string to LocalDate Object
					LocalDate startDate = stringToDate(start);
					LocalDate endDate = stringToDate(end);
					// call the generate report method to get the report of that specifics dates
					List<CostItem> report = dao.generateReport(startDate, endDate);
					// filter the report by the current user
					for (CostItem costItem : report) {
						// check if the user in the report match the current user
						if (costItem.getUser().equals(currentUser.getUsername())) {
							list.add(costItem);
						}
					}
					// get the total of all the costs
					double total = dao.getTotalCosts(list);
					// set the appropriate attributes
					request.setAttribute("report", list);
					request.setAttribute("reportsum", total);
				} else {
					// if the user input is invalid set custom attribute
					request.setAttribute("errorreport", "");
				}

			}
		}
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
