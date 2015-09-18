package be.vdab.valueobjects;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import be.vdab.entities.Artikel;

public class ContactBericht {
	@NotBlank
	@Length(min = 1, max = 255)
	@SafeHtml
	private String bericht;
	@NotBlank
	@Length(min = 1, max = 50)
	@SafeHtml
	private String naam;
	@Email
	private String email;
	private Artikel artikel;

	public ContactBericht() {

	}

	public ContactBericht(String bericht, String naam, String email) {
		this.bericht = bericht;
		this.naam = naam;
		this.email = email;
	}

	public String getBericht() {
		return bericht;
	}

	public String getNaam() {
		return naam;
	}

	public String getEmail() {
		return email;
	}

	public Artikel getArtikel() {
		return artikel;
	}

	public void setArtikel(Artikel artikel) {
		this.artikel = artikel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bericht == null) ? 0 : bericht.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		ContactBericht other = (ContactBericht) obj;
		if (bericht == null) {
			if (other.bericht != null)
				return false;
		} else if (!bericht.equals(other.bericht))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ContactBericht [bericht=" + bericht + ", naam=" + naam
				+ ", email=" + email + "]";
	}

}
