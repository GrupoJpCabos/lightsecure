package br.com.grupojp.lightsecure.service.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class StationModelService {
	
	
	@XmlElement(name="id")
	private Long id;
	
	@XmlElement(name="plc_id")
	private String registrationPlc;
	
	@XmlElement(name="name")
	private String name;
	
	@XmlElement(name="description")
	private String description;

	@XmlElement(name="status")
	private boolean status;
	
	@XmlElement(name="date")
	private Date date;

	public StationModelService() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegistrationPlc() {
		return registrationPlc;
	}

	public void setRegistrationPlc(String registrationPlc) {
		this.registrationPlc = registrationPlc;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
