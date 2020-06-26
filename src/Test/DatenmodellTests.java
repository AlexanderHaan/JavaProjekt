///*
package Test;

import Application.Datenmodell;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class DatenmodellTests {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	private Datenmodell dataModel = new Datenmodell();

	public DatenmodellTests() {
		dataModel.readDataFromFile("datenTest.db");
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
		dataModel.searchPerson("Alex");
		//Path path;
		//String expectedOutput = Files.readString(path);
	}
}
//*/
