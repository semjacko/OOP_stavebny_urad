package users;

/**
 * Trieda implementuje zamestnanca, ktory ma moznost prihlasenia sa do systemu
 * @author Robert Jacko
 *
 */
public class Admin extends User {
	
	private int salary;
	private int experiences;
	
	public Admin() {
	}
	
	/**
	 * Vytvori zamestnanca s menom, vekom, loginom a heslom
	 * @param name meno zamestnanca
	 * @param age vek zamestnanca
	 * @param login login zamestnanca
	 * @param password	heslo zamestnanca
	 */
	public Admin(String name, int age, String login, String password) {
		super(name, age, login, password);
		this.salary = 0;
		this.experiences = 0;
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
	 * @param f	financnik
	 */
	public void setMessage(String s,Financier f) {
		this.message.addMessage(f,s);
	}
	
	/**
	 * vrati plat zamestnanca
	 * @return aktualny plat zamestnanca
	 */
	public int getSalary() {
		return salary;
	}

	/**
	 * vrati skusenosti zamestnanca
	 * @return aktualne skusenosti zamestnanca
	 */
	public int getExperiences() {
		return experiences;
	}
	
	/**
	 * nastavi plat zamestnanca
	 * @param salary novy plat
	 */
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	/**
	 * prida zamestnancovi jednu skusenost
	 */
	public void addExperiences() {
		this.experiences++;
	}

	/**
	 * vrati informacie o zamestnancovi
	 * @return informacie o zamestnancovi
	 */
	public String getInfo() {
		String s = super.getInfo() + " " + experiences + " " + salary;
		return s;
	}
}
