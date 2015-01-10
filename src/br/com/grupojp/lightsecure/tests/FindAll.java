package br.com.grupojp.lightsecure.tests;

import java.util.List;

import javax.ejb.EJB;

import br.com.grupojp.lightsecure.daos.UserJPA;
import br.com.grupojp.lightsecure.model.User;

public class FindAll {

	@EJB
	private static UserJPA business;
	
	public static void main(String[] args) {
		List<User> list = business.findAll();
		for (User user2 : list) {
			System.out.println(user2.getLogin());
		}

	}

}
