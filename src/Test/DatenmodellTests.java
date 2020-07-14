package Test;

import Application.Datenmodell;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class DatenmodellTests {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private Datenmodell dataModel = new Datenmodell();
	private String s = System.lineSeparator();

	public DatenmodellTests() {
		dataModel.readDataFromFile("movieproject2020.db");
	}

	/*
	 * If the following tests are working, than we have not to test the import,
	 * because this tests depend on the import. If the import is false than
	 * this tests wil also fail
	 */

	@BeforeEach
	public void before() {
		System.setOut(new PrintStream(outContent));
	}

	@AfterEach
	public void after() {
		System.setOut(originalOut);
	}

	@Test
	public void testSearchPerson() {
		dataModel.searchPerson("Roger");
		String expectedOutput = 
			"ID:                              53"+s+
			"Name:                            Jaime Rogers"+s+
			"Geschlecht:                      Male"+s+
			"Anzahl der Freunde:              2"+s+
			"Anzahl der gekauften Produkte:   1"+s+
			s+
			"ID:                              70"+s+
			"Name:                            Roger Walker"+s+
			"Geschlecht:                      Male"+s+
			"Anzahl der Freunde:              3"+s+
			"Anzahl der gekauften Produkte:   1"+s+
			s;
		Assertions.assertEquals(expectedOutput, outContent.toString());
	}

	@Test
	public void testSearchProdukt() {
		dataModel.searchProdukt("iP");
		String expectedOutput = 
			"ID             : 203"+s+
			"Name           : iPad"+s+
			"Hersteller-ID  : 200"+s+
			"Hersteller-Name: Apple"+s+
			s+
			"ID             : 204"+s+
			"Name           : iPhone"+s+
			"Hersteller-ID  : 200"+s+
			"Hersteller-Name: Apple"+s+
			s+
			"ID             : 205"+s+
			"Name           : iPad Mini"+s+
			"Hersteller-ID  : 200"+s+
			"Hersteller-Name: Apple"+s+
			s;
		Assertions.assertEquals(expectedOutput, outContent.toString());
	}

	@Test
	public void testPrintProduktNetwork() {
		dataModel.printProduktNetwork(27);
		String expectedOutput = 
			"Google Nexus 7,iPad,iPad Mini,iPhone,MacBook Air,Samsung ChromeBook,Samsung Galaxy 5,Samsung Galaxy Tab 3"+s;
		Assertions.assertEquals(expectedOutput, outContent.toString());
	}

	@Test
	public void testPrintCompanyNetwork() {
		dataModel.printCompanyNetwork(45);
		String expectedOutput = "Apple,Google,Samsung"+s;
		Assertions.assertEquals(expectedOutput, outContent.toString());
	}
}
