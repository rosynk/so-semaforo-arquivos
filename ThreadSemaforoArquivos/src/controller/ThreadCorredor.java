package controller;

import java.util.concurrent.Semaphore;

public class ThreadCorredor extends Thread {
	
	private Semaphore porta;
	private int pessoas;
	private static boolean [] corredor = new boolean [4]; 

	
	public ThreadCorredor(Semaphore porta, int pessoas) {
		
		this.pessoas = pessoas;
		this.porta = porta;
		
	}
	
	@Override
	public void run() {
		escolhaCorredor();
	}
	
	private void escolhaCorredor() {
		// TODO Auto-generated method stub
		int randomCorredor = (int) ((Math.random() * 4));
		
		while (corredor[randomCorredor]) {
			randomCorredor = (int)((Math.random() * 4));	
		}
		
		corredor[randomCorredor] = true;
		
		System.out.println("#" + pessoas + " está no corredor: " + randomCorredor);
		
		int randomAndar = (int)((Math.random() * 3) + 4);
		int distanciaTotal = 200;
		int distanciaPercorrida = 0;
		
		while (distanciaPercorrida < distanciaTotal) {
			distanciaPercorrida += randomAndar;
			
			try {
				sleep (10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("#" + pessoas + " já andou " + distanciaPercorrida + "ms. >> " + randomAndar);
		}
		
		try {
			
			System.out.println("#" + pessoas + " chegou na porta! ------------");
			System.out.println("#" + pessoas + " está esperando para passar na porta.");
			porta.acquire(); //inicio critico
			cruzarPorta();
			System.out.println("#" + pessoas + " passou pela porta");
			porta.release(); // fim critico
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	private void cruzarPorta() throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.out.println("#" + pessoas + " está passando pela porta!");
		int passarPorta = (int)((Math.random() * 1001) + 1000);		
		sleep(passarPorta);
		
	}
}