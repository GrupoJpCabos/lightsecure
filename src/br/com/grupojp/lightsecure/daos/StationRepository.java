package br.com.grupojp.lightsecure.daos;

import java.util.List;

import br.com.grupojp.lightsecure.model.Station;

public interface StationRepository extends GenericRepository<Station, Long> {

	public Station findStationByRegistrationPlc(String registrationPlc);
	
	public List<Station> findAllLazy();
	
	public Station findByIdLazy(Long id);
}
