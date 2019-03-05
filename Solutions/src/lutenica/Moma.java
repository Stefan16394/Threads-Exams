package lutenica;

import java.util.Random;

public class Moma extends Person implements Runnable {
	private static final int MIN_KOLICHSETVO = 3;
	private static final int MAX_KOLICHESTVO = 6;
	private LutenicaBiznes lutenica;

	public Moma(String ime, int godini, LutenicaBiznes lutenica) {
		super(ime, godini);
		this.lutenica = lutenica;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		Baraka baraka =lutenica.getBaraka();
		while (true) {
			ZelenchukTip t = ZelenchukTip.values()[new Random().nextInt(ZelenchukTip.values().length)];
			System.out.println(this + " zapochva da bere " + t);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int kolichestvo = new Random().nextInt(MAX_KOLICHESTVO - MIN_KOLICHSETVO + 1) + MIN_KOLICHSETVO;
			System.out.println(this + " nabra " + kolichestvo + " ot " + t);
			baraka.dobaviZelenchuk(t, kolichestvo);
		}

	}

	@Override
	public String toString() {
		return "Moma [getIme()=" + getIme() + ", getGodini()=" + getGodini() + "]";
	}

}
