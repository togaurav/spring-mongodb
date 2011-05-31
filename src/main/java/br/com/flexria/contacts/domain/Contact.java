package br.com.flexria.contacts.domain;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.document.mongodb.mapping.Document;
/**
 * @author Fabio B. Silva <fabio.bat.silva@gmail.com>
 */
@Document(collection="contacts")
public class Contact {

	@Id
	private ObjectId id;
	private String name;
	private String email;

	private List<Phone> phones;

	public Contact() {
		phones = new ArrayList<Phone>();
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}
	
	public void addPhone(Phone phone) {
		this.phones.add(phone);

	}

}
