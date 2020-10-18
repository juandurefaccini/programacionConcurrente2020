package tp4.ej10.semaforo;

public class Enanito implements Runnable{
    int id;
    Casa casa;

    public Enanito(int id, Casa casa) {
        this.id = id;
        this.casa = casa;
    }

    @Override
    public void run() {
        System.out.println("Spawnea un enano [" + id + "]");
        casa.intentarSentarse();
        System.out.println("El enanito " + id + " se sento");
        casa.esperarComida(this);
        System.out.println("El enanito " + id + " esta comiendo");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("El enanito " + id + " ya comio");
        casa.irse();
        System.out.println("El enanito " + id + " se fue");
    }

    public int getID() {
        return id;
    }
}
