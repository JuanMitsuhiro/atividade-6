package view;

import java.util.concurrent.Semaphore;
import controller.AeroportoThread;

public class Main {
	public static void main(String[] args) {
		
		Semaphore semaforo = new Semaphore(2);
		
		for (int i = 1; i <= 12; i++) {
			AeroportoThread t = new AeroportoThread(semaforo, i);
			t.start();
		}

	}

}
