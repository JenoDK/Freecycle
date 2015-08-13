package be.vdab.enums;

public enum Soort {
	ELEKTRO("Elektro"), MEUBEL("Meubel"), GEREEDSCHAP("Gereedschap"), OVERIGE(
			"Overige");

	private String id;

	private Soort(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
