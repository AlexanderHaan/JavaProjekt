public class App {
    public static void main(String[] args) {
        /* Before we import the file we check if the arguments are right. */
        Datenmodell database = new Datenmodell();
        database.readDataFromFile("movieproject2020.db");
    }
}
