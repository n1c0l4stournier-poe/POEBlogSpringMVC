package fr.treeptik.service;

import java.util.List;

import fr.treeptik.service.exception.ServiceException;

public interface GenericService<T, Pk> {

	T save(T entite);

	T findOne(Pk id);

	T update(T entite);

	void delete(Pk id) throws ServiceException;

	List<T> findAll();

}
