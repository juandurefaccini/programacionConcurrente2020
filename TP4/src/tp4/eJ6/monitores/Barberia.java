package tp4.eJ6.monitores;

import java.util.ArrayList;
import java.util.List;

public class Barberia{
    List <Cliente> clientes;
    int cantSillasDisponibles;

    public Barberia(int cantSillasDisponibles) {
        this.clientes = new ArrayList<>();
        this.cantSillasDisponibles = cantSillasDisponibles;
    }

    public synchronized boolean intentarSentarse(Cliente cliente) {
        if (cantSillasDisponibles == 0){
            return false;
        }
        clientes.add(cliente);
        this.notify();
        return true;
    }

    public synchronized Cliente obtenerSiguiente() {
        while(clientes.isEmpty()){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return clientes.remove(0);
    }

    public static void main(String[] args) {
        Barberia barberia = new Barberia(3);
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
