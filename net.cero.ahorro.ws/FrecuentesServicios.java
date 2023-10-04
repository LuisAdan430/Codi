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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import net.cero.spring.config.IPAuthenticationProvider;
import net.cero.data.FrecuentesConsulta;
import net.cero.data.FrecuentesDesactivar;
import net.cero.data.FrecuentesOBJ;
import net.cero.data.Respuesta;
import net.cero.ahorro.logica.FrecuentesServiciosLogic;

@Controller
public class FrecuentesServicios {
		@Autowired
	protected IPAuthenticationProvider authenticationManager;

	private static final Logger log = LogManager.getLogger(FrecuentesWS.class);
	FrecuentesServiciosLogic frec = new FrecuentesServiciosLogic();

	@RequestMapping(value = "/registrarFavortio", method = RequestMethod.POST)
	public ResponseEntity<String> registrarFavortio(@RequestBody String json) {
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
			FrecuentesOBJ input = gson.fromJson(json, FrecuentesOBJ.class);

			if (!frec.validarInputRegistrarFrecuente(input)) {
				resp.setCodigo(2);
				resp.setMensaje("Parametro inválido ");
				response = new ResponseEntity<>((gson.toJson(resp)).toString(), HttpStatus.BAD_REQUEST);
			} else {
				resp = frec.registrarFrecuente(input);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@RequestMapping(value = "/consultarFrecuentesRecargas", method = RequestMethod.POST)
	public ResponseEntity<String> consultarFrecuentesRecargas(@RequestBody String json) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authenticate;
		ResponseEntity<String> response;
		Gson gson = new Gson();
		FrecuentesConsulta resp = new FrecuentesConsulta();
		response = new ResponseEntity<>((gson.toJson(resp)).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		authenticate = authenticationManager.authenticate(securityContext.getAuthentication());

		if (!authenticate.isAuthenticated()) {
			response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			return response;
		}

		try {

			if (!frec.validarInputConsulta(json)) {
				resp.setCodigo(2);
				resp.setMensaje("Parametro inválido ");
				response = new ResponseEntity<>((gson.toJson(resp)).toString(), HttpStatus.BAD_REQUEST);
			} else {
				resp = frec.consultarFrecuentesRecargas(json);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@RequestMapping(value = "/consultarFrecuentesTerceros", method = RequestMethod.POST)
	public ResponseEntity<String> consultarFrecuentesTerceros(@RequestBody String json) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authenticate;
		ResponseEntity<String> response;
		Gson gson = new Gson();
		FrecuentesConsulta resp = new FrecuentesConsulta();
		response = new ResponseEntity<>((gson.toJson(resp)).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		authenticate = authenticationManager.authenticate(securityContext.getAuthentication());

		if (!authenticate.isAuthenticated()) {
			response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			return response;
		}

		try {

			if (!frec.validarInputConsulta(json)) {
				resp.setCodigo(2);
				resp.setMensaje("Parametro inválido ");
				response = new ResponseEntity<>((gson.toJson(resp)).toString(), HttpStatus.BAD_REQUEST);
			} else {
				resp = frec.consultarFrecuentesTerceros(json);
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
		} catch (Exception e) {
			e.printStackTrace();
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

			if (!frec.validarInputDesactivarFrecuente(input)) {
				resp.setCodigo(2);
				resp.setMensaje("Parametro inválido ");
				response = new ResponseEntity<>((gson.toJson(resp)).toString(), HttpStatus.BAD_REQUEST);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
