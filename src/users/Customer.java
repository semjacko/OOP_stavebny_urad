package users;

import java.util.ArrayList;

import items.Building;

/**
 * Trieda implementuje uzivatela, ktory ma moznost prihlasenia sa do systemu
 * @author roboj
 *
 */
public class Customer extends User {
	
	private ArrayList<Building> buildings;
	
	/**
	 * vytvori uzivatela s menom, vekom, loginom a heslom a vytvori prazdny ArrayList stavieb ako atribut
	 * @param name meno uzivatela
	 * @param age vek uzivatela
	 * @param login login uzivatela
	 * @param password heslo uzivatela
	 */
	public Customer(String name, int age, String login, String password) {
		super(name, age, login, password);
		buildings = new ArrayList<>();
	}
	
	/**
	 * prida stavbu do ArrayListu
	 * @param b budova
	 */
	public void addBuilding(Building b) {
		buildings.add(b);
	}
	
	/**
	 * vrati uzivatelove stavby
	 * @return ArrayList stavieb patriacich uzivatelovi
	 */
	public ArrayList<Building> getBuildings() {
		return this.buildings;
	}

	/**
	 * prida spravu do konverzacie
	 * @param s sprava, ktora bude pridana do konverzacie
	 */
	public void setMessage(String s) {
		this.message.addMessage(this,s);
	}
	
	/**
	 * prida spravu do konverzacie
	 * @param s sprava, ktora bude pridana do konverzacie
	 * @param a zamestnanec
	 */
	public void setMessage(String s, Admin a) {
		this.message.addMessage(a, s);
	}
	
}
