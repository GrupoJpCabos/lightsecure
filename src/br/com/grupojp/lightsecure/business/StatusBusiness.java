package br.com.grupojp.lightsecure.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.grupojp.lightsecure.daos.StatusRepository;
import br.com.grupojp.lightsecure.model.Status;

@Stateless
public class StatusBusiness{

	@EJB
	private StatusRepository repository;
	 
	public void save(Status status) {
		this.repository.save(status);
		
	}

	 
	public void delete(Status status) {
		this.repository.delete(status.getId(),Status.class);
		
	}

	 
	public Status update(Status status) {
		return this.repository.update(status);
	}

	 
	public Status findByID(Long id) {
		return this.repository.findByID(id);
	}

	 
	public List<Status> findAll() {
		return this.repository.findAll();
	}

	 
	public List<Status> findAll(int begin, int end) {
		return this.repository.findAll(begin, end);
	}

}
