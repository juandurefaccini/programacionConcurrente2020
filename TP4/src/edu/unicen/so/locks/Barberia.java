package edu.unicen.so.locks;

import edu.unicen.so.monitor.Cliente;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Barberia {
	private List<Cliente> sillas = new LinkedList<>();
	private int capacidadMaxima=5;
	private Lock mutex = new ReentrantLock();
	private Semaphore lleno = new Semaphore(5);
	private Semaphore vacio = new Semaphore(0);

	public boolean intentarSentarse(Cliente cliente) {
		boolean hayLugar =  lleno.tryAcquire();
		if (!hayLugar) {
			return false;
		}
		this.mutex.lock();
		System.err.println("El cliente "+cliente.getId()+" se sento");
		this.sillas.add(cliente);
		this.vacio.release();
		this.mutex.unlock();
		return true;
	}

	public Cliente obtenerSiguiente() {
		try {
			this.vacio.acquire();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		this.mutex.lock();
		Cliente sig = this.sillas.remove(0);
		System.err.println("El cliente "+sig.getId()+" se fue a cortar el pelo");
		lleno.release();
		this.mutex.unlock();
		return sig;
	}

}
