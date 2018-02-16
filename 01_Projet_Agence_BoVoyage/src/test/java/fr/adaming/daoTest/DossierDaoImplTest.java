package fr.adaming.daoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IDossierDao;
import fr.adaming.model.Dossier;

/**
 * Classe recensant les test des methodes de la classe DossierDaoImpl
 * @author intiformation
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/application-context.xml" })
public class DossierDaoImplTest {
	
	@Autowired
	private IDossierDao dossierDao;
	
	Dossier d1 = new Dossier();//("2545", "dispo", 1000.5);
	Dossier d2 = new Dossier();//"2000", "dispo", 500.5);
	@Ignore
	@Test
	@Transactional
	@Rollback(true)
	public void testAddDossier(){
		Dossier d = dossierDao.addDossier(d1);
		assertNotNull(d.getId());
	}
	@Ignore
	@Test
	@Transactional(readOnly=true)
	public void testGetAllDossiers(){
		assertEquals(1, dossierDao.getAllDossiers().size());
	}
	@Ignore
	@Test
	@Transactional
	@Rollback(true)
	public void testDeleteDossier(){
		dossierDao.addDossier(d2);
		int tailleAvant = dossierDao.getAllDossiers().size();
		dossierDao.deleteDossier(d2);
		int tailleApres = dossierDao.getAllDossiers().size();
		assertEquals(tailleAvant-1, tailleApres);
	}
	@Ignore
	@Test
	@Transactional
	@Rollback(true)
	public void testGetDossierById(){
		Dossier dGet = dossierDao.getDossierById(7);
		assertEquals("2545", dGet.getNumDossier());
	}
	@Ignore
	@Test
	@Transactional
	@Rollback(true)
	public void testUpdateDossier(){
		//dossierDao.addDossier(d2);
		Dossier dModif = new Dossier(8, "test", "ok", 1.0);
		dossierDao.updateDossier(dModif);
		assertEquals("test", dossierDao.getDossierById(8).getNumDossier());
	}
}
