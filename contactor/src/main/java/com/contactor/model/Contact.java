package com.contactor.model;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.context.support.LiveBeansView;

public class Contact {
	private long id;
	private String nom;
	private String prenom;
	private String email;
	private Date date_naissance;
	private boolean actif;

	private ArrayList<Long> listAdresses;	//liste les id des adresses
	private long adresseLivraison;			//l'id de l'adresse de livraison

	public Contact() {
		ini();
	}
	
	public Contact(Contact other) {
		id = other.id;
		nom = other.nom;
		prenom = other.prenom;
		email = other.email;
		date_naissance = other.date_naissance;
		actif = other.actif;
		listAdresses = new ArrayList<Long> (other.listAdresses);
		adresseLivraison = other.adresseLivraison;
	}

	private void ini(){
		id = 0;
		nom = "";
		prenom = "";
		email = "";
		date_naissance = null;
		actif = false;
		listAdresses = new ArrayList<Long>();
		adresseLivraison = -1;
	}

	public ArrayList<Long> getListAdresses() {
		return listAdresses;
	}

	public boolean addAdresse(long adresse, boolean isLivraisonAdresse) {
		if(adresse < 0 || listAdresses.contains(adresse)){
			//mettre plutôt une exception éventuellement.
			return false;
		}
		this.listAdresses.add(adresse);
		if(isLivraisonAdresse)
			adresseLivraison = adresse;
		return true;
	}

	public long getAdresseLivraison() {
		return adresseLivraison;
	}

	public void setAdresseLivraison(long adresseLivraison) {
		this.adresseLivraison = adresseLivraison;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDate_naissance() {
		return date_naissance;
	}

	public void setDate_naissance(Date date_naissance) {
		this.date_naissance = date_naissance;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}
	
	@Override
	public int hashCode() {
		return nom.hashCode() + prenom.hashCode() + email.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Contact){
			Contact other = (Contact) obj;
			return other.id == this.id;
		}
		return super.equals(obj);
	}
}
