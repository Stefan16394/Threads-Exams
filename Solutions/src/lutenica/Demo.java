package lutenica;

import java.util.ArrayList;
import java.util.List;

public class Demo {
	public static void main(String[] args) {
		Baraka b = new Baraka();
		Kuhnq k = new Kuhnq();
		LutenicaBiznes lutenica = new LutenicaBiznes(b,k);
		List<Moma> momi = new ArrayList<>();
		List<Momak> momci = new ArrayList<>();
		List<Baba> babi = new ArrayList<>();
		for(int i=0;i<5;i++) {
			momi.add(new Moma("Moma "+i, i+10, lutenica));
			momci.add(new Momak("Momak "+i, i+10, lutenica));
		}
		for(int i=0;i<momi.size();i++) {
			new Thread(momi.get(i)).start();
			new Thread(momci.get(i)).start();
		}
		for(int i=0;i<3;i++) {
			babi.add(new Baba("Baba " +i, i+10, k));
		}
		for(Baba bab:babi) {
			new Thread(bab).start();
		}
		
		
	}
}
