package tarea.rest.springboot.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import tarea.jpa.swagger.springboot.app.Barco;
import tarea.jpa.swagger.springboot.app.Socio;
import tarea.jpa.swagger.springboot.app.gestorpersistencia.GestorPersistencia;

class TestBarco {

	static Socio ejemploS = new Socio ("12345678A", "Pepe", "Pérez", "Rodríguez", 666666666, "pepe@gmail.com");
	
	static Barco ejemplo1 = new Barco("123456ABC", "Perla Negra", 34, 90.5);
	static Barco ejemplo2 = new Barco("999456ABC", "Holandés Errante", 23, 107.3);
	static Barco ejemplo3 = new Barco("129956ABC", "Nautilus", 11, 75.2);
	
	@BeforeAll
	static void insertarBarcosEjemplo() {
		GestorPersistencia gp = new GestorPersistencia();
		gp.insertar(ejemploS);
		Socio socioBD = gp.getSocioId(ejemploS.getIdSocio());
		ejemplo1.setSocio(socioBD);
		ejemplo2.setSocio(socioBD);
		ejemplo3.setSocio(socioBD);
		gp.insertar(ejemplo1);
		gp.insertar(ejemplo2);
		gp.insertar(ejemplo3);
	}

	@AfterAll
	static void eliminarBarcosEjemplo() {
		GestorPersistencia gp = new GestorPersistencia();		
		gp.eliminar(ejemplo1);
		gp.eliminar(ejemplo2);
		gp.eliminar(ejemplo3);
		Socio socioBD = gp.getSocioId(ejemploS.getIdSocio());
		gp.eliminar(socioBD);
	}

	@Test
	void testInsertar() {
		GestorPersistencia gp = new GestorPersistencia();
		Socio socio = new Socio ("10345998A", "Sara", "Pérez", "Rodríguez", 666666666, "sara@gmail.com");
		gp.insertar(socio);
		
		Barco barco = new Barco("334675ABC", "Titanic", 67, 55.25); 
		Socio socioBD = gp.getSocioId(socio.getIdSocio());
		barco.setSocio(socioBD);
		gp.insertar(barco);
		
		Barco barcoBD = gp.getBarcoId(barco.getIdBarco());
		assertNotNull(barcoBD);
		gp.eliminar(barcoBD);
		gp.eliminar(socioBD);
	}

	@Test
	void testActualizar() {
		GestorPersistencia gp = new GestorPersistencia();
		ejemplo1 = gp.getBarcoId(ejemplo1.getIdBarco());
		ejemplo1.setNumAmarre(56);
		gp.actualizar(ejemplo1);
		Barco barcoBD = gp.getBarcoId(ejemplo1.getIdBarco());
		assertEquals(56, barcoBD.getNumAmarre());
	}

	@Test
	void testEliminar() {
		GestorPersistencia gp = new GestorPersistencia();
		Socio socio = new Socio ("10345678A", "María", "Pérez", "Rodríguez", 666666666, "mariaperez@gmail.com");
		gp.insertar(socio);
		
		Barco barco = new Barco("334455ABC", "Titanic", 14, 55.25); 
		Socio socioBD = gp.getSocioId(socio.getIdSocio());
		barco.setSocio(socioBD);
		gp.insertar(barco);
		
		Barco barcoBD = gp.getBarcoId(barco.getIdBarco());
		gp.eliminar(barcoBD);
		
		barcoBD = gp.getBarcoId(barcoBD.getIdBarco());
		assertNull(barcoBD);
		
		gp.eliminar(socioBD);
	}

	@Test
	void testGetTodosBarcos() {
		GestorPersistencia gp = new GestorPersistencia();
		List<Barco> barcosBD = gp.getTodosBarcos();
		assertTrue(barcosBD.size() == 3);
	}

	@Test
	void testGetBarcosCuotaInferiorA() {
		GestorPersistencia gp = new GestorPersistencia();
		List<Barco> barcosBD = gp.getBarcosCuotaInferiorA(100.0);
		assertTrue(barcosBD.size() == 2);
	}

}
