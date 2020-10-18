package tp4.parcial2019.monitores;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Banco {
    List<Cliente> cola = new ArrayList<>();

    //Entonces no ejecuta hasta que alguien llega
    //El primero que llega al no haber nadie lo despierta
    //Ejcuta atiende a todos while haya gente sigo atendiendo

    List<Cuenta> cuentas = new ArrayList<>();

    public synchronized Cliente siguienteCliente() {
        while (cola.isEmpty()){
            try {
                System.out.println("> La caja duerme");
                this.wait();
                System.out.println("> La caja se despierta");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return cola.remove(0);
    }

    public synchronized void avisoLlegada(Cliente cliente) {
        this.notify();
        cola.add(cliente);
        System.out.println("Se agrego un cliente");
    }

    public static void main(String[] args) {
        Banco galicia = new Banco();
        Caja caja = new Caja(galicia,1);
        Caja caja2 = new Caja(galicia,2);
        Thread thread = new Thread(caja);
        Thread thread2 = new Thread(caja2);
        thread.setDaemon(true);
        thread2.setDaemon(true);

        thread.start();
        thread2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 4; i++) {
            Thread cliente = new Thread(new Cliente(i,galicia,new));
            cliente.start();
        }

    }

    public Cuenta getCuenta(int cuenta) {
        if(cuentas.contains(new Cuenta(cuenta))){
            int n = cuentas.indexOf(new Cuenta((cuenta)));
            return cuentas.get(n);
        }

    }
}
