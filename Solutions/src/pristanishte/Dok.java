package pristanishte;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Dok {
	private String ime;
	private Korab korab;

	public Dok(String ime) {
		super();
		this.ime = ime;
	}

	public void dobaviKorab(Korab korab) {
		this.korab = korab;
	}

	public void removeKorab(Korab korab) {
		this.korab = null;
	}

	public boolean svobodenLiE() {
		return this.korab == null;
	}

	@Override
	public String toString() {
		return "Dok [ime=" + ime + ", korab=" + korab + "]";
	}

	public Korab getKorab() {
		return this.korab;
	}

	public String getIme() {
		return ime;
	}
}
