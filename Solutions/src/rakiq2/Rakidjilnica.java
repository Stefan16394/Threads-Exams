package rakiq2;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class Rakidjilnica {
	private Map<Rakidjiq, Integer> rakidjii = new ConcurrentHashMap<>();
	private Map<PlodoveTip, Integer> rakii = new ConcurrentHashMap<>();

	public void dobaviRakidjia(Rakidjiq r, int litri) {
		if (!this.rakidjii.containsKey(r)) {
			this.rakidjii.put(r, 0);
		}
		this.rakidjii.put(r, this.rakidjii.get(r) + litri);
	}

	public void dobaviRakiq(PlodoveTip r, int litri) {
		if (!this.rakii.containsKey(r)) {
			this.rakii.put(r, 0);
		}
		this.rakii.put(r, this.rakii.get(r) + litri);
	}
	
	public String daiStatistiki() {
		StringBuffer sb = new StringBuffer();
		for(Entry<Rakidjiq, Integer> s:this.rakidjii.entrySet()) {
			sb.append(s.getKey().getIme()+" - "+s.getValue()).append("\n");
		}
		for(Entry<PlodoveTip, Integer> s:this.rakii.entrySet()) {
			sb.append(s.getKey()+" - "+s.getValue()).append("\n");
		}
		return sb.toString(); 
	}

	public Map<Rakidjiq, Integer> getRakidjii() {
		return rakidjii;
	}

	public Map<PlodoveTip, Integer> getRakii() {
		return rakii;
	}
}
