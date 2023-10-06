package net.cero.spring.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import net.cero.data.FrecuentesConsultaResultOBJ;
import net.cero.data.FrecuentesDesactivar;
import net.cero.data.FrecuenteConsultaPeticionOBJ;
import net.cero.data.FrecuenteInsertOBJ;

public class FrecuentesServiciosDAO {
	public static final Logger LOG = LogManager.getLogger(FrecuentesServiciosDAO.class);

	private JdbcTemplate jdbcTemplate;
	private String nuevoFrecuenteServicios;
	private String obtenerFrecuenteTercerosYRecargas;
	private String desactivarFrecuente;
	private String buscarFrecuentePorId;
	private String buscarCoServiciosEmpresasPorId;
	private String buscarAhCuentasPorCuenta;
	private String buscarAhCuentasPorId;

	public Integer nuevoFrecuente(FrecuenteInsertOBJ req, Integer idCuenta) {
		try {
			Integer resultado = 0;
			resultado = jdbcTemplate.update(nuevoFrecuenteServicios, 289, req.getCoServicioId(),
					req.getReferencia(), req.getMonto(),289);
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	} 
	
	public List<FrecuentesConsultaResultOBJ> obtenerFrecuenteByCuenta(FrecuenteConsultaPeticionOBJ req) {
		List<FrecuentesConsultaResultOBJ> result = new ArrayList<FrecuentesConsultaResultOBJ>();
		List<Map<String, Object>> rows;
		FrecuentesConsultaResultOBJ object = new FrecuentesConsultaResultOBJ();
		try {
			rows = jdbcTemplate.queryForList(obtenerFrecuenteTercerosYRecargas, req.getTipoServicio(),
					req.getCeActivo(), req.getcCuenta(), req.getNtActivo());
			for (Map<String, Object> row : rows) {
				object = new FrecuentesConsultaResultOBJ();
				object.setCeId((Integer) row.get("ce.id"));
				object.setCeServicio((String) row.get("ce.servicio"));
				object.setCeTipoProducto((String) row.get("ce.tipo_producto"));
				object.setCeTipoServicio((String) row.get("ce.tipo_servicio"));
				object.setCeNememp((String) row.get("ce.nememp"));
				object.setCeSubemp((String) row.get("ce.subemp"));
				object.setCeComision((Integer) row.get("ce.comision"));
				object.setCcServicioCodigo((String) row.get("cc.servicio_codigo"));

				result.add(object);

			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}
	
	public Integer buscarCoServiciosEmpresasPorId(FrecuenteInsertOBJ req) {
		Integer respuesta = -1;
		try {
			List<String> results = jdbcTemplate.queryForList(buscarCoServiciosEmpresasPorId, String.class,
					req.getCoServicioId());
			if (!results.isEmpty()) {
				respuesta = Integer.parseInt(results.get(0));
			} else {
				respuesta = 1;
			}

		} catch (EmptyResultDataAccessException x) {
			x.printStackTrace();
			respuesta = -1;
		}
		return respuesta;
	}

	public Integer buscarAhCuentasPorCuenta(FrecuenteInsertOBJ req) {
		Integer respuesta = null;
		try {
			List<String> results = jdbcTemplate.queryForList(buscarAhCuentasPorCuenta, String.class, req.getCuenta());
			if (!results.isEmpty()) {
				respuesta = Integer.parseInt(results.get(0));
			} else {
				respuesta = -2;
			}

		} catch (EmptyResultDataAccessException x) {
			x.printStackTrace();
			respuesta = -1;
		}
		return respuesta;
	}


	public Integer buscarFrecuentePorId(FrecuentesDesactivar req) {
		Integer respuesta = null;
		try {
			List<String> results = jdbcTemplate.queryForList(buscarFrecuentePorId, String.class, req.getId());
			if (!results.isEmpty()) {
				respuesta = Integer.parseInt(results.get(0));			
			} else {
				respuesta = -2;
			}

		} catch (EmptyResultDataAccessException x) {
			x.printStackTrace();
			respuesta = -1;
		}
		return respuesta;
	}

	
	public Integer buscarAhCuentasPorId(FrecuentesDesactivar req) {
		Integer respuesta = null;
		try {
			List<String> results = jdbcTemplate.queryForList(buscarAhCuentasPorCuenta, String.class, req.getusuarioModificacion());
			if (!results.isEmpty()) {
				respuesta = Integer.parseInt(results.get(0));
			} else {
				respuesta = -2;
			}

		} catch (Exception x) {
			x.printStackTrace();
			respuesta = -1;
		}
		return respuesta;
	}

	public Integer desactivarFrecuentePorId(FrecuentesDesactivar req, Integer idUsuario) {
		Integer resultado = 0;
		try {
			resultado=jdbcTemplate.update(desactivarFrecuente, idUsuario, req.getId());			
		} catch (Exception e) {
			e.printStackTrace();
			resultado = -1;
		}
		return resultado;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public String getNuevoFrecuenteServicios() {
		return nuevoFrecuenteServicios;
	}

	public void setNuevoFrecuenteServicios(String nuevoFrecuenteServicios) {
		this.nuevoFrecuenteServicios = nuevoFrecuenteServicios;
	}

	public String getObtenerFrecuenteTercerosYRecargas() {
		return obtenerFrecuenteTercerosYRecargas;
	}

	public void setObtenerFrecuenteTercerosYRecargas(String obtenerFrecuenteTercerosYRecargas) {
		this.obtenerFrecuenteTercerosYRecargas = obtenerFrecuenteTercerosYRecargas;
	}

	public String getDesactivarFrecuente() {
		return desactivarFrecuente;
	}

	public void setDesactivarFrecuente(String desactivarFrecuente) {
		this.desactivarFrecuente = desactivarFrecuente;
	}

	public String getBuscarFrecuentePorId() {
		return buscarFrecuentePorId;
	}

	public void setBuscarFrecuentePorId(String buscarFrecuentePorId) {
		this.buscarFrecuentePorId = buscarFrecuentePorId;
	}

	public String getBuscarCoServiciosEmpresasPorId() {
		return buscarCoServiciosEmpresasPorId;
	}

	public void setBuscarCoServiciosEmpresasPorId(String buscarCoServiciosEmpresasPorId) {
		this.buscarCoServiciosEmpresasPorId = buscarCoServiciosEmpresasPorId;
	}

	public String getBuscarAhCuentasPorCuenta() {
		return buscarAhCuentasPorCuenta;
	}

	public void setBuscarAhCuentasPorCuenta(String buscarAhCuentasPorCuenta) {
		this.buscarAhCuentasPorCuenta = buscarAhCuentasPorCuenta;
	}

	public String getBuscarAhCuentasPorId() {
		return buscarAhCuentasPorId;
	}

	public void setBuscarAhCuentasPorId(String buscarAhCuentasPorId) {
		this.buscarAhCuentasPorId = buscarAhCuentasPorId;
	}

}
