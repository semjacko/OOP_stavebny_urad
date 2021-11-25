package items;

public enum TypStavby {
	
	Obytna,
	Priemyselna,
	Verejna,
	Kulturna;
	
	public static TypStavby stringToTypStavby(String s) {
		if(s.equals("Obytn�"))
			return TypStavby.Obytna;
		if(s.equals("Priemysel�"))
			return TypStavby.Priemyselna;
		if(s.equals("Verejn�"))
			return TypStavby.Verejna;
		if(s.equals("Kult�rn�"))
			return TypStavby.Kulturna;
		return TypStavby.Obytna;    //nejak to upravit vyjnimkami
	}
	
}
