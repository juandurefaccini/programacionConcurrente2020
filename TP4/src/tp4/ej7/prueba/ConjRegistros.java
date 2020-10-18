package tp4.ej7.prueba;

import java.util.concurrent.Semaphore;

public class ConjRegistros {
    Registro registro;
    Semaphore semaphoreEscritor = new Semaphore(1);
    Semaphore semaphoreLector = new Semaphore(5);
    // 0 --> 5      0
    int cant = 0;

    public ConjRegistros(Registro registro) {
        this.registro = registro;
    }

    public boolean intentarLeer() {
        if (semaphoreEscritor.availablePermits() == 0){ //Esta escrbiendo
            System.out.println("Hay alguien"); //Escriubiendo
            try {
                semaphoreEscritor.acquire(); //Falso
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void intentarEscribir() {
        try {
            semaphoreEscritor.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void avisar() {
        semaphoreEscritor.release();
    }

    public static void main(String[] args) {
        ConjRegistros c = new ConjRegistros(new Registro(1));

        ProcesoLector p1 = new ProcesoLector(c);
        ProcesoLector p2 = new ProcesoLector(c);
        ProcesoLector p3 = new ProcesoLector(c);
        ProcesoLector p4 = new ProcesoLector(c);
        ProcesoLector p5 = new ProcesoLector(c);
        ProcesoLector p6 = new ProcesoLector(c);

        ProcesoEscritor p7 = new ProcesoEscritor(c);

        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(p2);
        Thread t3 = new Thread(p3);
        Thread t4 = new Thread(p4);
        Thread t5 = new Thread(p5);
        Thread t6 = new Thread(p6);

        Thread t7 = new Thread(p7);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t7.start();
    }
}
