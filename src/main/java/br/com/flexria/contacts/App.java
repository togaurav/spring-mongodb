package br.com.flexria.contacts;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.flexria.contacts.domain.Contact;
import br.com.flexria.contacts.domain.Phone;
import br.com.flexria.contacts.repository.ContactRepository;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		BeanFactory factory = context;
		
		ContactRepository contactRepository = (ContactRepository) factory.getBean("contactRepository");
		
		
		Contact c = new Contact();
		c.setName("Fabio B. Silva");
		c.setEmail("fabio.bat.silva@gmail.com");
		
		Phone p = new Phone();
		p.setNumber("(xx) xxxx-xxxx");
		
		
		c.addPhone(p);
		
		contactRepository.persist(c);
		
		
		c.setName("Fabio Silva");
		contactRepository.merge(c);
		
		
		Contact c2 = new Contact();
		c2.setName("Fabio B. Silva");
		c2.setEmail("fabio.bat.silva@gmail.com");
		
		
		c.setName("Fabio Silva");
		contactRepository.remove(c2);
		
		List<Contact> list = contactRepository.findAll();
		
		new ArrayList<Contact>();
		
		System.out.println(list);
		
    }
    
}
