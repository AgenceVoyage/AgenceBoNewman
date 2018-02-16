package fr.adaming.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.adaming.model.Photo;
import fr.adaming.model.Voyage;
import fr.adaming.service.IClientService;
import fr.adaming.service.IPhotoService;
import fr.adaming.service.IVoyageService;

@Controller
@Scope("session")
public class VoyageController {

	@Autowired
	IVoyageService voyageService;
	@Autowired
	IPhotoService photoService;
	@Autowired
	private IClientService clientService;

	private Voyage voyage;

	@InitBinder
	public void dataBinding(WebDataBinder binder) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	// ***********************************************************************************************************************
	// ******************** AJOUTER UN VOYAGE
	// *****************************************************************************

	@RequestMapping(value = "/agent/ajouter")
	public ModelAndView afficheForm() {
		Voyage v = new Voyage();

		return new ModelAndView("ajoutVoyage", "vForm", v);
	}

	// @RequestParam("from") Date fromDate
	@RequestMapping(value = "/agent/soumettreAjoutVoyage", method = RequestMethod.POST)
	public String soumettreAjouterVoyage(@ModelAttribute("vForm") Voyage v) throws IOException {
		System.out.println("Ajout Voyage:" + v.getCompagnieVoyage());

		voyage = v;
		Photo p = new Photo();
		v.setImage(v.getFile().getBytes());
		p.setPicture(v.getFile().getBytes());

		voyageService.addVoyage(v);
		photoService.addPhoto(p, v);
		//return "redirect:accueilAgent";
		return "rajouterPhoto";
	}
	
	@RequestMapping(value = "/agent/redirect", method = RequestMethod.POST)
	public String redirect() throws IOException {
		
		return "redirect:accueilAgent";
	}
	@RequestMapping(value = "/agent/soumettreRajoutVoyage", method = RequestMethod.POST)
	public String soumettreAjouterVoyage(@ModelAttribute("pForm") Photo pRecup) throws IOException {
	
		pRecup.setPicture(pRecup.getFile().getBytes());
		photoService.addPhoto(pRecup, voyage);

		return "rajouterPhoto";
	}

	// ***********************************************************************************************************************
	// ******************** AJOUTER PHOTO

	// ***********************************************************************************************************************

	// -------------------- Modification d'un voyage via le lien
	@RequestMapping(value = "/agent/modifLienVoyage", method = RequestMethod.GET)
	public String formModifVoyage(Model model, @RequestParam("pId") int idVoyage) {
		// appel de la méthode service pour récupérer le voyage correspondant
		Voyage vOut = voyageService.getVoyageById(idVoyage);

		model.addAttribute("vModif", vOut);

		return "modificationVoyageAgent";
	}

	// ----------------- méthode pour soumettre le formulaire de modif
	@RequestMapping(value = "/agent/soumettreModifVoyage", method = RequestMethod.POST)
	public String modifierVoyage(@ModelAttribute("vModif") Voyage v) {
		// appel de la méthode service pour modifier l'etudiant recupéré du
		// formulaire de modif

		Voyage vModif = voyageService.getVoyageById(v.getId());
		v.setVoiture(vModif.getVoiture());
		v.setHotel(vModif.getHotel());
		Voyage vOut = voyageService.updateVoyage(v);

		if (vOut.getId() != 0) {
			return "redirect:accueilAgent";
		} else {
			return "modificationVoyageAgent";
		}
	}

	// ***********************************************************************************************************************
	// ******************** Affiche Map

	// ***********************************************************************************************************************

	@RequestMapping(value = "/voyage/afficheMap", method = RequestMethod.GET)
	public String afficheMap() {

		return "VoyageMapTest";

	}

	@RequestMapping(value = "/listeVoyageParCont", method = RequestMethod.GET)
	public String afficheListeVoyageCont(ModelMap model, @RequestParam("pContinent") String cont,
			@RequestParam("pNum") int numRole) {
		List<Voyage> listeVoyages = voyageService.getAllVoyages();
		List<Voyage> listeVoyParCont = new ArrayList<>();

		for (Voyage voyage : listeVoyages) {

			if (voyage.getContinent().equals(cont)) {
				listeVoyParCont.add(voyage);
			}
			if (cont.equals("All")) {
				listeVoyParCont.add(voyage);
			}
		}
		model.addAttribute("voyageListCont", listeVoyParCont);
		model.addAttribute("role", numRole);
		return "listeVoyParCont";
	}

}