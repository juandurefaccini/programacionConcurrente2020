package tp4.parcial2019.monitores;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cuenta {
    int id;
    int saldo = 0;
    List<Integer> permitidos = new ArrayList<>();

    public Cuenta(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cuenta cuenta = (Cuenta) o;
        return id == cuenta.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
