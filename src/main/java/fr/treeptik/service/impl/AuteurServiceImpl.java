package fr.treeptik.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.treeptik.dao.AuteurDAO;
import fr.treeptik.pojo.Auteur;
import fr.treeptik.service.AuteurService;

@Service
public class AuteurServiceImpl extends GenericServiceImpl<Auteur, Long> implements AuteurService {

	@Override
	public Auteur findAuteurArticleLePlusCommente() {
		return ((AuteurDAO) dao).findAuteurArticleLePlusCommente();
	}

	@Override
	public List<Auteur> listOrderByNombreArticles() {
		return ((AuteurDAO) dao).listOrderByNombreArticles();
	}

}