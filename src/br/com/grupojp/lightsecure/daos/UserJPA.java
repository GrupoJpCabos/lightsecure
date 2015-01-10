package br.com.grupojp.lightsecure.daos;


import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.grupojp.lightsecure.model.User;

import com.uaihebert.factory.EasyCriteriaFactory;
import com.uaihebert.model.EasyCriteria;


@Stateless
public class UserJPA extends RepositoryJPA<User, Long> implements UserRepository{
	
	public UserJPA() {
		super(User.class);
	}

	public User findUserByLogin(String login){
		TypedQuery<User> q = getEm().createNamedQuery(User.FIND_LOGIN, User.class);
		q.setParameter("login", login);
		return q.getSingleResult();
		
//		EasyCriteria<User> easyCriteria = EasyCriteriaFactory.createQueryCriteria(getEm(), User.class);
//		easyCriteria.andEquals("login", login);
//		return easyCriteria.getSingleResult();
		
		
	
    }




	
	
}
