package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import domain.Forest;
import domain.ForestException;

class ImportTest {

	@Test
	void shouldCantImportSomeThingOfText() {
		Forest forest = new Forest(); 
		try {
			File fileProof = new File("src/pruebaImport.txt");
			forest.importFile(fileProof);
			fail();
		} catch (ForestException e) {
			assertEquals(e.getMessage(), ForestException.CANT_IMPORT_THAT);
		} catch (IOException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	void shouldCantCreateAForest() {
		Forest forest = new Forest(); 
		try {
			File fileProof = new File("src/pruebaImport2.txt");
			forest.importFile(fileProof);
			fail();
		} catch (ForestException e) {
			assertEquals(e.getMessage(), ForestException.CANT_CREATE_FOREST);
		} catch (IOException e) {
			fail();
			e.printStackTrace();
		}
	}
	
}
