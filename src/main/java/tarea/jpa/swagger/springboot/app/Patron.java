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
@Table(name="patron")
public class Patron {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_patron")
	private int idPatron;
	
	@Column(name="dni_patron", nullable=false, length=9, unique=true)
	private String dniPatron;
	
	@Column(name="nombre_patron", nullable=false, length=20)
	private String nombrePatron;
	
	@Column(name="apellido1_patron", nullable=false, length=20)
	private String apellido1Patron;
	
	@Column(name="apellido2_patron", length=20)
	private String apellido2Patron;
	
	@Column(name="telefono_patron")
	private int telefonoPatron;
	
	@Column(name="email_patron", length=50)
	private String emailPatron;
	
	@OneToMany(mappedBy = "patron")
	private Set<Salida> salidas;	
	
	
	public Patron() {
		
	}

	public Patron(String dniPatron, String nombrePatron, String apellido1Patron, String apellido2Patron,
			int telefonoPatron, String emailPatron) {
		this.dniPatron = dniPatron;
		this.nombrePatron = nombrePatron;
		this.apellido1Patron = apellido1Patron;
		this.apellido2Patron = apellido2Patron;
		this.telefonoPatron = telefonoPatron;
		this.emailPatron = emailPatron;
		this.salidas = new HashSet<Salida>();
	}
	
	public int getIdPatron() {
		return idPatron;
	}

	public void setIdPatron(int idPatron) {
		this.idPatron = idPatron;
	}

	public String getDniPatron() {
		return dniPatron;
	}

	public void setDniPatron(String dniPatron) {
		this.dniPatron = dniPatron;
	}

	public String getNombrePatron() {
		return nombrePatron;
	}

	public void setNombrePatron(String nombrePatron) {
		this.nombrePatron = nombrePatron;
	}

	public String getApellido1Patron() {
		return apellido1Patron;
	}

	public void setApellido1Patron(String apellido1Patron) {
		this.apellido1Patron = apellido1Patron;
	}

	public String getApellido2Patron() {
		return apellido2Patron;
	}

	public void setApellido2Patron(String apellido2Patron) {
		this.apellido2Patron = apellido2Patron;
	}

	public int getTelefonoPatron() {
		return telefonoPatron;
	}

	public void setTelefonoPatron(int telefonoPatron) {
		this.telefonoPatron = telefonoPatron;
	}

	public String getEmailPatron() {
		return emailPatron;
	}

	public void setEmailPatron(String emailPatron) {
		this.emailPatron = emailPatron;
	}

	@Override
	public String toString() {
		return "Patron [idPatron=" + idPatron + ", dniPatron=" + dniPatron + ", nombrePatron=" + nombrePatron
				+ ", apellido1Patron=" + apellido1Patron + ", apellido2Patron=" + apellido2Patron + ", telefonoPatron="
				+ telefonoPatron + ", emailPatron=" + emailPatron + "]";
	}

	
	
	
	
}
