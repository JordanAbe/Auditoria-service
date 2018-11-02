package pe.edu.upc.tp.auditoria.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the planauditoria_procedimiento database table.
 * 
 */
@Entity
@Table(name="tbl_planauditoria_procedimiento")
@NamedQuery(name="PlanauditoriaProcedimiento.findAll", query="SELECT p FROM PlanauditoriaProcedimientoModel p")
public class PlanauditoriaProcedimientoModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String comentario;

	private String estado;

	@Column(name="planauditoria_id")
	private int planauditoriaId;

	@OneToOne
	@JoinColumn(name="procedimiento_id")
	private ProcedimientoModel procedimiento;

	public PlanauditoriaProcedimientoModel() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public ProcedimientoModel getProcedimiento() {
		return this.procedimiento;
	}

	public void setProcedimiento(ProcedimientoModel procedimiento) {
		this.procedimiento = procedimiento;
	}

	public int getPlanauditoriaId() {
		return planauditoriaId;
	}

	public void setPlanauditoriaId(int planauditoriaId) {
		this.planauditoriaId = planauditoriaId;
	}

}