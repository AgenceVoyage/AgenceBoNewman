package fr.adaming.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.adaming.model.Client;
import fr.adaming.service.IClientService;

@Controller
@Scope("request")
@RequestMapping("/agent")
public class AgentController {

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
	 * Récupération et affichage de la liste des clients
	 * 
	 * @return
	 */
	@RequestMapping(value = "/clients/liste", method = RequestMethod.GET)
	public ModelAndView afficheListe() {
		List<Client> liste = clientService.getAllClients();
		return new ModelAndView("listeClients", "clientsListe", liste);
	}

	/**
	 * Affichage du formulaire de recherche d'un client
	 * 
	 * @param modele
	 * @return
	 */
	@RequestMapping(value = "/clients/afficheRecherche", method = RequestMethod.GET)
	public String afficheRechercher(Model modele) {
		modele.addAttribute("clientRecherche", new Client());
		modele.addAttribute("indice", false);
		return "rechercheClient";
	}

	/**
	 * Soumission du formulaire de recherche d'un client
	 * 
	 * @param model
	 * @param c
	 * @return
	 */
	@RequestMapping(value = "/clients/soumettreRecherche", method = RequestMethod.POST)
	public String soumettreRechercher(Model model, @ModelAttribute("clientRecherche") Client c) {
		Client cOut = clientService.getClientById(c.getId());
		if (cOut != null) {
			model.addAttribute("client", cOut);
			model.addAttribute("indice", true);
			return "rechercheClient";
		} else {
			return "redirect:afficheRecherche";
		}
	}

	/**
	 * Affichage du formulaire de modification via le lien de la liste
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/clients/modiflien", method = RequestMethod.GET)
	public String updateLien(ModelMap model, @RequestParam("pId") int id) {
		Client c = clientService.getClientById(id);
		model.addAttribute("modifClientA", c);
		return "clientModifAgent";
	}

	/**
	 * Soumission du formulaire de modification des infos du client
	 * 
	 * @param c
	 * @return
	 */
	@RequestMapping(value = "/clients/soumettreModifAgent", method = RequestMethod.POST)
	public String soumettreModifierAgent(@ModelAttribute("modifClientA") Client c) {
		Client cModif = clientService.getClientById(c.getId());
		c.setActive(cModif.isActive());
		c.setClientResa(cModif.isClientResa());
		c.setRole(cModif.getRole());
		c.setMdp(cModif.getMdp());
		c.setNumCB(cModif.getNumCB());
		clientService.updateClient(c);
		return "redirect:liste";
	}

	/**
	 * Suppression d'un client via le lien de la liste
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/clients/suprimlien/{pId}", method = RequestMethod.GET)
	public String deletLien(Model model, @PathVariable("pId") int id) {
		clientService.deleteClient(id);
		List<Client> liste = clientService.getAllClients();
		model.addAttribute("clientsListe", liste);
		return "listeClients";
	}

}
