package com.contactor.services;

import java.util.List;

import com.contactor.dao.ContactFilter;
import com.contactor.dao.ContactFilterOnParam;
import com.contactor.dao.DAOContacts;
import com.contactor.dao.DAOFactory;
import com.contactor.model.Contact;

public class ServiceContact {
	
	/**
	 * Recupere un contact avec un id
	 * @param id
	 * @return
	 */
	public static Contact getContact(long id){
		return DAOFactory.getDAOContact().getContact(id);
	}
	
	/**
	 * Insert un contact en lui attribuant un id
	 * @param c
	 * @return
	 */
	public static long insertContact (Contact c){
		if(!isValid(c))
			return -1;
		else{
		DAOContacts dao = DAOFactory.getDAOContact();
		c.setId(dao.generateId());
		dao.insertContact(c);
		return c.getId();
		}
	}
	
	/**
	 * Met a jour le contact
	 * @param c
	 * @return
	 */
	public static boolean updateContact (Contact c){
		if(!isValid(c))
			return false;
		else
		return DAOFactory.getDAOContact().updateContact(c);
	}
	
	/**
	 * Supprime le contact
	 * @param c
	 * @return
	 */
	public static boolean removeContact (long id){
		return DAOFactory.getDAOContact().removeContact(id);
	}
	
	/**
	 * Recherche tout les contact dont le nom ou le prenom contient "name"
	 * @param name : la chaine a rechercher
	 * @return
	 */
	public static List<Contact> getContactListOnName (String name){
		return DAOFactory.getDAOContact().selectContactOnFilter(new ContactFilterOnParam(name) {
			@Override
			public boolean applyfilter(Contact contact) {
				return contact.getNom().contains(this.m_param) || contact.getPrenom().contains(m_param) || contact.getEmail().contains(m_param);
			}
		});
	}
	
	/**
	 * Recherche un contact dont l'email contient la chaine de caractere en parametre
	 * @param email : la chaine a recherchée dans les email
	 */
	public static List<Contact> getContactListOnEmail(String email){
		return DAOFactory.getDAOContact().selectContactOnFilter(new ContactFilterOnParam(email) {
			@Override
			public boolean applyfilter(Contact contact) {
				return contact.getEmail().contains(this.m_param);
			}
		});
	}
	
	/**
	 * Renvoi la liste de tout les contacts de la base
	 * @return
	 */
	public static List<Contact> getContactList(){
		return DAOFactory.getDAOContact().selectContactOnFilter(new ContactFilter() {
			@Override
			public boolean applyfilter(Contact contact) {
				return true;
			}
		});
	}
	
	public static boolean isValid (Contact contact){
		return !(contact.getNom().isEmpty() || contact.getPrenom().isEmpty());
	}
	
}

