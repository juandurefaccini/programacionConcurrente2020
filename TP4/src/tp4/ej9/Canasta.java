package tp4.ej9;

import java.util.ArrayList;
import java.util.List;

public class Canasta {
    List<Producto> productoList = new ArrayList<>();

    public boolean contains(Producto producto) {
        if (productoList.contains(producto)){
            productoList.remove(producto);
            return true;
        }
        return false;
    }

    public void addProducto(Producto producto){
        productoList.add(producto);
    }
}
