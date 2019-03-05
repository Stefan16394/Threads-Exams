package lutenica;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class Kuhnq {
	private static final int MAX_ZELENCHUCI_V_TAVA = 30;
	private Map<ZelenchukTip, Integer> tavi = new ConcurrentHashMap<>();
	private Map<Baba, Integer> babi = new ConcurrentHashMap<>();

	public Kuhnq() {
		this.tavi.put(ZelenchukTip.CHUSKA, 0);
		this.tavi.put(ZelenchukTip.DOMAT, 0);
		this.tavi.put(ZelenchukTip.PATLIDJAN, 0);
	}

	public void dobaviVTavata(ZelenchukTip t) {
		while (this.tavi.get(t) > MAX_ZELENCHUCI_V_TAVA) {
			System.out.println("Tavata e palna shte chakam");

//			synchronized (this) {
//				this.notifyAll();
//			}
			synchronized (this.tavi) {

				try {
					this.tavi.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		this.tavi.put(t, this.tavi.get(t) + 1);
		synchronized (this) {

			this.notifyAll();
		}
	}

	public  void proizvedi(Baba b) {
		if (!this.babi.containsKey(b)) {
			this.babi.put(b, 1);
		} else {
			this.babi.put(b, this.babi.get(b) + 1);
		}
		this.babi.entrySet().stream().forEach(x -> System.out.println(x.getKey() + " - " + x.getValue()));
	}

	public synchronized void imaLiZaLutenica() {

		while (this.tavi.get(ZelenchukTip.CHUSKA) < 5 || this.tavi.get(ZelenchukTip.DOMAT) < 5
				|| this.tavi.get(ZelenchukTip.PATLIDJAN) < 5) {
			System.out.println("[BABA] nqma neshto, chakam...");
			synchronized (this.tavi) {
				this.tavi.notifyAll();
			}
			synchronized (this) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(this.tavi.get(ZelenchukTip.CHUSKA));
			System.out.println(this.tavi.get(ZelenchukTip.DOMAT));
			System.out.println(this.tavi.get(ZelenchukTip.PATLIDJAN));

		}

		this.tavi.put(ZelenchukTip.CHUSKA, this.tavi.get(ZelenchukTip.CHUSKA) - 5);
		this.tavi.put(ZelenchukTip.DOMAT, this.tavi.get(ZelenchukTip.DOMAT) - 5);
		this.tavi.put(ZelenchukTip.PATLIDJAN, this.tavi.get(ZelenchukTip.PATLIDJAN) - 5);
		synchronized (this.tavi) {
			this.tavi.notifyAll();
		}
	}
}
