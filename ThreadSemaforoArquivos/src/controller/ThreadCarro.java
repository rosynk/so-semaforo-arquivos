package controller;

import java.util.concurrent.Semaphore;

public class ThreadCarro extends Thread {
    int carro;
    Semaphore permissaoCruzamento;
    Semaphore acidentes;

    public ThreadCarro(int carro, Semaphore permissaoCruzamento, Semaphore acidentes) {
        this.carro = carro;
        this.permissaoCruzamento = permissaoCruzamento;
        this.acidentes = acidentes;
    }

    public void run() {
        carroAndando();
    }

    private void carroAndando() {
        int random = (int) ((Math.random() * 4) + 1);

        try {
            permissaoCruzamento.acquire(); // Carro tenta adquirir permissão para passar pelo cruzamento

            if (random == 1) {
                esquerda();
            } else if (random == 2) {
                direita();
            } else if (random == 3) {
                frente();
            } else {
                atras();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            permissaoCruzamento.release(); // Após passar pelo cruzamento, o carro libera a permissão
        }
    }

    private void esquerda() throws InterruptedException {
        System.out.println("#" + carro + " está pela esquerda.");
        evitarAcidentes();
        System.out.println("#" + carro + " passou pelo cruzamento.");
    }

    private void direita() throws InterruptedException {
        System.out.println("#" + carro + " está pela direita.");
        evitarAcidentes();
        System.out.println("#" + carro + " passou pelo cruzamento.");
    }

    private void frente() throws InterruptedException {
        System.out.println("#" + carro + " está pela frente.");
        evitarAcidentes();
        System.out.println("#" + carro + " passou pelo cruzamento.");
    }

    private void atras() throws InterruptedException {
        System.out.println("#" + carro + " está por atras.");
        evitarAcidentes();
        System.out.println("#" + carro + " passou pelo cruzamento.");
    }

    private void evitarAcidentes() {
        try {
            acidentes.acquire();
            System.out.println("#" + carro + " está passando pelo cruzamento.");
            Thread.sleep(1000); // Simula a passagem pelo cruzamento
            acidentes.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
