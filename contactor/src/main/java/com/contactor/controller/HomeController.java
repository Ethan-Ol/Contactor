package com.contactor.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.contactor.model.Adresse;
import com.contactor.model.Contact;
import com.contactor.services.ServiceAdresse;
import com.contactor.services.ServiceContact;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.search.query.ExpressionParser.str_return;
import com.google.appengine.api.search.query.QueryParser.arg_return;

@Controller
public class HomeController {
	
	private ModelAndView goHome(){
		return new ModelAndView("home");
	}
	
	@RequestMapping(value="/")
	public String base(ModelMap model){
		return home(model);
	}

	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String home(ModelMap model){
		System.err.println("Requete home");
		List<Contact> list = ServiceContact.getContactList();
		model.addAttribute("contactList",  list);
		model.addAttribute("msgEmptyList","Votre liste de contact est vide.");
		return "home";
	}
	
	@RequestMapping(value="/search", method = RequestMethod.POST)
	public String search(HttpServletRequest request, ModelMap model) {

		String value = request.getParameter("svalue");
		System.err.println("Requete search : " + value);
		
		if(value == null)
			return home(model);
		
		List<Contact> list = ServiceContact.getContactListOnName(value);
		model.addAttribute("contactList",  list);
		model.addAttribute("msgEmptyList","Aucun contact ne correspond à votre recherche.");
		
		return "home";
	}
	
	@RequestMapping(value="/deleteContact", method = RequestMethod.GET)
	public ModelAndView deleteContact(HttpServletRequest request, ModelMap model) {

		String id = request.getParameter("id");
		System.err.println("Delete : " + id);
		
		Integer contact_id = -1;
		try{
		contact_id = Integer.parseInt(id);
		}
		catch (Exception e){
			System.err.println(e.getMessage());
			
			List<Contact> list = new ArrayList<Contact>();
			model.addAttribute("contactList",  list);
			model.addAttribute("msgEmptyList","Erreur lors de la suppression du contact.");
			return new ModelAndView("home");
		}
		
		if(!ServiceContact.removeContact(contact_id)){
			List<Contact> list = new ArrayList<Contact>();
			model.addAttribute("contactList",  list);
			model.addAttribute("msgEmptyList","Erreur lors de la suppression du contact.");
			return new ModelAndView("home");
		}
		
		return new ModelAndView("redirect:home");
	}
	
	@RequestMapping(value="/deleteAdresse", method = RequestMethod.GET)
	public ModelAndView deleteAdresse(HttpServletRequest request, ModelMap model) {

		String id = request.getParameter("id");
		System.err.println("Delete adr : " + id);
		
		Integer adr_id = -1;
		try{
		adr_id = Integer.parseInt(id);
		}
		catch (Exception e){
			System.err.println(e.getMessage());
			
			List<Contact> list = new ArrayList<Contact>();
			model.addAttribute("contactList",  list);
			model.addAttribute("msgEmptyList","Erreur lors de la suppression de l'adresse");
			return new ModelAndView("home");
		}
		
		String cid = request.getParameter("cid");
		System.err.println("From contact: " + cid);
		
		Integer contact_id = -1;
		try{
		contact_id = Integer.parseInt(cid);
		}
		catch (Exception e){
			System.err.println(e.getMessage());
			
			List<Contact> list = new ArrayList<Contact>();
			model.addAttribute("contactList",  list);
			model.addAttribute("msgEmptyList","Erreur lors de la suppression de l'adresse");
			return new ModelAndView("home");
		}
		
		Contact c = ServiceContact.getContact(contact_id);
		if(c!=null){
		c.removeAdresse(adr_id);
		ServiceContact.updateContact(c);
		}
		else{
			List<Contact> list = new ArrayList<Contact>();
			model.addAttribute("contactList",  list);
			model.addAttribute("msgEmptyList","Erreur lors de la suppression de l'adresse");
			return new ModelAndView("home");
		}
		
		if(!ServiceAdresse.removeAdresse(adr_id)){
			List<Contact> list = new ArrayList<Contact>();
			model.addAttribute("contactList",  list);
			model.addAttribute("msgEmptyList","Erreur lors de la suppression de l'adresse");
			return new ModelAndView("home");
		}
		
		return new ModelAndView("redirect:editContactForm?id="+contact_id);
	}
	
