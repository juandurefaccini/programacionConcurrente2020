package edu.unicen.so.monitor;

public class BarberiaMain {

	public static void main(String[] args) {
		Barberia b = new Barberia();
		Thread barbero = new Thread(new Barbero(1, b));
		barbero.setDaemon(true); // IGNORAR!!
		barbero.start();
		barbero = new Thread(new Barbero(2, b));
		barbero.setDaemon(true); // IGNORAR!!
		barbero.start();
		
		for(int i=0; i<10; i++) {
			Thread cliente = new Thread(new Cliente(i, b));
			cliente.start();
		}
	}

}
