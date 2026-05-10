package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileOutputStream;

import org.junit.jupiter.api.Test;

import domain.Forest;
import domain.ForestException;

class TestLab06 {

	@Test
	public void shouldTrhowAnExceptionIfFileIsCorrupt() throws Exception{
		File file = File.createTempFile("forest_corrupto", ".dat"); //Linea sugerida por IA.
		file.deleteOnExit();
		
		try(FileOutputStream fos = new FileOutputStream(file)){
			fos.write("Archivo inválido.".getBytes());
		}
		try {
			Forest.open02(file);
			fail("Se esperaba una excepción de tipo ForestException");
		} catch(ForestException fe) {
			assertEquals(fe.getMessage(), ForestException.CORRUPT_FILE);
		}
	}
	
	@Test
	public void shouldThrowFileNotFoundIfFileIsNotCreated() {
		File file = new File("archivo_no_Existe.dat");
		try {
			Forest.open02(file);
			fail("Se deberia lanzar una excepción por archivo no encontrado");
		} catch(ForestException fe) {
			assertEquals(fe.getMessage(), ForestException.FILE_NO_FOUND);
		}
	}
	
	@Test
    public void saveAsShouldThrowFileNotFoundOnInvalidPath() {
        File file = new File("carpetaAleatoria/forest.dat");
        Forest forest = new Forest();
        try {
            forest.saveAs02(file);
            fail("Se esperaba ForestException por path invalido");
        } catch (ForestException ex) {
            assertTrue(ex.getMessage().startsWith(ForestException.FILE_NOT_FOUND));
        }
    }
	
	 @Test
	    public void saveAsShouldCreateFileOnDisk() throws Exception {
	        File file = File.createTempFile("save_exists", ".dat");
	        file.deleteOnExit();
	        Forest forest = new Forest();
	        try {
	            forest.saveAs02(file);
	            assertTrue(file.exists());
	            assertTrue(file.length() > 0);
	        } catch (ForestException ex) {
	            fail("No se esperaba excepción al guardar");
	        }
	    }

}
