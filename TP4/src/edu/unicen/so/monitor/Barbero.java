package edu.unicen.so.monitor;

public class Barbero implements Runnable {
	private int id;
	private Barberia barberia;

	public Barbero(int id, Barberia barberia) {
		super();
		this.id = id;
		this.barberia = barberia;
	}

	@Override
	public void run() {
		while(true) {
			//Espera durmiendo hasta que haya clientes disponibles
			Cliente siguiente = this.barberia.obtenerSiguiente();
			System.out.println("El barbero "+this.id + " atiende a "+siguiente.getId());
			try {
				Thread.sleep((long) (2000 * Math.random() + 100));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("El barbero "+this.id + " le corto el pelo a "+siguiente.getId());
			siguiente.setAtendido();
		}
	}

}
