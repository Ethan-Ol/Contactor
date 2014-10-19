package com.contactor.services;

import com.contactor.dao.DAOAdresses;
import com.contactor.dao.DAOContacts;
import com.contactor.dao.DAOFactory;
import com.contactor.model.Adresse;

public class ServiceAdresse {
	
	/**
	 * Recupere une adresse avec un id
	 * @param id
	 * @return
	 */
	public static Adresse getAdresse (long id){
		return DAOFactory.getDAOAdresses().getAdresse(id);
	}
	
	/**
	 * Insert une adresse en lui attribuant un id
	 * @param c
	 * @return
	 */
	public static long insertAdresse (Adresse adresse){
		DAOAdresses dao = DAOFactory.getDAOAdresses();
		adresse.setId(dao.generateId());
		dao.insertAdresse(adresse);
		return adresse.getId();
	}
	
	/**
	 * Met a jour l'adresse
	 * @param c
	 * @return
	 */
	public static boolean updateAdresse (Adresse adresse){
		return DAOFactory.getDAOAdresses().updateAdresse(adresse);
	}
	
	/**
	 * Supprime l'adresse de la base
	 * @param c
	 * @return
	 */
	public static boolean removeAdresse (Adresse c){
		return DAOFactory.getDAOAdresses().removeAdresse(c.getId());
	}
}
