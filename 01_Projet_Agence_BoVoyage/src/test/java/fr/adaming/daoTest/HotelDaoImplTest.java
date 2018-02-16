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

import fr.adaming.dao.IHotelDao;
import fr.adaming.model.Adresse;
import fr.adaming.model.Hotel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/application-context.xml" })
public class HotelDaoImplTest {

	@Autowired
	IHotelDao hotelDao;
	
	Hotel h1 = new Hotel();//"Hotel de la plage", "Formule", 65, new Adresse(15,"rue",33500,"Bouscat"));
	Hotel h2 = new Hotel();//1,"Hotel de la rue", "Formule", 75, new Adresse(66,"pays",44000,"Bouscat"));
	
	@Test
	@Transactional
	@Rollback(false)
	@Ignore
	public void testAddHotel() { 
		Hotel h = hotelDao.addHotel(h1);
		assertNotNull(h.getId());
	}
	
	@Test
	@Transactional
	@Ignore
	public void testGetAllHotel() { 
		assertEquals(3, hotelDao.getAllHotels().size());
	}
	
	@Test
	@Ignore
	@Transactional
	public void deleteHotelTest() {
		assertEquals(1, hotelDao.deleteHotel(2));
	}

	@Test
	@Transactional
	@Rollback(false)
	@Ignore
	public void updateHotelTest() {
		
		hotelDao.updateHotel(h2);
		Hotel hote = hotelDao.getHotelById(1);
		assertEquals(h2.getNomHotel(), hote.getNomHotel());
	}
	
	@Test
	@Transactional
	@Ignore
	public void getHotelByIdTest() {
		
		Hotel hGet = hotelDao.getHotelById(4);

		assertEquals("Hotel de la plage", hGet.getNomHotel());
	}
}
