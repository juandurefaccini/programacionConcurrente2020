package tp4.eJ6.locks;

import java.util.concurrent.Semaphore;

public class Cliente implements Runnable{
    Barberia barberia;
    static int id_inc = 0;
    int id;
    Semaphore atendido = new Semaphore(0);

    public Cliente(Barberia barberia) {
        this.barberia = barberia;
        id = id_inc;
        id_inc++;
    }

    @Override
    public void run() {
        System.err.println("EL cliente "+id+" llega a la barberia");
        boolean pudoSentarse = this.barberia.intentarSentarse(this);
        if (!pudoSentarse) {
            System.out.println("El cliente " + id + " se fue");
            return;
        }
        System.out.println("El cliente " + id +" espera ser atendido");
        this.esperarCorte();
        System.err.println("El cliente " + id +" se va con su corte");
    }

    private void esperarCorte() {
        try {
            this.atendido.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setAtendido(){
        atendido.release();
    }

    @Override
    public String toString() {
        return "Cliente{" +
                id +
                '}';
    }

    public int getID() {
        return id;
    }
}
