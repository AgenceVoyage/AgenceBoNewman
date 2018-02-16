package fr.adaming.daoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IVoyageDao;
import fr.adaming.model.Voyage;

/**
 * classe de test des méthodes DAO concernant les voyages
 * @author Adaming44
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/application-context.xml" })
public class VoyageDaoImplTest {
	
	@Autowired
	private IVoyageDao voyageDao;
	
	SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy HH:mm");
	
	@Ignore
	@Test
	@Transactional
	@Rollback(false)
	public void testAddVoyage() throws ParseException {
		Voyage voyage = new Voyage("Idealiste", "kl9821sp", df.parse("05-03-2017 15:53"), df.parse("05-06-2017 17:05"), 12, 2500, 20, "France", "Paris", "Europe", "Airbus",null);
		
		Voyage vOut = voyageDao.addVoyage(voyage);
		
		assertTrue(voyage.getNom().equals(vOut.getNom())&&voyage.getDateDepart().equals(vOut.getDateDepart())&&voyage.getDateArrivee().equals(vOut.getDateArrivee())&&voyage.getNbPlaces()==vOut.getNbPlaces()&&voyage.getPrixPublic()==vOut.getPrixPublic()&&voyage.getRemise()==vOut.getRemise()&&voyage.getPays().equals(vOut.getPays())&&voyage.getVille().equals(vOut.getVille())&&voyage.getContinent().equals(vOut.getContinent())&&voyage.getCompagnieVoyage().equals(vOut.getCompagnieVoyage()));
	}
	
	@Ignore
	@Test
	@Transactional
	public void testGetVoyageById() {
		Voyage vOut = voyageDao.getVoyageById(2);
		assertTrue(vOut.getContinent().equals("Europe"));
	}
	
	@Ignore
	@Test
	@Transactional(readOnly=true)
	public void testGetAllVoyages() {
		List<Voyage> listOut = voyageDao.getAllVoyages();
		assertEquals(1, listOut.size());
	}
	
	@Ignore
	@Test
	@Transactional
	@Rollback(false)
	public void testUpdateVoyage() throws ParseException {
		Voyage voyage = new Voyage(4, "Utopie", "4567878khg", df.parse("05-03-2017 15:53"), df.parse("05-04-2017 17:05"), 55, 3025, 10, "Egypte", "Le Caire", "Afrique", "Pirate",null);
		
		voyageDao.updateVoyage(voyage);
		Voyage vOut = voyageDao.getVoyageById(4);
		assertTrue(voyage.getNom().equals(vOut.getNom())&&voyage.getDateDepart().equals(vOut.getDateDepart())&&voyage.getDateArrivee().equals(vOut.getDateArrivee())&&voyage.getNbPlaces()==vOut.getNbPlaces()&&voyage.getPrixPublic()==vOut.getPrixPublic()&&voyage.getRemise()==vOut.getRemise()&&voyage.getPays().equals(vOut.getPays())&&voyage.getVille().equals(vOut.getVille())&&voyage.getContinent().equals(vOut.getContinent())&&voyage.getCompagnieVoyage().equals(vOut.getCompagnieVoyage()));
	}
	
	@Ignore
	@Test
	@Transactional
	@Rollback(false)
	public void testDeleteVoyage() {
		voyageDao.deleteVoyage(2);
		
		List<Voyage> listOut = voyageDao.getAllVoyages();
		assertEquals(0, listOut.size());
	}
	
	

}
