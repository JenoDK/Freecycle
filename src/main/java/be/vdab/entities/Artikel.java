package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import be.vdab.enums.Ouderdom;
import be.vdab.enums.Soort;

@Entity
@Table(name = "artikels")
@XmlRootElement
public class Artikel implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;
	@NotBlank
	@Length(min = 1, max = 50)
	@SafeHtml
	private String naam;
	@NumberFormat(style = Style.NUMBER)
	@NotNull
	@Min(0)
	@Digits(integer = 10, fraction = 2)
	private BigDecimal geschatteWaarde;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Soort soort;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Ouderdom ouderdom;
	@NotBlank
	@Length(min = 1, max = 50)
	@SafeHtml
	private String regio;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "userid")
	private User user;

	public Artikel() {
	}

	public Artikel(String naam, BigDecimal geschatteWaarde, Soort soort,
			Ouderdom ouderdom, String regio) {
		this.naam = naam;
		this.geschatteWaarde = geschatteWaarde;
		this.soort = soort;
		this.ouderdom = ouderdom;
		this.regio = regio;
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

	public BigDecimal getGeschatteWaarde() {
		return geschatteWaarde;
	}

	public void setGeschatteWaarde(BigDecimal geschatteWaarde) {
		this.geschatteWaarde = geschatteWaarde;
	}

	public Soort getSoort() {
		return soort;
	}

	public void setSoort(Soort soort) {
		this.soort = soort;
	}

	public Ouderdom getOuderdom() {
		return ouderdom;
	}

	public void setOuderdom(Ouderdom ouderdom) {
		this.ouderdom = ouderdom;
	}

	public String getRegio() {
		return regio;
	}

	public void setRegio(String regio) {
		this.regio = regio;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		if (this.user != null && this.user.getArtikels().contains(this)) {
			this.user.removeArtikel(this);
		}
		this.user = user;
		if (user != null && !user.getArtikels().contains(this)) {
			user.addArtikel(this);
		}
	}

	@Override
	public String toString() {
		return "Artikel [id=" + id + ", naam=" + naam + ", geschatteWaarde="
				+ geschatteWaarde + ", soort=" + soort + ", ouderdom="
				+ ouderdom + ", regio=" + regio + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((geschatteWaarde == null) ? 0 : geschatteWaarde.hashCode());
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
		result = prime * result
				+ ((ouderdom == null) ? 0 : ouderdom.hashCode());
		result = prime * result + ((regio == null) ? 0 : regio.hashCode());
		result = prime * result + ((soort == null) ? 0 : soort.hashCode());
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
		Artikel other = (Artikel) obj;
		if (geschatteWaarde == null) {
			if (other.geschatteWaarde != null)
				return false;
		} else if (!geschatteWaarde.equals(other.geschatteWaarde))
			return false;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		if (ouderdom != other.ouderdom)
			return false;
		if (regio == null) {
			if (other.regio != null)
				return false;
		} else if (!regio.equals(other.regio))
			return false;
		if (soort != other.soort)
			return false;
		return true;
	}

}
