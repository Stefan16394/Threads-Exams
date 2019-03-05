package rakiq2;

import java.util.concurrent.atomic.AtomicInteger;

public class Kazan {
	private static int id = 0;
	private int kazanId;
	private PlodoveTip tip;
	private boolean variLiSeRakiq;
	private AtomicInteger kolichestvo = new AtomicInteger(0);

	public Kazan() {
		this.kazanId = id++;
	}

	public PlodoveTip getTip() {
		return tip;
	}
	
	public void resetKolichestvo() {
		this.kolichestvo.set(0);
	}

	public void uvelichiKolichestvo() {
		this.kolichestvo.incrementAndGet();
	}

	public AtomicInteger getKolichestvo() {
		return kolichestvo;
	}

	public boolean isVariLiSeRakiq() {
		return variLiSeRakiq;
	}

	public void setVariLiSeRakiq(boolean variLiSeRakiq) {
		this.variLiSeRakiq = variLiSeRakiq;
	}

	public void setTip(PlodoveTip tip) {
		this.tip = tip;
	}

	@Override
	public String toString() {
		return "Kazan [kazanId=" + kazanId + ", tip=" + tip + ", variLiSeRakiq=" + variLiSeRakiq + ", kolichestvo="
				+ kolichestvo + "]";
	}

}
