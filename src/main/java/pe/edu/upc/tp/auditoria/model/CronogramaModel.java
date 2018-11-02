package pe.edu.upc.tp.auditoria.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the cronograma database table.
 * 
 */
@Entity
@Table(name = "tbl_cronograma")
@NamedQuery(name="Cronograma.findAll", query="SELECT c FROM CronogramaModel c")
public class CronogramaModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cronograma_id")
	private int cronogramaId;

	private int etapa;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name="planauditoria_id")
	private int planauditoriaId;
	
	@Transient
	private int index;
	
	@Transient
	private String indexString;
	
	@Transient
	private String etapaString;

	public CronogramaModel() {
	}

	public int getCronogramaId() {
		return this.cronogramaId;
	}

	public void setCronogramaId(int cronogramaId) {
		this.cronogramaId = cronogramaId;
	}

	public int getEtapa() {
		return this.etapa;
	}

	public void setEtapa(int etapa) {
		this.etapa = etapa;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getPlanauditoriaId() {
		return this.planauditoriaId;
	}

	public void setPlanauditoriaId(int planauditoriaId) {
		this.planauditoriaId = planauditoriaId;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getEtapaString() {
		return etapaString;
	}

	public void setEtapaString(String etapaString) {
		this.etapaString = etapaString;
	}

	public String getIndexString() {
		return indexString;
	}

	public void setIndexString(String indexString) {
		this.indexString = indexString;
	}

}