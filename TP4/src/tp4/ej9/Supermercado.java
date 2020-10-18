package tp4.ej9;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Supermercado{
    List<Producto> pendientes = new ArrayList<>();
    Semaphore clientes = new Semaphore(0);
    Lock mutex = new ReentrantLock();

    public void addProducto(Producto producto){
        mutex.lock();
        pendientes.add(producto);
        clientes.release();
        mutex.unlock();
    }

    public void intentarAtender(){
        try {clientes.acquire();} catch (InterruptedException e) {e.printStackTrace();
        }
    }

    public synchronized Producto getSiguienteProducto() {
        return pendientes.remove(0);
    }

    public static void main(String[] args) {
        Supermercado carrefour = new Supermercado();
        Canasta canasta = new Canasta();
        Caja caja = new Caja(1,carrefour,canasta);

        Producto producto = new Producto(1,100);
        Producto producto1 = new Producto(2,10000);
        Producto producto2 = new Producto(3,1000);

        canasta.addProducto(producto1);
        canasta.addProducto(producto2);

        carrefour.addProducto(producto);
        carrefour.addProducto(producto1);
        carrefour.addProducto(producto2);

        Thread thread = new Thread(caja);
        thread.setDaemon(true);
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        canasta.addProducto(new Producto(4,10)); //Se hace
    }
}
