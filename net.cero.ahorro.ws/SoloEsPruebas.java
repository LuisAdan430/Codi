package net.cero.ahorro.ws;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import net.cero.ahorro.logica.FrecuentesServiciosLogic;
import net.cero.data.FrecuenteInsertOBJ;
import net.cero.spring.config.IPAuthenticationProvider;

@Controller
public class FrecuentesServicios {
	@Autowired
	protected IPAuthenticationProvider authenticationManager;
	private static final Logger log = LogManager.getLogger(DesactivarFavorito.class);
	
	@RequestMapping(value = "/registrarFrecuente", method = RequestMethod.POST)
	public ResponseEntity<String> insertarFrecuente(@RequestBody String json){
		log.info("---->Insertar Frecuente<----");
		Gson gson = null;
		gson = new Gson();
		SecurityContext securityContext = null;
		securityContext = SecurityContextHolder.getContext();
		Authentication authenticate = null;
		authenticate = authenticationManager.authenticate(securityContext.getAuthentication());
		FrecuentesServiciosLogic servicio = null;
		servicio = new FrecuentesServiciosLogic();
		if(!authenticate.isAuthenticated()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		FrecuenteInsertOBJ request = null;
		request = new FrecuenteInsertOBJ();
		
		request = gson.fromJson(json, FrecuenteInsertOBJ.class);
		String prueba = servicio.registrarFrecuente(request);
		
		return new ResponseEntity<>(prueba,HttpStatus.ACCEPTED); 
	}
	
	
	
	
	@PostMapping(path = "/consultarFrecuenteTerceros", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> consultarFrecuenteTerceros(@RequestBody String json){
		log.info("---->Consultar Frecuente Terceros<----");
		return new ResponseEntity<>(HttpStatus.OK); 
	}
	@PostMapping(path = "/consultarFrecuenteRecargas", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> consultarFrecuenteRecargas(@RequestBody String json){
		log.info("---->Consultar Frecuente Recargas<----");
		return new ResponseEntity<>(HttpStatus.OK); 
	}
	@PostMapping(path = "/desactivarFrecuente", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> desactivarFrecuente(@RequestBody String json){
		log.info("---->Desac<----");
		return new ResponseEntity<>(HttpStatus.OK); 
	}
	
}
