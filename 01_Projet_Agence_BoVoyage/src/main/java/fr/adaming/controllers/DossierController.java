package fr.adaming.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.adaming.model.Client;
import fr.adaming.model.Dossier;
import fr.adaming.model.Voyage;
import fr.adaming.model.Voyageur;
import fr.adaming.service.IClientService;
import fr.adaming.service.IDossierService;
import fr.adaming.service.IVoyageService;

/**
 * Controller pour gerer les flux frontOffice concernant les dossier de
 * reservation
 * 
 * @author intiformation
 *
 */
@Controller
public class DossierController {

	@Autowired
	private IDossierService dossierService;

	@Autowired
	private IClientService clientService;

	@Autowired
	private IVoyageService voyageService;

	public void setDossierService(IDossierService dossierService) {
		this.dossierService = dossierService;
	}

	public void setClientService(IClientService clientService) {
		this.clientService = clientService;
	}

	public void setVoyageService(IVoyageService voyageService) {
		this.voyageService = voyageService;
	}

	/**
	 * Methode permettant d'afficher la liste des dossiers
	 * 
	 * @return la page allDossier.jsp, la page affichant cette liste
	 */
	@RequestMapping(value = "/agent/liste", method = RequestMethod.GET)
	public ModelAndView afficheListeDossier() {
		// Recupereration de la liste de la bd
		List<Dossier> listeDossier = dossierService.getAllDossiers();
		return new ModelAndView("listeDossier", "dossierListe", listeDossier);
	}

