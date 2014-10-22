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

import com.contactor.model.Contact;
import com.contactor.services.ServiceContact;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@Controller
public class HomeController {

	@RequestMapping(value="/")
	public String base(ModelMap model){
		return home(model);
	}

	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String home(ModelMap model){
		System.err.println("Requete home");
		
		List<Contact> list = ServiceContact.getContactList();
		model.addAttribute("contactList",  list);
		
		return "test";
	}
	
	@RequestMapping(value="/search", method = RequestMethod.POST)
	public String search(HttpServletRequest request, ModelMap model) {

		String value = request.getParameter("svalue");
		System.err.println("Requete search : " + value);
		
		if(value == null)
			return home(model);
		
		List<Contact> list = ServiceContact.getContactListOnName(value);

		model.addAttribute("contactList",  list);
		return "test";
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
}

/*
 * 
Note pour les jsp avec les servlets

protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {

String parameter = request.getParameter("name");

request.setAttribute("name",parameter);

RequestDispatcher dispatch = request.getRequestDispatcher(« /page.jsp");

dispatch.forward(request, response);
}

 */
