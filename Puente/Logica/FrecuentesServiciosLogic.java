package net.cero.codi.logica;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.cero.data.FrecuentesConsultaResultOBJ;
import net.cero.data.Respuesta;
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

	public Respuesta registrarFrecuente(String data) {
		Respuesta respuesta = new Respuesta();
		Gson gson = new Gson();
		try {
			if (data != "") {
				String host = "http://127.0.0.1:8080/CEROAhorroWS/rest/registrarFrecuente";
				// String host = ConstantesUtil.SERVICIO_BASE_CERO_AHORRO +
				// "DesactivarFavortio";
				host = host.replace(" ", "");
				MediaType media = MediaType.parse("application/json; charset=utf-8");
				String auth = Credentials.basic("ASP", "a5p2017$");
				OkHttpClient client = new OkHttpClient.Builder().connectTimeout(120, TimeUnit.SECONDS)
						.writeTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS).build();
				Request request = new Request.Builder().url(host).post(RequestBody.create(media, data))
						.header("Authorization", auth).build();
				Response response = client.newCall(request).execute();
				String res = response.body().string();
				Respuesta respServicio = gson.fromJson(res, Respuesta.class);
				respuesta.setCodigo(respServicio.getCodigo());
				respuesta.setMensaje(respServicio.getMensaje());
			}

		} catch (Exception e) {
			respuesta.setCodigo(-1);
			respuesta.setMensaje("Error por excepcion " + e);
			e.printStackTrace();
		}
		return respuesta;
	}

	public FrecuentesConsultaResultOBJ consultarFrecuentes(String data) {
		FrecuentesConsultaResultOBJ respuesta = new FrecuentesConsultaResultOBJ();
		Gson gson = new Gson();
		try {
			if (data != "") {
				String host = "http://127.0.0.1:8080/CEROAhorroWS/rest/consultarFrecuentes";
				// String host = ConstantesUtil.SERVICIO_BASE_CERO_AHORRO +
				// "DesactivarFavortio";
				host = host.replace(" ", "");
				MediaType media = MediaType.parse("application/json; charset=utf-8");
				String auth = Credentials.basic("ASP", "a5p2017$");
				OkHttpClient client = new OkHttpClient.Builder().connectTimeout(120, TimeUnit.SECONDS)
						.writeTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS).build();
				Request request = new Request.Builder().url(host).post(RequestBody.create(media, data))
						.header("Authorization", auth).build();
				Response response = client.newCall(request).execute();
				String res = response.body().string();
				Respuesta respServicio = gson.fromJson(res, Respuesta.class);
				respuesta.setCodigo(respServicio.getCodigo());
				respuesta.setMensaje(respServicio.getMensaje());
			}

		} catch (Exception e) {
			respuesta.setCodigo(-1);
			respuesta.setMensaje("Error por excepcion " + e);
			e.printStackTrace();
		}
		return respuesta;
	}

	public Respuesta desactivarFrecuente(String data) {
		Respuesta respuesta = new Respuesta();
		Gson gson = new Gson();
		try {
			if (data != "") {
				String host = "http://127.0.0.1:8080/CEROAhorroWS/rest/desactivarFrecuente";
				// String host = ConstantesUtil.SERVICIO_BASE_CERO_AHORRO +
				// "DesactivarFavortio";
				host = host.replace(" ", "");
				MediaType media = MediaType.parse("application/json; charset=utf-8");
				String auth = Credentials.basic("ASP", "a5p2017$");
				OkHttpClient client = new OkHttpClient.Builder().connectTimeout(120, TimeUnit.SECONDS)
						.writeTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS).build();
				Request request = new Request.Builder().url(host).post(RequestBody.create(media, data))
						.header("Authorization", auth).build();
				Response response = client.newCall(request).execute();
				String res = response.body().string();
				Respuesta respServicio = gson.fromJson(res, Respuesta.class);
				respuesta.setCodigo(respServicio.getCodigo());
				respuesta.setMensaje(respServicio.getMensaje());
			}

		} catch (Exception e) {
			respuesta.setCodigo(-1);
			respuesta.setMensaje("Error por excepcion " + e);
			e.printStackTrace();
		}
		return respuesta;
	}
}
