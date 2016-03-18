package fr.treeptik.dao;

import java.util.List;

public interface GenericDAO<T, Pk> {

	T save(T entite);

	T findOne(Pk id);

	T update(T entite);

	void delete(Pk id);

	List<T> findAll();

}
