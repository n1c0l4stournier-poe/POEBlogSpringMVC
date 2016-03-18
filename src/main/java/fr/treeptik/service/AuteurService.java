package fr.treeptik.service;

import java.util.List;

import fr.treeptik.pojo.Auteur;

public interface AuteurService extends GenericService<Auteur, Long> {

	Auteur findAuteurArticleLePlusCommente();

	List<Auteur> listOrderByNombreArticles();
	
}