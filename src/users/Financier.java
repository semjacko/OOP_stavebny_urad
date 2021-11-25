package users;

import items.RequestBuilding;
import items.RequestSalary;
import java.util.ArrayList;
import items.Building;

/**
 * Trieda implementuje financnika, ktory ma moznost prihlasenia sa do systemu
 * @author roboj
 *
 */
public class Financier extends User{
	
	private ArrayList<RequestBuilding> requestsB;
	private ArrayList<RequestSalary> requestsS;
	
	/**
	 * vytvori financnika s menom, vekom, loginom a heslom a vytvori prazdne ArrayListy ziadosti stavieb a ziadosti platov ako atributy
	 * @param name meno financnika
	 * @param age vek financnika
	 * @param login login financnika
	 * @param password heslo financnika
	 */
	public Financier(String name, int age, String login, String password) {	
		super(name, age, login, password);
		requestsB = new ArrayList<>();
		requestsS = new ArrayList<>();
	}
	
	/**
	 * nastavi zamestnancovy novy plat
	 * @param a zamestnanec
	 * @param newSalary	novy plat
	 */
	public void nastavPlat(Admin a, int newSalary) {
		a.setSalary(newSalary);
	}
	
	/**
	 * nastavi budove financne ohodnotenie
	 * @param b budova
	 * @param price financne ohodnotenie
	 */
	public void evaluate(Building b,int price) {
		b.setPrice(price);
	}
	
	/**
	 * nastavi budove financny fond
	 * @param b budova
	 * @param fond financny fond
	 */
	public void giveFond(Building b,int fond) {
		b.setFond(fond);
	}

	/**
	 * vrati ArrayList ziadosti o stavby
	 * @return ArrayList ziadosti o stavby
	 */
	public ArrayList<RequestBuilding> getRequestsB() {
		return requestsB;
	}

	/**
	 * vrati ArrayList ziadosti o platy
	 * @return ArrayList ziadosti o platy
	 */
	public ArrayList<RequestSalary> getRequestsS() {
		return requestsS;
	}
	
	/**
	 * prida do ArrayListu ziadosti o stavby novu ziadost
	 * @param rb ziadost o stavbu
	 */
	public void addRequest(RequestBuilding rb) {
		requestsB.add(rb);
	}
	
	/**
	 * prida do ArrayListu ziadosti o platy novu ziadost
	 * @param rs ziadost o plat
	 */
	public void addRequest(RequestSalary rs) {
		requestsS.add(rs);
	}
	
	/**
	 * odstrani ziadost s indexom i z ArrayListu ziadosti o stavby
	 * @param i index ziadosti
	 */
	public void removeRequestB(int i) {
		requestsB.remove(i);
	}
	
	/**
	 * odstrani ziadost s indexom i z ArrayListu ziadosti o platy 
	 * @param i index ziadosti
	 */
	public void removeRequestS(int i) {
		requestsS.remove(i);
	}

}
