package fr.treeptik.pojo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Auteur implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private enum RoleEnum {
		ROLE_USER, ROLE_AUTEUR, ROLE_ADMIN
	};

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String nom;
	
	@NotEmpty
	private String prenom;
	
	@NotEmpty
	private String mail;
	
	@NotEmpty
	private String password;
	
	private String url;
	private String role;
	
	@OneToMany(mappedBy="auteur")
	private List<Article> articles;

	public Auteur() {

	}

	public Auteur(String nom, String prenom, String mail, String password) {
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
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
		Auteur other = (Auteur) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Auteur [id=" + id + ", nom=" + nom + ", prenom=" + prenom
				+ ", url=" + url + ", mail=" + mail + "]";
	}

	public String getNomComplet() {
		return prenom + " " + nom;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setRoleUser() {
		this.setRole(RoleEnum.ROLE_USER.name());
	}

	public void setRoleAuteur() {
		this.setRole(RoleEnum.ROLE_AUTEUR.name());
	}

	public void setRoleAdmin() {
		this.setRole(RoleEnum.ROLE_ADMIN.name());
	}

}
