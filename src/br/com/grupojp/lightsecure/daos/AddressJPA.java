package br.com.grupojp.lightsecure.daos;

import br.com.grupojp.lightsecure.model.Address;

public class AddressJPA extends RepositoryJPA<Address, Integer> implements AddressRepository {

	public AddressJPA() {
		super(Address.class);
	}

	
}
