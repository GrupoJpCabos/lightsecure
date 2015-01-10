package br.com.grupojp.lightsecure.daos;

import javax.ejb.Local;

import br.com.grupojp.lightsecure.model.User;

@Local
public interface UserRepository extends GenericRepository<User, Long>{
	
	public User findUserByLogin(String login);
	
	
}
