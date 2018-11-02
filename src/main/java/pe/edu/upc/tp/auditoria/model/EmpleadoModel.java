package pe.edu.upc.tp.auditoria.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the empleado database table.
 * 
 */
@Entity
@Table(name = "tbl_empleado")
@NamedQuery(name="Empleado.findAll", query="SELECT e FROM EmpleadoModel e")
public class EmpleadoModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="empleado_id")
	private int empleadoId;

	private String apellidos;

	private String cargo;

	private String correo;

	private String direccion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_ingreso")
	private Date fechaIngreso;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento;

	private String nivel;

	private String nombres;

	private String telefono;

	@Transient
	private Date fechaInicioAuditoria;
	@Transient
	private Date fechaFinAuditoria;
	@Transient
	private String fechaInicioAuditoriaString;
	@Transient
	private String fechaFinAuditoriaString;

	public EmpleadoModel() {
	}

	public EmpleadoModel(int id) {
		this.empleadoId = id;
	}

	public int getEmpleadoId() {
		return this.empleadoId;
	}

	public void setEmpleadoId(int empleadoId) {
		this.empleadoId = empleadoId;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCargo() {
		return this.cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNivel() {
		return this.nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Date getFechaInicioAuditoria() {
		return fechaInicioAuditoria;
	}

	public void setFechaInicioAuditoria(Date fechaInicioAuditoria) {
		this.fechaInicioAuditoria = fechaInicioAuditoria;
	}

	public Date getFechaFinAuditoria() {
		return fechaFinAuditoria;
	}

	public void setFechaFinAuditoria(Date fechaFinAuditoria) {
		this.fechaFinAuditoria = fechaFinAuditoria;
	}

	public String getFechaInicioAuditoriaString() {
		return fechaInicioAuditoriaString;
	}

	public void setFechaInicioAuditoriaString(String fechaInicioAuditoriaString) {
		this.fechaInicioAuditoriaString = fechaInicioAuditoriaString;
	}

	public String getFechaFinAuditoriaString() {
		return fechaFinAuditoriaString;
	}

	public void setFechaFinAuditoriaString(String fechaFinAuditoriaString) {
		this.fechaFinAuditoriaString = fechaFinAuditoriaString;
	}

}