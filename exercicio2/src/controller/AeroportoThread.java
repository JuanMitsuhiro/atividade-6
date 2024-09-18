package controller;

import java.util.concurrent.Semaphore;

public class AeroportoThread extends Thread{
	private int tempo;
	private int num;
	private static boolean[] pistas = new boolean[2];
	Semaphore semaforo;
	
	public AeroportoThread(Semaphore semaforo, int num) {
		this.semaforo = semaforo;
		this.num = num;
	}
	
	@Override
	public void run() {
		avioesPista();
	}
	
	public void avioesPista() {
		int escolha = 0;
		try {
			semaforo.acquire();
			do {
				escolha = (int)(Math.random() * 2);
			} while (pistas[escolha] != false);
			
			pistas[escolha] = true;
			if (escolha == 0) {
				System.out.println("Avião " + num + " iniciou procedimento de decolagem na PISTA NORTE");
			} else {
				System.out.println("Avião " + num + " iniciou procedimento de decolagem na PISTA SUL");
			}
			
			//manobra
			calcularTempo(700,300);
			System.out.println("Avião " + num + " terminou a Manobra (" + tempo + "ms);");
			//taxiar
			calcularTempo(1000,500);
			System.out.println("Avião " + num + " terminou a Taxiamento (" + tempo + "ms);");
			//decolagem
			calcularTempo(800,600);
			System.out.println("Avião " + num + " terminou a Decolagem (" + tempo + "ms);");
			//afastamento da área
			calcularTempo(800,300);
			System.out.println("Avião " + num + " terminou o Afastamento (" + tempo + "ms);");
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		} finally {
			pistas[escolha] = false;
			System.out.println("Avião " + num + " FINALIZOU o procedimento de decolagem.");
			semaforo.release();
		}
	}
	
	
	public void calcularTempo(int min, int max) {
		tempo = (int)(Math.random()*(max - min + 1))+ min;
		
		try {
			sleep(tempo);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		
	}

}