	@RequestMapping(value="/editContactForm", method=RequestMethod.GET)
	public ModelAndView editContactForm(HttpServletRequest request, ModelMap model){
		String id = request.getParameter("id");
		Integer contact_id=-1;
		
		try{
		contact_id = Integer.parseInt(id);
		}
		catch (Exception e){
			System.err.println(e.getMessage());
			
			List<Contact> list = new ArrayList<Contact>();
			model.addAttribute("contactList",  list);
			model.addAttribute("msgEmptyList","Erreur lors de l'édition du contact.");
			return new ModelAndView("home");
		}
		
		System.err.println("Edition contact : " + id);
		model.addAttribute("id",contact_id);
		return new ModelAndView("editContactForm");
	}
	
	@RequestMapping(value="/editAdresseForm", method=RequestMethod.GET)
	public ModelAndView editAdresseForm(HttpServletRequest request, ModelMap model){
		String id = request.getParameter("id");
		System.err.println("Edit adr : " + id);
		
		Integer adr_id = -1;
		try{
		adr_id = Integer.parseInt(id);
		}
		catch (Exception e){
			System.err.println(e.getMessage());
			
			List<Contact> list = new ArrayList<Contact>();
			model.addAttribute("contactList",  list);
			model.addAttribute("msgEmptyList","Erreur lors de l'édition de l'adresse");
			return new ModelAndView("home");
		}
		
		String cid = request.getParameter("cid");
		System.err.println("From contact: " + cid);
		
		Integer contact_id = -1;
		try{
		contact_id = Integer.parseInt(cid);
		}
		catch (Exception e){
			System.err.println(e.getMessage());
			
			List<Contact> list = new ArrayList<Contact>();
			model.addAttribute("contactList",  list);
			model.addAttribute("msgEmptyList","Erreur lors de l'édition de l'adresse");
			return new ModelAndView("home");
		}
		
		Adresse adr = ServiceAdresse.getAdresse(adr_id);
		Contact contact = ServiceContact.getContact(contact_id);
		if(adr==null || contact == null){
			return new ModelAndView("redirect:editContactForm?id="+contact_id);
		}
		
		System.err.println("Edition contact : " + id);
		model.addAttribute("adresse", adr);
		model.addAttribute("contact", contact);
		
		return new ModelAndView("EditAdresseForm");
	}
	
	@RequestMapping(value="/addContact", method = RequestMethod.POST)
	public ModelAndView addContact(HttpServletRequest request, ModelMap model) {

		String prenom = request.getParameter("prenom");
		String nom = request.getParameter("nom");
		String email = request.getParameter("email");
		String date = request.getParameter("date");
		
		Contact c = new Contact();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		try {
			Date d = formatter.parse(date);
			c.setDate_naissance(d);
		} catch (ParseException e) {
			e.printStackTrace();
			goHome();
		}
		c.setNom(nom);
		c.setEmail(email);
		c.setPrenom(prenom);
		
		ServiceContact.insertContact(c);
		
		List<Contact> list = ServiceContact.getContactList();
		model.addAttribute("contactList",  list);
		
		System.out.println("saved : " + prenom + " " + nom + " : " + email + " ; " + date);

		return new ModelAndView("redirect:home");
	}
	
	@RequestMapping(value="/addAddress", method = RequestMethod.POST)
	public ModelAndView addAddress(HttpServletRequest request, ModelMap model) {

		String num = request.getParameter("num");
		String cp = request.getParameter("cp");
		String ville = request.getParameter("ville");
		String rue = request.getParameter("rue");
		String id = request.getParameter("id");
		
		Integer contact_id = -1;
		try{
			contact_id = Integer.parseInt(id);
		}
		catch (Exception e){
			System.err.println(e.getMessage());
			
			List<Contact> list = new ArrayList<Contact>();
			model.addAttribute("contactList",  list);
			model.addAttribute("msgEmptyList","Erreur l'ajout de l'adresse.");
			return new ModelAndView("redirect:home");
		}
		
		Adresse adresse = new Adresse();
		adresse.setCodePostal(cp);
		adresse.setNumero(num);
		adresse.setRue(rue);
		adresse.setVille(ville);
		Long adrId = ServiceAdresse.insertAdresse(adresse);
		
		Contact c = ServiceContact.getContact(contact_id);
		if(c!=null){
			c.addAdresse(adrId, false);
			ServiceContact.updateContact(c);
		}
		else
		{
			List<Contact> list = new ArrayList<Contact>();
			model.addAttribute("contactList",  list);
			model.addAttribute("msgEmptyList","Erreur l'ajout de l'adresse.");
			return new ModelAndView("redirect:home");
		}
		
		System.out.println("adresse added : " + adresse);

		return new ModelAndView("redirect:editContactForm?id="+contact_id);
	}
	
