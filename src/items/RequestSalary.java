package items;

import users.Admin;

/**
 * Trieda implementuje ziadost o plat
 * @author roboj
 *
 */
public class RequestSalary extends Request{
	
	Admin admin;
	int newSalary;

	/**
	 * vytvori ziadost o plat so zamestnancom a novym platom
	 * @param admin zamestnanec
	 * @param newSalary novy plat
	 */
	public RequestSalary(Admin admin, int newSalary) {
		this.admin = admin;
		this.newSalary = newSalary;
	}

	/**
	 * vrati zamestnanca
	 * @return zamestnanec
	 */
	public Admin getAdmin() {
		return admin;
	}
	
	/**
	 * vrati novy plat
	 * @return novy plat
	 */
	public int getNewSalary() {
		return newSalary;
	}
	
	
}
