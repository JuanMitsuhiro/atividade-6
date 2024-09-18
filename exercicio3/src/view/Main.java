package view;

import java.util.concurrent.Semaphore;

import controller.TriatloThread;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Semaphore semaforo = new Semaphore(5);
		int[] vetorPontos = new int[25];
		int[] vetorId =  new int[25];
		int id = 0;
		int i;
		int j;

		TriatloThread[] atletas = new TriatloThread[25];
		
		for (i = 0; i < 25; i++) {
			atletas[i] = new TriatloThread(semaforo, id);
			atletas[i].start();
		}
		
		for (TriatloThread atleta : atletas) {
            atleta.join();
        }
		
		for (i = 0; i < 25; i++) {
			vetorPontos[i] = atletas[i].getPontuacao();		
			vetorId[i] = atletas[i].getID();
		}
		
		int aux;
		for (i = 0; i < 25; i++){
			for (j = i+1; j < 25; j++){
				if (vetorPontos[j] > vetorPontos[i]){
					aux = vetorPontos[i];
	                vetorPontos[i] = vetorPontos[j];
	                vetorPontos[j] = aux;
	                aux = vetorId[i];
	                vetorId[i] = vetorId[j];
	                vetorId[j] = aux;
	            }
	        }
		}
		System.out.println("\nCOLOCAÇÃO:");
		for(i = 0; i < 25; i++) {
			System.out.println(i+1 +"º - Atleta #" + vetorId[i] + " (" + vetorPontos[i] + " pts)");
		}
		
	}

}
