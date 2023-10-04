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
	private String sigSecFrecuenteServicios;
	private String nuevoFrecuenteServicios;
	private String obtenerFrecuenteTercerosYRecargas;
	private String desactivarFrecuente;
	private String buscarFrecuenteById;
	
	private Integer sigSecFrecuenteServicios() {
		try {
			return jdbcTemplate.queryForObject(sigSecFrecuenteServicios, Integer.class);
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	public Integer nuevoFrecuente(FrecuenteInsertOBJ req) {
		try {
			Integer idReq = sigSecFrecuenteServicios();
			jdbcTemplate.update(nuevoFrecuenteServicios,idReq,req.getAhCuenta(),req.getCoServicioId(),req.getReferencia(),req.getMonto(),req.getActivo(),req.getUsuarioCreacion());
			return idReq;
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	public List<FrecuentesConsultaResultOBJ> obtenerFrecuenteByCuenta(FrecuenteConsultaPeticionOBJ req){
		List<FrecuentesConsultaResultOBJ> result = new ArrayList<FrecuentesConsultaResultOBJ>();
		List<Map<String,Object>> rows;
		FrecuentesConsultaResultOBJ object = new FrecuentesConsultaResultOBJ();
		try {
			rows = jdbcTemplate.queryForList(obtenerFrecuenteTercerosYRecargas,req.getTipoServicio(),req.getCeActivo(),req.getcCuenta(),req.getNtActivo());
			for(Map<String, Object> row : rows) {
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
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}
	
	public Integer buscarFrecuenteById(FrecuentesDesactivar req) {
		Integer respuesta = null;
		try {
			List<String> results = jdbcTemplate.queryForList(buscarFrecuenteById,String.class,req.getId());
			if(!results.isEmpty()) {
				respuesta = Integer.parseInt(results.get(0));
				return respuesta;
			}else {
				return respuesta = 0;
			}
		
		}catch (EmptyResultDataAccessException  x) {
	        x.printStackTrace();
	       return respuesta = null;
		}
	}
	public void desactivarFrecuenteById(FrecuentesDesactivar req) {
		try {
			jdbcTemplate.update(desactivarFrecuente,req.getusuarioModificacion(),req.getId());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public String getSigSecFrecuenteServicios() {
		return sigSecFrecuenteServicios;
	}
	public void setSigSecFrecuenteServicios(String sigSecFrecuenteServicios) {
		this.sigSecFrecuenteServicios = sigSecFrecuenteServicios;
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
	public String getBuscarFrecuenteById() {
		return buscarFrecuenteById;
	}
	public void setBuscarFrecuenteById(String buscarFrecuenteById) {
		this.buscarFrecuenteById = buscarFrecuenteById;
	}
	
	
	
}
