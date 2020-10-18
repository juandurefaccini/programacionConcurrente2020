package tp4.eJ7.anda;

public class ProcesoEscritor extends Proceso{

    public ProcesoEscritor(ConjRegistros conjRegistros) {
        super(conjRegistros);
    }

    @Override
    public void run() {
        conjRegistros.intentarEscribir(); //Saca los lectores si hay y baja el semaforo
        System.out.println("Esta escribiendo");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(id + " termino de escribir");
        conjRegistros.avisar();
    }
}
