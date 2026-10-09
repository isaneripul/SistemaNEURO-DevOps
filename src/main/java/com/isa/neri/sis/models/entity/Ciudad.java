package com.isa.neri.sis.models.entity;

import java.io.Serializable;
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
@Table(name="ciudades")
public class Ciudad implements Serializable{
	// Ciudades..
	// Entidad...
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "SEQ_CIUDADES", sequenceName = "SEQ_CIUDADES", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CIUDADES")
	private Integer idciudad;
	
	@NotEmpty
	@Column(name="NOMCIUDAD")
	private String nomciudad;
	               
	@Column(name = "FECHA", updatable = false, nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")	
	private Date  fecha;		
	
	public Integer getIdciudad() {
		return idciudad;
	}
	public void setIdciudad(Integer idciudad) {
		this.idciudad = idciudad;
	}
	public String getNomciudad() {
		return nomciudad;
	}
	public void setNomciudad(String nomciudad) {
		this.nomciudad = nomciudad;
	}		
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	@Override
	public String toString() {
		return "Ciudad [idciudad=" + idciudad + ", nomciudad=" + nomciudad + ", fecha =" + fecha + "]";
	}
}
