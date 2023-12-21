package tarea.jpa.swagger.springboot.app.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.authority.AuthorityUtils;

import org.springframework.security.core.GrantedAuthority;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import tarea.jpa.swagger.springboot.app.Socio;
import tarea.jpa.swagger.springboot.app.gestorpersistencia.GestorPersistencia;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController()
public class SocioController {
	
	private ArrayList<Socio> socios = new ArrayList<Socio>();

	@Operation(summary = "Crea un socio")
	@PostMapping("/crear")
	@ResponseStatus(value = HttpStatus.CREATED)//Para devolver 201 cuando añade
	public Socio crearSocio(@RequestBody Socio socio) {
		GestorPersistencia gp = new GestorPersistencia();
		socios.add(socio);
		gp.insertar(socio);
		return socio;
	}
	
	
	
	@Operation(summary = "Devuelve un socio según su ID")
	@GetMapping("/obtener/{id}")
	public Socio obtenerSocio(@PathVariable("id") int id) {
		GestorPersistencia gp = new GestorPersistencia();
		Socio socio = gp.getSocioId(id);
		if (socio != null) {
			return socio;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Socio no encontrado");
		}
			
	}	
	
	
	
	@Operation(summary = "Devuelve todos los socios")
	@GetMapping("/obtener")
	public List<Socio> obtenerSocios() {
		GestorPersistencia gp = new GestorPersistencia();
		List<Socio> socios = gp.getTodosSocios();
		if (socios != null) {
			return socios;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Socio no encontrado");
		}
			
	}	
	
	@Operation(summary = "Elimina un socio")
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)//Devolver 204 si lo borra bien
	public Socio eliminarSocio(@PathVariable("id") int id) {
		GestorPersistencia gp = new GestorPersistencia();
		Socio socioBD = gp.getSocioId(id);
		gp.eliminar(socioBD);
		if (socioBD != null) {
			return socioBD;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Socio no encontrado");
		}
		
	} 
	
	
	@Operation(summary = "Modifica un socio según su ID")
	@PutMapping("/editar/{id}")
	public Socio editarSocio(@PathVariable("id") int id, @RequestBody Socio socio) {
		GestorPersistencia gp = new GestorPersistencia();
		Socio socioBD = gp.getSocioId(id);
		if (socioBD == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Socio no encontrado");
		}
		gp.actualizar(socio);
		return socio;
		
		
	}
	
}
