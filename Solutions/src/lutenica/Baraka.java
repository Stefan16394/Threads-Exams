package lutenica;

import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Baraka {
	private Map<ZelenchukTip, Integer> zelenchuci = new ConcurrentHashMap<>();

	public Baraka() {
		this.zelenchuci.put(ZelenchukTip.CHUSKA, 0);
		this.zelenchuci.put(ZelenchukTip.DOMAT, 0);
		this.zelenchuci.put(ZelenchukTip.PATLIDJAN, 0);

	}

	public void dobaviZelenchuk(ZelenchukTip t, int kolichsetvo) {
		int currentKolichestvo = this.zelenchuci.get(t);
		while (currentKolichestvo + kolichsetvo > 40) {
			System.out.println("[MOMA]To nqma mqsto za " + t + " shte chakam...");
			synchronized (this.zelenchuci) {
				this.zelenchuci.notifyAll();
			}
			synchronized (this) {
				try {
					this.wait(2000);
					t = ZelenchukTip.values()[new Random().nextInt(ZelenchukTip.values().length)];
					currentKolichestvo = this.zelenchuci.get(t);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		this.zelenchuci.put(t, currentKolichestvo + kolichsetvo);
		synchronized (this.zelenchuci) {
			this.zelenchuci.notifyAll();
		}
	}

	public ZelenchukTip daiZelenchuk(ZelenchukTip t) {
		while (this.zelenchuci.get(t) <= 0) {
			System.out.println("TO pyk nqma ot zelenchuci tiq momi kakvo pravqt, shte chakam...");
		
			synchronized (this) {
				this.notifyAll();
			}
			synchronized (this.zelenchuci) {
				try {
					this.zelenchuci.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		this.zelenchuci.put(t, this.zelenchuci.get(t) - 1);
		synchronized (this) {
			this.notifyAll();
		}
		return t;
	}

}
