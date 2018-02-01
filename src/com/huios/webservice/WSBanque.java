package com.huios.webservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.huios.domaine.Client;
import com.huios.domaine.Compte;
import com.huios.domaine.CompteCourant;
import com.huios.service.IServiceLocal;


@Path("/banque")
public class WSBanque {

	@Inject
	IServiceLocal service;

	
	@GET
	@Path("/listAllClients")
	@Produces(MediaType.TEXT_PLAIN)
	public List<Client> getTousLesClients() {
		System.out.println("Coucou" + service.getTousLesClients() );
		return service.getTousLesClients();
	}
	
	@GET
	@Path("/compte/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Compte getCompteById(@PathParam("id") int id) {
		System.out.println("louis et maria" + service.getCompteById(id) );
		return service.getCompteById(id);
	}
	
	
	@GET
	@Path("/listAuthName/{authName}")
	@Produces(MediaType.TEXT_PLAIN)
	public List<Client> getClientsByConseillerAuthName(@PathParam("authName") String authName) {
//		System.out.println("louis et maria" + service.getCompteById(id) );
		return service.getClientsByConseillerAuthName(authName);
	}
	
//	@GET
//	@Path("/list/{id}")
//	@Produces(MediaType.TEXT_PLAIN)
//	public List<Compte> getComptesByID(@PathParam("id") int idClient) {
//		System.out.println("louis et maria" + service.getComptesByID(idClient) );
//		return service.getComptesByID(idClient);
//	}
	
	
	//////////////////////////////////////////////////
	//r�cup�ration d'un �l�m�nt
		@GET
		@Path("/test")
		@Produces(MediaType.TEXT_PLAIN)
		public String Test() {
			return "TEST";
		}
		// r�cup�ration d'une valeur apr�s envoi de param�tre
		@GET
		@Path("/conversion/{montant}")
		public double conversionED(@PathParam("montant")double mt) {
			return mt*1.24;
		}
		//r�cup�ration d'une liste d'�l�ments
		@GET
		@Path("/infos")
		//@Produces(MediaType.TEXT_PLAIN)
		@Produces(MediaType.APPLICATION_JSON)
		//@Produces(MediaType.APPLICATION_XML)
		public List<String> getInfos() {
			List<String> liste = new ArrayList<String>();
			liste.add("A");
			liste.add("B");
			liste.add("C");
			liste.add("D");
			
			return liste;
		}
}