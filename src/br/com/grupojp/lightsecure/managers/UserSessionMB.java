package br.com.grupojp.lightsecure.managers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.grupojp.lightsecure.business.UserBusiness;
import br.com.grupojp.lightsecure.model.User;

@SessionScoped
@ManagedBean
public class UserSessionMB {

	private User user;
	 
    @EJB
    private UserBusiness userBusiness;
 
    public User getUser(){
        if(user == null){
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            String userLogin = context.getUserPrincipal().getName();
 
            user = userBusiness.findUserByLogin(userLogin);
        }
 
        return user;
    }
 
    public boolean isUserAdmin(){ //Usuario é  admin
        return getRequest().isUserInRole("ADMIN");
    }
    public boolean isUserUsuario(){ //Usuario é usuario
        return getRequest().isUserInRole("USUARIO");
    }
 
    public String logOut(){ // Sair do sistema
        getRequest().getSession().invalidate();
        return "logout";
    }
 
    private HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

	public void setUser(User user) {
		this.user = user;
	}
    
	
}
