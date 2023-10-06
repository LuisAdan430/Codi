package net.cero.ahorro.logica;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.cero.data.FrecuentesConsultaResultOBJ;
import net.cero.data.FrecuentesDesactivar;
import net.cero.data.FrecuenteConsultaPeticionOBJ;
import net.cero.data.FrecuenteInsertOBJ;
import net.cero.data.Respuesta;
import net.cero.spring.config.Apps;
import net.cero.spring.dao.FrecuentesServiciosDAO;

public class FrecuentesServiciosLogic {
	private static final Logger log = LogManager.getLogger(FrecuentesServiciosLogic.class);
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
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public Respuesta registrarFrecuente(FrecuenteInsertOBJ data) {
		initialized();
		Respuesta respuesta = new Respuesta();
		respuesta.setCodigo(-1);
		respuesta.setMensaje("Ocurrio un error al registrar frecuente");
		Integer resultado = -1;

		try {
			int idCuenta = dao.buscarAhCuentasPorCuenta(data);
			if (idCuenta > 0) {
				resultado = dao.buscarCoServiciosEmpresasPorId(data);
				if (resultado > 0) {
					//Validar que en la tabla frecuentes no este registrado ya con estos campos
					
					
					//Si ya existe evaluo el activo si esta activo ya existe
					
					//Si no esta activo se hace el update ,,, Se deja la fecha en la que se ejecuta el proceso
					
					//y si esta null se inserta el nuevo frecuente ,,
					
					resultado = dao.nuevoFrecuente(data, idCuenta);
					if (resultado == 1) {
						respuesta.setCodigo(0);
						respuesta.setMensaje("Frecuente registrado exitosamente");
					} else if (resultado == 0) {
						respuesta.setCodigo(1);
					}
				} else if (resultado == -2) {
					respuesta.setCodigo(4);
					respuesta.setMensaje("No se encontro información del servicio");
				}
			} else if (resultado == -2) {
				respuesta.setCodigo(4);
				respuesta.setMensaje("No se encontro información de la cuenta");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respuesta;
	}

	public List<FrecuentesConsultaResultOBJ> consultarFrecuentesRecargas(FrecuenteConsultaPeticionOBJ tipo) {
		initialized();
		List<FrecuentesConsultaResultOBJ> resultado = new ArrayList<FrecuentesConsultaResultOBJ>();
		try {
			resultado = dao.obtenerFrecuenteByCuenta(tipo);
		} catch (Exception e) {
			e.printStackTrace();
			resultado = null;
		}
		return resultado;
	}

	public List<FrecuentesConsultaResultOBJ> consultarFrecuentesTerceros(FrecuenteConsultaPeticionOBJ tipo) {
		initialized();
		List<FrecuentesConsultaResultOBJ> resultado = new ArrayList<FrecuentesConsultaResultOBJ>();
		try {
			resultado = dao.obtenerFrecuenteByCuenta(tipo);
		} catch (Exception e) {
			e.printStackTrace();
			resultado = null;
		}
		return resultado;
	}

	public Respuesta desactivarFrecuente(FrecuentesDesactivar data) {
		initialized();
		Respuesta respuesta = new Respuesta();
		respuesta.setCodigo(-1);
		respuesta.setMensaje("Ocurrio un error al eliminar frecuente");
		Integer resultado = -1;
		resultado = dao.buscarFrecuentePorId(data);
		if (resultado > 0) {
			int idCuenta = dao.buscarAhCuentasPorId(data);
			if (resultado > 0) {
				resultado = dao.desactivarFrecuentePorId(data,idCuenta);
				if (resultado == 1) {
					respuesta.setCodigo(0);
					respuesta.setMensaje("Frecuente eliminado correctamente.");
				} else {
					respuesta.setCodigo(1);
				}
			} else if (resultado == -2) {
				respuesta.setCodigo(4);
				respuesta.setMensaje("No se encontro información de la cuenta");
			}
		} else if (resultado == -2) {
			respuesta.setCodigo(1);
			respuesta.setMensaje("No se encuentra información del servicio frecuente.");
		}
		return respuesta;
	}
}
