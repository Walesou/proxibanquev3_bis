package com.huios.dao;

import java.util.List;

import com.huios.domaine.Client;
import com.huios.domaine.Compte;
import com.huios.domaine.Conseiller;
import com.huios.service.ConseillerServiceException;

public interface Idao {
	
	/**
	 *  Permet de faire la v�rification de l'identifiant et du mot de passe 
	 * du conseiller
	 * @param courriel
	 * 			identifiant du conseiller
	 * @param motDePasse
	 * 			mot de passe du conseiller
	 * @return
	 * 		true ou false
	 */
	public boolean verifAuthentification(String courriel, String motDePasse) throws ConseillerServiceException;
	
	public List<Compte> getTousLesComptes();

	/**
	 * Renvoie la liste des comptes d’un client en fonction de sa clé primaire.
	 *
	 * @param idClient
	 *            la clé primaire du client
	 * @return la liste des compte du client
	 * @throws CompteOADException
	 *             erreur SQL en cas d’échec de la récupération
	 */
	List<Compte> getComptesByID(int idClient);

	/**
	 * Récupère un compte en fonction de sa clé primaire.
	 *
	 * @param id
	 *            la clé primaire du compte
	 * @return le compte correspondant
	 * @throws CompteOADException
	 *             erreur en cas d’échec de la récupération
	 */
	Compte getCompteById(int id);

	/**
	 * Changement du solde de deux comptes de manière atomique.
	 *
	 * Utile dans le cas de virement.
	 *
	 * @param compte1
	 *            le premier compte à mettre à jour
	 * @param compte2
	 *            le second compte à mettre à jour
	 * @throws CompteOADException
	 *             erreur en cas d’échec de la mise à jour
	 */
	void majComptesAtomique(Compte compte1, Compte compte2) throws CompteOADException;

	/**
	 * Fournit la liste des clients d’un conseiller en fonction de son identifiant
	 * d’authentification.
	 *
	 * @param authName
	 *            l’identifiant renseigner au moment de l’authentification
	 * @return la liste des clients du conseiller
	 * @throws ClientOADException
	 *             erreur si la requête SQL a échouée
	 */
	List<Client> getClientsByConseillerAuthName(String authName);

	/**
	 * Fournit la liste de tous les clients de la banque.
	 *
	 * @return la liste de tous les clients de la banque
	 * @throws ClientOADException
	 *             erreur si la requête SQL échoue
	 */
	List<Client> getTousLesClients();

	/**
	 * Renvoie un client à partir de son identifiant unique dans la base de
	 * donnée.
	 *
	 * @param id
	 *            l’identifiant dans la base de donnée du client à retourner
	 * @return le client
	 * @throws ClientOADException
	 *             erreur si la requête SQL échoue
	 */
	Client getClientByID(int id);

	/**
	 * Met à jour un client dans la base de donnée.
	 *
	 * @param client
	 *            le client à mettre à jour
	 * @throws ClientOADException
	 *             erreur si la mise à jour a échouée
	 */
	boolean majClient(Client client) throws ClientOADException;

	// ================================================================================================================================================================

	/**
	 * Effectue un virement de compte à compte. Il faut que les deux comptes soient
	 * domiciliés dans la banque. Les conseillers peuvent effectuer des virement
	 * depuis et vers des comptes de clients qui ne leur sont pas assignés.
	 *
	 * @param compteADebiter
	 *            le compte à débiter
	 * @param compteACrediter
	 *            le compte à créditer
	 * @param montant
	 *            le montant, positif et en euros, du virement
	 * @throws ConseillerServiceException
	 *             erreur si le montant est négatif
	 * @throws ConseillerServiceException
	 *             erreur si la modification du solde a échouée
	 */
	public boolean effectuerVirement(Compte compteADebiter, Compte compteACrediter, double montant)
			throws ConseillerServiceException;

	/**
	 * Retourne la liste des comptes d’un client.
	 *
	 * @param idClient
	 *            la clé primaire du client
	 * @return la liste de ses comptes
	 * @throws ConseillerServiceException
	 *             l’erreur si la récupération a échouée
	 */
	public List<Compte> getComptes(int idClient);

}
