package com.contactor.dao;

/**
 * @author Ethan
 *
 * Cette classe permet de récupérer le DAO que l'on souhaite pour les contact et les adresses 
 * (pour le projet une seule implémentation existe pour chacun des deux DAO contact et adresse). 
 */

public class DAOFactory {
	public static DAOContacts getDAOContact(){
		return DAOContactSimulate.getInstance();
	}
	
	public static DAOAdresses getDAOAdresses(){
		return DAOAdresseSimulate.getInstance();
	}
}
