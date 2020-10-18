package tp4.ej10.monitores;

public class Enanito implements Runnable{
    int id;
    Casa casa;
    boolean sentado = false;
    boolean comiendo = false;
    boolean lugarDisponible = false;

    public Enanito(int id, Casa casa) {
        this.id = id;
        this.casa = casa;
    }

    @Override
    public void run() {
        System.out.println("Spawnea un enano [" + id + "]");
        if(casa.puedoSentarme(this)){
            System.out.println("El enanito " + id + " se sento");
            esperarParaComer();
            System.out.println("El enanito " + id + " esta comiendo");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("El enanito " + id + " ya comio");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            casa.irse(this);
            System.out.println("El enanito " + id + " se fue");
            //casa para 2
            //1 terminadno de comer -->
            //1 esperando a que lo atiendan <-- ZzZzZzZZ
            //1 enano esperando <--- ZZZzzzZz
        }
    }

    private synchronized void esperarParaComer() {
        while (!comiendo){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void setSentado(){
        sentado=true;
        notify();
    }

    public synchronized void setComiendo(){
        comiendo=true;
        notify();
    }

    public int getID() {
        return id;
    }
}
