package items;

public enum TypStavby {
	
	Obytna,
	Priemyselna,
	Verejna,
	Kulturna;
	
	public static TypStavby stringToTypStavby(String s) {
		if(s.equals("Obytná"))
			return TypStavby.Obytna;
		if(s.equals("Priemyselá"))
			return TypStavby.Priemyselna;
		if(s.equals("Verejná"))
			return TypStavby.Verejna;
		if(s.equals("Kultúrná"))
			return TypStavby.Kulturna;
		return TypStavby.Obytna;    //nejak to upravit vyjnimkami
	}
	
}
