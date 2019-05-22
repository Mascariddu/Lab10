package it.polito.tdp.porto.model;

public class Adiacenza {

	private int a1;
	private int a2;
	private int codP;
	
	public Adiacenza(int a1, int a2, int cod) {
		super();
		this.a1 = a1;
		this.a2 = a2;
		this.codP = cod;
	}

	public int getA1() {
		return a1;
	}

	public int getA2() {
		return a2;
	}
	
	public int getCodP() {
		return codP;
	}
	
}
