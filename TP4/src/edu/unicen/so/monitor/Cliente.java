package edu.unicen.so.monitor;

public class Cliente implements Runnable {
	private int id;
	private Barberia barberia;
	private boolean atendido = false;
	
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

	private synchronized void esperarCorte() {
		while(!this.atendido) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void setAtendido() {
		synchronized(this) {
			this.atendido = true;
			this.notify();
		}
	}

	public int getId() {
		return this.id;
	}
	
}
