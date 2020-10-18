package tp4.ej10.semaforo;

import java.util.concurrent.Semaphore;

public class Casa {
    Semaphore sillas = new Semaphore(4,true); //Controlar cant sillas disponibles
    Semaphore enanos = new Semaphore(0,true); //Controlar que hay enanos para comer
    Semaphore comer = new Semaphore(0,true); //Despierta a un enano para comer

    public synchronized void intentarSentarse() {
        try {
            sillas.acquire();
            enanos.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void irse() {
        sillas.release();
    }

    public void atender() {
        try {
            enanos.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void esperarComida(Enanito enanito) {
        try {
            comer.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void servirComida() {
        comer.release();
    }

    public static void main(String[] args) {
        Casa casa = new Casa();
        Thread blancanieves = new Thread(new Blancanieves(casa));
        blancanieves.setDaemon(true);
        blancanieves.start();
        for (int i = 0; i < 6; i++) {
            Thread thread = new Thread(new Enanito(i,casa));
            thread.start();
        }
    }
}
