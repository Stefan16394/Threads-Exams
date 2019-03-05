package pristanishte;

import java.time.LocalDateTime;
import java.util.Random;

public class Kran extends Thread {
	private Pristanishte p;
	private String ime;

	public Kran(Pristanishte p, String ime) {
		this.p = p;
		this.ime = ime;
		this.setDaemon(true);
	}

	@Override
	public void run() {
		while (true) {
			Korab korab = this.p.daiKorab();
			while (korab == null) {
				System.out.println("Kran " + this + " to nqma korabi shte chakam");
				synchronized (p.getDokove()) {
					try {
						p.getDokove().wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				korab = this.p.daiKorab();
			}
			int pratki = korab.getBroiPratki();
			System.out.println("Kran " + this + " raztovarva korab " + korab);
			try {
				Thread.sleep(pratki * 2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			korab.setBroiPratki(0);
			Dok dok = korab.getTekushtDok();
			korab.setTekushtDok(null);
			for (Dok d : p.getDokove()) {
				if (d.getKorab() == korab) {
					d.removeKorab(korab);
					break;
				}
			}
			p.dobaviPratka(new Pratka(new Random().nextInt(10000), dok.getIme(), korab.getIme(), this.ime,
					LocalDateTime.now()));
			synchronized (dok) {
				dok.notifyAll();
			}
		}
	}

	@Override
	public String toString() {
		return "Kran [p=" + p + ", ime=" + ime + "]";
	}

}
