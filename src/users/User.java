package users;

import java.io.Serializable;

import items.Message;

/**
 * Trieda implementuje pouzivatela, ktory ma moznost prihlasenia sa do systemu
 * @author roboj
 *
 */
public class User implements Serializable {
	
	private String name;
	private int age;
	private String login;
	private String password;
	protected Message message;
	
	public User() {
	}
	
	/**
	 * vytvori pouzivatela s menom, vekom, loginom a heslom a vytovri prazdnu konverzaciu
	 * @param name meno pouzivatela
	 * @param age vek pouzivatela
	 * @param login login pouzivatela
	 * @param password heslo pouzivatela
	 */
	public User(String name, int age, String login, String password) {
		this.name = name;
		this.age = age;
		this.login = login;
		this.password = password;
		this.message = new Message();
	}
	
	/**
	 * vrati meno pouzivatela
	 * @return meno pouzivatela
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * vrati vek pouzivatela
	 * @return vek pouzivatela
	 */
	public int getAge(){
		return age;
	}
	
	/**
	 * vrati login pouzivatela
	 * @return login pouzivatela
	 */
	public String getLogin(){
		return login;
	}
	
	/**
	 * vrati heslo pouzivatela
	 * @return heslo pouzivatela
	 */
	public String getPassword(){
		return password;
	}
	
	/**
	 * vrati konverzaciu pouzivatela
	 * @return konverzacia pouzivatela
	 */
	public String getMessage() {
		return message.getSpravaCela();
	}
	
	/**
	 * vrati informacie o pouzivatelovi
	 * @return informacie o pouzivatelovi
	 */
	public String getInfo() {
		String s = name + " " + age + " " + login;
		return s;
	}

}
