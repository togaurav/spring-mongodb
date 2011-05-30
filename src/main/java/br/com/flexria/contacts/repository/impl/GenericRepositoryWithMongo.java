package br.com.flexria.contacts.repository.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.document.mongodb.MongoTemplate;

public abstract class GenericRepositoryWithMongo<T, ID extends Serializable> {

	
	@Autowired
	protected MongoTemplate template;
	
	protected Class<T> targetClass;

	protected String collectionName;

	
	@SuppressWarnings("unchecked")
	public GenericRepositoryWithMongo() {
		this.targetClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	}

	@PostConstruct
	public void initCollection() {
		this.collectionName = this.targetClass.getSimpleName().toLowerCase();
	}
	
	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}
	
	public List<T> getCollection() {
		return template.getCollection(collectionName, targetClass);
	}

	public Class<T> getPersistentClass() {
		return targetClass;
	}

	public void persist(T entity) {
		template.insert(collectionName, entity);
	}

	public void merge(T entity) {
		template.save(collectionName, entity);
	}

	public void remove(T entity) {
		template.setDefaultCollectionName(collectionName);
		template.remove(entity);
	}

	public List<T> findAll() {
		return getCollection();
	}

	public List<T> findInRange(int firstResult, int maxResults) {
		return getCollection().subList(firstResult, maxResults);
	}

	public long count() {
		return getCollection().size();
	}

}