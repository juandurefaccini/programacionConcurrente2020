package tp4.eJ6.monitores;

public class Cliente implements Runnable{
    Barberia barberia;
    static int id_inc = 0;
    int id;
    private boolean atendido = false;

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

    private synchronized void esperarCorte() {
        while (!atendido){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void setAtendido(){
        atendido = true;
        notify();
    }

    public int getID() {
        return id;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                id +
                '}';
    }
}
