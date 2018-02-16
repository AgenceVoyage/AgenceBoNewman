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

import fr.adaming.model.Client;
import fr.adaming.model.Hotel;
import fr.adaming.model.Temp;
import fr.adaming.model.TempB;
import fr.adaming.model.Voiture;
import fr.adaming.model.Voyage;
import fr.adaming.service.IHotelService;
import fr.adaming.service.IVoyageService;

@Controller
@Scope("session")
@RequestMapping("/hotels")
public class HotelController {
	
	@Autowired
	private IHotelService hotelService;
	
	@Autowired
	private IVoyageService voyageService;

	public void setHotelService(IHotelService hotelService) {
		this.hotelService = hotelService;
	}
	
	public void setVoyageService(IVoyageService voyageService) {
		this.voyageService = voyageService;
	}

	@RequestMapping(value = "/liste", method = RequestMethod.GET)
	public ModelAndView afficheListe() {
		List<Hotel> liste = hotelService.getAllHotels();

		return new ModelAndView("listeHotels", "hotelListe", liste);

	}
	
	
	//**********************************  AJOUT  HOTEL  **************************************

	@RequestMapping(value = "/afficheAjoutHotel", method = RequestMethod.GET)
	public String afficheFormAjoutHotel(Model modele) {

		modele.addAttribute("voyageListe", voyageService.getAllVoyages());
		modele.addAttribute("temp", new TempB());
		return "ajoutHotel";		
	}

	@RequestMapping(value = "/soumettreAjoutHotel", method = RequestMethod.POST)
	public String soumettreAjoutHotel(ModelMap modele, TempB temp) {

		Hotel v = new Hotel();
		v.setAdresse(temp.getAdresse());
		v.setPrix(temp.getPrix());
		v.setNomHotel(temp.getNomHotel());
		Hotel vOut = hotelService.addHotel(v);
		
		Voyage voyage = voyageService.getVoyageById(temp.getIdVoy());
		voyage.setHotel(vOut);
		Voyage voyOut = voyageService.updateVoyage(voyage);
		
		if (voyOut!=null ) {
			return "redirect:liste";
		} else {
			return "redirect:afficheAjoutHotel";
		}
	}
	
	//**********************************  SUPPRIMER  HOTEL  **************************************

	@RequestMapping(value = "/suprimlien/{pId}", method = RequestMethod.GET)
	public String deletLien(Model model, @PathVariable("pId") int id) {
		hotelService.deleteHotel(id);
		List<Hotel> liste = hotelService.getAllHotels();
		model.addAttribute("hotelListe", liste);
		return "listeHotels";
	}
	
	//**********************************  RECHERCHER  HOTEL  **************************************

	@RequestMapping(value = "/afficheRecherche", method = RequestMethod.GET)
	public String afficheRechercher(Model modele) {
		modele.addAttribute("hotelRecherche", new Hotel());
		modele.addAttribute("indice", false);
		return "rechercheHotel";
	}

	@RequestMapping(value = "/soumettreRecherche", method = RequestMethod.POST)
	public String soumettreRechercher(Model model, @ModelAttribute("hotelRecherche") Hotel h) {
		Hotel hOut = hotelService.getHotelById(h.getId());
		if (hOut != null) {
			model.addAttribute("hotelRecherche", hOut);
			model.addAttribute("indice", true);
			return "rechercheHotel";
		} else {
			return "redirect:afficheRecherche";
		}
	}
	
	//**********************************  MODIFIER  HOTEL  **************************************

	
	@RequestMapping(value = "/afficheModif", method = RequestMethod.GET)
	public String afficheModifier(ModelMap model, @RequestParam("pId") int id) {
		Hotel v = hotelService.getHotelById(id);
		model.addAttribute("modifHotel", v);
		return "modifHotel";
	}

	@RequestMapping(value = "/soumettreModif", method = RequestMethod.POST)
	public String soumettreModifier(@ModelAttribute("modifHotel") Hotel v) {
		hotelService.updateHotel(v);
		return "redirect:liste";
	}

	@RequestMapping(value = "/modiflien", method = RequestMethod.GET)
	public String updateLien(ModelMap model, @RequestParam("pId") int id) {
		Hotel v = hotelService.getHotelById(id);
		model.addAttribute("modifHotel", v);
		return "modifHotel";
	}

}
