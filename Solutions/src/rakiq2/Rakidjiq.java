package rakiq2;

import java.util.Random;
import java.util.Set;

public class Rakidjiq implements Runnable {
	private PlodoveTip tip;
	private Set<Kazan> kazani;
	private String ime;
	private Rakidjilnica rakidjilnica;
	public Rakidjiq(PlodoveTip tip, Set<Kazan> kazani,String ime, Rakidjilnica rakidjilnica) {
		this.tip = tip;
		this.ime=ime;
		this.kazani = kazani;
		this.rakidjilnica=rakidjilnica;
		
	}

	@Override
	public void run() {
		while(true) {
			Kazan kazan = this.vzemiKazan();
			while(kazan ==null) {
				System.out.println(this + " to nqma gotovi kazani shte chakam");
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
			System.out.println(this +" namerih kazan sega shte svarq edna "+tip );
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int kolichestvo = new Random().nextInt(kazan.getKolichestvo().get())+1;
			System.out.println(this + " svarih "+kolichestvo +"kg rakiq");
			kazan.setVariLiSeRakiq(false);
			kazan.setTip(null);
			kazan.resetKolichestvo();
			this.rakidjilnica.dobaviRakidjia(this, kolichestvo);
			this.rakidjilnica.dobaviRakiq(this.getTip(), kolichestvo);
			synchronized (this.kazani) {
				this.kazani.notifyAll();
			}
		}
		
	}
	
	public Kazan vzemiKazan() {
		synchronized (this.kazani) {
			for(Kazan k:this.kazani) {
				if(!k.isVariLiSeRakiq() && k.getTip()!=null && k.getTip().equals(this.tip) && k.getKolichestvo().get()>=10) {
					k.setVariLiSeRakiq(true);
					return k;
				}
			}
			return null;
		}
	}

	@Override
	public String toString() {
		return "Rakidjiq [tip=" + tip + ", ime=" + ime + "]";
	}

	public PlodoveTip getTip() {
		return tip;
	}

	public Set<Kazan> getKazani() {
		return kazani;
	}

	public String getIme() {
		return ime;
	}

	public Rakidjilnica getRakidjilnica() {
		return rakidjilnica;
	}

}
