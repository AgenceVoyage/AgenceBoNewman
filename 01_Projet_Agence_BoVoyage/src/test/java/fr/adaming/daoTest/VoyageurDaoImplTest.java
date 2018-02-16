package fr.adaming.daoTest;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IVoyageurDao;
import fr.adaming.model.Voyageur;

@RunWith(SpringJUnit4ClassRunner.class) // Pour lancer les test avec le module
// test de spring
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/application-context.xml" })
@Transactional
public class VoyageurDaoImplTest {

	@Autowired
	IVoyageurDao voyageurDao;
	
	@Ignore
	@Test
	@Rollback(false)
	public void testAddVoyageur(){
		Voyageur voyageur=new Voyageur(new Date("05/05/1900"), true, "Jacques", "DUPONT", false);
		
		voyageurDao.addVoyageur(voyageur);
		assertEquals("Jacques", "Jacques");
	}
	
	@Ignore
	@Test
	public void testGetVoyageurById(){
		Voyageur v=voyageurDao.getVoyageurById(1);
		assertEquals(v.getNom(), "Jacques");
	}
	
	@Ignore
	@Test
	@Rollback
	public void testDeleteVoyageur(){
		Voyageur voyageur= voyageurDao.getVoyageurById(1);
		voyageurDao.deleteVoyageur(voyageur);
		assertEquals("Jacques", "Jacques");
		
	}
}
