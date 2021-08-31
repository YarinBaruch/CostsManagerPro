/*
 Assigned by:
 Yarin baruch, ID: 313204380
 Shimi Rokach, ID: 312493323
 */
package il.ac.hit.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import il.ac.hit.model.CostManagerDAO;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * RouterServlet class functions like a router that forwards requests from the
 * controller using reflection. Each request's URL will include the controller
 * name and the action name (in that specific controller) at which the request
 * targets.
 * 
 * 
 */
public class RouterServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pkg;
	/**
	 * This static block load the CostManagerDao class when the router servlet load
	 * 
	 */
	static {
		CostManagerDAO.getInstance();
	}

	/**
	 * initialization of the package before the servlet handles its first request
	 * 
	 */
	@Override
	public void init() {
		pkg = getServletConfig().getInitParameter("package");
	}

	/**
	 * This method get request URI ,extract from it the controller and the action,
	 * and forward the request to the correct jsp document
	 * 
	 * 
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get URI path
		String text = request.getRequestURI();
		// split it by '/' char
		String[] arr = text.split("/");
		// the controller is user by default
		String controller = "user";
		// the first action is login
		String action = "login";
		// extract the controller
		if (arr.length > 3) {
			controller = arr[3];
			if (controller.equals("*")) {
				controller = "user";
			}
		}
		// extract the action
		if (arr.length > 4) {
			action = arr[4].toLowerCase();
		}

		try {
			// calling the right action on the right controller
			String className = pkg + "." + controller.substring(0, 1).toUpperCase()
					+ controller.substring(1).toLowerCase() + "Controller";
			// attempt to retrieve the class from the class loader
			Class<?> clazz = Class.forName(className);
			// Returns a Constructor object that reflects the constructor of IProductsDAO
			// class
			Constructor<?> constructor = clazz.getConstructor(il.ac.hit.model.IProductsDAO.class);
			// create and initialize a new instance of the CostManagerDAO class
			Object object = constructor.newInstance(il.ac.hit.model.CostManagerDAO.getInstance());
			// get the Method object that matches the specified name and parameterTypes
			Method method = clazz.getMethod(action, HttpServletRequest.class, HttpServletResponse.class);
			// Invokes the underlying method represented by this Method object
			method.invoke(object, request, response);

			// forwarding the execution to the view (identical in its name to the action)
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/" + controller + "/" + action + ".jsp");
			dispatcher.forward(request, response);

		} catch (ClassNotFoundException e) {
			showErrorMessage(request, response, "The requested controler doesnot exist");
		} catch (NoSuchMethodException e) {
			showErrorMessage(request, response, "Problem with instantiating the Model class");
		} catch (InvocationTargetException e) {
			showErrorMessage(request, response, "Problem with instantiating the Model class or invoking the action");
		} catch (InstantiationException e) {
			showErrorMessage(request, response, "Problem with instantiating the Model class");
		} catch (IllegalAccessException e) {
			showErrorMessage(request, response, "Problem with instantiating the Model class");
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * The showErrorMessage will invoke when an error has occurred.
	 * 
	 * @param request
	 * @param response
	 * @param text
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void showErrorMessage(HttpServletRequest request, HttpServletResponse response, String text)
			throws ServletException, IOException {
		request.setAttribute("errormessage", text);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
		dispatcher.forward(request, response);
	}
}
