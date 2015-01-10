package br.com.grupojp.lightsecure.managers;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.jboss.security.auth.spi.Util;

import br.com.grupojp.lightsecure.business.UserBusiness;
import br.com.grupojp.lightsecure.execptions.UserExceptions;
import br.com.grupojp.lightsecure.model.User;

@ManagedBean
@SessionScoped
public class UserOptionsMB extends AbstractMB{

	private final static String HOME="home";
	
	private User user;
	private String confirmPassword;
	
	@EJB
	private UserBusiness userBusiness;
	
	public UserOptionsMB() {
		user = new User();
	}
	
	public String update(){
		FacesContext fc = FacesContext.getCurrentInstance();
		
		try {
			String password = this.user.getPassword();
			
			if(!password.equalsIgnoreCase(this.confirmPassword)){
				throw new UserExceptions("Senha Errada !");
			}
			if(password == null || password.equalsIgnoreCase(" ")){
				throw new UserExceptions("Senha invalido!");
			}
			this.user.setPassword(Util.createPasswordHash("MD5", Util.BASE64_ENCODING, null, null, password));
		
			
			System.out.println("ID USER" + this.user.getId());
			System.out.println("ROLES == " + this.user.getRoles().toString());
			
			this.userBusiness.update(this.user);
	
			fc.addMessage(null, new FacesMessage("Editado com Sucesso !"));
		} catch (UserExceptions e) {
			fc.addMessage(null, new FacesMessage(e.getMessage()));
		}
		return HOME;
	}
	
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
	
	
}
