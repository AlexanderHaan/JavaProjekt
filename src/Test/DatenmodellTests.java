///*
package Test;

import Application.Datenmodell;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class DatenmodellTests {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	private Datenmodell dataModel = new Datenmodell();

	public DatenmodellTests() {
		dataModel.readDataFromFile("movieproject2020.db");
	}

	/*
	 * If the following tests are working, than we have not to test the import,
	 * because this tests depend on the import. If the import is false than
	 * this tests wil also fail
	 */

	@Test
	public void testSearchPerson() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
		dataModel.searchPerson("Roger");
		String expectedOutput = 
			"ID:                              53\r\n"+
			"Name:                            Jaime Rogers\r\n"+
			"Geschlecht:                      Male\r\n"+
			"Anzahl der Freunde:              2\r\n"+
			"Anzahl der gekauften Produkte:   1\r\n"+
			"\r\n"+
			"ID:                              70\r\n"+
			"Name:                            Roger Walker\r\n"+
			"Geschlecht:                      Male\r\n"+
			"Anzahl der Freunde:              3\r\n"+
			"Anzahl der gekauften Produkte:   1\r\n"+
			"\r\n";
		Assertions.assertEquals(expectedOutput, outContent.toString());
		System.setOut(originalOut);
		System.setErr(originalErr);
	}

	@Test
	public void testSearchProdukt() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
		dataModel.searchProdukt("iP");
		String expectedOutput = 
			"ID             : 203\r\n"+
			"Name           : iPad\r\n"+
			"Hersteller-ID  : 200\r\n"+
			"Hersteller-Name: Apple\r\n"+
			"\r\n"+
			"ID             : 204\r\n"+
			"Name           : iPhone\r\n"+
			"Hersteller-ID  : 200\r\n"+
			"Hersteller-Name: Apple\r\n"+
			"\r\n"+
			"ID             : 205\r\n"+
			"Name           : iPad Mini\r\n"+
			"Hersteller-ID  : 200\r\n"+
			"Hersteller-Name: Apple\r\n"+
			"\r\n";
		Assertions.assertEquals(expectedOutput, outContent.toString());
		System.setOut(originalOut);
		System.setErr(originalErr);
	}

	@Test
	public void testPrintProduktNetwork() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
		dataModel.printProduktNetwork(27);
		String expectedOutput = 
			"Google Nexus 5, Google Nexus 7, iPad, iPad Mini, iPhone, MacBook Air, Samsung ChromeBook, Samsung Galaxy 5, Samsung Galaxy Tab 3\r\n";
		Assertions.assertEquals(expectedOutput, outContent.toString());
		System.setOut(originalOut);
		System.setErr(originalErr);
	}

	@Test
	public void testPrintCompanyNetwork() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
		dataModel.printCompanyNetwork(27);
		String expectedOutput = "Apple, Google, Samsung\r\n";
		Assertions.assertEquals(expectedOutput, outContent.toString());
		System.setOut(originalOut);
		System.setErr(originalErr);
	}
}
//*/
