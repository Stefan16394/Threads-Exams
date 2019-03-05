package rakiq2;

import java.util.Set;

public class Berach implements Runnable {
	private PlodoveTip tip;
	private String ime;
	private Set<Kazan> kazani;
	private Rakidjilnica r;

	public Berach(PlodoveTip tip, String ime, Set<Kazan> kazani, Rakidjilnica r) {
		this.tip = tip;
		this.r = r;
		this.ime = ime;
		this.kazani = kazani;
	}

	public PlodoveTip getTip() {
		return tip;
	}

	public String getIme() {
		return ime;
	}

	public Set<Kazan> getKazani() {
		return kazani;
	}

	@Override
	public void run() {
		while (true) {
			Kazan kazan = this.vzemiKazan();
			while (kazan == null) {
				System.out.println("Nqma kazan shte chakam " + this);
				synchronized (this.kazani) {
					try {
						this.kazani.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				kazan = this.vzemiKazan();
			}
			System.out.println(this + " zapochvam da bera " + this.getTip() + " v " + kazan);
			while (kazan.getKolichestvo().get() < 10) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				kazan.uvelichiKolichestvo();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized (this.kazani) {
				this.kazani.notifyAll();
			}
		}
	}

	public Kazan vzemiKazan() {
		synchronized (this.kazani) {
			for (Kazan k : this.kazani) {
				if (!k.isVariLiSeRakiq() && (k.getTip() == null || k.getTip().equals(this.getTip()))) {
					k.setTip(this.getTip());
					return k;
				}
			}
			return null;
		}

	}

	@Override
	public String toString() {
		return "Berach [tip=" + tip + ", ime=" + ime + "]";
	}

}
