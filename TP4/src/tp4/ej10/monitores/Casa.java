package tp4.ej10.monitores;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Casa {
    final int MAX_LUGAR = 4;
    List<Enanito> enanitoList = new ArrayList<>();
    int cantEnanos = 0;

    public synchronized boolean puedoSentarme(Enanito enanito) {
        if (hayLugar()){
            enanitoList.add(enanito);
            this.notify();
            cantEnanos++;
            return true;
        }
        else {
            return false;
        }
    }

    private boolean hayLugar() {
        return cantEnanos < MAX_LUGAR;
    }

    public synchronized Enanito intentarAtender() {
        if(enanitoList.isEmpty()){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return enanitoList.remove(0);
    }

    public void servirComida(Enanito enanito) {
        enanito.setComiendo();
    }

    public void irse(Enanito enanito) {
        enanitoList.remove(enanito);
    }

    public static void main(String[] args) {
        Casa casa = new Casa();
        Thread blancanieves = new Thread(new Blancanieves(casa));
        blancanieves.setDaemon(true);
        blancanieves.start();
        for (int i = 0; i < 6; i++) {
            Thread thread = new Thread(new Enanito(i,casa));
            thread.start();
        }
    }
}
