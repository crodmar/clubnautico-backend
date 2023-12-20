package tarea.jpa.swagger.springboot.app;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="socio")
public class Socio {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_socio")
	private int idSocio;
	
	@Column(name="dni_socio", nullable=false, length=9, unique=true)
	private String dniSocio;
	
	@Column(name="nombre_socio", nullable=false, length=20)
	private String nombreSocio;
	
	@Column(name="apellido1_socio", nullable=false, length=20)
	private String apellido1Socio;
	
	@Column(name="apellido2_socio", length=20)
	private String apellido2Socio;
	
	@Column(name="telefono_socio")
	private int telefonoSocio;
	
	@Column(name="email_socio", length=50)
	private String emailSocio;
	
	@OneToMany(mappedBy = "socio")
	private Set<Barco> barcos;	
			
	public Socio() {
		
	}
	
	public Socio(String dniSocio, String nombreSocio, String apellido1Socio, String apellido2Socio,
			int telefonoSocio, String emailSocio, String contrasena) {
		this.dniSocio = dniSocio;
		this.nombreSocio = nombreSocio;
		this.apellido1Socio = apellido1Socio;
		this.apellido2Socio = apellido2Socio;
		this.telefonoSocio = telefonoSocio;
		this.emailSocio = emailSocio;
		this.barcos = new HashSet<Barco>(); 
	}
	
	public int getIdSocio() {
		return idSocio;
	}

	public void setIdSocio(int idSocio) {
		this.idSocio = idSocio;
	}

	public String getDniSocio() {
		return dniSocio;
	}

	public void setDniSocio(String dniSocio) {
		this.dniSocio = dniSocio;
	}

	public String getNombreSocio() {
		return nombreSocio;
	}

	public void setNombreSocio(String nombreSocio) {
		this.nombreSocio = nombreSocio;
	}

	public String getApellido1Socio() {
		return apellido1Socio;
	}

	public void setApellido1Socio(String apellido1Socio) {
		this.apellido1Socio = apellido1Socio;
	}

	public String getApellido2Socio() {
		return apellido2Socio;
	}

	public void setApellido2Socio(String apellido2Socio) {
		this.apellido2Socio = apellido2Socio;
	}

	public int getTelefonoSocio() {
		return telefonoSocio;
	}

	public void setTelefonoSocio(int telefonoSocio) {
		this.telefonoSocio = telefonoSocio;
	}

	public String getEmailSocio() {
		return emailSocio;
	}

	public void setEmailSocio(String emailSocio) {
		this.emailSocio = emailSocio;
	}
	
	
	@Override
	public String toString() {
		return "Socio [idSocio=" + idSocio + ", dniSocio=" + dniSocio + ", nombreSocio=" + nombreSocio
				+ ", apellido1Socio=" + apellido1Socio + ", apellido2Socio=" + apellido2Socio + ", telefonoSocio="
				+ telefonoSocio + ", emailSocio=" + emailSocio + "]";
	}
	
	
	
	
}
