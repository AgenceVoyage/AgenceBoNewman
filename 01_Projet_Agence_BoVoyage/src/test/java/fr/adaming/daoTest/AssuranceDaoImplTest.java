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

import fr.adaming.dao.IAssuranceDao;
import fr.adaming.model.Assurance;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/application-context.xml" })
public class AssuranceDaoImplTest {

		@Autowired
		IAssuranceDao assuranceDao;
		
		Assurance a1 = new Assurance("annulation", 50);
		Assurance a2 = new Assurance(1,"transport",75);
		
		@Test
		@Ignore
		@Transactional
		@Rollback(false)
		public void testAddAssurance() { 
			Assurance a = assuranceDao.addAssurance(a1);
			assertNotNull(a.getId());
		}
		
		@Test
		@Transactional
		@Ignore
		public void testGetAllAssurance() { 
			assertEquals(2, assuranceDao.getAllAssurances().size());
		}
		
		@Test
		@Ignore
		@Transactional
		public void deleteAssuranceTest() {
			assertEquals(1, assuranceDao.deleteAssurance(3));
		}

		@Test
		@Transactional
		@Rollback(false)
		@Ignore
		public void updateTest() {
			
			Assurance aModif = assuranceDao.updateAssurance(a2);

			assertEquals("transport", aModif.getType());
		}
		
		@Test
		@Transactional
		@Ignore
		public void getAssuranceByIdTest() {
			
			Assurance aGet = assuranceDao.getAssuranceById(1);

			assertEquals("annulation", aGet.getType());
		}
	
}
