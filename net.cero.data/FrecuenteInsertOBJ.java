package net.cero.data;

public class FrecuenteInsertOBJ {
	
	private Integer ahCuenta;
	private Integer coServicioId;
	private String referencia;
	private Float monto;
	private String activo;
	private Integer usuarioCreacion;
	
	

	public void setAhCuenta(Integer ahCuenta) {
			this.ahCuenta = ahCuenta;
	}

	public Integer getAhCuenta() {
			return this.ahCuenta;
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

	public void setMonto(Float monto) {
			this.monto = monto;
	}

	public Float getMonto() {
			return this.monto;
	}

	public void setActivo(String activo) {
			this.activo = activo;
	}

	public String getActivo() {
			return this.activo;
	}

	public void setUsuarioCreacion(Integer usuarioCreacion) {
			this.usuarioCreacion = usuarioCreacion;
	}

	public Integer getUsuarioCreacion() {
			return this.usuarioCreacion;
	}

}
