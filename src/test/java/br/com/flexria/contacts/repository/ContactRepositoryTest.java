package br.com.flexria.contacts.repository;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.data.document.mongodb.query.Criteria;
import org.springframework.data.document.mongodb.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import br.com.flexria.contacts.domain.Contact;
import br.com.flexria.contacts.domain.Phone;


/**
 * @author Fabio B. Silva <fabio.bat.silva@gmail.com>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class ContactRepositoryTest extends TestCase{

	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	protected MongoTemplate template;

	@Test
	public void testPersist() {
		Contact contact = new Contact();
		contact.setName("Fabio B. Silva");
		contact.setEmail("fabio.bat.silva@gmail.com");
		
		Phone p = new Phone();
		p.setNumber("(xx) xxxx-xxxx");
		contact.addPhone(p);
		
		contactRepository.persist(contact);
		
		assertNotNull(contact.getId());
	}
	
	
	@Test
	public void testMerge() {
		
		Query query 	= new Query(Criteria.where("email").is("fabio.bat.silva@gmail.com"));
		Contact contact = template.findOne(query , Contact.class);
		contact.setName("Fabio Silva");
		
		contactRepository.merge(contact);
		
		assertEquals(contact.getName(), "Fabio Silva");
	}
	
	
	@Test
	public void testRemove() {
		Query query 	= new Query(Criteria.where("email").is("fabio.bat.silva@gmail.com"));
		Contact contact = template.findOne(query , Contact.class);
		
		contactRepository.remove(contact);
		
		
		query 	= new Query(Criteria.whereId().is(contact.getId()));
		contact = template.findOne(query , Contact.class);
		
		assertNull(contact);
	}
	
	@Test
	public void testFindAll() {
		List<Contact> list = contactRepository.findAll();
		assertNotNull(list);
	}
	
	@Test
	public void testByEmail() {
		
		Contact contact = new Contact();
		contact.setName("Fabio B. Silva");
		contact.setEmail("fabio.bat.silva@gmail.com");
		
		Phone p = new Phone();
		p.setNumber("(xx) xxxx-xxxx");
		contact.addPhone(p);
		
		contactRepository.persist(contact);
		
		contact = contactRepository.findByEmail("fabio.bat.silva@gmail.com");
		
		assertNotNull(contact);
	}
}
