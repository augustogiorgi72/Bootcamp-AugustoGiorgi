package ar.com.educacionit.daos.impl;


import ar.com.educacionit.daos.SocioDao;
import ar.com.educacionit.daos.db.ConexionDB;
import ar.com.educacionit.daos.db.ConnectionException;
import ar.com.educacionit.domain.Socio;	

public class SocioDaoImpl implements SocioDao {

	private ConexionDB con;
	
	public SocioDaoImpl () {
		
		this.con = new ConexionDB("root","1234");
	}

	public Socio create(Socio socio) {
		try(ConexionDB con = this.con.open();){
			socio.setId(10L);
		
		} catch (ConnectionException ce) {
			
		}
		return socio;
	}

	@Override
	public Socio read(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Socio update(Socio socioToUpdate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Socio delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	


}