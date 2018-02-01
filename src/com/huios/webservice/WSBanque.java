package com.huios.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.huios.dao.CompteOADException;
import com.huios.domaine.Client;
import com.huios.domaine.Compte;
import com.huios.service.ConseillerServiceException;
import com.huios.service.IServiceLocal;

@Path("/banque")
public class WSBanque {

	@Inject
	IServiceLocal service;
	
	
	@GET
	@Path("/virement/{idCompteADebiter}/{idCompteACrediter}/{montant}")
	@Produces(MediaType.TEXT_PLAIN)
	public void effectuerVirement(@PathParam("idCompteADebiter") int idCompteADebiter, @PathParam("idCompteACrediter") int idCompteACrediter, @PathParam("montant") double montant) throws ConseillerServiceException {
	System.out.println("ca marche?");
		Compte compteADebiter = service.getCompteById(idCompteADebiter);
		Compte compteACrediter = service.getCompteById(idCompteACrediter);
		service.effectuerVirement(compteADebiter, compteACrediter, montant);
	}
	
	
	
	// Ok
	@GET
	@Path("/listAllCompte")
	@Produces(MediaType.TEXT_PLAIN)
	public List<Compte> getTousLesComtpes() {
		return service.getTousLesComptes();
	}
	
	// Ok
	@GET
	@Path("/listAllClients")
	@Produces(MediaType.TEXT_PLAIN)
	public List<Client> getTousLesClients() {
		//System.out.println("Coucou" + service.getTousLesClients());
		return service.getTousLesClients();
	}
	
	//KO
	@GET
	@Path("/compte/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Compte getCompteById(@PathParam("id") int id) {
		//System.out.println("louis et maria" + service.getCompteById(id));
		return service.getCompteById(id);
	}

	// Ok
	@GET
	@Path("/listAuthName/{authName}")
	@Produces(MediaType.TEXT_PLAIN)
	public List<Client> getClientsByConseillerAuthName(@PathParam("authName") String authName) {
		// System.out.println("louis et maria" + service.getCompteById(id) );
		return service.getClientsByConseillerAuthName(authName);
	}

	// Ok
	@GET
	@Path("/client/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Client getClientByID(@PathParam("id") int id) {
		return service.getClientByID(id);
	}
	
	//KO
	// @GET
	// @Path("/list/{id}")
	// @Produces(MediaType.TEXT_PLAIN)
	// public List<Compte> getComptesByID(@PathParam("id") int idClient) {
	// System.out.println("louis et maria" + service.getComptesByID(idClient) );
	// return service.getComptesByID(idClient);
	// }
	
	
	@GET
	@Path("/majCompte/{idCompte}/{nouveauSolde}")
	@Produces(MediaType.TEXT_PLAIN)
	public void majCompte(@PathParam("idCompte") int idCompte, @PathParam("nouveauSolde") double nouveauSolde) throws CompteOADException {
		System.out.println("Coucou hibou");
	}
	
	
	//////////////////////////////////////////////////
	// récupération d'un élémént
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String Test() {
		return "TEST";
	}

	// récupération d'une valeur après envoi de paramètre
	@GET
	@Path("/conversion/{montant}")
	public double conversionED(@PathParam("montant") double mt) {
		return mt * 1.24;
	}

	// récupération d'une liste d'éléments
	@GET
	@Path("/infos")
	// @Produces(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	// @Produces(MediaType.APPLICATION_XML)
	public List<String> getInfos() {
		List<String> liste = new ArrayList<String>();
		liste.add("A");
		liste.add("B");
		liste.add("C");
		liste.add("D");

		return liste;
	}
}
