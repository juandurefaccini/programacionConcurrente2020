package tp4.parcial2019.semaforos;

public class Cliente implements Runnable{
    int id;
    Banco banco;

    public Cliente(int id, Banco banco) {
        this.id = id;
        this.banco = banco;
    }

    @Override
    public void run() {
        System.out.println(">> Spawneo el cliente "+id);
        banco.avisoLlegada(this);
    }

    public void atender() {
        System.out.println("Se atiende el cliente "+id);
    }

    public int getID() {
        return id;
    }
}
