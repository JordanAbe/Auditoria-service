package pe.edu.upc.tp.auditoria.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.tp.auditoria.bean.Mail;
import pe.edu.upc.tp.auditoria.constantes.OtrosConstantes;
import pe.edu.upc.tp.auditoria.dao.GenericDao;
import pe.edu.upc.tp.auditoria.dao.PlanAnualDao;
import pe.edu.upc.tp.auditoria.dao.PlanEspecificoDao;
import pe.edu.upc.tp.auditoria.dao.ProcedimientoDao;
import pe.edu.upc.tp.auditoria.dao.ProgramaDao;
import pe.edu.upc.tp.auditoria.model.CronogramaModel;
import pe.edu.upc.tp.auditoria.model.PlanauditoriaModel;
import pe.edu.upc.tp.auditoria.model.PlanauditoriaAuditorModel;
import pe.edu.upc.tp.auditoria.model.PlanauditoriaProcedimientoModel;
import pe.edu.upc.tp.auditoria.model.PlanauditoriaanualModel;
import pe.edu.upc.tp.auditoria.model.ProcedimientoModel;
import pe.edu.upc.tp.auditoria.model.ProgramaModel;
import pe.edu.upc.tp.auditoria.service.EmailService;
import pe.edu.upc.tp.auditoria.service.PlanEspecificoService;
import pe.edu.upc.tp.auditoria.util.UtilTransfer;

@Service
public class PlanEspecificoServiceImpl implements PlanEspecificoService{

	@Autowired
	GenericDao genericDao;

	@Autowired
	PlanAnualDao planAuditoriaAnualDao;

	@Autowired
	ProgramaDao programaDao;

	@Autowired
	ProcedimientoDao procedimientoDao;

	@Autowired
	PlanEspecificoDao planAuditoriaEspecificaDao;

	@Autowired
	EmailService emailService;

