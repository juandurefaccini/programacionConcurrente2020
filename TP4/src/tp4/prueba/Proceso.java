package tp4.prueba;

public abstract class Proceso implements Runnable{
    ConjRegistros conjRegistros;
    static int id_inc = 0;
    int id;

    public Proceso(ConjRegistros conjRegistros) {
        this.id = ++id_inc;
        this.conjRegistros = conjRegistros;
    }

    @Override
    public void run() {

    }
}
