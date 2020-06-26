package Application;

public class App {
	static int mode = 0;
	static String argument;
	public static void main(String[] args) {
		/* Before we import the file we check if the arguments are right. */
		if (args.length == 0 && args.length > 1) {
			System.out.println("There are too few or too much arguments");
			System.exit(0); 
		} else {
			getModusAndArgument(args[0]);
			if (mode == 0) {
				System.out.println("Wrong argument");
				System.exit(0); 
			}
		}
		Datenmodell database = new Datenmodell();
		database.readDataFromFile("movieproject2020.db");

		// Select mode and five the argument
		switch(mode) {
			case 1:
				database.searchPerson(argument.substring(1, argument.length()-1));
				break;
			case 2:
				database.searchProdukt(argument.substring(1, argument.length()-1));
				break;
			case 3:
				database.printProduktNetwork(Integer.parseInt(argument));
				break;
			case 4:
				database.printCompanyNetwork(Integer.parseInt(argument));
				break;
		}
	}

	/**
	 * Diese Funktion liest das Argument und �berpr�ft ob, 
	 * es richtig ist. Der Modus wird aus diesem Argument gelesen
	 * und gesetzt. Die Modi sind:
	 *     1 - Personensuche
	 *     2 - Produktsuche
	 *     3 - Produktnetzwerk
	 *     4 - Firmennetzwerk
	 *     0 - Kein valider Modi
	 * Der Parameter wird au�erdem in argument eingelesen.
	 */
	private static void getModusAndArgument(String arg) {
		if (arg.startsWith("--personensuche=")) {
			mode = 1;
			argument = arg.substring(arg.indexOf('=')+1);
		} else if (arg.startsWith("--produktsuche=")) {
			mode = 2;
			argument = arg.substring(arg.indexOf('=')+1);
		} else if (arg.startsWith("--produktnetzwerk=")) {
			mode = 3;
			argument = arg.substring(arg.indexOf('=')+1);
		} else if (arg.startsWith("--firmennetzwerk=")) {
			mode = 4;
			argument = arg.substring(arg.indexOf('=')+1);
		} else mode = 0;
	}
}
