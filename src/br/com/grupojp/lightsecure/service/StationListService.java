package br.com.grupojp.lightsecure.service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.com.grupojp.lightsecure.service.model.StationModelService;

@XmlRootElement
public class StationListService {
	
	private List<StationModelService> stations = new ArrayList<StationModelService>();

	public StationListService(List<StationModelService> stations) {
		this.stations = stations;
	}

	public StationListService() {
		// TODO Auto-generated constructor stub
	}

	@XmlTransient
	public List<StationModelService> getStations() {
		return stations;
	}

}
