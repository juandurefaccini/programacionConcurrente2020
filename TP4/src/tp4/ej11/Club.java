package tp4.ej11;

import java.util.concurrent.Semaphore;

public class Club {
    Almacen almacen;
    Semaphore vinoTomado = new Semaphore(0);
    int cantDegustadores = 8;

    public Club(Almacen almacen) {
        this.almacen = almacen;
    }

    public void intentarPrimeraEtapa() {
        almacen.primeraEtapa();
    }

    public void intentarSegundaEtapa() {
        almacen.segundaEtapa();
    }

    public synchronized void servirVino() {
        System.out.println(Thread.currentThread().getId() + " Sirviendo vino"); //Los demas miembros se duerman
        try {
            vinoTomado.acquire();
            System.out.println("Se tomo el vino");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Almacen almacen = new Almacen();
        Club club = new Club(almacen);
        Distribuidor distribuidor = new Distribuidor(almacen);
        Thread threadDist = new Thread(distribuidor);
        threadDist.start();

        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread(new Miembro(i,club));
//            thread.setDaemon(true);
            thread.start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void verSiHayVino() {
        if ()
    }
}
