package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;

import be.vdab.constraints.PasswordMatches;

@Entity
@Table(name = "gebruikers")
@XmlRootElement
@PasswordMatches
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;
	@NotBlank
	@Length(min = 1, max = 50)
	@SafeHtml
	private String naam;
	@NotNull
	@NotEmpty
	private String paswoord;
	@Transient
	private String matchingPaswoord;
	@Min(0)
	@Max(1)
	private int actief;
	@Email
	private String email;
	@ManyToMany
	@JoinTable(name = "gebruikersrollen", joinColumns = @JoinColumn(name = "gebruikerid"), inverseJoinColumns = @JoinColumn(name = "rolid"))
	private Set<Rol> rollen = new LinkedHashSet<>();
	@OneToMany(mappedBy = "user")
	@OrderBy("id")
	private Set<Artikel> artikels;

	public User() {
	}

	public User(String naam, String paswoord, String matchingPaswoord,
			int actief, String email) {
		this.naam = naam;
		this.paswoord = paswoord;
		this.matchingPaswoord = matchingPaswoord;
		this.actief = actief;
		this.email = email;
		artikels = new LinkedHashSet<>();
	}

	public Set<Artikel> getArtikels() {
		return Collections.unmodifiableSet(artikels);
	}

	public void addArtikel(Artikel artikel) {
		artikels.add(artikel);
		if (artikel.getUser() != this) {
			artikel.setUser(this);
		}
	}

	public void removeArtikel(Artikel artikel) {
		artikels.remove(artikel);
		if (artikel.getUser() == this) {
			artikel.setUser(null);
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getPaswoord() {
		return paswoord;
	}

	public void setPaswoord(String paswoord) {
		this.paswoord = paswoord;
	}

	public String getMatchingPaswoord() {
		return matchingPaswoord;
	}

	public void setMatchingPaswoord(String matchingPaswoord) {
		this.matchingPaswoord = matchingPaswoord;
	}

	public int getActief() {
		return actief;
	}

	public void setActief(int actief) {
		this.actief = actief;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Rol> getRollen() {
		return Collections.unmodifiableSet(rollen);
	}

	public void addRol(Rol rol) {
		rollen.add(rol);
		if (!rol.getUsers().contains(this)) {
			rol.addUser(this);
		}
	}

	public void removeRol(Rol rol) {
		rollen.remove(rol);
		if (rol.getUsers().contains(this)) {
			rol.removeUser(this);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [naam=" + naam + ", actief=" + actief + ", email=" + email
				+ ", rollen=" + rollen + "]";
	}

}