    @Scheduled(fixedRate = 100000)
	@Transactional
	public void generarPlanEspecifico() {
		try {
			System.out.println("generarPlanEspecifico");
			PlanauditoriaanualModel planAnual = planAuditoriaAnualDao
					.getPlanAuditoriaAnualByPeriodo(UtilTransfer.getAñoActual());
			if (planAnual != null) {
				Date currentDate = new Date();
				List<ProgramaModel> programas = programaDao.getProgramasByPlanAnualAndFechas(planAnual.getId(), currentDate);
				if (programas != null && programas.size() > 0) {

					int cont = 1;
					List<ProcedimientoModel> procedimientos = procedimientoDao.getProcedimientos();
					List<PlanauditoriaModel> planesauditorias = new ArrayList<>();
					List<PlanauditoriaAuditorModel> planauditoriaAuditores = new ArrayList<>();
					List<PlanauditoriaProcedimientoModel> planauditoriaProcedimientos = new ArrayList<>();
					int id = genericDao.ultimo("id", "PlanauditoriaModel") + 1;
					int idPlanauditoriaAuditor = genericDao.ultimo("id", "PlanauditoriaAuditorModel") + 1;
					int idPlanauditoriaProcedimiento = genericDao.ultimo("id", "PlanauditoriaProcedimientoModel") + 1;

					for (ProgramaModel programa : programas) {
						if (programa.getEstado().equals(OtrosConstantes.PENDIENTE)) {
							programa.setEstado(OtrosConstantes.PROCESO);
							PlanauditoriaModel planauditoria = new PlanauditoriaModel();
							planauditoria.setId(id);
							planauditoria.setPlanauditoriaanualId(planAnual.getId());
							planauditoria.setProceso(programa.getProceso());
							planauditoria.setDescripcion(programa.getProceso().getDescripcion());
							planauditoria.setAlcance(programa.getProceso().getAlcance());
							planauditoria.setObjetivo(programa.getProceso().getAlcance());
							planauditoria.setEstado(OtrosConstantes.GENERADO);
							planauditoria.setFechaCreacion(currentDate);
							planauditoria.setDuracion(programa.getDuracion());
							planauditoria.setFechaInicio(programa.getFechaInicio());
							planauditoria.setFechaFin(programa.getFechaFin());
							planesauditorias.add(planauditoria);
							cont++;


							PlanauditoriaAuditorModel planauditoriaAuditor = new PlanauditoriaAuditorModel();
							planauditoriaAuditor.setId(idPlanauditoriaAuditor);
							planauditoriaAuditor.setPlanauditoriaId(id);
							planauditoriaAuditor.setEmpleado(programa.getAuditor());
							planauditoriaAuditor.setCargo(programa.getAuditor().getCargo());
							planauditoriaAuditor.setNivel(programa.getAuditor().getNivel());
							planauditoriaAuditores.add(planauditoriaAuditor);
							idPlanauditoriaAuditor++;


							for (ProcedimientoModel procedimiento : procedimientos) {
								PlanauditoriaProcedimientoModel planauditoriaProcedimiento = new PlanauditoriaProcedimientoModel();
								planauditoriaProcedimiento.setId(idPlanauditoriaProcedimiento);
								planauditoriaProcedimiento.setPlanauditoriaId(id);
								planauditoriaProcedimiento.setEstado(OtrosConstantes.PENDIENTE);
								planauditoriaProcedimiento
										.setProcedimiento(new ProcedimientoModel(procedimiento.getProcedimientoId()));
								planauditoriaProcedimientos.add(planauditoriaProcedimiento);
								idPlanauditoriaProcedimiento++;
							}
							id++;
						}
					}
					if (planesauditorias.size() > 0) {
						List<CronogramaModel> cronogramas = getCronogramas(planesauditorias);
						genericDao.insert(planesauditorias);
						genericDao.insert(planauditoriaAuditores);
						genericDao.insert(planauditoriaProcedimientos);
						genericDao.insert(cronogramas);
						genericDao.edit(programas);
						enviarCorreo(programas, planAnual.getId());
					}
					//enviarCorreo(programas, planAnual.getId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<CronogramaModel> getCronogramas(List<PlanauditoriaModel> planesauditorias) {
		Date lastDate = null;
		List<CronogramaModel> cronogramas = new ArrayList<>();
		try {
			int id = genericDao.ultimo("cronogramaId", "CronogramaModel") + 1;
			for (PlanauditoriaModel p : planesauditorias) {
				int cantPlan = (int) (p.getDuracion() * 0.3);
				for (int i = 0; i < cantPlan; i += 2) {
					CronogramaModel crono = new CronogramaModel();
					crono.setCronogramaId(id);
					crono.setPlanauditoriaId(p.getId());
					crono.setEtapa(OtrosConstantes.PLANIFICACION);
					if (i == 0) {
						lastDate = p.getFechaInicio();
					} else {
						lastDate = getSiguienteDiaUtil(lastDate);
					}
					crono.setFecha(lastDate);
					cronogramas.add(crono);
					id++;
				}
				int cantEjec = (int) (p.getDuracion() * 0.5);
				for (int i = 0; i < cantEjec; i += 2) {
					CronogramaModel crono = new CronogramaModel();
					crono.setCronogramaId(id);
					crono.setPlanauditoriaId(p.getId());
					crono.setEtapa(OtrosConstantes.EJECUCION);
					lastDate = getSiguienteDiaUtil(lastDate);
					crono.setFecha(lastDate);
					cronogramas.add(crono);
					id++;
				}
				int cantInfo = (int) (p.getDuracion() * 0.2);
				for (int i = 0; i < cantInfo; i += 2) {
					CronogramaModel crono = new CronogramaModel();
					crono.setCronogramaId(id);
					crono.setPlanauditoriaId(p.getId());
					crono.setEtapa(OtrosConstantes.INFORME);
					lastDate = getSiguienteDiaUtil(lastDate);
					crono.setFecha(lastDate);
					cronogramas.add(crono);
					id++;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cronogramas;
	}

	private Date getSiguienteDiaUtil(Date lastDate) {
		Calendar currentCalendar = Calendar.getInstance();
		currentCalendar.setTime(lastDate);
		if (currentCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
			currentCalendar.add(Calendar.DATE, 4);
		} else if (currentCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
			currentCalendar.add(Calendar.DATE, 4);
		} else {
			currentCalendar.add(Calendar.DATE, 2);
		}
		return currentCalendar.getTime();
	}

	public void enviarCorreo(List<ProgramaModel> programas, int planAnualId) {
		int cont = 1;
		for (ProgramaModel p : programas) {
			System.out.println("init enviando correo : " + cont);
			PlanauditoriaModel planauditoria = planAuditoriaEspecificaDao
					.getPlanAuditoriaByPlanAnualIdAndProgramaId(planAnualId, p.getProceso().getProcesoId());
			System.out.println(UtilTransfer.objectToJson(planauditoria));
			
			int index = 1;
			for(CronogramaModel c: planauditoria.getCronogramas()){
				c.setIndex(index);
				c.setIndexString("Reunión " + index);
				if(c.getEtapa() == OtrosConstantes.PLANIFICACION){
					c.setEtapaString("Planificación");
				}else if(c.getEtapa() == OtrosConstantes.EJECUCION){
					c.setEtapaString("Ejecución");
				}else{
					c.setEtapaString("Informe");
				}
				index++;
			}
			System.out.println("destinatario : " + planauditoria.getPlanauditoriaAuditores().get(0).getEmpleado().getCorreo());
			try {
				Mail mail = new Mail();
				mail.setFrom("auditoriadmn@gmail.com");
				mail.setTo(planauditoria.getPlanauditoriaAuditores().get(0).getEmpleado().getCorreo());
				mail.setSubject("Cronograma de reuniones de plan de auditoría");
	
				Map<String, Object> model = new HashMap<>();
				model.put("planauditoria", planauditoria);
				mail.setModel(model);

			
				emailService.sendSimpleMessage(mail);
				System.out.println("end enviando correo : " + cont);
				cont++;
			} catch (MessagingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
