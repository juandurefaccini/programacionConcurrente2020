package tp4.eJ7.anda;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProcesoLector extends Proceso{
    boolean leyendo;
    Lock mutex = new ReentrantLock();

    public ProcesoLector(ConjRegistros conjRegistros) {
        super(conjRegistros);
    }

    @Override
    public void run() {
        //Leer
        conjRegistros.procesoLectors.add(this);
        leyendo = conjRegistros.intentarLeer();
        if (!leyendo){
            this.esperarLectura();
        }
        leer();
        System.out.println(id + " termino de leer y se fue");
        synchronized (conjRegistros.procesoLectors){
            conjRegistros.procesoLectors.remove(this);
        }
//        System.out.println(conjRegistros.procesoLectors.toString());
    }

    private synchronized void leer() {
        System.out.println(id + " Comienza a leer");
        int porcentaje = 0;
        while (porcentaje!=100){
            leyendo = conjRegistros.intentarLeer();
            if(!leyendo)
                this.esperarLectura();
            else {
                porcentaje = porcentaje + 10;
                System.out.println(id + " leyendo " + porcentaje +
                        "%");
            }
        }
    }

    public int getID(){return id;}

    public synchronized void esperarLectura() {
        System.out.println(id + " a dormir");
        while (!leyendo){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(id + " Se desperto");
        }

    }

    public synchronized void despertar(){
        leyendo = true;
        this.notify();
    }

    public void setLeyendo(boolean bool){
        leyendo = bool;
    }

    @Override
    public String toString() {
        return "ProcesoLector{" +
                "id=" + id +
                '}';
    }
}
