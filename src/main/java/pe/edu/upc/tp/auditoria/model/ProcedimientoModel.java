package pe.edu.upc.tp.auditoria.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the procedimiento database table.
 * 
 */
@Entity
@Table(name="tbl_procedimiento")
@NamedQuery(name="Procedimiento.findAll", query="SELECT p FROM ProcedimientoModel p")
public class ProcedimientoModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="procedimiento_id")
	private int procedimientoId;

	private String descripcion;

	private int etapa;

	public ProcedimientoModel() {
	}
	public ProcedimientoModel(int id) {
		this.procedimientoId = id;
	}

	public int getProcedimientoId() {
		return this.procedimientoId;
	}

	public void setProcedimientoId(int procedimientoId) {
		this.procedimientoId = procedimientoId;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getEtapa() {
		return this.etapa;
	}

	public void setEtapa(int etapa) {
		this.etapa = etapa;
	}

}