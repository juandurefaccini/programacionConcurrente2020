package tp4.ej7.prueba;

public class ProcesoEscritor extends Proceso {

    public ProcesoEscritor(ConjRegistros conjRegistros) {
        super(conjRegistros);
    }

    @Override
    public void run() {
        conjRegistros.intentarEscribir();
        System.out.println(id + "Esta escribiendo");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(id + " termino de escribir");
        conjRegistros.avisar();
    }
}
