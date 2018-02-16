package fr.adaming.daoTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IClientDao;
import fr.adaming.dao.IDossierDao;
import fr.adaming.model.Adresse;
import fr.adaming.model.Client;
import fr.adaming.model.Dossier;

/**
 * Classe de test pour le client
 * 
 * @author inti-0265
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/application-context.xml" })
public class ClientDaoImplTest {

	@Autowired
	IClientDao clientDao;
	Client client;
	
	@Autowired
	IDossierDao dossierDao;

	/**
	 * Instanciation d'un client avant les tests
	 */
	@Before
	public void setUp() {
		client = new Client(new Date(13 / 02 / 2018), true, "nomTest", "prenomTest", true, 123456, "0605040302", "c@c",
				"mdp", true, new Adresse(1, "rue", 75000, "ville"));
	}

	/**
	 * Test de la méthode pour la connection
	 */
	@Ignore
	@Transactional
	@Rollback(true)
	@Test
	public void isExist() {
		clientDao.addClient(client);
		assertEquals("c@c", clientDao.getAllClients().get(0).getMail());
		assertEquals("mdp", clientDao.getAllClients().get(0).getMdp());
	}

	/**
	 * Test de la méthode pour le récupération de la liste des clients
	 */
	@Ignore
	@Test
	@Transactional(readOnly = true)
	public void testGetAllClients() {
		assertEquals(0, clientDao.getAllClients().size());
	}

	/**
	 * Test de la méthode pour ajouter un client
	 */
	@Ignore
	@Transactional
	@Rollback(true)
	@Test
	public void testAddClient() {
		int taille = clientDao.getAllClients().size();
		clientDao.addClient(client);
		assertEquals(taille + 1, clientDao.getAllClients().size());
	}

	/**
	 * Test de la méthode pour la méthode pour modifier un client
	 */
	@Ignore
	@Transactional
	@Rollback(true)
	@Test
	public void testUpdateClient() {
		clientDao.addClient(client);
		Client clientModif = new Client(clientDao.getAllClients().get(0).getId(), new Date(13 / 02 / 2018), true,
				"nomTestModif", "prenomTest", true, 123456, "0605040302", "c@c", "mdp", true,
				new Adresse(1, "rue", 75000, "ville"));
		clientDao.updateClient(clientModif);
		assertEquals("nomTestModif", clientDao.getClientById(1).getNom());
	}

	/**
	 * Test de la méthode pour la méthode pour supprimer un client
	 */
	@Ignore
	@Transactional
	@Rollback(true)
	@Test
	public void testDeleteClient() {
		clientDao.addClient(client);
		int taille = clientDao.getAllClients().size();
		clientDao.deleteClient(clientDao.getAllClients().get(0).getId());
		assertEquals(taille - 1, clientDao.getAllClients().size());
	}

	/**
	 * Test de la méthode pour récupérer un client par son ID
	 */
	@Ignore
	@Transactional
	@Rollback(true)
	@Test
	public void testGetClientById() {
		clientDao.addClient(client);
		assertEquals("nomTest", clientDao.getClientById(clientDao.getAllClients().get(0).getId()).getNom());
	}
	
	@Ignore
	@Test
	@Transactional
	@Rollback(false)
	public void testGetListVoyageursByDossier(){
		Dossier d = dossierDao.getDossierById(1);
		List<Dossier> listOut = new ArrayList<Dossier>();
		listOut.add(d);
		
		Client c = clientDao.getClientById(1);
//		c.setListeDossiers(listOut);
//		clientDao.updateClient(c);
		
		Client c2 = clientDao.getClientById(2);
//		c2.setListeDossiers(listOut);
//		clientDao.updateClient(c2);
		
		List<Client> listIn = new ArrayList<Client>();
		listIn.add(c);
		listIn.add(c2);
		d.setListeClients(listIn);
		dossierDao.updateDossier(d);
		
		List<Client> listClient = clientDao.getListVoyageursByDossier(d);
		
		assertEquals(2, listClient.size());
	}
}
