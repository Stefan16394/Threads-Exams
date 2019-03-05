package pristanishte;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class Pristanishte {
	private List<Dok> dokove;
	private Set<Pratka> pratki = new CopyOnWriteArraySet<>();

	public Pristanishte() {
		super();
		this.dokove = new CopyOnWriteArrayList<>();
		for (int i = 0; i < 5; i++) {
			this.dokove.add(new Dok("Dok " + i));
		}
	}

	public Dok daiRandomDok() {
		return this.dokove.get(new Random().nextInt(this.dokove.size()));
	}

	public synchronized Korab daiKorab() {
		for (Dok d : this.dokove) {
			Korab k = d.getKorab();
			if (k != null && !k.isRaztovarvaLiSe()) {
				k.setRaztovarvaLiSe(true);
				return k;
			}
		}
		return null;
	}

	public Dok raztovariKorab(Korab k) {
		System.out.println(k);
		for (Dok d : this.dokove) {
			System.out.println(d);
			if (d.getKorab() == k) {
				k.setBroiPratki(0);
				d.removeKorab(k);
				return d;
			}
		}
		return null;
	}

	public void dobaviPratka(Pratka p) {
		if (p != null) {
			this.pratki.add(p);
		}
	}

	public List<Dok> getDokove() {
		return dokove;
	}
	
	public void printiraiPratki() {
		for(Pratka p:this.pratki) {
			System.out.println(p);
		}
	}

}
