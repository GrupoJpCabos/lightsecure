package br.com.grupojp.lightsecure.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

@Entity
@Table(name="station")
@NamedQueries ({
	@NamedQuery(name="Station.findStationByRegistrationPlc",
			query="select s from Station s where s.registrationPlc= :registrationPlc"),
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Station {
	
	public final static String FIND_REGISTRATION_PLC = "Station.findStationByRegistrationPlc";
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@XmlElement(name="id")
	private Long id;
	
	@XmlElement(name="plc_id")
	private String registrationPlc;
	
	@XmlElement(name="name")
	private String name;
	
	@XmlElement(name="description")
	private String description;
	
	@OneToOne(orphanRemoval=true, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.LAZY)
	@JoinColumn(name="address_id")
	private Address address;
	
	@OneToOne(mappedBy="station",cascade={CascadeType.PERSIST,CascadeType.MERGE}, orphanRemoval=true, fetch=FetchType.LAZY)
	@XmlElement(name="status")
	private Status status;
	
	public Station() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getRegistrationPlc() {
		return registrationPlc;
	}

	public void setRegistrationPlc(String registrationPlc) {
		this.registrationPlc = registrationPlc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((registrationPlc == null) ? 0 : registrationPlc.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Station other = (Station) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (registrationPlc == null) {
			if (other.registrationPlc != null)
				return false;
		} else if (!registrationPlc.equals(other.registrationPlc))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}









	public static class AddressAdapter extends XmlAdapter<String, Address>{

		@Override
		public String marshal(Address a) throws Exception {
			return a.getStreet();
		}

		@Override
		public Address unmarshal(String address) throws Exception {
			return new Address();
		}
		
	}
	
	
}
