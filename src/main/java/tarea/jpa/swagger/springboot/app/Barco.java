package tarea.jpa.swagger.springboot.app;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="barco")
public class Barco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_barco")
	private int idBarco;

	@Column(name="num_matricula", nullable=false, length=9, unique=true)
	private String numMatricula;
	
	@Column(name="nombre_barco", nullable=false, length=20)
	private String nombreBarco;
	
	@Column(name="num_amarre", nullable=false, unique=true)
	private int numAmarre;
	
	@Column(nullable=false)
	private double cuota;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="id_socio")
	private Socio socio;
	
	@OneToMany(mappedBy = "barco")
	private Set<Salida> salidas;	
	
	public Barco() {
		
	}

	public Barco(String numMatricula, String nombreBarco, int numAmarre, double cuota) {
		this.numMatricula = numMatricula;
		this.nombreBarco = nombreBarco;
		this.numAmarre = numAmarre;
		this.cuota = cuota;
		this.salidas = new HashSet<Salida>();
	}

	
	public int getIdBarco() {
		return idBarco;
	}

	public void setIdBarco(int idBarco) {
		this.idBarco = idBarco;
	}

	public String getNumMatricula() {
		return numMatricula;
	}

	public void setNumMatricula(String numMatricula) {
		this.numMatricula = numMatricula;
	}

	public String getNombreBarco() {
		return nombreBarco;
	}

	public void setNombreBarco(String nombreBarco) {
		this.nombreBarco = nombreBarco;
	}

	public int getNumAmarre() {
		return numAmarre;
	}

	public void setNumAmarre(int numAmarre) {
		this.numAmarre = numAmarre;
	}

	public double getCuota() {
		return cuota;
	}

	public void setCuota(double cuota) {
		this.cuota = cuota;
	}

	public Socio getSocio() {
		return socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}

	@Override
	public String toString() {
		return "Barco [idBarco=" + idBarco + ", numMatricula=" + numMatricula + ", nombreBarco=" + nombreBarco
				+ ", numAmarre=" + numAmarre + ", cuota=" + cuota + ", socio=" + socio + "]";
	}

	
	
}
