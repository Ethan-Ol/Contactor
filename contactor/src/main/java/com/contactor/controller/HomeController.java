package com.contactor.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




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
	public String base(){
		return home();
	}

	@RequestMapping(value="/addContactPage")
	public String test(){
		return "ajoutContactForm";
	}

	@RequestMapping(value="/home")
	public String home(){
		System.err.println("Requete home");
		return "home";
	}
	
	@RequestMapping(value="/addContact", method = RequestMethod.POST)
	public String addContact(HttpServletRequest request, ModelMap model) {

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
		
		System.out.println("saved : " + prenom + " " + nom + " : " + email + " ; " + date);

		return "home";
	}


	@RequestMapping(value="/addLogin")
	public String add(){
		System.err.println("Page : ajout de login");
		return "add";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(HttpServletRequest request, ModelMap model) {

		String name = request.getParameter("name");
		String email = request.getParameter("email");

		Key customerKey = KeyFactory.createKey("Customer", name);

		Date date = new Date();
		Entity customer = new Entity("Customer", customerKey);
		customer.setProperty("name", name);
		customer.setProperty("email", email);
		customer.setProperty("date", date);

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		datastore.put(customer);
		
		System.out.println("saved : " + name + " " + email + " ; " + date);

		return "home";
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
