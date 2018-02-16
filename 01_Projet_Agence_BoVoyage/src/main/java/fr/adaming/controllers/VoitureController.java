package fr.adaming.controllers;

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

import fr.adaming.dao.IVoyageDao;
import fr.adaming.model.Client;
import fr.adaming.model.Hotel;
import fr.adaming.model.Temp;
import fr.adaming.model.Voiture;
import fr.adaming.model.Voyage;
import fr.adaming.service.IVoitureService;
import fr.adaming.service.IVoyageService;

@Controller
@Scope("session")
@RequestMapping("/voitures")
public class VoitureController {
	
	@Autowired
	private IVoitureService voitureService;
	
	@Autowired
	private IVoyageService voyageService;

	public void setVoitureService(IVoitureService voitureService) {
		this.voitureService = voitureService;
	}


	@RequestMapping(value = "/liste", method = RequestMethod.GET)
	public ModelAndView afficheListe() {
		List<Voiture> liste = voitureService.getAllVoitures();

		return new ModelAndView("listeVoitures", "voitureListe", liste);

	}
	
	
	//**********************************  AJOUT  VOITURE  **************************************

	@RequestMapping(value = "/afficheAjoutVoiture", method = RequestMethod.GET)
	public String afficheFormAjoutVoiture(Model modele) {

		modele.addAttribute("voyageListe", voyageService.getAllVoyages());
		modele.addAttribute("temp", new Temp());
		return "ajoutVoiture";
	}

	@RequestMapping(value = "/soumettreAjoutVoiture", method = RequestMethod.POST)
	public String soumettreAjoutVoiture(ModelMap modele, Temp temp) {

		Voiture v = new Voiture();
		v.setCategorie(temp.getCategorie());
		v.setLoueur(temp.getLoueur());
		v.setPrix(temp.getPrix());
		Voiture vOut = voitureService.addVoiture(v);
		
		Voyage voyage = voyageService.getVoyageById(temp.getIdVoy());
		voyage.setVoiture(vOut);
		Voyage voyOut = voyageService.updateVoyage(voyage);
		
		if (voyOut!=null ) {
			return "redirect:liste";
		} else {
			return "redirect:afficheAjoutVoiture";
		}
	}

	
	//**********************************  SUPPRIMER  VOITURE  **************************************

	@RequestMapping(value = "/suprimlien/{pId}", method = RequestMethod.GET)
	public String deletLien(Model model, @PathVariable("pId") int id) {
		voitureService.deleteVoiture(id);
		List<Voiture> liste = voitureService.getAllVoitures();
		model.addAttribute("voitureListe", liste);
		return "listeVoitures";
	}
	
	//**********************************  RECHERCHER  VOITURE  **************************************

	@RequestMapping(value = "/afficheRecherche", method = RequestMethod.GET)
	public String afficheRechercher(Model modele) {
		modele.addAttribute("voitureRecherche", new Voiture());
		modele.addAttribute("indice", false);
		return "rechercheVoiture";
	}

	@RequestMapping(value = "/soumettreRecherche", method = RequestMethod.POST)
	public String soumettreRechercher(Model model, @ModelAttribute("voitureRecherche") Voiture v) {
		Voiture vOut = voitureService.getVoitureById(v.getId());
		if (vOut != null) {
			model.addAttribute("voitureRecherche", vOut);
			model.addAttribute("indice", true);
			return "rechercheVoiture";
		} else {
			return "redirect:afficheRecherche";
		}
	}
	
	//**********************************  MODIFIER  VOITURE  **************************************

	
	@RequestMapping(value = "/afficheModif", method = RequestMethod.GET)
	public String afficheModifier(ModelMap model, @RequestParam("pId") int id) {
		Voiture v = voitureService.getVoitureById(id);
		model.addAttribute("modifVoiture", v);
		return "modifVoiture";
	}

	@RequestMapping(value = "/soumettreModif", method = RequestMethod.POST)
	public String soumettreModifier(@ModelAttribute("modifVoiture") Voiture v) {
		System.out.println(v);
		voitureService.updateVoiture(v);
		return "redirect:liste";
	}

	@RequestMapping(value = "/modiflien", method = RequestMethod.GET)
	public String updateLien(ModelMap model, @RequestParam("pId") int id) {
		Voiture v = voitureService.getVoitureById(id);
		model.addAttribute("modifVoiture", v);
		return "modifVoiture";
	}

}
