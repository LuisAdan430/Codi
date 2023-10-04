package net.cero.ahorro.ws;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import net.cero.spring.config.IPAuthenticationProvider;

@Controller
public class FrecuentesServicios {
	@Autowired
	protected IPAuthenticationProvider authenticationManager;
	private static final Logger log = LogManager.getLogger(DesactivarFavorito.class);
	@PostMapping(path = "/insertarFrecuente", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> insertarFrecuente(@RequestBody String json){
		log.info("---->Insertar Frecuente<----");
		return new ResponseEntity<>(HttpStatus.OK); 
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
		log.info("---->Desactivar Frecuente<----");
		return new ResponseEntity<>(HttpStatus.OK); 
	}
	
}
