package tarea.rest.springboot.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import tarea.jpa.swagger.springboot.app.Barco;
import tarea.jpa.swagger.springboot.app.Patron;
import tarea.jpa.swagger.springboot.app.Salida;
import tarea.jpa.swagger.springboot.app.Socio;
import tarea.jpa.swagger.springboot.app.gestorpersistencia.GestorPersistencia;

class TestSalida {
	
	static Socio ejemploS = new Socio ("12345678A", "Pepe", "Pérez", "Rodríguez", 666666666, "pepe@gmail.com");
	
	static Barco ejemploB = new Barco("123456ABC", "Perla Negra", 34, 90.5);
	
	static Patron ejemploP = new Patron("23222789C", "Mario", "García", "Pérez", 666555222, "mariopatron@gmail.com");
	
	static Salida ejemplo1 = new Salida(LocalDateTime.parse("2023-03-15T13:30:00"), "Gran Canaria");
	static Salida ejemplo2 = new Salida(LocalDateTime.parse("2023-04-06T20:17:30"), "Tenerife");
	static Salida ejemplo3 = new Salida(LocalDateTime.parse("2023-03-29T09:45:00"), "Gran Canaria");

	@BeforeAll
	static void insertarSalidasEjemplo() {
		GestorPersistencia gp = new GestorPersistencia();
		gp.insertar(ejemploS);//insertamos el socio en la bd
		Socio socioBD = gp.getSocioId(ejemploS.getIdSocio());//traemos el socio de la bd a una variable
		ejemploB.setSocio(socioBD);//le añadimos el socio de la bd al barco
		gp.insertar(ejemploB);//insertamos el barco en la bd
		gp.insertar(ejemploP);//insertamos el patron en la bd
		ejemplo1.setBarco(ejemploB);//insertamos barco y patron en cada salida
		ejemplo1.setPatron(ejemploP);
		ejemplo2.setBarco(ejemploB);
		ejemplo2.setPatron(ejemploP);
		ejemplo3.setBarco(ejemploB);
		ejemplo3.setPatron(ejemploP);
		gp.insertar(ejemplo1);//insertamos las salidas en la bd
		gp.insertar(ejemplo2);
		gp.insertar(ejemplo3);		
	}

	@AfterAll
	static void eliminarSalidasEjemplo() {
		GestorPersistencia gp = new GestorPersistencia();		
		gp.eliminar(ejemplo1);
		gp.eliminar(ejemplo2);
		gp.eliminar(ejemplo3);
		Barco barcoBD = gp.getBarcoId(ejemploB.getIdBarco());
		gp.eliminar(barcoBD);		
		Socio socioBD = gp.getSocioId(ejemploS.getIdSocio());
		gp.eliminar(socioBD);
		Patron patronBD = gp.getPatronId(ejemploP.getIdPatron());
		gp.eliminar(patronBD);
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
		
		Patron patron = new Patron("12345678Z", "María", "Pérez", "Rodríguez", 666666666, "mariaperez@gmail.com");
		gp.insertar(patron);
				
		Salida salida = new Salida(LocalDateTime.parse("2023-02-28T14:30:00"), "Tenerife");
		Barco barcoBD = gp.getBarcoId(barco.getIdBarco());
		salida.setBarco(barcoBD);
		Patron patronBD = gp.getPatronId(patron.getIdPatron());
		salida.setPatron(patronBD);
		gp.insertar(salida);
		
		Salida salidaBD = gp.getSalidaId(salida.getIdSalida());
		assertNotNull(salidaBD);
		
		gp.eliminar(salidaBD);
		gp.eliminar(barcoBD);
		gp.eliminar(socioBD);
		gp.eliminar(patronBD);
	}

	@Test
	void testActualizar() {
		GestorPersistencia gp = new GestorPersistencia();
		ejemplo1 = gp.getSalidaId(ejemplo1.getIdSalida());
		ejemplo1.setDestino("Cádiz");
		gp.actualizar(ejemplo1);
		Salida salidaBD = gp.getSalidaId(ejemplo1.getIdSalida());
		assertEquals("Cádiz", salidaBD.getDestino());
	}

	@Test
	void testEliminar() {
		GestorPersistencia gp = new GestorPersistencia();
		Socio socio = new Socio ("10345998A", "Sara", "Pérez", "Rodríguez", 666666666, "sara@gmail.com");
		gp.insertar(socio);
		
		Barco barco = new Barco("334675ABC", "Titanic", 67, 55.25); 
		Socio socioBD = gp.getSocioId(socio.getIdSocio());
		barco.setSocio(socioBD);
		gp.insertar(barco);
		
		Patron patron = new Patron("12345678Z", "María", "Pérez", "Rodríguez", 666666666, "mariaperez@gmail.com");
		gp.insertar(patron);
				
		Salida salida = new Salida(LocalDateTime.parse("2023-02-28T14:30:00"), "Tenerife");
		Barco barcoBD = gp.getBarcoId(barco.getIdBarco());
		salida.setBarco(barcoBD);
		Patron patronBD = gp.getPatronId(patron.getIdPatron());
		salida.setPatron(patronBD);
		gp.insertar(salida);
		
		gp.eliminar(salida);
		Salida salidaBD = gp.getSalidaId(salida.getIdSalida());
		assertNull(salidaBD);
		
		gp.eliminar(barcoBD);
		gp.eliminar(socioBD);
		gp.eliminar(patronBD);
	}

	@Test
	void testGetTodasSalidas() {
		GestorPersistencia gp = new GestorPersistencia();
		List<Salida> salidasBD = gp.getTodasSalidas();
		assertTrue(salidasBD.size() == 3);
	}

	@Test
	void testGetSalidasDestino() {
		GestorPersistencia gp = new GestorPersistencia();
		List<Salida> salidasBD = gp.getSalidasDestino("Gran Canaria");
		assertTrue(salidasBD.size() == 2);
	}

}
