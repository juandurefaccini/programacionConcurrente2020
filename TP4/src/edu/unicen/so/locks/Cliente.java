package edu.unicen.so.locks;

import edu.unicen.so.monitor.Barberia;

import java.util.concurrent.Semaphore;

public class Cliente implements Runnable {
	private int id;
	private Barberia barberia;
	private Semaphore atendido = new Semaphore(0);
	
	public Cliente(int id, Barberia barberia) {
		super();
		this.id = id;
		this.barberia = barberia;
	}

	@Override
	public void run() {
		System.out.println("El cliente "+id+" llega a la barberia");
		boolean pudoSentarse = this.barberia.intentarSentarse(this);
		if (!pudoSentarse) {
			System.out.println("El cliente "+id+" no pudo sentarse y se fue");
			return;
		}
		System.out.println("El cliente "+id+" espera ser atendido");
		this.esperarCorte();
		System.out.println("El cliente "+id+" se va con su corte");
	}

	private void esperarCorte() {
		try {
			this.atendido.acquire(); //wait down
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void setAtendido() {
		this.atendido.release();
	}

	public int getId() {
		return this.id;
	}
	
}
