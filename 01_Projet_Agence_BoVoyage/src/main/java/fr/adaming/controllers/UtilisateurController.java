package fr.adaming.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import fr.adaming.model.Client;
import fr.adaming.model.Photo;
import fr.adaming.model.Voyage;
import fr.adaming.service.IClientService;
import fr.adaming.service.IPhotoService;
import fr.adaming.service.IVoyageService;

/**
 * controller pour gérer les flux concernant le frontOffice utilisateur
 * 
 * @author Adaming44
 *
 */
@Controller
@RequestMapping("/utilisateur")
public class UtilisateurController {

	@Autowired
	IVoyageService voyageService;

	@Autowired
	private IClientService clientService;

	@Autowired
	IPhotoService photoService;

	private List<Voyage> list_voyage;

	public void setVoyageService(IVoyageService voyageService) {
		this.voyageService = voyageService;
	}

	public void setClientService(IClientService clientService) {
		this.clientService = clientService;
	}

	@RequestMapping(value = "/accueil")
	public String affichageAccueil(Model model) {
		// récupération de la liste de voyages de la BD
		List<Voyage> listVoyages = voyageService.getAllVoyages();
		List<Voyage> listVoyagesImage = new ArrayList<Voyage>();
		List<Photo> listVoyagesPhoto = new ArrayList<Photo>();
		int i = 0; // permet de connaitre le nombre de photo de chaque voyage
		List<String> liste_photoString = new ArrayList<String>();
		list_voyage = new ArrayList<Voyage>();

		for (Voyage v : listVoyages) {
			liste_photoString = new ArrayList<String>();
			listVoyagesPhoto = photoService.getAllPhotoByIdVoyage(v.getId());
			i = 0;
			try {
				// System.out.println("PHOTO:"+v.getListePhotos().get(0));
				for (Photo p : listVoyagesPhoto) {

					if (p.getPicture() != null) {
						i++;
						System.out.println("id photo=" + p.getId() + " size=" + liste_photoString.size());
						p.setImage("data:image/png;base64," + Base64.encodeBase64String(v.getImage()));
						// liste_photoString.add("data:image/png;base64," +
						// Base64.encodeBase64String(v.getImage()));

					} else
						p.setImage("data:image/png;base64," + Base64.encodeBase64String(new byte[0]));
				}
			} catch (Exception e) {
				System.out.println("Erreur Voyage+" + v.getId());
			}
			v.setNbPhoto(i);
			listVoyagesImage.add(v);
			v.setListePhotos(listVoyagesPhoto);
			list_voyage.add(v);

		}

		model.addAttribute("voyageList", listVoyagesImage);

		return "accueil";
	}

	// *************************************************************************************************
	// ***************** Fonction appelee par .jsp pour recuperer photos voyage
	@RequestMapping(value = "/accueilPhoto", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] affichageAccueilPhoto(int idV, int idP) throws IOException {

		System.out.println("Affiche=" + idV + " " + idP);
		System.out.println("Voyage=" + list_voyage.get(0).getCompagnieVoyage());
		try {
			for (Voyage v : list_voyage) {
				if (v.getId() == idV) {
					if (v.getListePhotos().get(idP) == null)
						return new byte[0];
					else
						return IOUtils.toByteArray(new ByteArrayInputStream(v.getListePhotos().get(idP).getPicture()));
				}
			}
		} catch (Exception e) {
			System.out.println("PAs d'image Exception!!");
			return new byte[0];
		}
		return new byte[0];
	}

	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	/**
	 * Méthode pour afficher le formulaire d'inscription
	 * 
	 * @return
	 */
	@RequestMapping(value = "/client/afficheAjout", method = RequestMethod.GET)
	public ModelAndView afficheAjouter() {
		return new ModelAndView("ajoutClient", "clientAjout", new Client());
	}

	/**
	 * Méthode de soumision du formulaire d'inscription : Ajout d'un client dans
	 * la base
	 * 
	 * @param c
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/client/soumettreAjout", method = RequestMethod.POST)
	public String soumettreAjouter(@ModelAttribute("clientAjout") Client c) throws Exception {
		Client cOut = clientService.addClient(c);
		clientService.confirmAddClient(c,c.getMail());
		if (cOut.getId() != 0) {
			return "accueil";
		} else {
			return "redirect:afficheAjout";
		}
	}

}
