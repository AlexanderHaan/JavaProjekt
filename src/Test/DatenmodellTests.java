///*
package Test;

import Application.Datenmodell;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatenmodellTests {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	private Datenmodell dataModel = new Datenmodell();

	public DatenmodellTests() {
		dataModel.readDataFromFile("movieproject2020.db");
	}

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@After
	public void restoreStreams() {
		System.setOut(originalOut);
		System.setErr(originalErr);
	}

	@Test
	public void testSearchPerson() {
		dataModel.searchPerson("Roger");
		String expectedOutput = 
			"ID:                              53\n"+
			"Name:                            Jaime Rogers\n"+
			"Geschlecht:                      Male\n"+
			"Anzahl der Freunde:              4\n"+
			"Anzahl der gekauften Produkte:   1\n"+
			"\n"+
			"ID:                              70\n"+
			"Name:                            Roger Walker\n"+
			"Geschlecht:                      Male\n"+
			"Anzahl der Freunde:              6\n"+
			"Anzahl der gekauften Produkte:   1\n";
		assertEquals(expectedOutput, outContent.toString());
	}
}
//*/
