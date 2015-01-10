package br.com.grupojp.lightsecure.business;

import java.util.Date;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.grupojp.lightsecure.daos.StationRepository;
import br.com.grupojp.lightsecure.model.Station;
import br.com.grupojp.lightsecure.model.Status;

@Stateless
public class StationBusiness{

	@EJB
	private StationRepository stationRepository;
	 
	public Status putStatus(Station station){
		Status status = new Status();
		status.setId(null);
		status.setStatus(false);
		status.setDate(new Date());
		status.setStation(station);
		return status;
	}
	
	@RolesAllowed({"ADMIN"})
	public void save(Station station) {
		
		Status status = putStatus(station);
		station.setStatus(status);
		this.stationRepository.save(station);
		
	}

	@RolesAllowed({"ADMIN"})
	public void delete(Station station) {
		this.stationRepository.delete(station.getId(), Station.class);
		
	}

	@RolesAllowed({"ADMIN"})
	public Station update(Station station) {
		return this.stationRepository.update(station);
	}

	 
	public Station findByID(Long id) {
		return this.stationRepository.findByID(id); 
	}
	
	public Station findByIDLazy(Long id) {
		return this.stationRepository.findByIdLazy(id); 
	}

	 
	public List<Station> findAll() {
		return this.stationRepository.findAll();
	}
	
	public List<Station> findAllLazy(){
		return this.stationRepository.findAllLazy();
	}
	
	public Station findStationByRegistrationPlc(String registrationPlc) {
		return this.stationRepository.findStationByRegistrationPlc(registrationPlc);
	}


}
