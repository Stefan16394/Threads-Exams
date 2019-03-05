package lutenica;

public enum ZelenchukTip {
	DOMAT(3),PATLIDJAN(6),CHUSKA(9);
	
	private int vremeZaObrabotka;

	ZelenchukTip(int vremeZaObrabotka) {
		this.vremeZaObrabotka  = vremeZaObrabotka;
	}

	public int getVremeZaObrabotka() {
		return vremeZaObrabotka;
	}
}
