package inMemoryDB;

public class Libro extends Articulo {

	private String isbn;

	public Libro(Long id, String nombre, String autor, Double precio, String isbn) {
		super(id, nombre, autor, precio);
		this.isbn = isbn;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public void detalleHijo() {
		System.out.println("ISBN: " + this.isbn);

	}

}