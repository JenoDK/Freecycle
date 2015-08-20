package be.vdab.web;

import javax.validation.Valid;

import be.vdab.entities.Artikel;
import be.vdab.entities.Reactie;

public class ReactieForm {
	private Artikel artikel;

	@Valid
	private Reactie reactie;

	public Artikel getArtikel() {
		return artikel;
	}

	public void setArtikel(Artikel artikel) {
		this.artikel = artikel;
	}

	public Reactie getReactie() {
		return reactie;
	}

	public void setReactie(Reactie reactie) {
		this.reactie = reactie;
	}

}
