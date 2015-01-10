package br.com.grupojp.lightsecure.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.jboss.security.auth.spi.Util;

import br.com.grupojp.lightsecure.business.UserBusiness;
import br.com.grupojp.lightsecure.model.User;

@Stateless
@Path("/user")
@Consumes({"application/json"})
@Produces({"application/json"})
public class UserService{

	@EJB
	private UserBusiness userBusiness;
	
	
	@GET
	@Path("/{login}/{password}")
	public Response checkLogin(@PathParam("login")String login,
								  @PathParam("password")String password){
		try {
			User u = userBusiness.findUserByLogin(login);
			if ( u != null){
				password = Util.createPasswordHash("MD5", Util.BASE64_ENCODING, null, null, password);
				if(password.equals(u.getPassword())){
					return Response.ok(u).build();
				}

			}
			return Response.status(Response.Status.NOT_FOUND).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.NOT_MODIFIED).build();
			
		}
		
	}


}
