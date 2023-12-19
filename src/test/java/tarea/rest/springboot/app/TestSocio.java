package tarea.rest.springboot.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import tarea.jpa.swagger.springboot.app.Socio;
import tarea.jpa.swagger.springboot.app.gestorpersistencia.GestorPersistencia;

class TestSocio {
	
	
	static Socio ejemplo1 =  new Socio("87654321B", "Juan", "Sánchez", "García", 666666612, "juansanchez123@gmail.com");
	static Socio ejemplo2 =  new Socio("87655521C", "Carlos", "Martín", "Ballester", 666666634, "carlos1234@gmail.com");
	static Socio ejemplo3 =  new Socio("12654321D", "Sandra", "Expósito", "García", 666666656, "sandraeg123@gmail.com");
	
	@BeforeAll
	static void insertarSociosEjemplo() {
		GestorPersistencia gp = new GestorPersistencia();		
		gp.insertar(ejemplo1);		
		gp.insertar(ejemplo2);		
		gp.insertar(ejemplo3);
	}
	
	@AfterAll
	static void eliminarSociosEjemplo() {
		GestorPersistencia gp = new GestorPersistencia();		
		gp.eliminar(ejemplo1);		
		gp.eliminar(ejemplo2);		
		gp.eliminar(ejemplo3);
	}
	
	

	@Test
	void testInsertar() {//Para comprobar la inserción, eliminamos después el socio para poder hacer el test varias veces
		GestorPersistencia gp = new GestorPersistencia();
		Socio nuevoSocio =  new Socio("12345678A", "María", "Pérez", "Rodríguez", 666666666, "mariaperez@gmail.com");
		gp.insertar(nuevoSocio);
		Socio socioBD = gp.getSocioId(nuevoSocio.getIdSocio());
		assertNotNull(socioBD);//Comprobamos que el socio no sea null (está en la BD), si lo es da failure
		gp.eliminar(socioBD);
	}

	@Test
	void testActualizar() {
		GestorPersistencia gp = new GestorPersistencia();
		ejemplo1 = gp.getSocioId(ejemplo1.getIdSocio());
		ejemplo1.setTelefonoSocio(666666660);
		gp.actualizar(ejemplo1);
		Socio socioBD = gp.getSocioId(ejemplo1.getIdSocio());
		assertEquals(666666660, socioBD.getTelefonoSocio());
	}

	@Test
	void testEliminar() {
		GestorPersistencia gp = new GestorPersistencia();
		Socio nuevoSocio =  new Socio("12345678A", "María", "Pérez", "Rodríguez", 666666666, "mariaperez@gmail.com");
		gp.insertar(nuevoSocio);
		Socio socioBD = gp.getSocioId(nuevoSocio.getIdSocio());
		gp.eliminar(socioBD);
		
		socioBD = gp.getSocioId(socioBD.getIdSocio());
		assertNull(socioBD);
	}

	
	@Test
	void testGetSocioDni() {
		GestorPersistencia gp = new GestorPersistencia();
		Socio socioBD = gp.getSocioDni("87655521C");
		assertEquals(ejemplo2.getIdSocio(), socioBD.getIdSocio());
	}	
	
	@Test
	void testGetTodosSocios(){
		GestorPersistencia gp = new GestorPersistencia();
		List<Socio> sociosBD = gp.getTodosSocios();
		assertTrue(sociosBD.size() == 3);
	}
	
	
}
