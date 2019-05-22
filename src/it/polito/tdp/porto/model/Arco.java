package it.polito.tdp.porto.model;

import org.jgrapht.graph.DefaultEdge;

public class Arco extends DefaultEdge{

	Paper paper;

	public Arco() {
		
	}

	public Paper getPaper() {
		// TODO Auto-generated method stub
		return paper;
	}

	public void setPaper(Paper paper2) {
		// TODO Auto-generated method stub
		this.paper = paper2;
	}
}
