package com.isa.neri.sis.models.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="pacientes")
public class Pacientes {

	@Id
	@SequenceGenerator(name = "SEQ_PACIENTES", sequenceName = "SEQ_PACIENTES", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PACIENTES")
	@Column(name="IDPACIENTE")
	private Integer idpaciente;
	
	@NotEmpty
	@Column(name="NOMBRE")
	private String nombre;
	
	
	@Column(name="APATERNO")
	private String apaterno;
	
	
	@Column(name="AMATERNO")
	private String amaterno;
	
	@NotEmpty
	@Column(name="GENERO")
	private String genero;
	
	@NotEmpty
	@Column(name="EDAD")
	private Integer edad;
	
	@Column(name = "FH_NACIMIENTO", updatable = false, nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")	
	private Date  fhnacimiento;
	
	@Column(name = "FH_ENTRADA", updatable = false, nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")	
	private Date  fhentrada;
	
	@Column(name = "FH_ALTA", updatable = false, nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")	
	private Date  fhalta;
	
	public Integer getIdpaciente() {
		return idpaciente;
	}

	public void setIdpaciente(Integer idpaciente) {
		this.idpaciente = idpaciente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApaterno() {
		return apaterno;
	}

	public void setApaterno(String apaterno) {
		this.apaterno = apaterno;
	}

	public String getAmaterno() {
		return amaterno;
	}

	public void setAmaterno(String amaterno) {
		this.amaterno = amaterno;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Date getFhnacimiento() {
		return fhnacimiento;
	}

	public void setFhnacimiento(Date fhnacimiento) {
		this.fhnacimiento = fhnacimiento;
	}

	public Date getFhentrada() {
		return fhentrada;
	}

	public void setFhentrada(Date fhentrada) {
		this.fhentrada = fhentrada;
	}

	public Date getFhalta() {
		return fhalta;
	}

	public void setFhalta(Date fhalta) {
		this.fhalta = fhalta;
	}

	@Override
	public String toString() {
		return "Pacientes [idpaciente=" + idpaciente + ", nombre=" + nombre + ", apaterno=" + apaterno + ", amaterno="
				+ amaterno + ", genero=" + genero + ", edad=" + edad + ", fhnacimiento=" + fhnacimiento + ", fhentrada="
				+ fhentrada + ", fhalta=" + fhalta + "]";
	}
	
	
}
