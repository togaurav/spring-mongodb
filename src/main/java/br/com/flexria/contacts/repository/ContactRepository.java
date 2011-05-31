package br.com.flexria.contacts.repository;

import java.util.List;

import br.com.flexria.contacts.domain.Contact;
/**
 * @author Fabio B. Silva <fabio.bat.silva@gmail.com>
 */
public interface ContactRepository {
	
	public Contact findByEmail(String email);

	public void persist(Contact entity);

	public void merge(Contact entity);

	public void remove(Contact entity);

	public List<Contact> findAll();
}
