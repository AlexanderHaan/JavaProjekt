import Data.Firma;
import Data.Person;
import Data.Produkt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
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
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("\nFile \"movieproject2020.db\" must be in same directory of \"Datenmodell\"-Class");
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
            personen.get(index2).addFriend(personen.get(index1));
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