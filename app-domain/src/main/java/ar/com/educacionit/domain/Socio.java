package ar.com.educacionit.domain;

public class Socio {

	private Long id;
	private String nombre;
	private String apellido;
	private Integer anioAdhesion;
	
	
	public Socio(Long id, String nombre, String apellido, Integer anioAdhesion) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.anioAdhesion = anioAdhesion;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public Integer getAnioAdhesion() {
		return anioAdhesion;
	}


	public void setAnioAdhesion(Integer anioAdhesion) {
		this.anioAdhesion = anioAdhesion;
	}


	@Override
	public String toString() {
		return "Socio [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", anioAdhesion=" + anioAdhesion
				+ "]";
	}



	
}