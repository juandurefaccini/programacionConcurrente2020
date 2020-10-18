package tp4.parcial2019.monitores;

import java.util.ArrayList;
import java.util.List;

public class Cliente implements Runnable{
    int id;
    Banco banco;
    boolean atendido = false;
    Operacion operacion;

    List<Integer> cuentas = new ArrayList<>();

    public Cliente(int id, Banco banco,Operacion operacion) {
        this.id = id;
        this.banco = banco;
        this.operacion = operacion;
    }

    @Override
    public void run() {
        System.out.println(">> Spawneo el cliente "+id);
        banco.avisoLlegada(this);
        esperarTurno();
        System.out.println(">> se va el cliente "+id);
    }

    private synchronized void esperarTurno() {
        while ( !atendido){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void setAtendido(){
        this.notify();
        atendido=true;
    }

    public void atender() {
        System.out.println("Se atiende el cliente "+id);
    }

    public Operacion getOperacion() {
        return operacion;
    }

    public int getID() {
        return id;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                '}';
    }
}
