package Data;

import java.util.ArrayList;
import java.util.List;

public class Firma {
    private int id;
    private String name;
    private List<Produkt> produkte;

    public Firma(int id, String name) {
        produkte = new ArrayList<Produkt>();
        this.id = id;
        this.name = name;
    }

    public int getID() {
        return id;
    }

    public void addProdukt(Produkt produkt) {
        produkte.add(produkt);
        produkt.setMadeBy(id);
    }
}
