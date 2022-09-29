package ar.com.educacionit.daos;

import ar.com.educacionit.domain.Socio;

public interface SocioDao {

	public Socio create (Socio socio);
	public Socio read (Long id);
	public Socio update (Socio socioToUpdate);
	public Socio delete (Long id);
	
	
	
}