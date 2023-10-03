package net.cero.data;

public class FrecuentesDesactivar {
	private int id;
	private int usuarioModificacion;
	
	public void setId (Integer id) {
		this.id = id;
	}

	public Integer getId () {
		return this.id;
	}

	public void setusuarioModificacion (Integer usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public Integer getusuarioModificacion () {
		return this.usuarioModificacion;
	}

}
