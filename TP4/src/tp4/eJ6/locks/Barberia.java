package tp4.eJ6.locks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Barberia{
    List <Cliente> clientes;
    Semaphore lleno;
    Semaphore vacio = new Semaphore(0);
    Lock mutex = new ReentrantLock(); //Me dice si puedo agarrar o no

    public Barberia(int cantSillasDisponibles) {
        this.clientes = new ArrayList<>();
        lleno = new Semaphore(cantSillasDisponibles);
    }

    public boolean intentarSentarse(Cliente cliente) {
        boolean hayLugar = lleno.tryAcquire(); //Intenta agarrar un permiso, si no hay no se bloquea, pone false
        if (!hayLugar){
            return false;
        }
        this.mutex.lock();
        System.out.println("El cliente " + cliente.getID() +" se sento");
        clientes.add(cliente);
        vacio.release(); //Ahora hay un lugar entonces incremento para los que estan bloqueados (barbero)
        this.mutex.unlock();
        return true;
    }

    public Cliente obtenerSiguiente() {
        try {
            this.vacio.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.mutex.lock();
        Cliente siguiente = clientes.remove(0);
        System.out.println("El cliente " + siguiente.getID() +" es el siguiente");
        lleno.release();
        this.mutex.unlock();
        return siguiente;
    }

    public static void main(String[] args) {
        Barberia barberia = new Barberia(5);
        Barbero b = new Barbero(barberia,"Juan");
        Cliente c1 = new Cliente(barberia);
        Cliente c2 = new Cliente(barberia);
        Cliente c3 = new Cliente(barberia);
        Cliente c4 = new Cliente(barberia);
        Cliente c5 = new Cliente(barberia);
        Thread t0 = new Thread(b);
        Thread t1 = new Thread(c1);
        Thread t2 = new Thread(c2);
        Thread t3 = new Thread(c3);
        Thread t4 = new Thread(c4);
        Thread t5 = new Thread(c5);
        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
