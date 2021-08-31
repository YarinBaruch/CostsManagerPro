/*
 Assigned by:
 Yarin baruch, ID: 313204380
 Shimi Rokach, ID: 312493323
 */

package il.ac.hit.model;

/**
 * CostItem class represent a cost item product
 *
 */
public class CostItem {
	private int id;
	private String name;
	private String description;
	private double price;
	private String date;
	private String category;
	private String user;

	/**
	 * Class Constructor
	 * 
	 * @param id
	 * @param name
	 * @param description
	 * @param price
	 * @param date
	 * @param category
	 * 
	 */
	public CostItem(int id, String name, String description, double price, String date, String category, String user) {
		setId(id);
		setName(name);
		setDescription(description);
		setPrice(price);
		setDate(date);
		setCategory(category);
		setUser(user);

	}

	/**
	 * default class constructor
	 */
	public CostItem() {

	}

	/**
	 * return a cost item id
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * set a cost item id
	 * 
	 * @param id
	 */
	public void setId(int id) {
		if (id > 0) {
			this.id = id;
		}
	}

	/**
	 * return the name of the cost item
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * set a cost item name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * return the description of the cost item
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * set a cost item description
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * return the price of cost item
	 * 
	 * @return price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * set a cost item price
	 * 
	 * @param price
	 */
	public void setPrice(double price) {
		if (price > 0) {
			this.price = price;
		}
	}

	/**
	 * return the date of the cost item
	 * 
	 * @return date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * set date for the cost item
	 * 
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * return the category of the cost item
	 * 
	 * @return category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * set the category of the cost item
	 * 
	 * @param category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * get the user that created the item
	 * 
	 * @return
	 */
	public String getUser() {
		return user;
	}

	/**
	 * set the user that perform the action
	 * 
	 * @param user
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * return a String with details about CostItem
	 */
	@Override
	public String toString() {
		return "CostItem [id=" + id + ",  Name= ' " + name + '\'' + ",  Description= ' " + description + '\''
				+ "  , Price= " + price + ",   Date=  " + date + ",  Category=  " + category + "]" + "\n";
	}

	/**
	 * Returns a hash code value for the object.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		CostItem other = (CostItem) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}
