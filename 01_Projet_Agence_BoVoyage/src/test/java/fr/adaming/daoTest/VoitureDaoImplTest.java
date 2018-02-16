package fr.adaming.daoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IVoitureDao;
import fr.adaming.model.Voiture;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/application-context.xml" })
public class VoitureDaoImplTest {

		@Autowired
		IVoitureDao voitureDao;
		
		Voiture v1 = new Voiture("EuropCar", "SUV", 500);
		Voiture v2 = new Voiture(1,"AsiaCar", "Kart", 500);
		
		@Test
		@Transactional
		@Ignore
		@Rollback(false)
		public void testAddVoiture() { 
			Voiture v = voitureDao.addVoiture(v1);
			assertNotNull(v.getId());
		}
		
		@Test
		@Transactional
		@Ignore
		public void testGetAllVoiture() { 
			assertEquals(2, voitureDao.getAllVoitures().size());
		}
		
		@Test
		@Ignore
		@Transactional
		public void deleteVoitureTest() {
			assertEquals(1, voitureDao.deleteVoiture(3));
		}

		@Test
		@Transactional
		@Ignore
		public void updateTest() {
			
			Voiture vModif = voitureDao.updateVoiture(v2);

			assertEquals("Kart", vModif.getCategorie());
		}
		
		@Test
		@Transactional
		@Ignore
		public void getVoitureByIdTest() {
			
			Voiture vGet = voitureDao.getVoitureById(3);

			assertEquals("EuropCar", vGet.getLoueur());
		}
}
