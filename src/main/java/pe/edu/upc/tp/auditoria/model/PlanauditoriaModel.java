package pe.edu.upc.tp.auditoria.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the planauditoria database table.
 * 
 */
@Entity
@Table(name="tbl_planauditoria")
@NamedQuery(name="Planauditoria.findAll", query="SELECT p FROM PlanauditoriaModel p")
public class PlanauditoriaModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String alcance;

	private String descripcion;

	private String estado;
	
	private int duracion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_fin")
	private Date fechaFin;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inicio")
	private Date fechaInicio;

	private String objetivo;

	@Column(name="planauditoriaanual_id")
	private int planauditoriaanualId;

	@OneToOne
	@JoinColumn(name = "proceso_id")
	private ProcesoModel proceso;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "planauditoria_id")
	private List<PlanauditoriaAuditorModel> planauditoriaAuditores;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "planauditoria_id")
	private List<CronogramaModel> cronogramas;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "planauditoria_id")
	private List<PlanauditoriaProcedimientoModel> procedimientos;

	public PlanauditoriaModel() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAlcance() {
		return this.alcance;
	}

	public void setAlcance(String alcance) {
		this.alcance = alcance;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getObjetivo() {
		return this.objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public int getPlanauditoriaanualId() {
		return this.planauditoriaanualId;
	}

	public void setPlanauditoriaanualId(int planauditoriaanualId) {
		this.planauditoriaanualId = planauditoriaanualId;
	}

	public ProcesoModel getProceso() {
		return proceso;
	}

	public void setProceso(ProcesoModel proceso) {
		this.proceso = proceso;
	}

	public List<PlanauditoriaAuditorModel> getPlanauditoriaAuditores() {
		return planauditoriaAuditores;
	}

	public void setPlanauditoriaAuditores(List<PlanauditoriaAuditorModel> planauditoriaAuditores) {
		this.planauditoriaAuditores = planauditoriaAuditores;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public List<CronogramaModel> getCronogramas() {
		return cronogramas;
	}

	public void setCronogramas(List<CronogramaModel> cronogramas) {
		this.cronogramas = cronogramas;
	}

	public List<PlanauditoriaProcedimientoModel> getProcedimientos() {
		return procedimientos;
	}

	public void setProcedimientos(List<PlanauditoriaProcedimientoModel> procedimientos) {
		this.procedimientos = procedimientos;
	}

}