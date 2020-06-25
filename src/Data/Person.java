package Data;

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

    public void addFriend(Person person) {
        freunde.add(person);
    }

    public void boughtProdukt(Produkt produkt) {
        produkte.add(produkt);
    }
}
