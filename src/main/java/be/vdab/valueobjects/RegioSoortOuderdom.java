package be.vdab.valueobjects;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.SafeHtml;

import be.vdab.enums.Soort;

public class RegioSoortOuderdom {
	@Length(min = 1, max = 50)
	@SafeHtml
	private String regio;
	private Soort soort;

	public RegioSoortOuderdom() {

	}

	public RegioSoortOuderdom(String regio, Soort soort) {
		this.regio = regio;
		this.soort = soort;
	}

	public String getRegio() {
		return regio;
	}

	public void setRegio(String regio) {
		this.regio = regio;
	}

	public Soort getSoort() {
		return soort;
	}

	public void setSoort(Soort soort) {
		this.soort = soort;
	}

}
