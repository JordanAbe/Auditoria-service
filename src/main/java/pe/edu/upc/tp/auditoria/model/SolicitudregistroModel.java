package pe.edu.upc.tp.auditoria.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbl_solicitudregistro")
public class SolicitudregistroModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	private String asunto;
	private String motivo;
	private String prioridad;
	private String periodo;
	private int estadoid;
	private Date fecharegistro;
	private Date fechaevaluacion;
	@OneToOne
	@JoinColumn(name="proceso_id")
	private ProcesoModel proceso;
	@OneToOne
	@JoinColumn(name="solicitante_id")
	private EmpleadoModel solicitante;
	@OneToOne
	@JoinColumn(name="evaluador_id")
	private EmpleadoModel evaluador;
	private int actareunionId;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}
	public int getEstadoid() {
		return estadoid;
	}
	public void setEstadoid(int estadoid) {
		this.estadoid = estadoid;
	}
	public Date getFecharegistro() {
		return fecharegistro;
	}
	public void setFecharegistro(Date fecharegistro) {
		this.fecharegistro = fecharegistro;
	}
	public Date getFechaevaluacion() {
		return fechaevaluacion;
	}
	public void setFechaevaluacion(Date fechaevaluacion) {
		this.fechaevaluacion = fechaevaluacion;
	}
	public ProcesoModel getProceso() {
		return proceso;
	}
	public void setProceso(ProcesoModel proceso) {
		this.proceso = proceso;
	}
	public EmpleadoModel getSolicitante() {
		return solicitante;
	}
	public void setSolicitante(EmpleadoModel solicitante) {
		this.solicitante = solicitante;
	}
	public EmpleadoModel getEvaluador() {
		return evaluador;
	}
	public void setEvaluador(EmpleadoModel evaluador) {
		this.evaluador = evaluador;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public int getActareunionId() {
		return actareunionId;
	}

	public void setActareunionId(int actareunionId) {
		this.actareunionId = actareunionId;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
}
