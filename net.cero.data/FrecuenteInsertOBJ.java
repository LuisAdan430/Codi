package net.cero.data;

public class FrecuenteInsertOBJ {
	
	private String cuenta;
	private Integer coServicioId;
	private String referencia;
	private Double monto;

	
	

	public void setCuenta(String cuenta) {
			this.cuenta = cuenta;
	}

	public String getCuenta() {
			return this.cuenta;
	}

	public void setCoServicioId(Integer coServicioId) {
			this.coServicioId = coServicioId;
	}

	public Integer getCoServicioId() {
			return this.coServicioId;
	}

	public void setReferencia(String referencia) {
			this.referencia = referencia;
	}

	public String getReferencia() {
			return this.referencia;
	}

	public void setMonto(Double monto) {
			this.monto = monto;
	}

	public Double getMonto() {
			return this.monto;
	}
	

}
