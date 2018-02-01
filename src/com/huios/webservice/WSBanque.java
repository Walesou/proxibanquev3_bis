package com.huios.webservice;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.huios.dao.ClientOADException;
import com.huios.domaine.Client;
import com.huios.domaine.Compte;
import com.huios.service.ConseillerServiceException;
import com.huios.service.IServiceLocal;

@Path("/banque")
public class WSBanque {

	@Inject
	IServiceLocal service;
	
	// Ok
	@GET
	@Path("/authentification/{courriel}/{motDePasse}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean verifAuthentification(@PathParam("courriel") String courriel,
			@PathParam("motDePasse") String motDePasse)
					throws ConseillerServiceException {
		return service.verifAuthentification(courriel, motDePasse);
	}
	
	
	// Ok
	@GET
	@Path("/virement/{idCompteADebiter}/{idCompteACrediter}/{montant}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean effectuerVirement(@PathParam("idCompteADebiter") int idCompteADebiter,
			@PathParam("idCompteACrediter") int idCompteACrediter, @PathParam("montant") double montant)
			throws ConseillerServiceException {
		Compte compteADebiter = service.getCompteById(idCompteADebiter);
		Compte compteACrediter = service.getCompteById(idCompteACrediter);
		return service.effectuerVirement(compteADebiter, compteACrediter, montant);
	}

	// Ok
	@GET
	@Path("/listAllClients")
	@Produces(MediaType.TEXT_PLAIN)
	public List<Client> getTousLesClients() {
		return service.getTousLesClients();
	}

	// Ok
	@GET
	@Path("/client/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Client getClientByID(@PathParam("id") int id) {
		return service.getClientByID(id);
	}

	// Ok
	@GET
	@Path("/listAuthName/{authName}")
	@Produces(MediaType.TEXT_PLAIN)
	public List<Client> getClientsByConseillerAuthName(@PathParam("authName") String authName) {
		return service.getClientsByConseillerAuthName(authName);
	}

	// Ok
	@GET
	@Path("/nomclient/{idClient}/{nom}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean modifierNomClient(@PathParam("idClient") int idClient, @PathParam("nom") String nom)
			throws ClientOADException {
		Client client = service.getClientByID(idClient);
		client.setNom(nom);
		return service.majClient(client);
	}

	// Ok
	@GET
	@Path("/prenomclient/{idClient}/{prenom}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean modifierPrenomClient(@PathParam("idClient") int idClient, @PathParam("prenom") String prenom)
			throws ClientOADException {
		Client client = service.getClientByID(idClient);
		client.setPrenom(prenom);
		return service.majClient(client);
	}

	// Ok
	@GET
	@Path("/courrielclient/{idClient}/{courriel}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean modifierCourrielClient(@PathParam("idClient") int idClient, @PathParam("courriel") String courriel)
			throws ClientOADException {
		Client client = service.getClientByID(idClient);
		client.setCourriel(courriel);
		return service.majClient(client);
	}

	// Ok
	@GET
	@Path("/adresseclient/{idClient}/{adresse}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean modifierAdresseClient(@PathParam("idClient") int idClient, @PathParam("adresse") String adresse)
			throws ClientOADException {
		Client client = service.getClientByID(idClient);
		client.setAdresse(adresse);
		return service.majClient(client);
	}

	// Ok
	@GET
	@Path("/compte/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Compte getCompteById(@PathParam("id") int id) {
		return service.getCompteById(id);
	}

	// Ok
	@GET
	@Path("/listComptes/{idClient}")
	@Produces(MediaType.TEXT_PLAIN)
	public List<Compte> getComptesByID(@PathParam("idClient") int idClient) {
		return service.getComptesByID(idClient);
	}

}
