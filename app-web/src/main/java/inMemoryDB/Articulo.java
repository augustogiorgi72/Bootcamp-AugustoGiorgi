package inMemoryDB;

public abstract class Articulo {

	private Long id;
	private String nombre;
	private String autor;
	private Double precio;

	public Articulo(Long id, String nombre, String autor, Double precio) {
		this.id = id;
		this.nombre = nombre;
		this.autor = autor;
		this.precio = precio;
	}

	public Articulo() {
	}

	public void detalle() {
		System.out.println(this.getNombre());
		System.out.println(this.getAutor());
		System.out.println(this.getPrecio());

		this.detalleHijo();
		System.out.println("");
	}

	public abstract void detalleHijo();

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

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Articulo [id=" + id + ", nombre=" + nombre + ", autor=" + autor + ", precio=" + precio + "]";
	}

}