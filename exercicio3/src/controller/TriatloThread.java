package controller;

import java.util.concurrent.Semaphore;

public class TriatloThread extends Thread{
	private Semaphore semaforo;
	private int id;
	private int pontuacao = 0;
	private static int valor;
	
	public TriatloThread(Semaphore semaforo, int id) {
		this.semaforo = semaforo;
		this.id = id;
	}
	
	@Override
	public void run() {
		id = (int) threadId();
		//corrida
		corridaCiclismo(20, 25, 300, 30);
		try {
			semaforo.acquire();
			System.out.println("Atleta #" + id + " finalizou CORRIDA");
			tiroAlvo(0, 10, 500, 3000);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("Atleta #" + id + " finalizou TIRO AO ALVO");
			semaforo.release();
		}
		//ciclismo
		corridaCiclismo(30, 40, 500, 40);
		pontuacaoFinal();
		System.out.println("Atleta #" + id + " finalizou CICLISMO");
	}
	
	private void corridaCiclismo(int min, int max, int circuito, int espera) {
		int totalPercorrido = 0;
		while(totalPercorrido < circuito){
			try {
				System.out.println("Atleta #" + id + " alcançou " + totalPercorrido + "m");
				sleep(espera);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			totalPercorrido += (int)(Math.random()*(max - min + 1)) + min; 
		}
	}
	
	private void tiroAlvo(int minPonto, int maxPonto, int minEspera, int maxEspera) {
		int tiro;
		for(int i = 1; i <= 3; i++) {
			int espera = (int)(Math.random()*(maxEspera - minEspera + 1)) + minEspera;
			try {
				sleep(espera);
				tiro = (int)(Math.random()*(maxPonto - minPonto + 1)) + minPonto; 
				pontuacao += tiro;
				System.out.println("Atleta #" + id + " pontuou " + tiro + " no " + i + "º tiro");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private synchronized void pontuacaoFinal() {
		pontuacao += (250-valor);
		valor += 10;
	}	
	
	
	public int getPontuacao() {
		return pontuacao;
	}
	
	public int getID() {
		return id;
	}

}
