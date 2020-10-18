package tp4.parcial2019.semaforos;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Caja implements Runnable{
    Banco banco;
    int id;
    Lock mutex = new ReentrantLock();

    public Caja(Banco banco, int id) {
        this.banco = banco;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            banco.hayClientes.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(true){
            System.out.println("||||| La caja "+id+" se fija si hay alguien");
            banco.intentarAtender(); //Hay un cliente
            mutex.lock();
            Cliente cliente = banco.siguienteCliente();
            mutex.unlock();
            System.out.println("Se atiende el cliente "+cliente.getID());
            System.out.println("En la caja "+id+ " |</>| "+ cliente.getID() + " se va");
            banco.retirarse();
            if (banco.cola.isEmpty()){
                banco.hayClientes.drainPermits();
            }
        }
    }
}
