/*
 Assigned by:
 Yarin baruch, ID: 313204380
 Shimi Rokach, ID: 312493323
 */
package il.ac.hit.controller;

import il.ac.hit.model.IProductsDAO;

/**
 * BasicController class represents the main controller
 *
 */
public class BasicController extends AbstractController {
	/**
	 * Class Constructor
	 * @param dao
	 */
    public BasicController(IProductsDAO dao) {
        super(dao);
    }

    @Override
    public void init() {

    }
}
