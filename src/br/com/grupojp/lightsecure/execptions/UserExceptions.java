package br.com.grupojp.lightsecure.execptions;

import javax.ejb.ApplicationException;


/**
 * @author marcos
 *
 * Quando um método de um Session Bean lança uma System Exception, 
 * o EJB Container aborta a transação corrente
 */

@ApplicationException(rollback=true)
public class UserExceptions extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public UserExceptions(String message) {
	    super(message);
	}
	
}
