package tp4.ej11;

public class Club {
    Almacen almacen;

    public Club(Almacen almacen) {
        this.almacen = almacen;
    }

    public void intentarPrimeraEtapa() {
        almacen.primeraEtapa();
    }

    public void intentarSegundaEtapa() {
        almacen.segundaEtapa();
    }

    public void servirVino() {
        System.out.println(Thread.currentThread().getId() + " Sirviendo vino"); //Los demas miembros se duerman
    }

    public static void main(String[] args) {
        Almacen almacen = new Almacen();
        Club club = new Club(almacen);
        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread(new Miembro(i,club));
            thread.setDaemon(true);
            thread.start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
