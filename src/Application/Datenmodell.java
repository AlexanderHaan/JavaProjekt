package Application;

import Application.Data.Firma;
import Application.Data.Person;
import Application.Data.Produkt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Datenmodell {
	private List<Firma> firmen;
	private List<Person> personen;
	private List<Produkt> produkte;


	public Datenmodell() {
		firmen = new ArrayList<Firma>();
		personen = new ArrayList<Person>();
		produkte = new ArrayList<Produkt>();
	}

	/**
	 * Diese Funktion gibt das Firmennetzwerk
	 * einer Person aus. Falls diese Person
	 * nicht existiert, schliesst das Programm.
	 *
	 * @param id Id der Person.
	 */
	public void printCompanyNetwork(int id) {
		boolean foundPerson = false;
		int personIndex = -1;

		for(int i = 0; i < personen.size(); i++) {
			if(personen.get(i).getID() == id) {
				personIndex = i;
				foundPerson = true;
				break;
			}
		}

		if(!foundPerson) {
			System.out.println("Die ID ist falsch.");
			System.exit(0);
		} else {
			List<String> firms = new ArrayList<String>();
			Person person = personen.get(personIndex);
			for(Produkt p: person.getProdukte()) {
				if(!firms.contains(p.getCompanyName())) {
					firms.add(p.getCompanyName());
				}
			}
			for(Person friend: person.getFriends()) {
				for(Produkt p: friend.getProdukte()) {
					if(!firms.contains(p.getCompanyName())) {
						firms.add(p.getCompanyName());
					}
				}
			}

			Collections.sort(firms, String.CASE_INSENSITIVE_ORDER);
			for(int i = 0; i < firms.size(); i++) {
				if(i == firms.size() - 1) {
					System.out.print(firms.get(i));
				} else {
					System.out.print(firms.get(i) + ", ");
				}
			}
			System.out.println();
		}
	}

	/**
	 * Diese Funktion gibt das Produktnetzwerk von 
	 * einer Person aus. Falls diese Person nicht existiert
	 * dann wir das Programm abgebrochen.
	 *
	 * @param id Id der Person.
	 */
	public void printProduktNetwork(int id) {
		boolean foundPerson = false;
		int personIndex = -1;

		for(int i = 0; i < personen.size(); i++) {
			if(personen.get(i).getID() == id) {
				personIndex = i;
				foundPerson = true;
				break;
			}
		}

		if(!foundPerson) {
			System.out.println("Die ID ist falsch.");
			System.exit(0);
		}
		else {
			List<String> prod = new ArrayList<String>();
			Person person = personen.get(personIndex);
			for(Produkt p: person.getProdukte()) {
				if(!prod.contains(p.getName())) {
					prod.add(p.getName());
				}
			}
			for(Person friend: person.getFriends()) {
				for(Produkt p: friend.getProdukte()) {
					if(!prod.contains(p.getName())) {
						prod.add(p.getName());
					}
				}
			}

			Collections.sort(prod, String.CASE_INSENSITIVE_ORDER);
			for(int i = 0; i < prod.size(); i++) {
				if(i == prod.size() - 1) {
					System.out.print(prod.get(i));
				} else {
					System.out.print(prod.get(i) + ", ");
				}
			}
			System.out.println();
		}
	}

	/**
	 * Such in der Produktliste nach dem Produkt,
	 * welches den String beinhaltet und gibt deren
	 * Details aus.
	 *
	 * @param produktName Das ist der einzulesene String.
	 */
	public void searchProdukt(String produktName) {
		for(int i = 0; i < produkte.size(); i++) {
			if(produkte.get(i).getName().toLowerCase().contains(produktName.toLowerCase())) {
				produkte.get(i).printDetails();
			}
		}
	}

	/**
	 * Sucht in der Personenliste nach der Person die 
	 * den String beihnaltet und gibt deren Details aus.
	 *
	 * @param personName Das ist der einzulesene String.
	 */
	public void searchPerson(String personName) {
		for(int i = 0; i < personen.size(); i++) {
			if(personen.get(i).getName().toLowerCase().contains(personName.toLowerCase())) {
				personen.get(i).printDetails();
			}
		}
	}

	/**
	 * Liest eine Datei und schreibt die Daten in die Instanz
	 * Datenmodell.
	 *
	 * @param fileName Diese Datei soll sich in dem gleichen Ordner befinden,
	 *                 wie die Klasse Datenmodell.
	 */
	public void readDataFromFile(String fileName) {
		String line = null;
		URL absPath = Datenmodell.class.getResource(fileName);
		try (BufferedReader br = new BufferedReader(new FileReader(new File(absPath.toURI())))) {
			int type = 0;
			while ((line = br.readLine()) != null) {
				if(line.startsWith("New_Entity:")) {
					type = typeOfData(line);
				} else {
					switch (type) {
						case 1:
							addPerson(line);
							break;
						case 2:
							addProdukt(line);
							break;
						case 3:
							addFirma(line);
							break;
						case 4:
							addFriendship(line);
							break;
						case 5:
							addPersonBuyProdukt(line);
							break;
						case 6:
							addFirmaMakeProdukt(line);
							break;
						default:
					}
				}
			}
			checkAllFriendships();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("\nFile \"movieproject2020.db\" must be in same directory of \"Datenmodell\"-Class");
		}
	}

	private void checkAllFriendships() {
		for(Person p : personen) {
			for(Person friend : p.getFriends()) {
				if(!friend.getFriends().contains(p)) {
					p.getFriends().remove(friend);
				}
			}
		}
	}

	/**
	 * Diese Funktion liest ein String ein und erweitert
	 * die Liste von hergestellten Produkte einer Firma
	 * um ein weiteres Produkt.
	 *
	 * @param line Das ist der einzulesene String
	 */
	private void addFirmaMakeProdukt(String line) {
		int produktId, companyId;

		String[] strings = line.split(",");
		produktId = Integer.parseInt(strings[0].substring(1, strings[0].length()-1));
		companyId = Integer.parseInt(strings[1].substring(1, strings[1].length()-1));

		int indexProdukt = -1, indexCompany = -1;
		for (int i = 0; i < produkte.size(); i++) {
			if (produkte.get(i).getID() == produktId) {
				indexProdukt = i;
				break;
			}
		}

		for (int i = 0; i < firmen.size(); i++) {
			if (firmen.get(i).getID() == companyId) {
				indexCompany = i;
				break;
			}
		}

		if (indexProdukt != -1 && indexCompany != -1) {
			firmen.get(indexCompany).addProdukt(produkte.get(indexProdukt));
		}
	}

	/**
	 * Diese Funktion liest die Information aus einem String und
	 * ergänzt die Liste gekaufter Produkte von einer Person um ein
	 * weiteres Produkt.
	 *
	 * @param line Das ist der einzulesene String.
	 */
	private void addPersonBuyProdukt(String line) {
		int personId, productId;

		String[] strings = line.split(",");
		personId = Integer.parseInt(strings[0].substring(1, strings[0].length()-1));
		productId = Integer.parseInt(strings[1].substring(1, strings[1].length()-1));

		int indexPerson = -1, indexProdukt = -1;
		for (int i = 0; i < personen.size(); i++) {
			if (personen.get(i).getID() == personId) {
				indexPerson = i;
				break;
			}
		}

		for (int i = 0; i < produkte.size(); i++) {
			if (produkte.get(i).getID() == productId) {
				indexProdukt = i;
				break;
			}
		}

		if (indexPerson != -1 && indexProdukt != -1) {
			personen.get(indexPerson).boughtProdukt(produkte.get(indexProdukt));
		}
	}

	/**
	 * Diese Funktion liest die Information aus
	 * dem String und fügt die jeweils andere Person
	 * in die Freundesliste hinzu.
	 *
	 * @param line Das ist der einzulesene String.
	 */
	private void addFriendship(String line) {
		int id1, id2;
		int index1 = -1, index2 = -1;

		String[] strings = line.split(",");
		id1 = Integer.parseInt(strings[0].substring(1, strings[0].length()-1));
		id2 = Integer.parseInt(strings[1].substring(1, strings[1].length()-1));

		for (int i = 0; i < personen.size(); i++) {
			if (personen.get(i).getID() == id1)
				index1 = i;
			else if (personen.get(i).getID() == id2)
				index2 = i;

			if (index1 != -1 && index2 != -1) break;
		}

		if (index1 != -1 && index2 != -1) {
			personen.get(index1).addFriend(personen.get(index2));
		}
	}

	/**
	 * Diese Funktion liest aus einem String die Information
	 * und ergänzt die Firmenliste um eine weitere Firma
	 *
	 * @param line Das ist der einzulesene String
	 */
	private void addFirma(String line) {
		int id;
		String name;

		String[] strings = line.split(",");
		id = Integer.parseInt(strings[0].substring(1, strings[0].length()-1));
		name = strings[1].substring(1, strings[1].length()-1);

		Firma firma = new Firma(id, name);
		firmen.add(firma);
	}

	/**
	 * Diese Funktion liest aus einem String die Information
	 * und ergänzt die Produktliste um ein weiteres Produkt
	 *
	 * @param line Das ist der einzulesene String
	 */
	private void addProdukt(String line) {
		int id;
		String name;

		String[] strings = line.split(",");
		id = Integer.parseInt(strings[0].substring(1, strings[0].length()-1));        firmen = new ArrayList<Firma>();
		name = strings[1].substring(1, strings[1].length()-1);

		Produkt produkt = new Produkt(id, name);
		produkte.add(produkt);
	}

	/**
	 * Diese Funktion liest aus einem String die Information
	 * und ergänzt dir Personenliste um eine weitere Person
	 *
	 * @param line Das ist der einzulesene String
	 */
	private void addPerson(String line) {
		int id;
		String name;
		String geschlecht;

		String[] strings = line.split(",");
		id = Integer.parseInt(strings[0].substring(1, strings[0].length()-1));
		name = strings[1].substring(1, strings[1].length()-1);
		geschlecht = strings[2].substring(1, strings[2].length()-1);

		Person person = new Person(id, name, geschlecht);
		personen.add(person);
	}


	/**
	 * Erkennt, um welchen Datentyp es sich handelt.
	 *
	 * @param line Liest den String der aktuellen Zeile ein
	 * @return     Gibt eine Nummer zurück die für einen Typ steht:
	 *             1 - Person;
	 *             2 - Produkt;
	 *             3 - Firma;
	 *             4 - Freundschaft von Personen;
	 *             5 - Person hat Produkt gekauft;
	 *             6 - Firma stellt Produkt her;
	 *             Falls nichts identifiziert werden konnte, wird das
	 *             Programm abgebrochen.
	 */
	private int typeOfData(String line) {
		if (line.contains("person_id") && line.contains("person_name") && line.contains("person_gender")) {
			return 1;
		} else if (line.contains("product_id") && line.contains("product_name")) {
			return 2;
		} else if (line.contains("company_id") && line.contains("company_name")) {
			return 3;
		} else if (line.contains("person1_id") && line.contains("person2_id")) {
			return 4;
		} else if (line.contains("person_id") && line.contains("product_id")) {
			return 5;
		} else if (line.contains("product_id") && line.contains("company_id")) {
			return 6;
		}
		System.exit(0);
		return 0;
	}
}
