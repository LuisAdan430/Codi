package net.cero.ahorro.logica;
import net.cero.data.FrecuentesConsulta;
import net.cero.data.FrecuentesDesactivar;
import net.cero.data.FrecuentesOBJ;
import net.cero.data.Respuesta;

public class FrecuentesServiciosLogic {
	public Boolean validarInputRegistrarFrecuente(FrecuentesOBJ data) {
		return true;
	}
	
	public Respuesta registrarFrecuente(FrecuentesOBJ data) {
		Respuesta respuesta = new Respuesta();
		return respuesta;
	}
	
	public Boolean validarInputConsulta(String data) {
		return true;
	}
	public FrecuentesConsulta consultarFrecuentes(String tipo) {
		FrecuentesConsulta consulta = new FrecuentesConsulta();
		/*
		 
		 * */
		return consulta;
	}
	
	public Boolean validarInputDesactivarFrecuente(FrecuentesDesactivar data) {
		return true;
	}
	
	public Respuesta desactivarFrecuente (FrecuentesDesactivar data) {
		Respuesta respuesta = new Respuesta();
		return respuesta;
	}
}
