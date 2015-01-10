package br.com.grupojp.lightsecure.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.grupojp.lightsecure.business.StationBusiness;
import br.com.grupojp.lightsecure.business.UserBusiness;
import br.com.grupojp.lightsecure.model.Station;
import br.com.grupojp.lightsecure.model.User;
import br.com.grupojp.lightsecure.service.model.StationModelService;

@Stateless
@Path("/station")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StationService {
	
	@EJB
	private StationBusiness stationBusiness;
	
	@EJB
	private UserBusiness userBusiness;
	
	@GET
	@Path("/{id}/{status}")
	@Produces(MediaType.APPLICATION_XML)
	public String stationStatus(@PathParam("id")String registrationPlc,
				@PathParam("status")String status ){
		
		
		Station station = new Station();
		if(status.equalsIgnoreCase("off")){
			station = stationBusiness.findStationByRegistrationPlc(registrationPlc);
			station.getStatus().setStatus(false);
			station.getStatus().setDate(new Date());
			stationBusiness.update(station);
			
		}else if(status.equalsIgnoreCase("on")){
			station = stationBusiness.findStationByRegistrationPlc(registrationPlc);
			station.getStatus().setStatus(true);
			station.getStatus().setDate(new Date());
			stationBusiness.update(station);
			
		}else{
			return "aconteceu erro";
		}
		return "ok";
		
	}
	
	@POST
	@Path("/createUser")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public Response createUser(User user){
		if(user != null){
			userBusiness.save(user);
			return Response.ok().build();
		}else{
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@GET
	@Path("/{id}")
	public StationModelService stationStatus(@PathParam("id")Long id){
		Station s = stationBusiness.findByID(id);
		StationModelService sms = new StationModelService();
		sms.setId(s.getId());
		sms.setName(s.getName());
		sms.setDescription(s.getDescription());
		sms.setRegistrationPlc(s.getRegistrationPlc());
		sms.setDate(s.getStatus().getDate());
		sms.setStatus(s.getStatus().isStatus());
		
		return sms;
	}
	
	@GET
	public StationListService findAllStation(){
		List<StationModelService> lstStationModel = new ArrayList<StationModelService>();
		StationModelService sms = new StationModelService();
		List<Station> stations = stationBusiness.findAll();
		
		for (Station s : stations) {
			sms.setId(s.getId());
			sms.setName(s.getName());
			sms.setDescription(s.getDescription());
			sms.setRegistrationPlc(s.getRegistrationPlc());
			sms.setDate(s.getStatus().getDate());
			sms.setStatus(s.getStatus().isStatus());
			lstStationModel.add(sms);
			sms = new StationModelService();
		}
		
		
		return new StationListService(lstStationModel);
		
	}
	

}
