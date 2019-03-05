package pristanishte;

import java.time.LocalDateTime;

public class Pratka {
	private int nomer;
	private String dok;
	private String korab;
	private String kran;
	private LocalDateTime vreme;
	public Pratka(int nomer, String dok, String korab, String kran, LocalDateTime vreme) {
		super();
		this.nomer = nomer;
		this.dok = dok;
		this.korab = korab;
		this.kran = kran;
		this.vreme = vreme;
	}
	public int getNomer() {
		return nomer;
	}
	public String getDok() {
		return dok;
	}
	public String getKorab() {
		return korab;
	}
	public String getKran() {
		return kran;
	}
	public LocalDateTime getVreme() {
		return vreme;
	}
	@Override
	public String toString() {
		return "Pratka [nomer=" + nomer + ", dok=" + dok + ", korab=" + korab + ", kran=" + kran + ", vreme=" + vreme
				+ "]";
	}
	
}
