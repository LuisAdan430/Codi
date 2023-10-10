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
import net.cero.data.FrecuenteExistente;

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
	private String buscarFrecuenteValidacion;
	private String actualizarFrecuente;

	public Integer nuevoFrecuente(FrecuenteInsertOBJ req, Integer idCuenta) {
		try {
			Integer resultado = 0;
			resultado = jdbcTemplate.update(nuevoFrecuenteServicios, idCuenta, req.getCoServicioId(),
					req.getReferencia(), req.getMonto(), idCuenta);
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public FrecuenteExistente validarFrecuente(FrecuenteInsertOBJ req, Integer idCuenta) {
		FrecuenteExistente frecuenteExistente = new FrecuenteExistente();
		Map<String, Object> row;
		try {
			row = jdbcTemplate.queryForMap(buscarFrecuenteValidacion, idCuenta, req.getCoServicioId(),
					req.getReferencia());
			frecuenteExistente.setId((Integer) row.get("id"));
			frecuenteExistente.setActivo((String) row.get("activo"));
		} catch (EmptyResultDataAccessException x) {
			x.printStackTrace();
			frecuenteExistente = null;
		} catch (Exception e) {
			e.printStackTrace();
			frecuenteExistente.setId(-1);
		}
		return frecuenteExistente;
	}
	
	public List<FrecuentesConsultaResultOBJ> obtenerFrecuenteTerceros(FrecuenteConsultaPeticionOBJ req) {
		List<FrecuentesConsultaResultOBJ> result = new ArrayList<FrecuentesConsultaResultOBJ>();
		List<Map<String, Object>> rows;
		try {
			rows = jdbcTemplate.queryForList(obtenerFrecuenteTercerosYRecargas, "Terceros", req.getCuenta());
			result = mapearConsulta(rows);			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	public List<FrecuentesConsultaResultOBJ> obtenerFrecuenteRecargas(FrecuenteConsultaPeticionOBJ req) {
		List<FrecuentesConsultaResultOBJ> result = new ArrayList<FrecuentesConsultaResultOBJ>();
		List<Map<String, Object>> rows;
		try {
			rows = jdbcTemplate.queryForList(obtenerFrecuenteTercerosYRecargas, "Recargas", req.getCuenta());
			result = mapearConsulta(rows);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	public List<FrecuentesConsultaResultOBJ> mapearConsulta(List<Map<String, Object>> rows) {
		List<FrecuentesConsultaResultOBJ> result = new ArrayList<FrecuentesConsultaResultOBJ>();
		FrecuentesConsultaResultOBJ object = new FrecuentesConsultaResultOBJ();
		for (Map<String, Object> row : rows) {
			object = new FrecuentesConsultaResultOBJ();
			object.setCeId((Long) row.get("id"));
			object.setCeServicio((String) row.get("servicio"));
			object.setCeTipoProducto((String) row.get("tipo_producto"));
			object.setCeTipoServicio((String) row.get("tipo_servicio"));
			object.setCeNememp((String) row.get("nememp"));
			object.setCeSubemp((String) row.get("subemp"));
			object.setCeComision((Float) row.get("comision"));
			object.setCcServicioCodigo((String) row.get("servicio_codigo"));
			result.add(object);
		}
		return result;
	}

	public Integer buscarCoServiciosEmpresasPorId(Integer idServicio) {
		Integer respuesta = 0;
		try {
			List<Integer> results = jdbcTemplate.queryForList(buscarCoServiciosEmpresasPorId, Integer.class,
					idServicio);
			if (!results.isEmpty()) {
				respuesta = results.get(0);
			}

		} catch (Exception x) {
			x.printStackTrace();
			respuesta = -1;
		}
		return respuesta;
	}

	public Integer buscarAhCuentasPorCuenta(String cuenta) {
		Integer respuesta = 0;
		try {
			List<Integer> results = jdbcTemplate.queryForList(buscarAhCuentasPorCuenta, Integer.class, cuenta);
			if (!results.isEmpty()) {
				respuesta = results.get(0);
			}
		} catch (Exception x) {
			x.printStackTrace();
			respuesta = -1;
		}
		return respuesta;
	}

	public Integer buscarFrecuentePorId(FrecuentesDesactivar req) {
		Integer respuesta = 0;
		try {
			List<String> results = jdbcTemplate.queryForList(buscarFrecuentePorId, String.class,
					req.getCoFrecuenteId());
			if (!results.isEmpty()) {
				respuesta = Integer.parseInt(results.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
			respuesta = -1;
		}
		return respuesta;
	}

	public Integer buscarAhCuentasPorId(FrecuentesDesactivar req) {
		Integer respuesta = 0;
		try {
			List<String> results = jdbcTemplate.queryForList(buscarAhCuentasPorCuenta, String.class, req.getCuenta());
			if (!results.isEmpty()) {
				respuesta = Integer.parseInt(results.get(0));
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
			resultado = jdbcTemplate.update(desactivarFrecuente, idUsuario, req.getCoFrecuenteId());
		} catch (Exception e) {
			e.printStackTrace();
			resultado = -1;
		}
		return resultado;
	}

	public Integer actualizarFrecuente(FrecuenteInsertOBJ req, Integer idFrecuente, Integer idUsuario) {
		Integer resultado = 0;
		try {
			resultado = jdbcTemplate.update(actualizarFrecuente, idUsuario, req.getReferencia(), req.getMonto(),
					idFrecuente);
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

	public String getBuscarFrecuenteValidacion() {
		return buscarFrecuenteValidacion;
	}

	public void setBuscarFrecuenteValidacion(String buscarFrecuenteValidacion) {
		this.buscarFrecuenteValidacion = buscarFrecuenteValidacion;
	}

	public String getActualizarFrecuente() {
		return actualizarFrecuente;
	}

	public void setActualizarFrecuente(String actualizarFrecuente) {
		this.actualizarFrecuente = actualizarFrecuente;
	}

}
