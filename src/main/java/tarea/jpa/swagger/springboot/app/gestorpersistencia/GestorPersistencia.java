package tarea.jpa.swagger.springboot.app.gestorpersistencia;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import tarea.jpa.swagger.springboot.app.Barco;
import tarea.jpa.swagger.springboot.app.Patron;
import tarea.jpa.swagger.springboot.app.Salida;
import tarea.jpa.swagger.springboot.app.Socio;

public class GestorPersistencia {
	EntityManagerFactory fabrica;	;
	
	public GestorPersistencia() {
		this.fabrica = Persistence.createEntityManagerFactory("clubnautico");
	}
	
	public void cerrar() {
		this.fabrica.close();
	}	
	
	//Métodos genéricos
	public <T extends Object> boolean insertar(T objeto) {//T es un tipo genérico
		EntityManager em = this.fabrica.createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		transaccion.begin();
		em.persist(objeto);
		try {
			transaccion.commit();
		} catch(Exception e) {
			return false;
		} finally {
			em.close();
		}
		return true;
	}
	
	public <T extends Object> boolean actualizar(T objeto) {
		EntityManager em = this.fabrica.createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		transaccion.begin();		
		try {
			T objetoMerge = em.merge(objeto);
			em.persist(objetoMerge);
			transaccion.commit();
		} catch(Exception e) {
			return false;
		} finally {
			em.close();
		}
		return true;
	}
	
	public <T extends Object> boolean eliminar(T objeto) {
		EntityManager em = this.fabrica.createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		transaccion.begin();		
		try {
			T objetoMerge = em.merge(objeto);
			em.remove(objetoMerge);
			transaccion.commit();
		} catch(Exception e) {
			System.err.println(e);
			return false;
		} finally {
			em.close();
		}
		return true;
	}
			
	
	//Métodos de socio
	public Socio getSocioId(int id){
		EntityManager em = this.fabrica.createEntityManager();
		Socio socio = em.find(Socio.class, id);
		em.close();
		return socio;
	}
	
	public Socio getSocioDni(String dni) {
		EntityManager em = this.fabrica.createEntityManager();
		@SuppressWarnings("unchecked")
		Socio socio = (Socio) em.createQuery("SELECT s FROM Socio s WHERE s.dniSocio = :dniSocio")//clase y atributo en java
				.setParameter("dniSocio", dni).getSingleResult();
		em.close();
		return socio;
	}
	
	public List<Socio> getTodosSocios(){
		EntityManager em = this.fabrica.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Socio> cqSocio = cb.createQuery(Socio.class);
		Root<Socio> rootSocio = cqSocio.from(Socio.class);
		
		cqSocio.select(rootSocio);		
		List<Socio> socios = em.createQuery(cqSocio).getResultList();
		return socios;
	}
	
	
	//Métodos de barco
	public Barco getBarcoId(int id){
		EntityManager em = this.fabrica.createEntityManager();
		Barco barco = em.find(Barco.class, id);
		em.close();
		return barco;
	}
	
	public List<Barco> getTodosBarcos(){
		EntityManager em = this.fabrica.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Barco> cqBarco = cb.createQuery(Barco.class);
		Root<Barco> rootBarco = cqBarco.from(Barco.class);
		
		cqBarco.select(rootBarco);		
		List<Barco> barcos = em.createQuery(cqBarco).getResultList();
		return barcos;
	}
	
	public List<Barco> getBarcosCuotaInferiorA(double cuota){
		EntityManager em = this.fabrica.createEntityManager();
		@SuppressWarnings("unchecked")
		List<Barco> barcos = em.createQuery("SELECT b FROM Barco b WHERE b.cuota < :cuotaInferior")
				.setParameter("cuotaInferior", cuota).getResultList();
		em.close();		
		return barcos;
	}
	
	
	//Métodos de patrón
	public Patron getPatronId(int id){
		EntityManager em = this.fabrica.createEntityManager();
		Patron patron = em.find(Patron.class, id);
		em.close();
		return patron;
	}
	
	public Patron getPatronDni(String dni) {
		EntityManager em = this.fabrica.createEntityManager();
		Patron patron = (Patron) em.createQuery("SELECT p FROM Patron p WHERE p.dniPatron = :dniPatron")
				.setParameter("dniPatron", dni).getSingleResult();
		em.close();
		return patron;
	}
	
	public List<Patron> getTodosPatrones(){
		EntityManager em = this.fabrica.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Patron> cqPatron = cb.createQuery(Patron.class);
		Root<Patron> rootPatron = cqPatron.from(Patron.class);
		
		cqPatron.select(rootPatron);		
		List<Patron> patrones = em.createQuery(cqPatron).getResultList();
		return patrones;
	}
	
	
	//Método de salida
	public Salida getSalidaId(int id){
		EntityManager em = this.fabrica.createEntityManager();
		Salida salida = em.find(Salida.class, id);
		em.close();
		return salida;
	}
	
	public List<Salida> getTodasSalidas(){
		EntityManager em = this.fabrica.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Salida> cqSalida = cb.createQuery(Salida.class);
		Root<Salida> rootSalida = cqSalida.from(Salida.class);
		
		cqSalida.select(rootSalida);		
		List<Salida> salidas = em.createQuery(cqSalida).getResultList();
		return salidas;
	}

	public List<Salida> getSalidasDestino(String destino) {
		EntityManager em = this.fabrica.createEntityManager();
		@SuppressWarnings("unchecked")
		List<Salida> salidas = em.createQuery("SELECT s FROM Salida s WHERE s.destino = :destinoElegido")
				.setParameter("destinoElegido", destino).getResultList();
		em.close();
		return salidas;
	}
	
	
}
