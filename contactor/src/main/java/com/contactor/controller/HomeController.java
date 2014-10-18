package com.contactor.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@Controller
public class HomeController {

	@RequestMapping(value="/")
	public String base(){
		System.err.println("Coucou la base");
		return "test";
	}

	@RequestMapping(value="/test")
	public String test(){

		return "test";
	}

	@RequestMapping(value="/addLogin")
	public String add(){
		System.err.println("Page : ajout de login");
		return "add";
	}

	@RequestMapping(value="/home")
	public String home(){
		System.err.println("Coucou la home");
		return "home";
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
