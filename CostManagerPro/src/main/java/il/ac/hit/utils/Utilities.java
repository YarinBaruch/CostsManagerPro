/*
 Assigned by:
 Yarin baruch, ID: 313204380
 Shimi Rokach, ID: 312493323
 */
package il.ac.hit.utils;

/**
 * This class used for input validation
 * 
 * @author Yarin
 *
 */
public class Utilities {
	/**
	 * Class Constructor
	 * 
	 */
	public Utilities() {

	}

	/**
	 * This method check the add cost item form and return true if all is OK
	 * 
	 * @param name
	 * @param price
	 * @param description
	 * @param category
	 * @param date
	 * @param id
	 * @return
	 */
	public boolean validateItem(String name, String price, String description, String category, String date,
			String id) {
		// check if one of the fields is empty, if yes return false
		if (name.equals("") || description.equals("") || price.equals("") || category.equals("") || date.equals("")
				|| id.equals("")) {
			return false;
		} else {
			// validate price and id
			if (validateID(id) && validatePrice(price)) {
				return true;
			}
		}
		return false;

	}

	/**
	 * This method validate username and password
	 * 
	 * @param user
	 * @param pass
	 * @return
	 */
	public boolean validateUser(String user, String pass) {
		// check if one of the fields is empty, if yes return false
		if (user.equals("") || pass.equals("")) {
			return false;
		}

		return true;

	}

	/**
	 * This method validate that the id is an integer
	 * 
	 * @param input
	 * @return
	 */
	public boolean validateID(String input) {
		// check if id is 0
		if (input.equals("0")) {
			return false;
		} else {
			try {
				// try to parse the string to Integer and if it fails return false
				Integer.parseInt(input);
			} catch (NumberFormatException e) {
				return false;
			}
			return true;
		}
	}

	/**
	 * This method validate that the price is really a number
	 * 
	 * @param input
	 * @return
	 */
	public boolean validatePrice(String input) {
		// check if the input is not empty
		if (input.equals("")) {
			return false;
		} else {
			try {
				// try to parse the string to Double and if it fails return false
				Double.parseDouble(input);
			} catch (NumberFormatException e) {
				return false;
			}
			return true;
		}
	}

	/**
	 * validateDate method check the input date
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public boolean validateDate(String start, String end) {
		// check if the input is not empty
		if (start.equals("") || end.equals("")) {
			return false;
		}
		// split the dates to arrays
		String startArr[] = start.split("-");
		String endArr[] = end.split("-");
		// check if we get 3 words
		if (startArr.length != 3 && endArr.length != 3) {
			return false;
		}
		return true;

	}

}
