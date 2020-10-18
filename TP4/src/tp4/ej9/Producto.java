package tp4.ej9;

import java.util.Objects;

public class Producto {
    int id;
    int precio;

    public Producto(int id, int precio) {
        this.id = id;
        this.precio = precio;
    }

    public int getID() {
        return id;
    }

    public int getPrecio() {
        return precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return id == producto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
