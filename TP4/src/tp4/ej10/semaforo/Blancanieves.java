package tp4.ej10.semaforo;

public class Blancanieves implements Runnable{
    Casa casa;

    public Blancanieves(Casa casa) {
        this.casa = casa;
    }

    @Override
    public void run() {
        while (true){
            casa.atender();
            System.out.println("Blancanieves esta atendiendo");
            try {
                Thread.sleep(4500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Blancanieves termino de cocinar");
            casa.servirComida();
        }
    }
}
