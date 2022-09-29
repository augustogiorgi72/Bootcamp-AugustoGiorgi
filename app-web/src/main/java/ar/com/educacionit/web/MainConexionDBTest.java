package ar.com.educacionit.web;

import ar.com.educacionit.daos.ArticuloDao;
import ar.com.educacionit.daos.OrdenDao;
import ar.com.educacionit.daos.SocioDao;
import ar.com.educacionit.daos.impl.OrdenDaoImpl;
import ar.com.educacionit.domain.Articulo;
import ar.com.educacionit.domain.Orden;


public class MainConexionDBTest {

	public MainConexionDBTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		
		//SocioDao daoSocio = new SocioDaoImpl();
		
		//Socio newSocio = new Socio (001L,"Brenda","Narbona",2000);
		
		//daoSocio.create(newSocio);
		
		
		/*ArticuloDao daoArt = new ArticuloDaoImpl();
		
		//Articulo newArticulo = new Articulo(6L,"Algo",10f);
		
		daoArt.create(newArticulo);*/
		
		
		OrdenDao daoOrd = new OrdenDaoImpl();
		
		//crear la orden para usar en la prueba
		Orden neworden = new Orden (1L,"Test",150f);
		
		daoOrd.create(neworden);
		
	}

}