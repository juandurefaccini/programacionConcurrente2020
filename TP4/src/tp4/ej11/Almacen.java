package tp4.ej11;

import java.util.concurrent.Semaphore;

public class Almacen {
    Semaphore jarra = new Semaphore(6);
    Semaphore unidadFermentacion_descanso = new Semaphore(7);
    Semaphore estacionDeMezcla = new Semaphore(2);
    Semaphore envaseDeJugo = new Semaphore(5);
    Semaphore paqueteLevadura = new Semaphore(5);
    Semaphore cupo = new Semaphore(1);

    public void segundaEtapa() {
        try {
            cupo.acquire();
            jarra.acquire(); //Jarra para decantar
            jarra.release(); //Jarra de la etapa anterior
            unidadFermentacion_descanso.release();
            estacionDeMezcla.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        jarra.release();
        cupo.release();
    }

    public void primeraEtapa() {
        try {
            cupo.acquire();
            jarra.acquire(1);
            unidadFermentacion_descanso.acquire(1);
            estacionDeMezcla.acquire(1);
            envaseDeJugo.acquire(1);
            paqueteLevadura.acquire(1);
            cupo.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
