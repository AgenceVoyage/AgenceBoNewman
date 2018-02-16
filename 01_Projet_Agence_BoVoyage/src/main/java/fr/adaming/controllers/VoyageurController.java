package fr.adaming.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.adaming.model.Client;
import fr.adaming.model.Dossier;
import fr.adaming.model.Voyage;
import fr.adaming.model.Voyageur;
import fr.adaming.service.IDossierService;
import fr.adaming.service.IVoyageService;
import fr.adaming.service.IVoyageurService;

@Controller
@RequestMapping("/client")
public class VoyageurController {

	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@Autowired
	private IVoyageurService voyageurService;
	
	@Autowired
	private IVoyageService voyageService;

	@Autowired
	private IDossierService dossierService;

	public void setVoyageurService(IVoyageurService voyageurService) {
		this.voyageurService = voyageurService;
	}

	public void setDossierService(IDossierService dossierService) {
		this.dossierService = dossierService;
	}

	// *******************************PAGE
	// ACCUEIL****************************************************************

	@RequestMapping(value = "/accueil")
	public String affichageAccueil(Model model) {
		System.out.println("Accueil");

		return "accueil";
	}
	// ***********************************************************************************************************************

	// *******************************AJOUTER UN
	// VOYAGEUR****************************************************************

	@RequestMapping(value = "/formAjouter", method = RequestMethod.GET)
	public String afficheFormAjout(Model model) {
		model.addAttribute("vForm", new Client()); // ajouter model Voyage
		return "ajoutVoyageur";
	}

	@RequestMapping(value = "/soumettreFormAjoutVoyageur", method = RequestMethod.POST)
	public String soumettreAjouterVoyageur(Model model, @ModelAttribute("vForm") Client voyageur) {
		voyageur.setClientResa(false);

		Client cOut = voyageurService.addVoyageur(voyageur);

		Dossier dOut = dossierService.recupDernierDossier();

		Voyage vOut = dOut.getVoyage();

		if (vOut.getNbPlaces() >= 1) {
			vOut.setNbPlaces(vOut.getNbPlaces()-1);
			Voyage vIn = voyageService.updateVoyage(vOut);
			dOut.setVoyage(vIn);
			if(vIn.getNbPlaces()==0) {
				model.addAttribute("msg", "Il n'y a plus de place sur ce voyage !!");
			}
			List<Client> listClient = dOut.getListeClients();
			listClient.add(cOut);
			dOut.setListeClients(listClient);
			dossierService.updateDossier(dOut);

			if (cOut.getId() != 0) {
				return "redirect:formAjouter";
			} else
				return "redirect:formAjouter";

		} else {
			model.addAttribute("msg", "Ce voyage ne contient pas assez de places !!");
			return "redirect:formAjouter";
		}
	}

	// ***********************************************************************************************************************

}