	@RequestMapping(value="/editContact", method = RequestMethod.POST)
	public ModelAndView editContact(HttpServletRequest request, ModelMap model) {

		String prenom = request.getParameter("prenom");
		String nom = request.getParameter("nom");
		String email = request.getParameter("email");
		String date = request.getParameter("date");
		String actif = request.getParameter("actif");
		String id = request.getParameter("id");
		
		System.err.println("actif : " +actif+ " ; id = " + id);
		
		Contact c = new Contact();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		try {
			Date d = formatter.parse(date);
			c.setDate_naissance(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Integer contact_id = -1;
		try{
			contact_id = Integer.parseInt(id);
		}
		catch (Exception e){
			System.err.println(e.getMessage());
			
			List<Contact> list = new ArrayList<Contact>();
			model.addAttribute("contactList",  list);
			model.addAttribute("msgEmptyList","Erreur lors de l'édition du contact.");
			return new ModelAndView("redirect:home");
		}
		
		
		c.setNom(nom);
		c.setEmail(email);
		c.setPrenom(prenom);
		c.setActif("on".equals(actif));
		c.setId(contact_id);
		

		if(ServiceContact.updateContact(c)){
			//good
		}
		else
		{
			List<Contact> list = new ArrayList<Contact>();
			model.addAttribute("contactList",  list);
			model.addAttribute("msgEmptyList","Erreur lors de l'édition du contact.");
			return new ModelAndView("redirect:home");
		}
		
		List<Contact> clist = ServiceContact.getContactList();
		model.addAttribute("contactList",  clist);
		
		System.out.println("modified : " + prenom + " " + nom + " : " + email + " ; " + date);

		return new ModelAndView("redirect:home");
	}
	
	@RequestMapping(value="/editAdresse", method = RequestMethod.POST)
	public ModelAndView editAdresse(HttpServletRequest request, ModelMap model) {

		String num = request.getParameter("num");
		String cp = request.getParameter("cp");
		String ville = request.getParameter("ville");
		String rue = request.getParameter("rue");
		String livraison = request.getParameter("livraison");
		String id = request.getParameter("id");
		String cid = request.getParameter("cid");
		
		System.err.println("adresse livraison : " +livraison+ " ; id = " + id);
		
		Integer adr_id = -1;
		try{
			adr_id = Integer.parseInt(id);
		}
		catch (Exception e){
			System.err.println(e.getMessage());
			
			List<Contact> list = new ArrayList<Contact>();
			model.addAttribute("contactList",  list);
			model.addAttribute("msgEmptyList","Erreur lors de l'édition de l'adresse");
			return new ModelAndView("home");
		}
		
		System.err.println("From contact: " + cid);
		
		Integer contact_id = -1;
		try{
			contact_id = Integer.parseInt(cid);
		}
		catch (Exception e){
			System.err.println(e.getMessage());
			
			List<Contact> list = new ArrayList<Contact>();
			model.addAttribute("contactList",  list);
			model.addAttribute("msgEmptyList","Erreur lors de l'édition de l'adresse");
			return new ModelAndView("home");
		}
		
		Adresse adresse = ServiceAdresse.getAdresse(adr_id);
		adresse.setCodePostal(cp);
		adresse.setNumero(num);
		adresse.setRue(rue);
		adresse.setVille(ville);
		
		Contact contact = ServiceContact.getContact(contact_id);
		if(contact == null || adresse==null){
			List<Contact> list = new ArrayList<Contact>();
			model.addAttribute("contactList",  list);
			model.addAttribute("msgEmptyList","Erreur lors de l'édition de l'adresse.");
			return new ModelAndView("redirect:home");
		}
		
		if(ServiceAdresse.updateAdresse(adresse)){
			if("on".equals(livraison)){
				contact.setAdresseLivraison(adresse.getId());
				ServiceContact.updateContact(contact);
			}
		}
		else
		{
			List<Contact> list = new ArrayList<Contact>();
			model.addAttribute("contactList",  list);
			model.addAttribute("msgEmptyList","Erreur lors de l'édition de l'adresse.");
			return new ModelAndView("redirect:home");
		}
		
		
		List<Contact> clist = ServiceContact.getContactList();
		model.addAttribute("contactList",  clist);
		
		System.out.println("modified : " + num + " " + rue + " : " + ville + " ; " + cp);

		return new ModelAndView("redirect:editContactForm?id="+contact_id);
	}
}
