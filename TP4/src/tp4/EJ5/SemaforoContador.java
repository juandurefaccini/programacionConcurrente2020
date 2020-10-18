package tp4.EJ5; /**
 * 5. Escriba un semáforo contador con una operación wait() que:
 * (a) Permita ejecución concurrente a N procesos, bloqueando temporalmente el resto.
 * (b) Bloquee hasta un máximo de M procesos, sin bloquear los M+1 siguientes. La operación debe
 * retornar un booleano asociado a si la espera pudo iniciarse o no en cada caso.
 */

public class SemaforoContador{
    private int value = 0;
    //EXECUTORS static method

    public SemaforoContador(int set) {
        this.value = set;
    }

    public synchronized void acquire(){
        while (value == 0 ){ //Esta lleno
            try {
                System.out.println(Thread.currentThread().getName() + " a mimir");
                this.wait();
                System.out.println(Thread.currentThread().getName() + " Se despertó");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        value-=1; //Queda uno menos
    }

    public synchronized void release(){
        value+=1; //Queda un lugar mas
        notify();
    }

    public static void main(String[] args) {
        SemaforoContador semaforoContador = new SemaforoContador(1);
        System.out.println(Thread.currentThread().getName());
        Proceso p1 = new Proceso(semaforoContador);
        Proceso p2 = new Proceso(semaforoContador);
        Proceso p3 = new Proceso(semaforoContador);
        Proceso p4 = new Proceso(semaforoContador);
        p1.start();
        p2.start();
        p3.start();
        p4.start();
    }
}


