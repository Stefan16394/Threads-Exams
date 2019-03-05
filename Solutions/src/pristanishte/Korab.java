package pristanishte;

import java.util.Random;

public class Korab implements Runnable {
	private String ime;
	private Pristanishte pristanishte;
	private int broiPratki;
	private Dok tekushtDok;
	private boolean raztovarvaLiSe;

	public Korab(String ime, Pristanishte p) {
		super();
		this.ime = ime;
		this.pristanishte = p;
		this.broiPratki = new Random().nextInt(4) + 1;
	}

	public String getIme() {
		return ime;
	}

	@Override
	public void run() {
		Dok dok = pristanishte.daiRandomDok();
		synchronized (dok) {
			if(dok.svobodenLiE()) {
				dok.dobaviKorab(this);
				this.setTekushtDok(dok);
			}
		}
		while (!dok.svobodenLiE() && dok.getKorab()!=this) {
			System.out.println("Shte chakam doka e zaet " + this + " " + dok);
			synchronized (dok) {
				try {
					dok.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			synchronized (dok) {
				if(dok.svobodenLiE()) {
					dok.dobaviKorab(this);
					this.setTekushtDok(dok);
				}
			}
		}
		System.out.println("Namerih dok " + dok + " za " + this);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		synchronized (pristanishte.getDokove()) {
			pristanishte.getDokove().notifyAll();
		}

		while (this.broiPratki != 0) {
			synchronized (dok) {
				try {
					dok.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.println("Raztovarih se ai chao " + this);

		synchronized (dok) {
			dok.notifyAll();
		}
	}

	@Override
	public String toString() {
		return "Korab [ime=" + ime + "]";
	}

	public Pristanishte getPristanishte() {
		return pristanishte;
	}

	public int getBroiPratki() {
		return broiPratki;
	}

	public void setBroiPratki(int broiPratki) {
		this.broiPratki = broiPratki;
	}

	public void setTekushtDok(Dok tekushtDok) {
		this.tekushtDok = tekushtDok;
	}

	public Dok getTekushtDok() {
		return tekushtDok;
	}

	public boolean isRaztovarvaLiSe() {
		return raztovarvaLiSe;
	}

	public void setRaztovarvaLiSe(boolean raztovarvaLiSe) {
		this.raztovarvaLiSe = raztovarvaLiSe;
	}
}
