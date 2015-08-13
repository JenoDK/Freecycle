package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	private Soort soort;
	@NotNull
	private Ouderdom ouderdom;
	@NotBlank
	@Length(min = 1, max = 50)
	@SafeHtml
	private String regio;

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

	@Override
	public String toString() {
		return "Artikel [id=" + id + ", naam=" + naam + ", geschatteWaarde="
				+ geschatteWaarde + ", soort=" + soort + ", ouderdom="
				+ ouderdom + ", regio=" + regio + "]";
	}

}
