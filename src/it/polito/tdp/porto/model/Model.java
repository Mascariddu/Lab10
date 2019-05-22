package it.polito.tdp.porto.model;

import java.awt.geom.PathIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.event.TraversalListener;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import it.polito.tdp.porto.db.PortoDAO;

public class Model {

	PortoDAO dao = new PortoDAO();
	Map<Integer, Author> idMap = new HashMap<Integer, Author>();
	Map<Integer, Paper> paperMap = new HashMap<Integer, Paper>();
	List<Paper> papers = new ArrayList<Paper>();
	SimpleGraph<Author, Arco> graph;
	
	public List<Author> getAllAutori() {
		// TODO Auto-generated method stub
		return dao.getAllAutori(this.idMap);
	}

	public List<Author> getCoAutori(Author value) {
		// TODO Auto-generated method stub
		List<Author> result = new ArrayList<Author>();
		
		result = Graphs.neighborListOf(graph, value);
			
		return result;
	}

	public void creaGrafo() {
		// TODO Auto-generated method stub
		
		//graph = new SimpleGraph<Author, DefaultEdge>(DefaultEdge.class);
		graph = new SimpleGraph<Author, Arco>(Arco.class);
		papers = dao.getAllPapers(paperMap);
		
		Graphs.addAllVertices(graph, idMap.values());
		
		for(Adiacenza adiacenza : dao.getAdiacenza()) {
			Arco arco = graph.addEdge(idMap.get(adiacenza.getA1()), idMap.get(adiacenza.getA2()));
			
			if(arco != null)
				arco.setPaper(paperMap.get(adiacenza.getCodP()));
		}
		
	}

	public List<Paper> getPapers(Author source, Author target) {
		// TODO Auto-generated method stub
		//int i = 0;
		List<Paper> result = new ArrayList<Paper>();
		
		DijkstraShortestPath<Author, Arco> dijkstraShortestPath = new DijkstraShortestPath<Author, Arco>(graph);
		GraphPath<Author, Arco> path = dijkstraShortestPath.getPath(source, target);
		//List<Author> autori = path.getVertexList();
		
		/*
		for(i = 0; i< autori.size()-1; i++) {
			result.add(this.getPaperDaAutori(autori.get(i),autori.get(i+1)));
		}
		*/
		
		for(Arco arco : path.getEdgeList())
			result.add(arco.getPaper());
			
		return result;
		
		
	}

	/*private Paper getPaperDaAutori(Author author, Author author2) {
		// TODO Auto-generated method stub
		
		return dao.getPaperDatiAutori(author,author2,paperMap);
	}
	*/
}
