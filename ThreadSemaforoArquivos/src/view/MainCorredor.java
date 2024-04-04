package view;

import java.util.concurrent.Semaphore;

import controller.ThreadCorredor;

public class MainCorredor {
	public static void main(String[] args) {
		
		Semaphore porta = new Semaphore(1);
		
		for (int pessoas = 0; pessoas < 4; pessoas++) {
			Thread TCorredor = new ThreadCorredor(porta, pessoas);
			TCorredor.start();
		}
	}
}
