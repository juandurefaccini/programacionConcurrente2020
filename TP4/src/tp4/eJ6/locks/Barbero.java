package tp4.eJ6.locks;

public class Barbero implements Runnable{
    Barberia barberia;
    String nombre;

    public Barbero(Barberia barberia, String nombre) {
        this.barberia = barberia;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        while (true){
            Cliente siguiente = barberia.obtenerSiguiente();
            try {
                Thread.sleep((long) (2000 * Math.random() + 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("El barbero " + nombre +" le corto el pelo al cliente "+siguiente.getID());
            siguiente.setAtendido();
        }
    }

}
