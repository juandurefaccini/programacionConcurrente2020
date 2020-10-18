package tp4.ej11;

public class Miembro implements Runnable{
    int id;
    Club club;

    public Miembro(int id, Club club) {
        this.id = id;
        this.club = club;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getId() + " >> Comienzo primera etapa");
            club.intentarPrimeraEtapa();
            System.out.println(Thread.currentThread().getId() + " >> Primera etapa realizada");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getId() + " >> Comienzo Segunda etapa");
            club.intentarSegundaEtapa();
            System.out.println(Thread.currentThread().getId() + " >> Segunda etapa terminada");
            club.verSiHayVino();
            club.servirVino();
            System.out.println(Thread.currentThread().getId() + " sirvio vino");
        }
    }
}
