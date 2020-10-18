package tp4.ej11;

public class Distribuidor implements Runnable{
    Almacen almacen;

    public Distribuidor(Almacen almacen) {
        this.almacen = almacen;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(10000);
                almacen.realizarEntrega();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("bla");
        }
    }

    public static void main(String[] args) {
        Almacen almacen = new Almacen();
        Distribuidor distribuidor = new Distribuidor(almacen);
        Distribuidor distribuidor2 = new Distribuidor(almacen);
        Distribuidor distribuidor3 = new Distribuidor(almacen);
        Thread thread = new Thread(distribuidor);
        Thread thread2 = new Thread(distribuidor2);
        Thread thread3 = new Thread(distribuidor3);
        thread.setDaemon(false);
        thread2.setDaemon(true);
        thread3.setDaemon(true);
        thread.start();
        thread2.start();
        thread3.start();
        System.out.println("El ultimo print");
    }
}
