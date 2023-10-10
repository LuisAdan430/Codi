package net.cero.ahorro.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.cero.spring.config.IPAuthenticationProvider;
import net.cero.data.FrecuenteConsultaPeticionOBJ;
import net.cero.data.FrecuenteInsertOBJ;
import net.cero.data.FrecuentesConsultaResultOBJ;
import net.cero.data.FrecuentesDesactivar;
import net.cero.data.Respuesta;
import net.cero.ahorro.logica.FrecuentesServiciosLogic;

@Controller
public class FrecuentesServicios {
	@Autowired
	protected IPAuthenticationProvider authenticationManager;

	private static final Logger log = LogManager.getLogger(FrecuenteInsertOBJ.class);
	FrecuentesServiciosLogic frec = new FrecuentesServiciosLogic();
	private String mensajeValidacion = "";

	@RequestMapping(value = "/registrarFrecuente", method = RequestMethod.POST)
	public ResponseEntity<String> registrarFrecuente(@RequestBody String json) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authenticate;
		ResponseEntity<String> response;
		Gson gson = new Gson();
		Respuesta resp = new Respuesta();
		response = new ResponseEntity<>((gson.toJson(resp)).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		authenticate = authenticationManager.authenticate(securityContext.getAuthentication());

		if (!authenticate.isAuthenticated()) {
			response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			return response;
		}
		try {
			FrecuenteInsertOBJ input = gson.fromJson(json, FrecuenteInsertOBJ.class);
			if (!this.validarInputRegistrarFrecuente(input)) {
				resp.setCodigo(2);
				resp.setMensaje("Parametro inválido: " + mensajeValidacion);
				response = new ResponseEntity<>((gson.toJson(resp)), HttpStatus.BAD_REQUEST);
				mensajeValidacion = "";
			} else {
				resp = frec.procesaFrecuente(input);
				if (resp.getCodigo() == -1)
					response = new ResponseEntity<>((gson.toJson(resp)), HttpStatus.INTERNAL_SERVER_ERROR);
				else
					response = new ResponseEntity<>((gson.toJson(resp)), HttpStatus.OK);
			}
		} catch (JsonSyntaxException j) {
			resp.setCodigo(3);
			resp.setMensaje("Error de syntaxis, formato de entrada incorrecto");
			response = new ResponseEntity<>((gson.toJson(resp)), HttpStatus.BAD_REQUEST);
		} catch (Exception i) {
			i.printStackTrace();
			if (i.getMessage().contains("NumberFormatException")) {
				resp.setCodigo(3);
				resp.setMensaje("Error de syntaxis, formato de entrada incorrecto");
				response = new ResponseEntity<>((gson.toJson(resp)), HttpStatus.BAD_REQUEST);
			}
		}
		return response;
	}

	@RequestMapping(value = "/consultarFrecuentesRecargas", method = RequestMethod.POST)
	public ResponseEntity<String> consultarFrecuentesRecargas(@RequestBody String json) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authenticate;
		ResponseEntity<String> response;
		Gson gson = new Gson();
		FrecuenteConsultaPeticionOBJ consulta = new FrecuenteConsultaPeticionOBJ();
		Respuesta resp = new Respuesta();
		resp.setCodigo(-1);
		resp.setMensaje("No se pudo encontrar informacion de los frecuentes");

