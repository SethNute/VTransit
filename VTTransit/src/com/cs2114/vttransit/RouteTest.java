package com.cs2114.vttransit;


/**
 * Test class for Route.
 * 
 * @author Seth Nute (seth12)
 * @author Ben Kodres (bkobrien)
 * @author Joe Fletcher (joevt)
 * @version 11/8/13
 */
public class RouteTest extends student.TestCase {

	/** 
	 * setup
	 */
	protected void setUp() throws Exception {
		
	}
	
	/**
	 * Tests the static method
	 */
	public void testAllRoutes()
	{
		Exception e = null;
		try {
			Route.allRoutes();
		} 
		catch (Exception ex) {
			e = ex;
		}
		assertNull(e);
	}

}
