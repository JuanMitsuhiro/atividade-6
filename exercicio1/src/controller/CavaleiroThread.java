package controller;

import java.util.concurrent.Semaphore;

public class CavaleiroThread extends Thread{
	Semaphore semaforo;
	private static int[] portaEscolha = new int[4];
	private int id;
	private static boolean tocha = true;
	private static boolean pedra = true;
	private int portaCerta;
	
	public CavaleiroThread(Semaphore semaforo, int id, int portaCerta) {
		this.semaforo = semaforo;
		this.id = id;
		this.portaCerta = portaCerta;
	}
	
	@Override
	public void run() {
		corredor();
		
		try {
			semaforo.acquire();
			portas();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		} finally {
			semaforo.release();
		}
		
		
	}
	
	public void corredor() {
		int metrosTotal = 0;
		int bonus = 0;
		
		while (metrosTotal < 2000) {
			try {
				metrosTotal += (int)(Math.random()* 3)+2 + bonus; 
				System.out.println("Cavaleiro " + id + " andou " + metrosTotal);
				if (metrosTotal >= 500 && tocha == true) {
					tocha = false;
					bonus = 2;
					System.out.println("Cavaleiro " + id + " pegou a tocha");
				} else {
					if (metrosTotal >= 1500 && pedra == true &&bonus == 0) {
						pedra = false;
						bonus = 2;
						System.out.println("Cavaleiro " + id + " pegou a pedra");
					}
				}
				
				sleep(50);
			} catch (Exception e) {
				System.out.println(e.getStackTrace());
			}
			
		}
		System.out.println("O Cavaleiro " + id + " está no fim do corredor!");
	}
	
	public void portas() {
		int escolhaPorta;
		do {
			escolhaPorta = (int)(Math.random()*4);
			
			if (escolhaPorta == portaCerta && portaEscolha[portaCerta] == 0) {
				portaEscolha[escolhaPorta] = id;
				System.out.println("Cavaleiro " + id + " abriu a Porta " + escolhaPorta + " e VENCEU!");
			} else if (escolhaPorta != portaCerta && portaEscolha[escolhaPorta] == 0){
				portaEscolha[escolhaPorta] = id;
				System.out.println("Cavaleiro " + id + " abriu a Porta " + escolhaPorta + " e PERDEU!");			
			}
		} while (portaEscolha[escolhaPorta] != id);
		
		
	}

}
