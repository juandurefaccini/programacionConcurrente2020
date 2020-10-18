package edu.unicen.so.monitor;

import java.util.LinkedList;
import java.util.List;

public class Barberia {
	private List<Cliente> sillas = new LinkedList<>();
	private int capacidadMaxima=5;

	public boolean intentarSentarse(Cliente cliente) {
		synchronized(this.sillas) {
			if(sillas.size() == capacidadMaxima) {
				return false;
			}
			System.err.println("El cliente "+cliente.getId()+" se sento");
			this.sillas.add(cliente);
			this.sillas.notify();
			return true;
		}
	}

	public Cliente obtenerSiguiente() {
		synchronized (this.sillas) {
			while (this.sillas.isEmpty()) {
				try {
					this.sillas.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			System.err.println("El cliente "+this.sillas.get(0).getId()+" se fue a cortar el pelo");
			return this.sillas.remove(0);
		}
	}

}
