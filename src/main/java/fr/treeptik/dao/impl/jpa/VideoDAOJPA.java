package fr.treeptik.dao.impl.jpa;

import org.springframework.stereotype.Repository;

import fr.treeptik.dao.VideoDAO;
import fr.treeptik.pojo.Video;

@Repository
public class VideoDAOJPA extends GenericDAOJPA<Video, Long> implements VideoDAO {

}