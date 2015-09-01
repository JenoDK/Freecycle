package be.vdab.valueobjects;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.SafeHtml;

import be.vdab.enums.Ouderdom;
import be.vdab.enums.Soort;

public class RegioSoortOuderdom {
	@Length(min = 0, max = 50)
	@SafeHtml
	private String regio;
	private Soort soort;
	private Ouderdom ouderdom;

	public RegioSoortOuderdom() {

	}

	public RegioSoortOuderdom(String regio, Soort soort, Ouderdom ouderdom) {
		this.regio = regio;
		this.soort = soort;
		this.ouderdom = ouderdom;
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

	public Ouderdom getOuderdom() {
		return ouderdom;
	}

	public void setOuderdom(Ouderdom ouderdom) {
		this.ouderdom = ouderdom;
	}

}
