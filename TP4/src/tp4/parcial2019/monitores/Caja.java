package tp4.parcial2019.monitores;

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
        while(true){
            System.out.println("||||| La caja "+id+" se fija si hay alguien");
            Cliente cliente = banco.siguienteCliente();
            System.out.println("Se atiende el cliente " + cliente.getID());
            try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}
            operar(cliente);
            cliente.setAtendido();
            System.out.println("En la caja "+id+ " |</>| "+ cliente.getID() + " se va");
        }
    }

    private void operar(Cliente cliente) {
        Operacion operacion = cliente.getOperacion();
        System.out.println("El cliente "+cliente.getID() +" op: ["+operacion.getTipo()+"]["+operacion.getMonto()+"]["+operacion.getCuenta()+"]" );
        Cuenta cuenta = banco.getCuenta(operacion.getCuenta());
        if(cuenta.permite(cliente.getID())){
            System.out.println("La operacion se permite");
        }
        else{
            System.out.println("Operacion invalida, el cliente no tiene permiso sobre la cuenta");
        }
    }
}
