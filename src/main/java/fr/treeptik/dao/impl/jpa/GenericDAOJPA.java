package fr.treeptik.dao.impl.jpa;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import fr.treeptik.dao.GenericDAO;

public abstract class GenericDAOJPA<T, Pk> implements GenericDAO<T, Pk> {

	private Class<T> type;

	@PersistenceContext
	protected EntityManager em;

	@SuppressWarnings("unchecked")
	public GenericDAOJPA() {
		ParameterizedType genericSuperClass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		this.type = (Class<T>) genericSuperClass.getActualTypeArguments()[0];
	}

	@Override
	@Transactional
	public T save(T entite) {
		entite = this.em.merge(entite);
		return entite;
	}

	@Override
	@Transactional(readOnly=true)
	public T findOne(Pk id) {
		return this.em.find(type, id);
	}

	@Override
	@Transactional
	public T update(T entite) {
		return save(entite);
	}

	@Override
	@Transactional
	public void delete(Pk id) {
		this.em.createQuery(
				"DELETE FROM " + type.getName() + " WHERE id = :id")
				.setParameter("id", id).executeUpdate();
	}

	@Override
	@Transactional(readOnly=true)
	public List<T> findAll() {
		return this.em.createQuery("SELECT e FROM " + type.getName() + " e ", type).getResultList();
	}

}
