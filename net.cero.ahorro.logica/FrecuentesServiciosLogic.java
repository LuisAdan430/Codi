package net.cero.ahorro.logica;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import net.cero.data.FrecuentesConsultaResultOBJ;
import net.cero.data.FrecuentesDesactivar;
import net.cero.data.FrecuenteConsultaPeticionOBJ;
import net.cero.data.FrecuenteInsertOBJ;
import net.cero.data.Respuesta;
import net.cero.spring.config.Apps;
import net.cero.spring.dao.FrecuentesServiciosDAO;
import net.cero.data.FrecuenteExistente;

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

	public Respuesta procesaFrecuente(FrecuenteInsertOBJ data) {
		initialized();
		Respuesta respuesta = new Respuesta();
		respuesta.setCodigo(-1);
		respuesta.setMensaje("Error Interno en el servidor.");
		Integer idCuenta = 0;
		Integer idServicio = 0;
		try {
			idCuenta = validarCuenta(data.getCuenta());
			if (idCuenta > 0) {
				idServicio = validarServicio(data.getCoServicioId());
				if (idServicio > 0) {
					FrecuenteExistente frecuente = dao.validarFrecuente(data, idCuenta);
					if (frecuente == null) {
						respuesta = registraFrecuente(data, idCuenta);
					} else if (frecuente.getId() > 0) {
						respuesta = actualizaFrecuente(data, frecuente, idCuenta);
					}
				} else {
					respuesta.setCodigo(4);
					respuesta.setMensaje("No se encontro informacion con el servicio proporcionado.");
				}
			} else if (idCuenta == 0) {
				respuesta.setCodigo(4);
				respuesta.setMensaje("No se encontro informacion con la cuenta proporcionada.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respuesta;
	}

	public Respuesta registraFrecuente(FrecuenteInsertOBJ data, Integer idCuenta) {
		initialized();
		Respuesta respuesta = new Respuesta();
		respuesta.setCodigo(-1);
		respuesta.setMensaje("Error Interno en el servidor.");
		Integer nuevoFrecuente = 0;
		try {
			nuevoFrecuente = dao.nuevoFrecuente(data, idCuenta);
			if (nuevoFrecuente == 1) {
				respuesta.setCodigo(0);
				respuesta.setMensaje("Frecuente registrado exitosamente");
			} else if (nuevoFrecuente == 0) {
				respuesta.setCodigo(1);
				respuesta.setMensaje("Ocurrio un error al registrar frecuente");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respuesta;
	}

	public Respuesta actualizaFrecuente(FrecuenteInsertOBJ data, FrecuenteExistente frecuente, Integer idCuenta) {
		initialized();
		Respuesta respuesta = new Respuesta();
		respuesta.setCodigo(-1);
		respuesta.setMensaje("Error Interno en el servidor.");
		Integer actualizarFrecuente;
		try {
			if (frecuente.getActivo().equals("NO")) {
				actualizarFrecuente = dao.actualizarFrecuente(data, frecuente.getId(), idCuenta);
				if (actualizarFrecuente == 1) {
					respuesta.setCodigo(0);
					respuesta.setMensaje("Frecuente actualizado exitosamente");
				} else if (actualizarFrecuente == 0) {
					respuesta.setCodigo(1);
					respuesta.setMensaje("Ocurrio un error al registrar frecuente");
				}
			} else {
				respuesta.setCodigo(5);
				respuesta.setMensaje("El frecuente ya se encuentra registrado.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return respuesta;
	}

	public Integer validarCuenta(String cuenta) {
		Integer idCuenta = 0;
		idCuenta = dao.buscarAhCuentasPorCuenta(cuenta);
		return idCuenta;
	}

	public Integer validarServicio(Integer idServicio) {
		Integer idServ = 0;
		idServ = dao.buscarCoServiciosEmpresasPorId(idServicio);
		return idServ;
	}

	public Respuesta consultarFrecuentesRecargas(FrecuenteConsultaPeticionOBJ tipo) {
		Respuesta respuesta = new Respuesta();
		respuesta.setCodigo(-1);
		respuesta.setMensaje("Error Interno en el servidor.");
		initialized();
		Gson gson = new Gson();
		List<FrecuentesConsultaResultOBJ> listFrecuentes = new ArrayList<FrecuentesConsultaResultOBJ>();
		try {
			listFrecuentes = dao.obtenerFrecuenteRecargas(tipo);
			if(listFrecuentes.size()>0) {
				respuesta.setCodigo(0);
				respuesta.setMensaje("Consulta de frecuentes exitosa");
				respuesta.setData(gson.toJson(listFrecuentes));
			}else if(listFrecuentes.isEmpty()){
				respuesta.setCodigo(4);
				respuesta.setMensaje("No se encontro información de frecuentes");
			}
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return respuesta;
	}

	public Respuesta consultarFrecuentesTerceros(FrecuenteConsultaPeticionOBJ tipo) {
		Respuesta respuesta = new Respuesta();
		respuesta.setCodigo(-1);
		respuesta.setMensaje("Error Interno en el servidor.");
		initialized();
		Gson gson = new Gson();
		List<FrecuentesConsultaResultOBJ> listFrecuentes = new ArrayList<FrecuentesConsultaResultOBJ>();
		try {
			listFrecuentes = dao.obtenerFrecuenteTerceros(tipo);
			if(listFrecuentes.size()>0) {
				respuesta.setCodigo(0);
				respuesta.setMensaje("Consulta de frecuentes exitosa");
				respuesta.setData(gson.toJson(listFrecuentes));
			}else if(listFrecuentes.isEmpty()){
				respuesta.setCodigo(4);
				respuesta.setMensaje("No se encontro información de frecuentes");
			}
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return respuesta;
	}

	public Respuesta desactivarFrecuente(FrecuentesDesactivar data) {
		initialized();
		Respuesta respuesta = new Respuesta();
		respuesta.setCodigo(-1);
		respuesta.setMensaje("Error Interno en el servidor.");
		Integer idCuenta = 0;
		Integer idFrecuente = 0;
		Integer desacFrecuente = 0;
		idFrecuente = dao.buscarFrecuentePorId(data);
		if (idFrecuente > 0) {
			idCuenta = dao.buscarAhCuentasPorId(data);
			if (idCuenta > 0) {
				desacFrecuente = dao.desactivarFrecuentePorId(data, idCuenta);
				if (desacFrecuente == 1) {
					respuesta.setCodigo(0);
					respuesta.setMensaje("Frecuente eliminado correctamente.");
				} else {
					respuesta.setCodigo(1);
					respuesta.setMensaje("Ocurrio un error al eliminar frecuente");
				}
			} else if (idCuenta == 0) {
				respuesta.setCodigo(4);
				respuesta.setMensaje("No se encontro información de la cuenta");
			}
		} else if (idFrecuente == 0) {
			respuesta.setCodigo(1);
			respuesta.setMensaje("No se encuentra información del servicio frecuente.");
		}
		return respuesta;
	}
}
