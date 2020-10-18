package tp4.parcial2019.monitores;

public class Operacion {
    int tipo;
    int monto;
    int cuenta;

    public Operacion(int tipo, int monto, int cuenta) {
        this.tipo = tipo;
        this.monto = monto;
        this.cuenta = cuenta;
    }

    public int getTipo() {
        return tipo;
    }

    public int getMonto() {
        return monto;
    }

    public int getCuenta() {
        return cuenta;
    }
}
