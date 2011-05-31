package br.com.flexria.contacts;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.flexria.contacts.domain.Contact;
import br.com.flexria.contacts.domain.Phone;
import br.com.flexria.contacts.repository.ContactRepository;

/**
 * @author Fabio B. Silva <fabio.bat.silva@gmail.com>
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		BeanFactory factory = context;
		
		ContactRepository contactRepository = (ContactRepository) factory.getBean("contactRepository");
		
		Contact contact = new Contact();
		contact.setName("Fabio B. Silva");
		contact.setEmail("fabio.bat.silva@gmail.com");
		
		Phone p = new Phone();
		p.setNumber("(xx) xxxx-xxxx");
		
		
		contact.addPhone(p);
		
		contactRepository.persist(contact);

		List<Contact> list = contactRepository.findAll();

		System.out.println(list);
		
    }
    
}
