package br.com.grupojp.lightsecure.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.grupojp.lightsecure.business.StatusBusiness;
import br.com.grupojp.lightsecure.model.Status;

@ManagedBean
@ViewScoped
public class StatusMB extends AbstractMB{

	@EJB
	private StatusBusiness statusBusiness;
	
	private List<Status> lstStatus;
	private Status status;
	
	public StatusMB() {
		status = new Status();
	}
	
	
	public List<Status> getLstStatus() {	
		if(this.lstStatus == null){
			this.lstStatus = this.statusBusiness.findAll();
		}
		return this.lstStatus;
	}
	
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	
	
	
}
