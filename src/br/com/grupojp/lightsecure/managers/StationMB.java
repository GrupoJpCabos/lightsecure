package br.com.grupojp.lightsecure.managers;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.com.grupojp.lightsecure.business.StationBusiness;
import br.com.grupojp.lightsecure.execptions.UserExceptions;
import br.com.grupojp.lightsecure.model.Address;
import br.com.grupojp.lightsecure.model.Station;

@ManagedBean
@ViewScoped
public class StationMB extends AbstractMB implements Serializable{

	private final static String STAY_HERE = null; 
	
	private Station station;
	private Address address;
	private Station stationSelectedView;
	
	private List<Station> stations;
	
	@EJB
	private StationBusiness stationBusiness;
	
	public StationMB() {
		station = new Station();
		address = new Address();
	}
	
	
	public void cleanForm(){
		station = new Station();
		address = new Address();
		stations = null;
	}
	
	
	/*************************************************************************************************
	 *  
	 *  Create Stations
	 *  
	 ************************************************************************************************/
	public String create(){
		try {
			this.station.setAddress(this.address);
			this.stationBusiness.save(this.station);
			menssagemInfo("Estação cadastrada com sucesso");
		} catch (UserExceptions e) {
			menssagemErro(e.getMessage());
		}
		cleanForm();
		return STAY_HERE;
		
	}
	
	
	/*************************************************************************************************
	 *  
	 *  Delete Stations
	 *  
	 ************************************************************************************************/
	public String delete(){
		try {
			this.stationBusiness.delete(this.station);
			menssagemInfo("Estação deletada com sucesso");
		} catch (UserExceptions e) {
			menssagemErro(e.getMessage());
		}
		cleanForm();
		return STAY_HERE;
		
	}
	
	/*************************************************************************************************
	 *  
	 *  Update Stations
	 *  
	 ************************************************************************************************/
	public void update(){
		try {
			//this.station.setAddress(this.address);
			this.stationBusiness.update(this.station);

			menssagemInfo("Estação editada com sucesso");
		} catch (UserExceptions e) {
			menssagemErro(e.getMessage());
		}
		cleanForm();	
	}
	
	public void setStationRecup(Station station){
		this.station = station;
	}
	

	/*************************************************************************************************
	 *  
	 *  Find few stations
	 *  
	 ************************************************************************************************/
	
	public List<Station> getStations() {
		if(this.stations == null){
			this.stations = this.stationBusiness.findAllLazy();
		}
		return stations;
	}
	/*************************************************************************************************
	 *  
	 *  Find all stations
	 *  
	 ************************************************************************************************/
	public void onRowSelect(SelectEvent event) {
		this.station = (Station) event.getObject();
		//Long id =  (Long) ((Station) event.getObject()).getId();
		//this.station = stationBusiness.findByIDLazy(id);
		menssagemInfo("Seleção: " + ((Station) event.getObject()).getName());
    }
 
    public void onRowUnselect(UnselectEvent event) {
        
        menssagemInfo("Deseleção" + ((Station) event.getObject()).getName());
    }
	
	
	/*************************************************************************************************
	 *  
	 *  Getter & Setter
	 *  
	 ************************************************************************************************/

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = stationBusiness.findByID(station.getId());
		this.address = this.station.getAddress();
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		System.out.println("ADDRESS" + address.getId());
		this.address = address;
	}


	public Station getStationSelectedView() {
		return stationSelectedView;
	}


	public void setStationSelectedView(Station stationSelectedView) {
		this.stationSelectedView = stationSelectedView;
	}




	
	
	
	
}
