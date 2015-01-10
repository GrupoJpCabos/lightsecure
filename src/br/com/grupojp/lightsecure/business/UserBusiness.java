package br.com.grupojp.lightsecure.business;

import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import br.com.grupojp.lightsecure.daos.UserRepository;
import br.com.grupojp.lightsecure.execptions.UserExceptions;
import br.com.grupojp.lightsecure.model.User;

/**
 * 
 * @author marcos
 * 
 * Utilizando o  REQUIRED, como interceptador da sessionbean, controlando o container.
 * REQUIRED se não tiver uma transação aberta , abre uma nova.
 * se tiver aberta , usa essa transação aberta.
 * 
 * 
 */


@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class UserBusiness {

	@EJB
	private UserRepository userRepository;
	
	/*
	 * Utilizando o Resource para controlar a Session Context, como lancaremos uma 
	 * exception na regra de negocio a qualquer momento,podemos optar pela declaração no
	 * UserExecptions.java ou utilizando a variavel de instancia context.  
	 * Optei utilizar as duas ( @author marcos )
	 * 
	 */
	@Resource
	private SessionContext context;
	
	public boolean allData(User user){
		
		boolean hasData = true;
		
		if(user == null){
			hasData = false;
		}
		if(user.getLogin() == null){
			hasData = false;
		}
		if(user.getPassword().equalsIgnoreCase(" ") ||
				user.getPassword() == null){
			hasData = false;
			
		}
		
		return hasData;
		
	}
	
	public boolean newLogin(User user){
		boolean canUseLogin = true;
		
		User user2 = findUserByLogin(user.getLogin());
		if(user2 == null){
			canUseLogin = false;
		}
		
		return canUseLogin;
	}
	
	public boolean isAdmin(User user){
		boolean isAdmin = true;
		if(!user.getRoles().contains("ADMIN")){
			isAdmin = false;
		}
		return isAdmin;
	}
	
	
	@RolesAllowed({"ADMIN"})
	public void save(User user) throws UserExceptions{
		
		if(!allData(user)){
			new UserExceptions("Verifique todos os dados do Usuário");
		}else{
			this.userRepository.save(user);
		}

	}
	
	@RolesAllowed({"ADMIN"})
	public void delete(User user)throws UserExceptions {
		
		
		
		if(isAdmin(user)){
			
			this.context.setRollbackOnly();
			throw new UserExceptions("Aconteceu um comportamento imprevisto ao "
					+ "realizar sua ação. Caso essa mensagem apareça novamente mais tarde,"
					+ " favor entrar em contato com o suporte pelo email suporte@grupojp.ind.br");
			
		}else{
			this.userRepository.delete(user.getId(), User.class);
		}
	}

	public User update(User user) {
		return this.userRepository.update(user);
	}

	 
	public User findByID(Long id) {
		return this.userRepository.findByID(id);
	}

	 
	public List<User> findAll() {
		return this.userRepository.findAll();
	}

	 
	public List<User> findAll(int begin, int end) {
		return this.userRepository.findAll(begin, end);
	}
	
	public User findUserByLogin(String login){
		return this.userRepository.findUserByLogin(login);
	}

}
