package com.huios.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.huios.domaine.Client;
import com.huios.domaine.Compte;
import com.huios.domaine.CompteException;
import com.huios.domaine.Conseiller;
import com.huios.service.ConseillerServiceException;

@Singleton
public class DaoImpl implements Idao {
	@PersistenceContext
	EntityManager em;
	

	@Override
	public boolean verifAuthentification(String courriel, String motDePasse) throws ConseillerServiceException {
		// TODO Auto-generated method stub
		
		Conseiller conseiller = null;
		Query query = em.createQuery("select alias from Conseiller alias where courriel = :courriel and motDePasse = :motDePasse");
		query.setParameter("courriel", courriel);
		query.setParameter("motDePasse", motDePasse);
		conseiller = (Conseiller) query.getSingleResult();
		
		if(conseiller == null) {
			return false;
		}
		else
			return true;			
	}

	
	// WebService
	@Override
	public boolean effectuerVirement(Compte compteADebiter, Compte compteACrediter, double montant)
			throws ConseillerServiceException {
		// TODO Auto-generated method stub
		if (montant <= 0) {
			throw new ConseillerServiceException("Le montant à débiter doit être positif");
		}

		if (compteADebiter.equals(compteACrediter)) {
			throw new ConseillerServiceException("On ne peut pas effectuer un virement entre même compte");
		}

		double montantADebiter = -montant;
		double montantACrediter = +montant;

		try {
			compteADebiter.modifierSolde(montantADebiter);
			compteACrediter.modifierSolde(montantACrediter);
		} catch (CompteException e) {
			throw new ConseillerServiceException("Erreur lors de la mise à jour du solde");
		}

		/*
		 * La transaction est effectuée de manière atomique pour éviter une mise à
		 * jour partielle des comptes.
		 *
		 * Idéalement on utiliserai les JTA, mais Tomcat ne les fournit pas ; il faut
		 * donc utiliser une unique méthode OAD.
		 */
		try {
			/*
			 * La mise à jour atomique remplace :
			 *
			 * compteOAD.majCompte(compteADebiter.getIdentifiant(),
			 * compteADebiter.getSolde());
			 * compteOAD.majCompte(compteACrediter.getIdentifiant(),
			 * compteACrediter.getSolde());
			 */
			this.majComptesAtomique(compteADebiter, compteACrediter);
			return true;
		} catch (CompteOADException e) {
			throw new ConseillerServiceException("Erreur lors de la mise à jour du compte dans la base de donnée");
		}
	}

	// WebService
	@Override
	public List<Client> getTousLesClients() {
		List<Client> clients = new ArrayList<>();
		Query query = em.createQuery("select alias from Client alias");
		clients = query.getResultList();
		return clients;
	}

	// WebService
	@Override
	public Client getClientByID(int id) {
		// TODO Auto-generated method stub
		return em.find(Client.class, id);
	}

	// WebService
	@Override
	public List<Client> getClientsByConseillerAuthName(String authName) {
		List<Client> clients = new ArrayList<>();
		Query query = em.createQuery("select alias from Client alias where alias.conseiller.nom like :authName ");
		query.setParameter("authName", "%" + authName + "%");
		clients = query.getResultList();
		return clients;
	}

	// WebService
	@Override
	public boolean majClient(Client client) throws ClientOADException {
		// TODO Auto-generated method stub
		try {
			em.merge(client);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	// WebService
	@Override
	public List<Compte> getComptesByID(int idClient) {
		List<Compte> comptes = new ArrayList<>();
		Query query = em.createQuery("select alias from Compte alias WHERE alias.client.identifiant = :idClient");
		query.setParameter("idClient", idClient);
		comptes = query.getResultList();
		return comptes;
	}

	// WebService
	@Override
	public Compte getCompteById(int id) {
		return em.find(Compte.class, id);
	}

	@Override
	public void majComptesAtomique(Compte compte1, Compte compte2) throws CompteOADException {
		em.merge(compte1);
		em.merge(compte2);
	}

	@Override
	public List<Compte> getTousLesComptes() {
		List<Compte> comptes = new ArrayList<>();
		Query query = em.createQuery("select alias from Compte alias");
		comptes = query.getResultList();
		return comptes;
	}

	// WebService
	@Override
	public List<Compte> getComptes(int idClient) {
		// TODO Auto-generated method stub
		List<Compte> comptes = new ArrayList<>();
		Query query = em.createQuery("select alias from Compte alias");
		comptes = query.getResultList();
		return comptes;
	}

}