	@RequestMapping(value = "/client/listeDossier", method = RequestMethod.GET)
	public ModelAndView afficheListeDossierClient() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String mail = auth.getName();
		Client c = clientService.getClientByMail(mail);
		List<Dossier> listeDossier = c.getListeDossiers();
		return new ModelAndView("listeDossierClient", "dossierListe", listeDossier);
	}

	@RequestMapping(value = "/client/listeVoyage", method = RequestMethod.GET)
	public ModelAndView afficheListeVoyageClient() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String mail = auth.getName();
		Client c = clientService.getClientByMail(mail);
		List<Dossier> listeDossier = c.getListeDossiers();
		return new ModelAndView("listeVoyageClient", "dossierListe", listeDossier);
	}

	/////////// -- Ajout d'un dossier-- /////////////
	/**
	 * Methode pour afficher le formulaire d'ajout d'un dossier
	 * 
	 * @return la page ajoutDossier.jsp ou est présent le formulaire
	 * @throws Exception
	 */
	@RequestMapping(value = "/client/afficheAjoutDossier", method = RequestMethod.GET)
	public String afficheAjout(Model model, @RequestParam("pId") int idVoyage) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String mail = auth.getName();
		Client c = clientService.getClientByMail(mail);

		Voyage voyage = voyageService.getVoyageById(idVoyage);

		if (voyage.getNbPlaces() >= 1) {

			c.setClientResa(true);
			Client cOut = clientService.updateClient(c);
			List<Client> listClients = new ArrayList<Client>();
			listClients.add(cOut);

			Dossier dossier = new Dossier();
			dossier.setListeClients(listClients);

			voyage.setNbPlaces(voyage.getNbPlaces()-1);
			Voyage vOut = voyageService.updateVoyage(voyage);
			dossier.setVoyage(vOut);

			dossierService.addDossier(dossier);
			int idClientResa = 0;
			for (Voyageur voyageur : dossier.getListeClients()) {
				if (voyageur.isClientResa()) {
					idClientResa = voyageur.getId();
				}
			}
			dossierService.confirmAddDossier(dossier, clientService.getClientById(idClientResa).getMail());

			model.addAttribute("dossierAjout", dossier);

			return "ajoutDossier";
		} else {
			model.addAttribute("msg", "Ce voyage ne contient pas assez de places !!");
			return "redirection:afficheAjoutDossier";
		}
	}

	/**
	 * Methode pour soumettre le formulaire d'ajout d'un dossier
	 * 
	 * @param dossier
	 * @return un dossier
	 * @throws Exception
	 */
	@RequestMapping(value = "/client/soumettreAjoutDossier", method = RequestMethod.POST)
	public String soumettreAjout(@ModelAttribute("dossierAjout") Dossier dossier) throws Exception {
		// Appel de la methode service
		Dossier d = dossierService.getDossierById(dossier.getId());
		dossier.setVoyage(d.getVoyage());
		dossier.setListeClients(d.getListeClients());
		dossier.setStatut(d.getStatut());

		Dossier dOut = dossierService.updateDossier(dossier);

		int idClientResa = 0;
		for (Voyageur voyageur : dossier.getListeClients()) {
			if (voyageur.isClientResa()) {
				idClientResa = voyageur.getId();
			}
		}
		dossierService.confirmAddDossier(dossier, clientService.getClientById(idClientResa).getMail());

		if (dOut.getId() != 0) {
			return "redirect:formAjouter";
		} else {
			return "redirect:afficheAjoutDossier";
		}
	}

	/////////// -- Suppression d'un dossier-- /////////////
	/**
	 * Methode permettant d'afficher le formulaire de suppression
	 * 
	 * @param modele
	 * @return
	 */
	@RequestMapping(value = "/afficheSuppDossier", method = RequestMethod.GET)
	public String afficherSupprimer(Model modele) {
		modele.addAttribute("dossierSupp", new Dossier());
		return "supprimerDossier";
	}

	/**
	 * Methode pour soumettre le formulaire de suppression d'un dossier.
	 * 
	 * @param dossier
	 * @return
	 */
	@RequestMapping(value = "/soumettreSuppDossier", method = RequestMethod.POST)
	public String SoumettreSupprimer(Dossier dossier) {
		// Appel de la methode service
		int verif = dossierService.deleteDossier(dossier);
		if (verif != 0) {
			return "redirect:liste";
		} else {
			return "redirect:afficheSuppDossier";
		}
	}

	/////////// -- Suppression d'un dossier avec un bouton-- /////////////
	/**
	 * Methode pour supprimer un dossier par un bouton dans la liste de dossier
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/suppLien", method = RequestMethod.GET)
	public String suppLien(@RequestParam("pId") int id) {
		Dossier d = new Dossier();
		d.setId(id);
		dossierService.deleteDossier(d);
		return "redirect:liste";
	}

	/////////// -- Modification d'un dossier avec un bouton-- /////////////
	/**
	 * Methode pour modifier un dossier via un bouton dans la liste de dossier
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/modifLien", method = RequestMethod.GET)
	public String modifLien(ModelMap model, @RequestParam("pId") int id) {
		Dossier dIn = new Dossier();
		dIn.setId(id);
		// Recuperation du dossier de la bd
		Dossier dOut = dossierService.getDossierById(dIn.getId());
		model.addAttribute("dossierModif", dOut);
		return "modifierDossier";
	}

	@RequestMapping(value = "/soumettreModifLien", method = RequestMethod.POST)
	public String soumettreModifier(@ModelAttribute("dossierModif") Dossier dossier) {
		// Appel de la methode service
		dossierService.updateDossier(dossier);
		// if(dmod != null){
		return "redirect: liste";
		// }else{
		// return "redirect : modifLien";
		// }
	}

	/////////// -- Recherche d'un dossier -- /////////////
	/**
	 * Methode pour afficher le formulaire de recherche
	 * 
	 * @param modele
	 * @return
	 */
	@RequestMapping(value = "/afficheRech", method = RequestMethod.GET)
	public String afficherRecherche(Model modele) {
		modele.addAttribute("dossierRech", new Dossier());
		modele.addAttribute("indice", false);
		return "rechercheDossier";
	}

	/**
	 * Methode permettant de soumettre le formulaire de recherche d'un dossier
	 * 
	 * @param ra
	 * @param modele
	 * @param dossier
	 * @return
	 */

	@RequestMapping(value = "/soumettreRech", method = RequestMethod.POST)
	public String soumettreRecherche(RedirectAttributes ra, Model modele,
			@ModelAttribute("dossierRech") Dossier dossier) {
		Dossier dOut = dossierService.getDossierById(dossier.getId());
		modele.addAttribute("dossierRech", dOut);
		if (dOut != null) {
			modele.addAttribute("dossierRech", dOut);
			modele.addAttribute("indice", true);
			return "rechercheDossier";
		} else {
			ra.addFlashAttribute("message", "Le dossier recherche n'existe pas!!");
			return "redirect:afficheRech";
		}
	}
}
