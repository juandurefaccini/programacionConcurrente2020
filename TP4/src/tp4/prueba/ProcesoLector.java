package tp4.prueba;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProcesoLector extends Proceso {
    Lock mutex = new ReentrantLock();

    public ProcesoLector(ConjRegistros conjRegistros) {
        super(conjRegistros);
    }

    @Override
    public void run() {
        if (!conjRegistros.intentarLeer()){
            conjRegistros.semaphoreEscritor.release();
        };
        System.out.println(id + " termino de intentar leer");
        leer();
        System.out.println(id + " termino de leer y se fue");
    }

    private void leer() {
        System.out.println(id + " Comienza a leer");
        int porcentaje = 0;
        while (porcentaje!=100){
            if (!conjRegistros.intentarLeer()){
                conjRegistros.semaphoreEscritor.release();
            };
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
