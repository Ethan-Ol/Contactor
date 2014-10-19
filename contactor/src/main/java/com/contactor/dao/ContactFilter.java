package com.contactor.dao;

import com.contactor.model.Contact;

/**
 * @author Ethan
 *	Permet d'appliquer une méthode de filtre personnaliser lors de la recherche de contact.
 */

public interface ContactFilter {
	/**
	 * @param contact : le contact a analyser
	 * @return
	 * 		true si le contact doit être gardé
	 * 		false si le contact ne doit pas etre retenu par le filtre
	 */
	public boolean applyfilter(Contact contact);
}
