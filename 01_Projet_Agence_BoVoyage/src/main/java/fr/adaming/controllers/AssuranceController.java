package fr.adaming.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.adaming.model.Assurance;
import fr.adaming.model.Client;
import fr.adaming.model.Dossier;
import fr.adaming.model.Hotel;
import fr.adaming.service.IAssuranceService;

@Controller
@Scope("session")
public class AssuranceController {
	
	@Autowired
	private IAssuranceService assuranceService;

	public void setAssuranceService(IAssuranceService assuranceService) {
		this.assuranceService = assuranceService;
	}
	
	@RequestMapping(value = "/assurances/liste", method = RequestMethod.GET)
	public ModelAndView afficheListe() {
		List<Assurance> liste = assuranceService.getAllAssurances();

		return new ModelAndView("listeAssurances", "assuranceListe", liste);

	}
	
	
	//**********************************  AJOUT  ASSURANCE  **************************************

	@RequestMapping(value = "/assurances/afficheAjoutAssurance", method = RequestMethod.GET)
	public String afficheFormAjoutAssurance(Model modele) {

		modele.addAttribute("assurance", new Assurance());
		return "ajoutAssurance";
	}

	@RequestMapping(value = "/assurances/soumettreAjoutAssurance", method = RequestMethod.POST)
	public String soumettreAjoutAssurance(ModelMap modele, Assurance assurance) {

		Assurance hOut = assuranceService.addAssurance(assurance);

		if (hOut.getId() != 0) {
			return "redirect:liste";
		} else {
			return "redirect:afficheAjoutAssurance";
		}
	}
	
	//**********************************  SUPPRIMER  ASSURANCE  **************************************

	@RequestMapping(value = "/assurances/suprimlien/{pId}", method = RequestMethod.GET)
	public String deletLien(Model model, @PathVariable("pId") int id) {
		assuranceService.deleteAssurance(id);
		List<Assurance> liste = assuranceService.getAllAssurances();
		model.addAttribute("assuranceListe", liste);
		return "listeAssurances";
	}
	
	//**********************************  RECHERCHER  ASSURANCE  **************************************

	@RequestMapping(value = "/assurances/afficheRecherche", method = RequestMethod.GET)
	public String afficheRechercher(Model modele) {
		modele.addAttribute("assuranceRecherche", new Assurance());
		modele.addAttribute("indice", false);
		return "rechercheAssurance";
	}

	@RequestMapping(value = "/assurances/soumettreRecherche", method = RequestMethod.POST)
	public String soumettreRechercher(Model model, @ModelAttribute("assuranceRecherche") Assurance h) {
		Assurance hOut = assuranceService.getAssuranceById(h.getId());
		if (hOut != null) {
			model.addAttribute("assuranceRecherche", hOut);
			model.addAttribute("indice", true);
			return "rechercheAssurance";
		} else {
			return "redirect:afficheRecherche";
		}
	}
	
	//**********************************  MODIFIER  ASSURANCE  **************************************

	
	@RequestMapping(value = "/assurances/afficheModif", method = RequestMethod.GET)
	public String afficheModifier(ModelMap model, @RequestParam("pId") int id) {
		Assurance v = assuranceService.getAssuranceById(id);
		model.addAttribute("modifAssurance", v);
		return "modifAssurance";
	}

	@RequestMapping(value = "/assurances/soumettreModif", method = RequestMethod.POST)
	public String soumettreModifier(@ModelAttribute("modifAssurance") Assurance v) {
		System.out.println(v);
		assuranceService.updateAssurance(v);
		return "redirect:liste";
	}

	@RequestMapping(value = "/assurances/modiflien", method = RequestMethod.GET)
	public String updateLien(ModelMap model, @RequestParam("pId") int id) {
		Assurance v = assuranceService.getAssuranceById(id);
		model.addAttribute("modifAssurance", v);
		return "modifAssurance";
	}
	
	//********************* CHOISIR ASSURANCE RESERVATION
	@RequestMapping(value="/client/pageChoixAss", method=RequestMethod.GET)
	public String afficheChoixAssurance(Model model) {
		
		return "choixAssurance";
	}
	
	
	
	
}
