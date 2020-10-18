package tp4.ej10.monitores;

public class Blancanieves implements Runnable{
    Casa casa;
    boolean atender = false;

    public Blancanieves(Casa casa) {
        this.casa = casa;
    }

    @Override
    public void run() {
        while (true){
            Enanito siguiente = casa.intentarAtender();
            System.out.println(" >> Blancanieves empieza a cocinar al enano "+siguiente.getID());
            try {
                Thread.sleep(4500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" >> Blancanieves termino de cocinar para el enano "+siguiente.getID());
            casa.servirComida(siguiente);
        }
    }
}
