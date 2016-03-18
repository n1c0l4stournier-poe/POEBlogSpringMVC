package fr.treeptik.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.treeptik.dao.GenericDAO;
import fr.treeptik.service.GenericService;
import fr.treeptik.service.exception.ServiceException;

@Service
public abstract class GenericServiceImpl<T, Pk> implements GenericService<T, Pk> {

	@Autowired
	protected GenericDAO<T, Pk> dao;

	@Override
	@Transactional
	public T save(T entite) {
		return dao.save(entite);
	}

	@Override
	public T findOne(Pk id) {
		return dao.findOne(id);
	}

	@Override
	@Transactional
	public T update(T entite) {
		return dao.update(entite);
	}

	@Override
	@Transactional(rollbackFor=ServiceException.class)
	public void delete(Pk id) throws ServiceException {
		if (findOne(id) == null) {
			throw new ServiceException();
		}
		dao.delete(id);
	}

	@Override
	public List<T> findAll() {
		return dao.findAll();
	}

}
