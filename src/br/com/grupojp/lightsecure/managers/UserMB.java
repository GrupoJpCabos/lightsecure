package br.com.grupojp.lightsecure.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.jboss.security.auth.spi.Util;

import br.com.grupojp.lightsecure.business.UserBusiness;
import br.com.grupojp.lightsecure.execptions.UserExceptions;
import br.com.grupojp.lightsecure.model.User;

@ManagedBean
@ViewScoped
public class UserMB extends AbstractMB {
	
	
	private User user;
	private List<User> users;
	private String confirmPassword;
	private String roles;
	
	@EJB
	private UserBusiness userBusiness;



	public UserMB() {
		user = new User();
	}
	
	
	public void cleanForm(){
		user = new User();
		users = null;
	}
	
	
	/*************************************************************************************************
	 *  
	 *  Create User
	 *  
	 ************************************************************************************************/
	public void create(){
		
		
		try {
			
			String password = this.user.getPassword();
			
			if(!password.equalsIgnoreCase(this.confirmPassword)){
				throw new UserExceptions("Senha Errada !");
			}
			if(password == null || password.equalsIgnoreCase(" ")){
				throw new UserExceptions("Senha invalido!");
			}
			this.user.setPassword(Util.createPasswordHash("MD5", Util.BASE64_ENCODING, null, null, password));
			this.user.getRoles().add(this.roles);
			this.user.setActive(true);
			this.userBusiness.save(this.user);
			
			menssagemInfo("Cadastrado com Sucesso");
			
			
		} catch (UserExceptions e) {
			menssagemErro(e.getMessage());
			System.err.print(e.getMessage());
			
		} catch (Exception e) {
			menssagemErro("Login ja existe");
		}
		cleanForm();
	}
	
	/*************************************************************************************************
	 *  
	 *  Add Roles for User
	 *  
	 ************************************************************************************************/	
	public void addRoles(User user, String permission){
		this.user = user;
		Set<String> roles = this.user.getRoles();
		
		try {
			
			if(roles.contains(permission)){
				roles.remove(permission);
			}else{
				roles.add(permission);
			}
			this.userBusiness.update(user);
			menssagemInfo("Permissão adicionada ao usuário: " + user.getLogin());
		} catch (UserExceptions e) {
			menssagemErro(e.getMessage());
			e.printStackTrace();
		}
		
	}

	/*************************************************************************************************
	 *  
	 *  Active User in the System 
	 *  
	 ************************************************************************************************/	
	public void isActive(){
		try {
			if(this.user.isActive()){
				this.user.setActive(false);
			}else{
				this.user.setActive(true);
			}
			this.userBusiness.update(user);
			menssagemInfo("Ativação aceita");
		} catch (UserExceptions e) {
			menssagemErro(e.getMessage());
			e.printStackTrace();
		}
		
		
	}
	
	/*************************************************************************************************
	 *  
	 *  Delete Users
	 *  
	 ************************************************************************************************/
	
	public void delete(){
		try {
			this.userBusiness.delete(this.user);
			menssagemInfo("Usuario " + this.user.getLogin() + " excluido com sucesso");
		} catch (UserExceptions e) {
			menssagemErro("" + e.getMessage());
		}
		cleanForm();
	}
	
	
	/*************************************************************************************************
	 *  
	 *  Update User
	 *  
	 ************************************************************************************************/
	
	public void update(){
		try {
			String password = this.user.getPassword();
			
			if(!password.equalsIgnoreCase(this.confirmPassword)){
				throw new UserExceptions("Senha Errada !");
			}
			if(password == null || password.equalsIgnoreCase(" ")){
				throw new UserExceptions("Senha invalido!");
			}
			
			
			this.user.setPassword(Util.createPasswordHash("MD5", Util.BASE64_ENCODING, null, null, password));
			this.userBusiness.update(user);
			menssagemInfo("Usuário "+ user.getLogin() + " editado com sucesso !");
		} catch (UserExceptions e) {
			menssagemErro(e.getMessage());
		
		} catch (Exception e) {
			menssagemErro("Login ja existe");
		}
		cleanForm();
	}

	/*************************************************************************************************
	 *  
	 *  Retrieve Users
	 *  
	 ************************************************************************************************/
	public List<User> getUsers(){
		if(this.users == null){
			this.users = new ArrayList<User>();
			this.users = this.userBusiness.findAll();
		}
		return this.users;
	}
	


	/**************************************************************************************************
	 * 
	 * 
	 * Getter & Setter
	 * 
	 * 
	 **************************************************************************************************/

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}


	public String getRoles() {
		return roles;
	}


	public void setRoles(String roles) {
		this.roles = roles;
	}
}
