package net.cero.ahorro.logica;
import net.cero.data.FrecuentesConsultaResultOBJ;
import net.cero.data.FrecuentesDesactivar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import net.cero.data.FrecuenteInsertOBJ;
import net.cero.data.Respuesta;
import net.cero.spring.config.Apps;
import net.cero.spring.dao.FrecuentesServiciosDAO;

public class FrecuentesServiciosLogic {

	private static final Logger log = LogManager.getLogger(DesactivarFavoritoLogic.class);
	private static FrecuentesServiciosDAO dao;
	private static Apps apps = null;
	
	private static void initialized() {
		try {
			Apps s = Apps.getInstance();
			synchronized (Apps.class) {
				if (apps == null) // si la referencia es null ...
					apps = s; // ... agrega la clase singleton
			}
			dao = (FrecuentesServiciosDAO) s.getApplicationContext().getBean("FrecuentesServiciosDAO");
			
		}catch(Exception e) {
			log.error(e.getMessage());
		}
	}
	
	
	
	public String registrarFrecuente(FrecuenteInsertOBJ data) {
		initialized();
		Gson gson  = new Gson();
		log.info(gson.toJson(data));
		
		Integer prueba = dao.nuevoFrecuente(data);
		log.info(prueba);
		
		String acceso  = "Correcto";
		return acceso;
	}
	
	public Boolean validarInputConsulta(String data) {
		return true;
	}
	public FrecuentesConsultaResultOBJ consultarFrecuentes(String tipo) {
		FrecuentesConsultaResultOBJ consulta = new FrecuentesConsultaResultOBJ();
		/*
		 
		 * */
		return consulta;
	}
	
	public Boolean validarInputDesactivarFrecuente(FrecuentesDesactivar data) {
		return true;
	}
	
	public Respuesta desactivarFrecuente (FrecuentesDesactivar data) {
		Respuesta respuesta = new Respuesta();
		return respuesta;
	}
}
