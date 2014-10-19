package com.contactor.dao;

import java.util.ArrayList;
import java.util.List;

import com.contactor.model.Contact;

public interface DAOContacts extends DAOObject {
	public boolean insertContact(Contact contact);
	public Contact getContact (long id);
	public boolean updateContact (Contact contact);
	public boolean removeContact (long id);
	
	public List<Contact> selectContactOnFilter(ContactFilter filter);
}
