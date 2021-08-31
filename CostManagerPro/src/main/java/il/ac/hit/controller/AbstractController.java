/*
 Assigned by:
 Yarin baruch, ID: 313204380
 Shimi Rokach, ID: 312493323
 */
package il.ac.hit.controller;
import il.ac.hit.model.IProductsDAO;

/**
 * AbstractController is an abstract class that represent the controller
 *
 */
public abstract class AbstractController {

	protected IProductsDAO dao;
	/**
	 * Class Constructor
	 * @param dao
	 */
    AbstractController(IProductsDAO dao) {
        this.dao = dao;
    }

    public abstract void init();
}
