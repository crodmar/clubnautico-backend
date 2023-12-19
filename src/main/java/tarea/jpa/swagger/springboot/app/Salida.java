package tarea.jpa.swagger.springboot.app;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="salida")
public class Salida {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_salida")
	private int idSalida;
	
	@Column(name="fecha_hora", nullable = false)
	private LocalDateTime fechaHora;
	
	@Column(length=20, nullable = false)
	private String destino;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="id_patron")
	private Patron patron;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="id_barco")
	private Barco barco;
	
	public Salida() {
		
	}

	public Salida(LocalDateTime fechaHora, String destino) {
		this.fechaHora = fechaHora;
		this.destino = destino;
		
	}

	public int getIdSalida() {
		return idSalida;
	}

	public void setIdSalida(int idSalida) {
		this.idSalida = idSalida;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Patron getPatron() {
		return patron;
	}

	public void setPatron(Patron patron) {
		this.patron = patron;
	}

	public Barco getBarco() {
		return barco;
	}

	public void setBarco(Barco barco) {
		this.barco = barco;
	}

	@Override
	public String toString() {
		return "Salida [idSalida=" + idSalida + ", fechaHora=" + fechaHora + ", destino=" + destino + ", patron="
				+ patron + ", barco=" + barco + "]";
	}
	
	
	
}
