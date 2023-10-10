package net.cero.codi.ws;

import net.cero.codi.logica.FrecuentesServiciosLogic;
import net.cero.data.FrecuenteInsertOBJ;
import net.cero.data.FrecuentesConsultaResultOBJ;
import net.cero.data.Respuesta;
import net.cero.seguridad.utilidades.ConceptosUtil;
import net.cero.seguridad.utilidades.Utilerias;
import net.cero.spring.config.IPAuthenticationProvider;

import java.net.URLDecoder;

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
		Utilerias util = new Utilerias();
		Respuesta resp = new Respuesta();
		ResponseEntity<String> response = null;
		String respEnc = "";
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
			String input = this.jsonDecode(data);
			if (input == null || input.isEmpty()) {
				resp.setCodigo(2);
				resp.setMensaje("Parametro inválido ");
				respEnc = util.encriptaInformacionB64(util.generaKeySource(ConceptosUtil.AESKEY), gson.toJson(resp));
				response = new ResponseEntity<>(respEnc, HttpStatus.BAD_REQUEST);
			} else {
				response = frec.registrarFrecuente(input);
			}
		} catch (Exception ex) {
			resp.setCodigo(-1);
			resp.setMensaje("Error por excepcion " + ex);
			respEnc = util.encriptaInformacionB64(util.generaKeySource(ConceptosUtil.AESKEY), gson.toJson(resp));
			response = new ResponseEntity<>(respEnc, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@RequestMapping(value = "/consultarFrecuentesRecargas", method = RequestMethod.POST)
	public ResponseEntity<String> consultarFrecuentesRecargas(@RequestBody String data) {
		String result = "";
		Gson gson = new Gson();
		Utilerias util = new Utilerias();
		Respuesta resp = new Respuesta();
		ResponseEntity<String> response = null;
		String respEnc = "";
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
			String input = this.jsonDecode(data);
			if (input == null || input.isEmpty()) {
				resp.setCodigo(2);
				resp.setMensaje("Parametro inválido ");
				respEnc = util.encriptaInformacionB64(util.generaKeySource(ConceptosUtil.AESKEY), gson.toJson(resp));
				response = new ResponseEntity<>(respEnc, HttpStatus.BAD_REQUEST);
			} else {
				response = frec.consultarFrecuentesRecargas(input);
			}
		} catch (Exception ex) {
			resp.setCodigo(-1);
			resp.setMensaje("Error por excepcion " + ex);
			respEnc = util.encriptaInformacionB64(util.generaKeySource(ConceptosUtil.AESKEY), gson.toJson(resp));
			response = new ResponseEntity<>(respEnc, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	@RequestMapping(value = "/consultarFrecuentesTerceros", method = RequestMethod.POST)
	public ResponseEntity<String> consultarFrecuentesTerceros(@RequestBody String data) {
		String result = "";
		Gson gson = new Gson();
		Utilerias util = new Utilerias();
		Respuesta resp = new Respuesta();
		ResponseEntity<String> response = null;
		String respEnc = "";
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
			String input = this.jsonDecode(data);
			if (input == null || input.isEmpty()) {
				resp.setCodigo(2);
				resp.setMensaje("Parametro inválido ");
				respEnc = util.encriptaInformacionB64(util.generaKeySource(ConceptosUtil.AESKEY), gson.toJson(resp));
				response = new ResponseEntity<>(respEnc, HttpStatus.BAD_REQUEST);
			} else {
				response = frec.consultarFrecuentesTerceros(input);
			}
		} catch (Exception ex) {
			resp.setCodigo(-1);
			resp.setMensaje("Error por excepcion " + ex);
			respEnc = util.encriptaInformacionB64(util.generaKeySource(ConceptosUtil.AESKEY), gson.toJson(resp));
			response = new ResponseEntity<>(respEnc, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	@RequestMapping(value = "/desactivarFrecuente", method = RequestMethod.POST)
	public ResponseEntity<String> desactivarFrecuente(@RequestBody String data) {
		String result = "";
		Gson gson = new Gson();
		Utilerias util = new Utilerias();
		Respuesta resp = new Respuesta();
		ResponseEntity<String> response = null;
		String respEnc = "";
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
			String input = this.jsonDecode(data);
			if (input == null || input.isEmpty()) {
				resp.setCodigo(2);
				resp.setMensaje("Parametro inválido ");
				respEnc = util.encriptaInformacionB64(util.generaKeySource(ConceptosUtil.AESKEY), gson.toJson(resp));
				response = new ResponseEntity<>(respEnc, HttpStatus.BAD_REQUEST);
			} else {
				response = frec.desactivarFrecuente(input);
			}
		} catch (Exception ex) {
			resp.setCodigo(-1);
			resp.setMensaje("Error por excepcion " + ex);
			respEnc = util.encriptaInformacionB64(util.generaKeySource(ConceptosUtil.AESKEY), gson.toJson(resp));
			response = new ResponseEntity<>(respEnc, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}


	public String jsonDecode(String json) {
		String jsonDecoded = "";
		Utilerias util = new Utilerias();
		try {
			String jsonURLDecoded = URLDecoder.decode(json, "UTF-8");
			jsonURLDecoded = jsonURLDecoded.replace(" ", "+");
			jsonDecoded = util.desencriptaInformacionB64(util.generaKeySource(ConceptosUtil.AESKEY), jsonURLDecoded);
		} catch (Exception ex) {
		}
		return jsonDecoded;
	}

	@RequestMapping(value = "/encriptar", method = RequestMethod.POST)
	public ResponseEntity<String> encriptar(@RequestBody String data) {
		ResponseEntity<String> response = null;
		Utilerias util = new Utilerias();
		Gson gson = new Gson();
		Respuesta resp = new Respuesta();

		String res = util.encriptaInformacionB64(util.generaKeySource(ConceptosUtil.AESKEY), gson.toJson(data));
		resp.setCodigo(0);
		resp.setMensaje(res);
		/*
		 * try { String jsonURLDecoded = URLDecoder.decode(data, "UTF-8");
		 * jsonURLDecoded = jsonURLDecoded.replace(" ", "+"); String jsonDecoded =
		 * util.desencriptaInformacionB64(util.generaKeySource(ConceptosUtil.AESKEY),
		 * jsonURLDecoded); String stop3="Desencript: "+ jsonDecoded; }catch (Exception
		 * ex) { String stop1=""; } String stop2="";
		 */
		response = new ResponseEntity<>((gson.toJson(res)).toString(), HttpStatus.OK);
		return response;
	}

	@RequestMapping(value = "/desencriptar", method = RequestMethod.POST)
	public ResponseEntity<String> desencriptar(@RequestBody String data) {
		ResponseEntity<String> response = null;
		Utilerias util = new Utilerias();
		Gson gson = new Gson();
		String res = "";
		try {
			String jsonURLDecoded = URLDecoder.decode(data, "UTF-8");
			jsonURLDecoded = jsonURLDecoded.replace(" ", "+");
			String jsonDecoded = util.desencriptaInformacionB64(util.generaKeySource(ConceptosUtil.AESKEY),
					jsonURLDecoded);
			res = jsonDecoded;
		} catch (Exception ex) {
			String stop1 = "";
		}
		String stop2 = "";

		response = new ResponseEntity<>((gson.toJson(res)).toString(), HttpStatus.OK);
		return response;
	}
	
	@RequestMapping(value = "/crearEntradas", method = RequestMethod.POST)
	public ResponseEntity<String> crearEntradas(@RequestBody String json) {		
		Utilerias util = new Utilerias();
		Gson gson = new Gson();
		ResponseEntity<String> response;
		FrecuenteInsertOBJ input = gson.fromJson(json, FrecuenteInsertOBJ.class);
		String res="";
		try {
			res = util.encriptaInformacionB64(util.generaKeySource(ConceptosUtil.AESKEY), gson.toJson(input));	
		}catch(Exception ex) {
			
		}
		
		response = new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return response;
	}

}