		response = new ResponseEntity<>((gson.toJson(resp)).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		authenticate = authenticationManager.authenticate(securityContext.getAuthentication());

		if (!authenticate.isAuthenticated()) {
			response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			return response;
		}

		try {
			consulta = gson.fromJson(json, FrecuenteConsultaPeticionOBJ.class);
			if (!this.validarInputConsultarFrecuente(consulta)) {
				resp.setCodigo(2);
				resp.setMensaje("Parametro inválido ");
				response = new ResponseEntity<>((gson.toJson(resp)).toString(), HttpStatus.BAD_REQUEST);
				mensajeValidacion = "";
			} else {
				resp = frec.consultarFrecuentesRecargas(consulta);
				if (resp.getCodigo() == -1) 
					response = new ResponseEntity<>((gson.toJson(resp)).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
				else {
					response = new ResponseEntity<>((gson.toJson(resp)).toString(), HttpStatus.OK);
				}
			}
		} catch (JsonSyntaxException j) {
			j.printStackTrace();
			if (j.getMessage().contains("NumberFormatException")) {
				resp.setCodigo(3);
				resp.setMensaje("Error de syntaxis, formato de entrada incorrecto");
				response = new ResponseEntity<>((gson.toJson(resp)).toString(), HttpStatus.BAD_REQUEST);
			}
		}
		return response;
	}
	
	@RequestMapping(value = "/consultarFrecuentesTerceros", method = RequestMethod.POST)
	public ResponseEntity<String> consultarFrecuentesTerceros(@RequestBody String json) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authenticate;
		ResponseEntity<String> response;
		Gson gson = new Gson();
		FrecuenteConsultaPeticionOBJ consulta = new FrecuenteConsultaPeticionOBJ();
		Respuesta resp = new Respuesta();
		resp.setCodigo(-1);
		resp.setMensaje("No se pudo encontrar informacion de los frecuentes");

		response = new ResponseEntity<>((gson.toJson(resp)).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		authenticate = authenticationManager.authenticate(securityContext.getAuthentication());

		if (!authenticate.isAuthenticated()) {
			response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			return response;
		}

		try {
			consulta = gson.fromJson(json, FrecuenteConsultaPeticionOBJ.class);
			if (!this.validarInputConsultarFrecuente(consulta)) {
				resp.setCodigo(2);
				resp.setMensaje("Parametro inválido ");
				response = new ResponseEntity<>((gson.toJson(resp)).toString(), HttpStatus.BAD_REQUEST);
				mensajeValidacion = "";
			} else {
				resp = frec.consultarFrecuentesTerceros(consulta);
				if (resp.getCodigo() == -1) 
					response = new ResponseEntity<>((gson.toJson(resp)).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
				else {
					response = new ResponseEntity<>((gson.toJson(resp)).toString(), HttpStatus.OK);
				}
			}
		} catch (JsonSyntaxException j) {
			j.printStackTrace();
			if (j.getMessage().contains("NumberFormatException")) {
				resp.setCodigo(3);
				resp.setMensaje("Error de syntaxis, formato de entrada incorrecto");
				response = new ResponseEntity<>((gson.toJson(resp)).toString(), HttpStatus.BAD_REQUEST);
			}
		}
		return response;
	}	

	@RequestMapping(value = "/desactivarFrecuente", method = RequestMethod.POST)
	public ResponseEntity<String> desactivarFrecuente(@RequestBody String json) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authenticate;
		ResponseEntity<String> response;
		Gson gson = new Gson();
		Respuesta resp = new Respuesta();
		response = new ResponseEntity<>((gson.toJson(resp)).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		authenticate = authenticationManager.authenticate(securityContext.getAuthentication());

		if (!authenticate.isAuthenticated()) {
			response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			return response;
		}
		try {
			FrecuentesDesactivar input = gson.fromJson(json, FrecuentesDesactivar.class);

			if (!this.validarInputDesactivarFrecuente(input)) {
				resp.setCodigo(2);
				resp.setMensaje("Parametro inválido ");
				response = new ResponseEntity<>((gson.toJson(resp)).toString(), HttpStatus.BAD_REQUEST);
				mensajeValidacion = "";
			} else {
				resp = frec.desactivarFrecuente(input);
				if (resp.getCodigo() == -1)
					response = new ResponseEntity<>((gson.toJson(resp)).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
				else
					response = new ResponseEntity<>((gson.toJson(resp)).toString(), HttpStatus.OK);
			}
		} catch (JsonSyntaxException j) {
			j.printStackTrace();
			if (j.getMessage().contains("NumberFormatException")) {
				resp.setCodigo(3);
				resp.setMensaje("Error de syntaxis, formato de entrada incorrecto");
				response = new ResponseEntity<>((gson.toJson(resp)).toString(), HttpStatus.BAD_REQUEST);
			}
		}
		return response;
	}

	private Boolean validarInputRegistrarFrecuente(FrecuenteInsertOBJ regFrec) {
		if (regFrec.getCuenta() == null || regFrec.getCuenta().isEmpty()) {
			mensajeValidacion = "cuenta";
		}
		if (regFrec.getCoServicioId() == null || regFrec.getCoServicioId() <= 0) {
			if (mensajeValidacion.isEmpty())
				mensajeValidacion = "coServicioId";
			else
				mensajeValidacion = mensajeValidacion + ", coServicioId";
		}
		if (regFrec.getReferencia() == null || regFrec.getReferencia().isEmpty()) {
			if (mensajeValidacion.isEmpty())
				mensajeValidacion = "referencia";
			else
				mensajeValidacion = mensajeValidacion + ", referencia";
		}
		if (regFrec.getMonto() == null || regFrec.getMonto() <= 0) {
			if (mensajeValidacion.isEmpty())
				mensajeValidacion = "monto";
			else
				mensajeValidacion = mensajeValidacion + ", monto";
		}
		return mensajeValidacion.isEmpty() ? true : false;
	}

	private Boolean validarInputConsultarFrecuente(FrecuenteConsultaPeticionOBJ consulFrec) {
		if (consulFrec.getCuenta() == null || consulFrec.getCuenta().isEmpty()) {
			mensajeValidacion = "cuenta";
		}
		return mensajeValidacion.isEmpty() ? true : false;
	}

	public Boolean validarInputDesactivarFrecuente(FrecuentesDesactivar desacFrec) {
		if (desacFrec.getCoFrecuenteId() == null || desacFrec.getCoFrecuenteId() <= 0) {
			mensajeValidacion = "id";
		}
		if (desacFrec.getCuenta() == null || desacFrec.getCuenta().isEmpty()) {
			if (mensajeValidacion.isEmpty())
				mensajeValidacion = "usuarioModificacion";
			else
				mensajeValidacion = mensajeValidacion + ", usuarioModificacion";
		}
		return true;
	}

}
