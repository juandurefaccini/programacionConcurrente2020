package tp4.eJ7.anda;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ConjRegistros {
    Registro registro;
    Semaphore semaphoreEscritor = new Semaphore(1);
    List<ProcesoLector> procesoLectors = new ArrayList<>();

    public ConjRegistros(Registro registro) {
        this.registro = registro;
    }

    public synchronized boolean intentarLeer() {
        int valor = semaphoreEscritor.availablePermits();
        return valor > 0;
    }

    public synchronized void intentarEscribir() {
        System.out.println("-----El escritor va a desalojar a los lectores" + "|| Hay " + procesoLectors.size() + " lectores");
        if (!procesoLectors.isEmpty()){
            try {
                semaphoreEscritor.acquire(); //Se puede poner solo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void avisar() {
        semaphoreEscritor.release();
//        for (ProcesoLector p :procesoLectors) {
//            System.out.println("Avisa al " + p.getID());
//            p.despertar();
//        }
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
            Thread.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t7.start();
    }
}
