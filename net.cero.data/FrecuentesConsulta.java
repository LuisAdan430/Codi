package net.cero.data;

public class FrecuentesConsulta {
	private Integer id;
	private String servicio;
	private String tipoProducto;
	private String tipoServicio;
	private String nememp;
	private String subemp;
	private Integer comision;
	private String codigo;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public String getServicio() {
		return this.servicio;
	}

	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public String getTipoProducto() {
		return this.tipoProducto;
	}

	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	public String getTipoServicio() {
		return this.tipoServicio;
	}

	public void setNememp(String nememp) {
		this.nememp = nememp;
	}

	public String getNememp() {
		return this.nememp;
	}

	public void setSubemp(String subemp) {
		this.subemp = subemp;
	}

	public String getSubemp() {
		return this.subemp;
	}

	public void setComision(Integer comision) {
		this.comision = comision;
	}

	public Integer getComision() {
		return this.comision;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return this.codigo;
	}

}
