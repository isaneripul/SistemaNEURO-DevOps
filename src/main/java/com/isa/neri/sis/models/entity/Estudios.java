package com.isa.neri.sis.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "estudios_lab")
public class Estudios implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SEQ_ESTUDIOS", sequenceName = "SEQ_ESTUDIOS", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ESTUDIOS")	
	private Integer idestudio;
	
	@NotEmpty
	@Column(name="ESTUDIO")
	private String estudio;

	@NotEmpty
	@Column(name="UNIDADMED")
	private String unidad; // Unidad de Medida
	
	@NotEmpty
	@Column(name="VALREF") // Valor de Referencia
	private String valor;
	
	
	public Integer getIdestudio() {
		return idestudio;
	}

	public void setIdestudio(Integer idestudio) {
		this.idestudio = idestudio;
	}

	public String getEstudio() {
		return estudio;
	}

	public void setEstudio(String estudio) {
		this.estudio = estudio;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Estudios [idestudio=" + idestudio + ", estudio=" + estudio + ", unidad=" + unidad + ", valor=" + valor
				+ "]";
	}

	
	
}
