package Application.Data;

public class Produkt {
	private int id;
	private String name;
	private Firma hersteller;

	public Produkt(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public void printDetails() {
		System.out.println("ID             : " + id);
		System.out.println("Name           : " + name);
		System.out.println("Hersteller-ID  : " + hersteller.getID());
		System.out.println("Hersteller-Name: " + hersteller.getName());
		System.out.println();
	}

	public String getName() {
		return name;
	}

	public String getCompanyName() {
		return hersteller.getName();
	}

	public int getID() {
		return id;
	}

	public void setMadeBy(Firma hersteller) {
		this.hersteller = hersteller;
	}
}
