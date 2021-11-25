package file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import items.LoginException;
import items.RequestBuilding;
import items.RequestSalary;
import users.Admin;
import users.Customer;
import users.Financier;
import users.User;

/**
 * Trieda, kotra pracuje so suborom pouzivatelia.out
 * @author roboj
 *
 */
public class FileUsers implements File{

	private ArrayList<User> users;
	
	/**
	 * vytvori nastroj na pracu s databazou pouzivatelov a nacita pouzivatelov z databazy
	 */
	public FileUsers() {
		read();
	}
	
	/**
	 * vrati ArrayList pouzivatelov
	 * @return pouzivatelia
	 */
	public ArrayList<User> getUsers() {
		return users;
	}
	
	/**
	 * prida pouzivatela do ArrayListu pouzivatelia 
	 * @param u pouzivatel
	 */
	public void add(User u) {
		users.add(u);
		write();
	}
	
	/**
	 * zapise ArrayList pouzivatelia do suboru pouzivatelia.out
	 */
	public void write() {											
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("pouzivatelia.out"));
			out.writeObject(users);
			out.close();
		}
		catch(IOException e) {
			System.out.println("Chyba pri pristupe k suboru");
		}
	}
	
	/**
	 * vymaze subor pouzivatelia.out
	 */
	public void erase() {
		try {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("pouzivatelia.out"));
		users = new ArrayList<User>();
		User f = new Financier("financnik",50,"financnik","f");
		users.add(f);
		out.writeObject(users);
		out.close();
		} 
		catch (IOException e) {
			System.out.println("Chyba pri pristupe k suboru");
		}
	}
	
	/**
	 * nacita pouzivatelov zo suboru pouzivatelia.out
	 */
	public void read() {     
		try {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("pouzivatelia.out"));
		users = ((ArrayList<User>)in.readObject());
		in.close();
		} 
		catch (ClassNotFoundException e) {} 
		catch (IOException e2) {
			System.out.println("Chyba pri pristupe k suboru");
		} 
	}
	
	/**
	 * zisti, ci uz zadany login nahodou neexistuje
	 * @param login login pouzivatela
	 * @throws LoginException login uz existuje
	 */
	public void zvalidujLogin(String login) throws LoginException{
		for(User u : users) {
			if(u.getLogin().equals(login))
				throw new LoginException();
		}
	}
	
	/**
	 * zisti, ci boli spravne zadane heslo a login a vrati index loginu v ArrayListe pouzivatelia
	 * @param login login pouzivatela
	 * @param heslo heslo pouzivatela
	 * @return index loginu v ArrayListe pouzivatelia
	 */
	public int zvalidujPrihlasenie(String login,String heslo) {
		int i = 0;
		for(User p : users) {
			if(p.getLogin().equals(login) & p.getPassword().equals(heslo))
				return i;
			i++;
		}
		return -1;
	}
	
	/**
	 * zisti index zadaneho loginu v ArrayListe pouzivatelia a vrati ho
	 * @param login login pouzivatela
	 * @return index loginu v ArrayListe pouzivatelia
	 */
	public int indexOfLogin(String login) {
		int i = 0;
		for(User u : users) {
			if(u.getLogin().equals(login))
				return i;
			i++;
		}
		return -1;
	}
	
	/**
	 * vrati ArrayList loginov adminov
	 * @return ArrayList loginov adminov 
	 */
	public ArrayList<String> getAdminLogins() {
		ArrayList<String> list = new ArrayList<>();
		for(User u : users) {
			if(u instanceof Admin)
				list.add(u.getLogin());
		}
		return list;
	}
	
	/**
	 * vrati ArrayList loginov uzivatelov
	 * @return ArrayList loginov uzivatelov 
	 */
	public ArrayList<String> getCustomerLogins() {
		ArrayList<String> list = new ArrayList<>();
		for(User u : users) {
			if(u instanceof Customer)
				list.add(u.getLogin());
		}
		return list;
	}

	public void add(RequestSalary r) {
	}

	public void add(RequestBuilding r) {
	}
}
