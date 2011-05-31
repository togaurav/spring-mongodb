package br.com.flexria.contacts.repository.impl;

import org.bson.types.ObjectId;
import org.springframework.data.document.mongodb.query.Criteria;
import org.springframework.data.document.mongodb.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import br.com.flexria.contacts.domain.Contact;
import br.com.flexria.contacts.repository.ContactRepository;
/**
 * @author Fabio B. Silva <fabio.bat.silva@gmail.com>
 */
@Service("contactRepository")
@Repository
public class ContactRepositoryImpl extends GenericRepositoryWithMongo<Contact, ObjectId> implements ContactRepository{

	@Override
	public Contact findByEmail(String email) {
		Criteria criteria	= Criteria.where("email").is(email);
		Query query 		= new Query(criteria);
		return template.findOne(query, targetClass);
	}
	
}