package br.com.grupojp.lightsecure.daos;

import javax.ejb.Stateless;

import br.com.grupojp.lightsecure.model.Status;

@Stateless
public class StatusJPA extends RepositoryJPA<Status, Long> implements StatusRepository{

	public StatusJPA() {
		super(Status.class);
	}
}
