///*
package Test;

import Application.Datenmodell;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
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


	@Test
	public void testSearchPerson() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
		dataModel.searchPerson("Roger");
		String expectedOutput = 
			"ID:                              53\r\n"+
			"Name:                            Jaime Rogers\r\n"+
			"Geschlecht:                      Male\r\n"+
			"Anzahl der Freunde:              4\r\n"+
			"Anzahl der gekauften Produkte:   1\r\n"+
			"\r\n"+
			"ID:                              70\r\n"+
			"Name:                            Roger Walker\r\n"+
			"Geschlecht:                      Male\r\n"+
			"Anzahl der Freunde:              6\r\n"+
			"Anzahl der gekauften Produkte:   1\r\n"+
			"\r\n";
		Assertions.assertEquals(expectedOutput, outContent.toString());
		System.setOut(originalOut);
		System.setErr(originalErr);
	}
}
//*/
