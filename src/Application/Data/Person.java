package Application.Data;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private int id;
    private String name;
    private String geschlecht;
    private List<Person> freunde;
    private List<Produkt> produkte;

    public Person(int id, String name, String geschlecht) {
        freunde = new ArrayList<Person>();
        produkte = new ArrayList<Produkt>();
        this.id = id;
        this.name = name;
        this.geschlecht = geschlecht;
    }

    public int getID() {
        return id;
    }

	public String getName() {
		return name;
	}

	public void printDetails() {
		System.out.println("ID:                              " + id);
		System.out.println("Name:                            " + name);
		System.out.println("Geschlecht:                      " + geschlecht);
		System.out.println("Anzahl der Freunde:              " + freunde.size());
		System.out.println("Anzahl der gekauften Produkte:   " + produkte.size());
		System.out.println();
	}

    public void addFriend(Person person) {
        freunde.add(person);
    }

    public void boughtProdukt(Produkt produkt) {
        produkte.add(produkt);
    }

	public List<Produkt> getProdukte() {
		return produkte;
	}

	public List<Person> getFriends() {
		return freunde;
	}
}
