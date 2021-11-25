package items;

import users.Customer;

/**
 * Trieda implementuje ziadost o stavbu
 * @author roboj
 *
 */
public class RequestBuilding extends Request {
	
	private Customer customer;
	private Building building;
	private boolean ziadostFond;
	private boolean ziadostOhodnotenie;
	
	/**
	 * vytvori ziadost o stavbu so stavbou, majitelom, ziadostou o financny fond a ziadostou o financne ohodnotenie
	 * @param b stavba
	 * @param c majitel stavby
	 * @param fond ziadost o financny fond
	 * @param valuation ziadost o financne ohodnotenie
	 */
	public RequestBuilding(Building b,Customer c, boolean fond, boolean valuation) {
		this.building = b;
		this.customer = c;
		this.ziadostFond = fond;
		this.ziadostOhodnotenie = valuation;
	}

	/**
	 * vrati majitela stavby
	 * @return majitel stavby
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * vrati stavbu
	 * @return stavba
	 */
	public Building getBuilding() {
		return building;
	}

	/**
	 * vrati pravdu ak je poziadane o financny fond
	 * @return ziadost o financny fond
	 */
	public boolean isZiadostFond() {
		return ziadostFond;
	}

	/**
	 * vrati pravdu ak je poziadane o financne ohodnotenie
	 * @return ziadost o financne ohodnotenie
	 */
	public boolean isZiadostOhodnotenie() {
		return ziadostOhodnotenie;
	}

}
