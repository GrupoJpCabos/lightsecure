package br.com.grupojp.lightsecure.managers;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.com.grupojp.lightsecure.model.Station;

public class StationDataModel extends ListDataModel<Station> implements SelectableDataModel<Station>{

	
	   public StationDataModel() { 

	    }  
	  
	    public StationDataModel(List<Station> data) {  
	    	super(data);	   
	    }  
	
	
	
	 
	public Station getRowData(String rowKey) {
		
		 @SuppressWarnings("unchecked")
		List<Station> stations = (List<Station>) getWrappedData();  
      
	        for(Station station : stations) {  
	            if(station.getName().equals(rowKey))  
	                return station;  
	        }  
		
		
		return null;
	}

	 
	public Object getRowKey(Station station) {  
     return station.getName(); 

	}

}
