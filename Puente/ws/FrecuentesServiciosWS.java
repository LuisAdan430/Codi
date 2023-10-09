package net.cero.codi.ws;

import net.cero.codi.spei.logic.FrecuentesServiciosLogic;
import net.cero.data.FrecuentesConsultaResultOBJ;
import net.cero.data.Respuesta;
import net.cero.spring.config.IPAuthenticationProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

@Controller
public class FrecuentesServiciosWS {
	private static final Logger log = LogManager.getLogger(FrecuentesServiciosWS.class);
	@Autowired
	protected IPAuthenticationProvider authenticationManager;

	@RequestMapping(value = "/registrarFrecuente", method = RequestMethod.POST)
	public ResponseEntity<String> registrarFrecuente(@RequestBody String data) {
		String result = "";
		Gson gson = new Gson();
		Respuesta resp = new Respuesta();
		ResponseEntity<String> response = null;
		log.error("Autenticación de basicAuth");
		try {
			FrecuentesServiciosLogic frec = new FrecuentesServiciosLogic();
			SecurityContext securityContext = SecurityContextHolder.getContext();
			Authentication authenticate;

			authenticate = authenticationManager.authenticate(securityContext.getAuthentication());
			if (!authenticate.isAuthenticated()) {
				response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				return response;
			}

			if (data == null || data.isEmpty()) {
				resp.setCodigo(2);
				resp.setMensaje("Parametro inválido ");
				response = new ResponseEntity<>((gson.toJson(resp)).toString(), HttpStatus.BAD_REQUEST);
			} else {
				resp = frec.registrarFrecuente(data);
				result = gson.toJson(resp);
				if (resp.getCodigo() == -1)
					response = new ResponseEntity<>((gson.toJson(resp)).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
				else
					response = new ResponseEntity<>((gson.toJson(resp)).toString(), HttpStatus.OK);
			}

		} catch (Exception ex) {
			resp.setCodigo(-1);
			resp.setMensaje("Error por excepcion " + ex);
			response = new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@RequestMapping(value = "/consultarFrecuentes", method = RequestMethod.POST)
	public ResponseEntity<String> consultarFrecuentes(@RequestBody String data) {
		String result = "";
		Gson gson = new Gson();
		FrecuentesConsultaResultOBJ resp = new FrecuentesConsultaResultOBJ();
		ResponseEntity<String> response = null;
		log.error("Autenticación de basicAuth");
		try {
			FrecuentesServiciosLogic frec = new FrecuentesServiciosLogic();
			SecurityContext securityContext = SecurityContextHolder.getContext();
			Authentication authenticate;

			authenticate = authenticationManager.authenticate(securityContext.getAuthentication());
			if (!authenticate.isAuthenticated()) {
				response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				return response;
			}

			if (data == null || data.isEmpty()) {
				resp.setCodigo(2);
				resp.setMensaje("Parametro inválido ");
				response = new ResponseEntity<>((gson.toJson(resp)).toString(), HttpStatus.BAD_REQUEST);
			} else {
				resp = frec.consultarFrecuentes(data);
				result = gson.toJson(resp);
				if (resp.getCodigo() == -1)
					response = new ResponseEntity<>((gson.toJson(resp)).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
				else
					response = new ResponseEntity<>((gson.toJson(resp)).toString(), HttpStatus.OK);
			}

		} catch (Exception ex) {
			resp.setCodigo(-1);
			resp.setMensaje("Error por excepcion " + ex);
			response = new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@RequestMapping(value = "/desactivarFrecuente", method = RequestMethod.POST)
	public ResponseEntity<String> desactivarFrecuente(@RequestBody String data) {
		String result = "";
		Gson gson = new Gson();
		Respuesta resp = new Respuesta();
		ResponseEntity<String> response = null;
		log.error("Autenticación de basicAuth");
		try {
			FrecuentesServiciosLogic frec = new FrecuentesServiciosLogic();
			SecurityContext securityContext = SecurityContextHolder.getContext();
			Authentication authenticate;

			authenticate = authenticationManager.authenticate(securityContext.getAuthentication());
			if (!authenticate.isAuthenticated()) {
				response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				return response;
			}

			if (data == null || data.isEmpty()) {
				resp.setCodigo(2);
				resp.setMensaje("Parametro inválido ");
				response = new ResponseEntity<>((gson.toJson(resp)).toString(), HttpStatus.BAD_REQUEST);
			} else {
				resp = frec.registrarFrecuente(data);
				result = gson.toJson(resp);
				if (resp.getCodigo() == -1)
					response = new ResponseEntity<>((gson.toJson(resp)).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
				else
					response = new ResponseEntity<>((gson.toJson(resp)).toString(), HttpStatus.OK);
			}

		} catch (Exception ex) {
			resp.setCodigo(-1);
			resp.setMensaje("Error por excepcion " + ex);
			response = new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

}
