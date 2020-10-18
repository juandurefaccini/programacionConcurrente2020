package tp4.parcial2019.semaforos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Banco {
    List<Cliente> cola = new ArrayList<>();
    Semaphore hayClientes = new Semaphore(0);

    //Entonces no ejecuta hasta que alguien llega
    //El primero que llega al no haber nadie lo despierta
    //Ejcuta atiende a todos while haya gente sigo atendiendo

    List<Cuenta> cuentas = new ArrayList<>();

    public void intentarAtender() {
        try {
            hayClientes.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Cliente siguienteCliente() {
        Cliente sig = cola.remove(0);
        System.out.println(sig.toString());
        return sig;
    }

    public void avisoLlegada(Cliente cliente) {
        cola.add(cliente);
        System.out.println("Se agrego un cliente " + " || Estado semaforo: "+hayClientes.availablePermits());
        if(cola.isEmpty()){
            hayClientes.release();
        }
    }

    public static void main(String[] args) {
        Banco galicia = new Banco();
        Caja caja = new Caja(galicia,1);
        Thread thread = new Thread(caja);
        thread.setDaemon(true);
        thread.start();

        for (int i = 0; i < 4; i++) {
            Thread cliente = new Thread(new Cliente(i,galicia));
            cliente.start();
        }
    }

    public void retirarse() {
        hayClientes.release();
    }
}
