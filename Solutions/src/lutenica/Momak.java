package lutenica;

import java.util.Random;

public class Momak extends Person implements Runnable{
	private LutenicaBiznes lutenica;
	public Momak(String ime, int godini,LutenicaBiznes b) {
		super(ime, godini);
		this.lutenica= b;
	}

	@Override
	public void run() {
		Baraka b= this.lutenica.getBaraka();
		Kuhnq k = this.lutenica.getKuhnq();
		while(true) {
			ZelenchukTip t = ZelenchukTip.values()[new Random().nextInt(ZelenchukTip.values().length)];
			System.out.println(this +" reshi da obrabotva "+t);
			ZelenchukTip zelenchuk = b.daiZelenchuk(t);
			System.out.println(this+" nai nakraq poluchih "+t);
			try {
				Thread.sleep(zelenchuk.getVremeZaObrabotka()*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			k.dobaviVTavata(t);		
		}	
	}

	@Override
	public String toString() {
		return "Momak [getIme()=" + getIme() + ", getGodini()=" + getGodini() + "]";
	}

}
