package net.cero.codi.logica;

import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.apache.logging.log4j.LogManager;

import net.cero.data.FrecuentesConsultaResultOBJ;
import net.cero.data.Respuesta;
import net.cero.seguridad.utilidades.ConceptosUtil;
import net.cero.seguridad.utilidades.Utilerias;

import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FrecuentesServiciosLogic {
	private static final Logger log = LogManager.getLogger(FrecuentesServiciosLogic.class);

	public ResponseEntity<String> registrarFrecuente(String data) {
		ResponseEntity<String> responseEntity = null;
		Respuesta respuesta = new Respuesta();
		Gson gson = new Gson();
		Utilerias util = new Utilerias();
		Response response = null;
		try {
			if (data != "") {
				String host = "http://127.0.0.1:8080/CEROAhorroWS/rest/registrarFrecuente";
				// String host = ConstantesUtil.SERVICIO_BASE_CERO_AHORRO +
				host = host.replace(" ", "");
				MediaType media = MediaType.parse("application/json; charset=utf-8");
				String auth = Credentials.basic("ASP", "a5p2017$");
				OkHttpClient client = new OkHttpClient.Builder().connectTimeout(120, TimeUnit.SECONDS)
						.writeTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS).build();
				Request request = new Request.Builder().url(host).post(RequestBody.create(media, data))
						.header("Authorization", auth).build();
				try {
					response = client.newCall(request).execute();
					String res = response.body().string();
					respuesta = gson.fromJson(res, Respuesta.class);
				} catch (Exception ex) {
					respuesta.setCodigo(-1);
					respuesta.setMensaje("Se presento un problema interno");
					respuesta.setData(null);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			respuesta.setCodigo(-1);
			respuesta.setMensaje("Error por excepcion " + e);
			respuesta.setData(null);
			e.printStackTrace();
		}
		String respEnc = util.encriptaInformacionB64(util.generaKeySource(ConceptosUtil.AESKEY),
				gson.toJson(respuesta));
		responseEntity = new ResponseEntity<>(respEnc, HttpStatus.valueOf(response.code()));
		return responseEntity;
	}

	public ResponseEntity<String> consultarFrecuentesRecargas(String data) {
		ResponseEntity<String> responseEntity = null;
		Respuesta respuesta = new Respuesta();
		Gson gson = new Gson();
		Utilerias util = new Utilerias();
		Response response = null;
		try {
			if (data != "") {
				String host = "http://127.0.0.1:8080/CEROAhorroWS/rest/consultarFrecuentesRecargas";
				// String host = ConstantesUtil.SERVICIO_BASE_CERO_AHORRO +
				host = host.replace(" ", "");
				MediaType media = MediaType.parse("application/json; charset=utf-8");
				String auth = Credentials.basic("ASP", "a5p2017$");
				OkHttpClient client = new OkHttpClient.Builder().connectTimeout(120, TimeUnit.SECONDS)
						.writeTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS).build();
				Request request = new Request.Builder().url(host).post(RequestBody.create(media, data))
						.header("Authorization", auth).build();
				try {
					response = client.newCall(request).execute();
					String res = response.body().string();
					respuesta = gson.fromJson(res, Respuesta.class);
				} catch (Exception ex) {
					respuesta.setCodigo(-1);
					respuesta.setMensaje("Se presento un problema interno");
					respuesta.setData(null);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			respuesta.setCodigo(-1);
			respuesta.setMensaje("Error por excepcion " + e);
			respuesta.setData(null);
			e.printStackTrace();
		}
		String respEnc = util.encriptaInformacionB64(util.generaKeySource(ConceptosUtil.AESKEY),
				gson.toJson(respuesta));
		responseEntity = new ResponseEntity<>(respEnc, HttpStatus.valueOf(response.code()));
		return responseEntity;
	}
	
	
	public ResponseEntity<String> consultarFrecuentesTerceros(String data) {
		ResponseEntity<String> responseEntity = null;
		Respuesta respuesta = new Respuesta();
		Gson gson = new Gson();
		Utilerias util = new Utilerias();
		Response response = null;
		try {
			if (data != "") {
				String host = "http://127.0.0.1:8080/CEROAhorroWS/rest/consultarFrecuentesTerceros";
				// String host = ConstantesUtil.SERVICIO_BASE_CERO_AHORRO +
				host = host.replace(" ", "");
				MediaType media = MediaType.parse("application/json; charset=utf-8");
				String auth = Credentials.basic("ASP", "a5p2017$");
				OkHttpClient client = new OkHttpClient.Builder().connectTimeout(120, TimeUnit.SECONDS)
						.writeTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS).build();
				Request request = new Request.Builder().url(host).post(RequestBody.create(media, data))
						.header("Authorization", auth).build();
				try {
					response = client.newCall(request).execute();
					String res = response.body().string();
					respuesta = gson.fromJson(res, Respuesta.class);
				} catch (Exception ex) {
					respuesta.setCodigo(-1);
					respuesta.setMensaje("Se presento un problema interno");
					respuesta.setData(null);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			respuesta.setCodigo(-1);
			respuesta.setMensaje("Error por excepcion " + e);
			respuesta.setData(null);
			e.printStackTrace();
		}
		String respEnc = util.encriptaInformacionB64(util.generaKeySource(ConceptosUtil.AESKEY),
				gson.toJson(respuesta));
		responseEntity = new ResponseEntity<>(respEnc, HttpStatus.valueOf(response.code()));
		return responseEntity;
	}

	public ResponseEntity<String> desactivarFrecuente(String data) {
		ResponseEntity<String> responseEntity = null;
		Respuesta respuesta = new Respuesta();
		Gson gson = new Gson();
		Utilerias util = new Utilerias();
		Response response = null;
		try {
			if (data != "") {
				String host = "http://127.0.0.1:8080/CEROAhorroWS/rest/desactivarFrecuente";
				// String host = ConstantesUtil.SERVICIO_BASE_CERO_AHORRO +
				host = host.replace(" ", "");
				MediaType media = MediaType.parse("application/json; charset=utf-8");
				String auth = Credentials.basic("ASP", "a5p2017$");
				OkHttpClient client = new OkHttpClient.Builder().connectTimeout(120, TimeUnit.SECONDS)
						.writeTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS).build();
				Request request = new Request.Builder().url(host).post(RequestBody.create(media, data))
						.header("Authorization", auth).build();
				try {
					response = client.newCall(request).execute();
					String res = response.body().string();
					respuesta = gson.fromJson(res, Respuesta.class);
				} catch (Exception ex) {
					respuesta.setCodigo(-1);
					respuesta.setMensaje("Se presento un problema interno");
					respuesta.setData(null);
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			respuesta.setCodigo(-1);
			respuesta.setMensaje("Error por excepcion " + e);
			respuesta.setData(null);
			e.printStackTrace();
		}
		String respEnc = util.encriptaInformacionB64(util.generaKeySource(ConceptosUtil.AESKEY),
				gson.toJson(respuesta));
		responseEntity = new ResponseEntity<>(respEnc, HttpStatus.valueOf(response.code()));
		return responseEntity;
	}
}
