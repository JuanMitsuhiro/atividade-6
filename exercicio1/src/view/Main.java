package view;

import java.util.concurrent.Semaphore;

import controller.CavaleiroThread;

public class Main {
	public static void main(String[] args) {
		
		Semaphore semaforo = new Semaphore(1);
		int portaCerta = (int)Math.random()*4;
		
		
		for(int i = 1; i <= 4; i++ ){
			CavaleiroThread t = new CavaleiroThread(semaforo, i, portaCerta);
			t.start();
		}

	}

}
