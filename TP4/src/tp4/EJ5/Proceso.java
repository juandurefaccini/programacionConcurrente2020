package tp4.EJ5;
//Implementar el semaforo
public class Proceso extends Thread{
    SemaforoContador semaforoContador;

    public Proceso(SemaforoContador semaforoContador){
        this.semaforoContador = semaforoContador;
    }

    @Override
    public void run() {
        semaforoContador.acquire();
        System.out.println(this.getId() + " Run init");
        System.out.println("");
        System.out.print(".");
        try {
            sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print(".");
        try {
            sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print(".");
        try {
            sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" ");
        System.out.println(this.getId() + " Run finish");
        semaforoContador.release();
    }
}