package com.huios.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.huios.dao.ClientOADException;
import com.huios.dao.CompteOADException;
import com.huios.dao.Idao;
import com.huios.domaine.Client;
import com.huios.domaine.Compte;

@Stateless
public class ServiceImpl implements IServiceLocal, IServiceRemote {

	@Inject
	Idao dao;

	@Override
	public List<Compte> getTousLesComptes() {
		return dao.getTousLesComptes();
	}

	@Override
	public List<Client> getTousLesClients() {
		// TODO Auto-generated method stub
		return dao.getTousLesClients();
	}

	@Override
	public List<Compte> getComptesByID(int idClient) {
		// TODO Auto-generated method stub
		return dao.getComptesByID(idClient);
	}

	@Override
	public Compte getCompteById(int id) {
		// TODO Auto-generated method stub
		return dao.getCompteById(id);
	}

	@Override
	public void majComptesAtomique(Compte compte1, Compte compte2) throws CompteOADException {
		// TODO Auto-generated method stub
		dao.majComptesAtomique(compte1, compte2);
	}

	@Override
	public List<Client> getClientsByConseillerAuthName(String authName) {
		// TODO Auto-generated method stub
		return dao.getClientsByConseillerAuthName(authName);
	}

	@Override
	public Client getClientByID(int id) {
		// TODO Auto-generated method stub
		return dao.getClientByID(id);
	}

	@Override
	public void majClient(Client client) throws ClientOADException {
		// TODO Auto-generated method stub
		dao.majClient(client);
	}

	// ================================================================================================================================================

	@Override
	public void effectuerVirement(Compte compteADebiter, Compte compteACrediter, double montant)
			throws ConseillerServiceException {
		// TODO Auto-generated method stub
		dao.effectuerVirement(compteADebiter, compteACrediter, montant);

	}

	@Override
	public List<Compte> getComptes(int idClient) {
		// TODO Auto-generated method stub
		return dao.getComptes(idClient);
	}

}
