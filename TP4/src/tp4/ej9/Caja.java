package tp4.ej9;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Caja implements Runnable{
    int id;
    Supermercado supermercado;
    Canasta canasta;
    Lock mutex = new ReentrantLock();

    public Caja(int id, Supermercado supermercado, Canasta canasta) {
        this.id = id;
        this.supermercado = supermercado;
        this.canasta = canasta;
    }

    @Override
    public void run() {
        while(true){
            supermercado.intentarAtender();
            Producto siguienteProducto = supermercado.getSiguienteProducto();
            mutex.lock();
            if (canasta.contains(siguienteProducto)){
                System.out.println("La caja " + id + " vendio " + siguienteProducto.getID() + " a " + siguienteProducto.getPrecio()*0.9);
            }
            else{
                System.out.println("La caja " + id + " vendio " + siguienteProducto.getID() + " a " + siguienteProducto.getPrecio());
            }
            mutex.unlock();
        }
    }
}
