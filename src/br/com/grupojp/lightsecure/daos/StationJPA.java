package br.com.grupojp.lightsecure.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.TypedQuery;

import br.com.grupojp.lightsecure.model.Station;

@Stateless
public class StationJPA extends RepositoryJPA<Station, Long> implements StationRepository {

	public StationJPA() {
		super(Station.class);
	}

	/*
	 * Informa que uma transação não será necessária para essa consulta.
	 * Toda entidade da lista esta Detached ( Otimização ) 
	 */
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Station findStationByRegistrationPlc(String registrationPlc) {
		TypedQuery<Station> query = 
				getEm().createNamedQuery( Station.FIND_REGISTRATION_PLC, Station.class);
		query.setParameter("registrationPlc", registrationPlc);
		return query.getSingleResult();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Station> findAllLazy() {
		String jpql = "select s from Station s join fetch s.status join fetch s.address";
		TypedQuery<Station> query = getEm().createQuery(jpql, Station.class);
		return query.getResultList();
	}

	@Override
	public Station findByIdLazy(Long id) {
		String jpql = "select s from Station s join fetch s.status status "
				+ "join fetch s.address address "
				+ "where s.id=:id";
		TypedQuery<Station> query = getEm().createQuery(jpql, Station.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	
	
}
