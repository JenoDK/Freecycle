package be.vdab.enums;

public enum Soort {
	SOORTEN("Soort"), ELEKTRO("Elektro"), MEUBEL("Meubel"), GEREEDSCHAP("Gereedschap"), OVERIGE("Overige"), FOOD("Food"), KEUKEN("Keuken"), AUTO("Auto");
	
	private String id;

	private Soort(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

}
