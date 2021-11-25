package items;

import java.io.Serializable;

public class Building implements Serializable{

	private String name;
	private TypStavby type;
	private int area;
	private int forest;
	private int fond;
	private int price;
	private String owner;
	
	public Building(String name, TypStavby type, int area, int forest, String owner) {

		this.name = name;
		this.type = type;
		this.area = area;
		this.forest = forest;
		this.fond = 0;
		this.price = 0;
		this.owner = owner;
			
	}
	
	public Building(String name, String type, int area, int forest, String owner) {

		this.name = name;
		this.type = TypStavby.stringToTypStavby(type);
		this.area = area;
		this.forest = forest;
		this.fond = 0;
		this.price = 0;
		this.owner = owner;
			
	}

	public String getName() {
		return name;
	}

	public TypStavby getType() {
		return type;
	}

	public int getArea() {
		return area;
	}

	public int getForest() {
		return forest;
	}

	public int getFond() {
		return fond;
	}

	public int getPrice() {
		return price;
	}
	
	public void setFond(int fond) {
		this.fond = fond;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getOwner() {
		return owner;
	}
		
}
