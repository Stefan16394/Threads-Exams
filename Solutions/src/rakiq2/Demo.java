package rakiq2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class Demo {
	public static void main(String[] args) {
		Rakidjilnica rakidjilnica = new Rakidjilnica();
		Set<Kazan> kazani = new CopyOnWriteArraySet<>();
		new Thread(new Statistika(rakidjilnica)).start();
		for(int i=0;i<5;i++) {
			kazani.add(new Kazan());
		}
		List<Berach> berachi = new ArrayList<>();
		List<Rakidjiq> rakidjii = new ArrayList<>();
		PlodoveTip[] plodove = PlodoveTip.values();
		for(int i=0;i<7;i++) {
			berachi.add(new Berach(plodove[i%plodove.length], "Berach "+i, kazani,rakidjilnica));
		}
		for(Berach b:berachi) {
			new Thread(b).start();
		}
		for(PlodoveTip p:plodove) {
			rakidjii.add(new Rakidjiq(p, kazani, "Rakidjia "+p.ordinal(),rakidjilnica));
		}
		for(Rakidjiq r:rakidjii) {
			new Thread(r).start();
		}
	}
}
