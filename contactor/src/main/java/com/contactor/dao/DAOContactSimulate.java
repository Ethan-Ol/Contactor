package com.contactor.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.contactor.model.Adresse;
import com.contactor.model.Contact;

public class DAOContactSimulate implements DAOContacts {

	HashMap<Long, Contact> m_contacts;
	private long nextId;
	private static DAOContactSimulate instance = null; 

	private DAOContactSimulate() {
		m_contacts = new HashMap<Long, Contact>();
		nextId = 0;
	}

	public static DAOContactSimulate getInstance(){
		if(instance == null){
			instance = new DAOContactSimulate();
		}
		return instance;
	}

	@Override
	public long generateId() {
		return nextId++;
	}

	@Override
	public boolean insertContact(Contact contact) {
		if(m_contacts.containsKey(contact.getId()))
			return false;
		else
			m_contacts.put(contact.getId(), contact);
		return true;
	}

	@Override
	public Contact getContact(long id) {
		return m_contacts.get(id);
	}

	@Override
	public boolean updateContact(Contact contact) {
		if(m_contacts.containsKey(contact.getId())){
			Contact a = m_contacts.get(contact.getId());
			a.setActif(contact.isActif());
			a.setAdresseLivraison(contact.getAdresseLivraison());
			a.setDate_naissance(contact.getDate_naissance());
			a.setEmail(contact.getEmail());
			a.setNom(contact.getNom());
			a.setPrenom(contact.getPrenom());
			return true;
		}
		else
			return false;
	}

	@Override
	public boolean removeContact(long id) {
		return m_contacts.remove(id) != null;
	}

	@Override
	public List<Contact> selectContactOnFilter(ContactFilter filter) {
		ArrayList<Contact> result = new ArrayList<Contact>();
		Set<Long> keys = m_contacts.keySet();
		
		for (Long key : keys) {
			Contact c = m_contacts.get(key);
			if(filter.applyfilter(c)){
				result.add(c);
			}
		}
		
		return result;
	}

}
