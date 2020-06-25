package Data;

public class Produkt {
    private int id;
    private String name;
    private int madeBy;

    public Produkt(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getID() {
        return id;
    }

    public void setMadeBy(int id) {
        madeBy = id;
    }
}
