package view;

import java.util.concurrent.Semaphore;

import controller.ThreadCarro;

public class MainCarro {
	public static void main(String[] args) {
		
		Semaphore acidentes = new Semaphore(1);
		Semaphore permissaoCruzamento = new Semaphore(1);
		
		for (int carro = 0 ; carro < 4 ; carro++) {
			Thread Tcarro = new ThreadCarro(carro, permissaoCruzamento, acidentes);
			Tcarro.start();			
		}
	}
}
