package be.vdab.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Table(name = "reacties")
@XmlRootElement
public class Reactie implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;
	@NotBlank
	@Length(min = 1, max = 255)
	@SafeHtml
	private String reactie;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "userid")
	private User user;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "artikelid")
	private Artikel artikel;

	public Reactie() {
	}

	public Reactie(String reactie) {
		this.reactie = reactie;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getReactie() {
		return reactie;
	}

	public void setReactie(String reactie) {
		this.reactie = reactie;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		if (this.user != null && this.user.getReacties().contains(this)) {
			this.user.removeReactie(this);
		}
		this.user = user;
		if (user != null && !user.getReacties().contains(this)) {
			user.addReactie(this);
		}
	}

	public Artikel getArtikel() {
		return artikel;
	}

	public void setArtikel(Artikel artikel) {
		if (this.artikel != null && this.artikel.getReacties().contains(this)) {
			this.artikel.removeReactie(this);
		}
		this.artikel = artikel;
		if (artikel != null && !artikel.getReacties().contains(this)) {
			artikel.addReactie(this);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artikel == null) ? 0 : artikel.hashCode());
		result = prime * result + ((reactie == null) ? 0 : reactie.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Reactie other = (Reactie) obj;
		if (artikel == null) {
			if (other.artikel != null)
				return false;
		} else if (!artikel.equals(other.artikel))
			return false;
		if (reactie == null) {
			if (other.reactie != null)
				return false;
		} else if (!reactie.equals(other.reactie))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}
