package tarea.rest.springboot.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import tarea.jpa.swagger.springboot.app.Patron;
import tarea.jpa.swagger.springboot.app.gestorpersistencia.GestorPersistencia;

class TestPatron {

	static Patron ejemplo1 = new Patron("23456789A", "Rafael", "Rodríguez", "Ballester", 666555222, "rafaelpatron@gmail.com");
	static Patron ejemplo2 = new Patron("23455789B", "Alicia", "Herrera", "Martín", 666555000, "aliciapatrona@gmail.com");
	static Patron ejemplo3 = new Patron("23222789C", "Mario", "García", "Pérez", 666555222, "mariopatron@gmail.com");
	
	@BeforeAll
	static void insertarPatronesEjemplo() {
		GestorPersistencia gp = new GestorPersistencia();		
		gp.insertar(ejemplo1);		
		gp.insertar(ejemplo2);		
		gp.insertar(ejemplo3);
	}

	@AfterAll
	static void eliminarPatronesEjemplo() {
		GestorPersistencia gp = new GestorPersistencia();		
		gp.eliminar(ejemplo1);		
		gp.eliminar(ejemplo2);		
		gp.eliminar(ejemplo3);
	}

	@Test
	void testInsertar() {
		GestorPersistencia gp = new GestorPersistencia();
		Patron nuevoPatron =  new Patron("12345678A", "María", "Pérez", "Rodríguez", 666666666, "mariaperez@gmail.com");
		gp.insertar(nuevoPatron);
		Patron patronBD = gp.getPatronId(nuevoPatron.getIdPatron());
		assertNotNull(patronBD);
		gp.eliminar(patronBD);
	}

	@Test
	void testActualizar() {
		GestorPersistencia gp = new GestorPersistencia();
		ejemplo1 = gp.getPatronId(ejemplo1.getIdPatron());
		ejemplo1.setEmailPatron("rafaelpatron@hotmail.com");
		gp.actualizar(ejemplo1);
		Patron patronBD = gp.getPatronId(ejemplo1.getIdPatron());
		assertEquals("rafaelpatron@hotmail.com", patronBD.getEmailPatron());
	}

	@Test
	void testEliminar() {
		GestorPersistencia gp = new GestorPersistencia();
		Patron nuevoPatron =  new Patron("12345678A", "María", "Pérez", "Rodríguez", 666666666, "mariaperez@gmail.com");
		gp.insertar(nuevoPatron);
		Patron patronBD = gp.getPatronId(nuevoPatron.getIdPatron());
		gp.eliminar(patronBD);
		
		patronBD = gp.getPatronId(patronBD.getIdPatron());
		assertNull(patronBD);
	}

	@Test
	void testGetPatronDni() {
		GestorPersistencia gp = new GestorPersistencia();
		Patron patronBD = gp.getPatronDni("23455789B");
		assertEquals(ejemplo2.getIdPatron(), patronBD.getIdPatron());
	}

	@Test
	void testGetTodosPatrones() {
		GestorPersistencia gp = new GestorPersistencia();
		List<Patron> patronesBD = gp.getTodosPatrones();
		assertTrue(patronesBD.size() == 3);
	}

}
