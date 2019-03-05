package pristanishte;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Demo {
	public static void main(String[] args) {
		Pristanishte p = new Pristanishte();
		List<Thread> korabi = new CopyOnWriteArrayList<>();
		List<Thread> kranove = new CopyOnWriteArrayList<>();
		for (int i = 0; i < 2; i++) {
			kranove.add(new Kran(p, "Kran " + i));
		}
		for (int i = 0; i < 10; i++) {
			korabi.add(new Thread(new Korab("Korab " + i, p)));
		}
		for (Thread t : korabi) {
			t.start();
		}
		for (Thread t : kranove) {
			t.start();
		}
		for (Thread t : korabi) {
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		p.printiraiPratki();
		
	}
}
