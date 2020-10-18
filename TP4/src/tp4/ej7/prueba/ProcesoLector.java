package tp4.ej7.prueba;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProcesoLector extends Proceso {
    Lock mutex = new ReentrantLock();

    public ProcesoLector(ConjRegistros conjRegistros) {
        super(conjRegistros);
    }

    @Override
    public void run() {
        conjRegistros.intentarLeer();
        System.out.println(id + " Comienza a leer");
        leer();
        System.out.println(id + " termino de leer y se fue");
    }

    private void leer() {
        int porcentaje = 0;
        while (porcentaje!=100){
             conjRegistros.intentarLeer();
             porcentaje = porcentaje + 10;
             System.out.println(id + " leyendo " + porcentaje + "%");
            }
        conjRegistros.semaphoreEscritor.release();
    }

    public int getID(){return id;}

    @Override
    public String toString() {
        return "ProcesoLector{" +
                "id=" + id +
                '}';
    }
}
