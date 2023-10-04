package net.cero.ahorro.logica;

import net.cero.data.FrecuentesConsultaResultOBJ;
import net.cero.data.FrecuentesDesactivar;
import net.cero.data.FrecuenteConsultaPeticionOBJ;
import net.cero.data.FrecuenteInsertOBJ;
import net.cero.data.Respuesta;

public class FrecuentesServiciosLogic {

	public Respuesta registrarFrecuente(FrecuenteInsertOBJ data) {
		Respuesta respuesta = new Respuesta();
		return respuesta;
	}

	public FrecuentesConsultaResultOBJ consultarFrecuentesRecargas(FrecuenteConsultaPeticionOBJ tipo) {
		FrecuentesConsultaResultOBJ consulta = new FrecuentesConsultaResultOBJ();
		/*
		 
		 * */
		return consulta;
	}

	public FrecuentesConsultaResultOBJ consultarFrecuentesTerceros(FrecuenteConsultaPeticionOBJ tipo) {
		FrecuentesConsultaResultOBJ consulta = new FrecuentesConsultaResultOBJ();
		/*
		 
		 * */
		return consulta;
	}

	public Respuesta desactivarFrecuente(FrecuentesDesactivar data) {
		Respuesta respuesta = new Respuesta();
		return respuesta;
	}
}
