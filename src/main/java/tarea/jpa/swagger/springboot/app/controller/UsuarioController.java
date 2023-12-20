package tarea.jpa.swagger.springboot.app.controller;

import java.util.Arrays;
import java.util.Date;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.server.ResponseStatusException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.v3.oas.annotations.Operation;
import tarea.jpa.swagger.springboot.app.Socio;
import tarea.jpa.swagger.springboot.app.Usuario;
import tarea.jpa.swagger.springboot.app.gestorpersistencia.GestorPersistencia;

@RestController()
public class UsuarioController {

	
	//Token
	@Operation(summary = "Inicio de sesión")
	@PostMapping("/login")
	public Usuario login(@RequestBody Usuario body) {

		GestorPersistencia gp = new GestorPersistencia();
		Usuario usuario = gp.getUsuarioEmail(body.getEmail());
		if (usuario != null) {
			
			if(usuario.getContrasena().equals(body.getContrasena())) {
				String token = getJWTToken(body.getEmail());				
				usuario.setToken(token);
				return usuario;
			} else {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Contraseña incorrecta");
			}
						
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Socio no encontrado");
		}
			
	}
	
	private String getJWTToken(String dni) {
		String secretKey = "tFKMnCBLXb57DFOscYtV6NcAq8WCJZFktFKMnCBLXb57DFOscYtV6NcAq8WCJZFk";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(dni)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}
