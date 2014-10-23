package com.contactor.services;

import java.util.ArrayList;
import java.util.List;

import com.contactor.dao.DAOAdresses;
import com.contactor.dao.DAOContacts;
import com.contactor.dao.DAOFactory;
import com.contactor.model.Adresse;
import com.contactor.model.Contact;

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
	 * Recupere une liste d'adresse à partir d'un contact
	 * @param id
	 * @return
	 */
	public static List<Adresse> getAdresses (Contact contact){
		ArrayList<Adresse> list = new ArrayList<Adresse>();
		ArrayList<Long> adr_list_id = contact.getListAdresses();
		
		Adresse adr = null;
		
		for (Long adrId : adr_list_id) {
			adr = DAOFactory.getDAOAdresses().getAdresse(adrId);
			if(adr != null){
				list.add(adr);
			}
		}
		
		return list;
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
	public static boolean removeAdresse (long id){
		return DAOFactory.getDAOAdresses().removeAdresse(id);
	}
}
