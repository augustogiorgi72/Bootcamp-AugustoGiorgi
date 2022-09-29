package ar.com.educacionit.domain;

public class Marca implements Entity{

	private Long id;
	private String descripcion;
	private Long habilitada;
	private String cuit;
	
	public Marca(Long id, String descripcion, Long habilitada, String cuit) {
		this.id=id;
		this.descripcion = descripcion;
		this.habilitada = habilitada;
		this.cuit = cuit;
	}
	
	public Marca(String descripcion, Long habilitada, String cuit) {
		this.descripcion = descripcion;
		this.habilitada = habilitada;
		this.cuit = cuit;
		
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getHabilitada() {
		return habilitada;
	}

	public void setHabilitada(Long habilitada) {
		this.habilitada = habilitada;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	@Override
	public String toString() {
		return "Marca [id=" + id + ", descripcion=" + descripcion + ", habilitada=" + habilitada + ", cuit=" + cuit
				+ "]";
	}
	
}