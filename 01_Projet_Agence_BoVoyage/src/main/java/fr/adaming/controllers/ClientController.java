package fr.adaming.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.adaming.model.Client;
import fr.adaming.service.IClientService;

@Controller
@Scope("request")
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private IClientService clientService;

	public void setClientService(IClientService clientService) {
		this.clientService = clientService;
	}

	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	/**
	 * Affichage du formulaire de modification des infos client
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/afficheModifClient", method = RequestMethod.GET)
	public String afficheModifier(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String mail = auth.getName();
		Client c = clientService.getClientByMail(mail);
		model.addAttribute("modifClientC", c);
		return "clientModifClient";
	}

	/**
	 * Soumission du formulaire de modification des infos du client
	 * 
	 * @param c
	 * @return
	 */
	@RequestMapping(value = "/soumettreModifClient", method = RequestMethod.POST)
	public String soumettreModifier(@ModelAttribute("modifClientC") Client c) {
		Client cModif = clientService.getClientById(c.getId());
		c.setActive(cModif.isActive());
		c.setClientResa(cModif.isClientResa());
		c.setRole(cModif.getRole());
		clientService.updateClient(c);
		return "accueilClient";
	}

}
