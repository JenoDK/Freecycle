package be.vdab.enums;

public enum Ouderdom {
	OUDERDOM("Ouderdom"), OUD("Oud"), MIDDELMATIG("Middelmatig"), NIEUW("Nieuw");

	private String id;

	private Ouderdom(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

}
