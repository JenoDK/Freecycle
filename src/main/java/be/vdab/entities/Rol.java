package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Table(name = "rollen")
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;
	@NotBlank
	@Length(min = 1, max = 50)
	@SafeHtml
	private String naam;
	@ManyToMany(mappedBy = "rollen")
	private Set<User> users = new LinkedHashSet<>();
	
	public Rol() {
	}

	public Rol(String naam) {
		this.naam = naam;
	}

	public long getId() {
		return id;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public Set<User> getUsers() {
		return Collections.unmodifiableSet(users);
	}

	public void addUser(User user) {
		users.add(user);
		if (!user.getRollen().contains(this)) {
			user.addRol(this);
		}
	}

	public void removeUser(User user) {
		users.remove(user);
		if (user.getRollen().contains(this)) {
			user.removeRol(this);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
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
		Rol other = (Rol) obj;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rol [naam=" + naam + "]";
	}

}
