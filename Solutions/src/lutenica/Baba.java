package lutenica;

import java.util.Random;

public class Baba extends Person implements Runnable {
	private static final int MIN_LUTENICA = 3;
	private static final int MAX_LUTENICA = 12;
	private Kuhnq kuhnq;

	public Baba(String ime, int godini, Kuhnq kuhnq) {
		super(ime, godini);
		this.kuhnq = kuhnq;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			this.kuhnq.imaLiZaLutenica();
			System.out.println("Zapochvam da varim");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Svarih lutenica " + this);
			this.kuhnq.proizvedi(this);
			int proizvedeno = new Random().nextInt(MAX_LUTENICA - MIN_LUTENICA + 1) + MIN_LUTENICA;
			System.out.println(this+" proizvedoh "+proizvedeno+ "kg naikachestvenata lutenica ,aide ela da opitash brat...");
			synchronized (this.kuhnq) {
				this.kuhnq.notifyAll();
			}
		}
	}

	@Override
	public String toString() {
		return "Baba [getIme()=" + getIme() + ", getGodini()=" + getGodini() + "]";
	}

}
