package tarea.jpa.swagger.springboot.app;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

@Entity
@Table(name="usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private int id;
	
	@Column(name="email_usuario")
	private String email;
	
	@Column(name="contrasena_usuario")
	private String contrasena;
	
	@Transient
	private String token;
	
	public Usuario() {
		
	}
	
	public Usuario(int id, String email, String contrasena) {
		this.id = id;
		this.email = email;
		this.contrasena = contrasena;
	}
	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getContrasena() {
		return contrasena;
	}



	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}



	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
