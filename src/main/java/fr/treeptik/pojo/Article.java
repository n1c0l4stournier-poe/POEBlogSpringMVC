package fr.treeptik.pojo;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Article implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String titre;
	@Column(columnDefinition="TEXT")
	private String chapeau;
	@Column(columnDefinition="TEXT")
	private String contenu;
	
	private Date dateCreation;
	private Boolean enLigne;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Categorie categorie;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Auteur auteur;
	
	@OneToMany(mappedBy="article")
	private List<Commentaire> commentaires;
	
	@OneToMany(mappedBy="article")
	private List<Image> images;
//	
//	@OneToMany(mappedBy="article")
//	private List<Video> videos;

	public Article() {

	}

	public Article(String titre, String chapeau, String contenu) {
		this.titre = titre;
		this.chapeau = chapeau;
		this.contenu = contenu;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getChapeau() {
		return chapeau;
	}

	public void setChapeau(String chapeau) {
		this.chapeau = chapeau;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public String getDateCreationJsp() {
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
		return df.format(dateCreation);
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Boolean getEnLigne() {
		return enLigne;
	}

	public void setEnLigne(Boolean enLigne) {
		this.enLigne = enLigne;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Auteur getAuteur() {
		return auteur;
	}

	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}

	public List<Commentaire> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(List<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((titre == null) ? 0 : titre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.equals(other.titre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", titre=" + titre + ", chapeau="
				+ chapeau + ", contenu=" + contenu + ", dateCreation="
				+ dateCreation + "]";
	}

}
