package items;

import java.io.Serializable;

import users.Financier;
import users.Customer;
import users.Admin;

/**
 * Trieda implementuje konverzaciu, ktora patri pouzivatelovi, ktory ju agreguje
 * @author roboj
 *
 */
public class Message implements Serializable{
	
	 private String fullMessage;
	 
	 /**
	  * vytvori prazdnu konverzaciu
	  */
	 public Message() {
		 this.fullMessage = "";
	 }
	 
	 /**
	  * vrati celu konverzaciu
	  * @return cela konverzacia
	  */
	 public String getSpravaCela() {
		 return fullMessage;
	 }
	 
	 /**
	  * prida do konverzacie spravu
	  * @param z zamestnanec
	  * @param s sprava, ktora bude pridana do konverzacie
	  */
	 public void addMessage(Admin z, String s) {
		 this.fullMessage += "<admin>\n" + s + "\n";
	 }
	 
	 /**
	  * prida do konverzacie spravu
	  * @param f financnik
	  * @param s sprava, ktora bude pridana do konverzacie
	  */
	 public void addMessage(Financier f, String s) {
		 this.fullMessage += "<financnik>\n" + s + "\n";
	 }
	 
	 /**
	  * prida do konverzacie spravu
	  * @param u uzivatel
	  * @param s sprava, ktora bude pridana do konverzacie
	  */
	 public void addMessage(Customer u, String s) {
		 this.fullMessage += "<" + u.getLogin() + ">" + "\n" + s + "\n";;
	 }
	 
}
