package org.acme.graph.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

import org.locationtech.jts.geom.Coordinate;

/**
 * 
 * Un sommet dans un graphe
 * 
 * @author MBorne
 *
 */
public class Vertex {

	/**
	 * Identifiant du sommet
	 */
	private String id;

	/**
	 * Position du sommet
	 */
	private Coordinate coordinate;

	
	@JsonIgnore
	private List<Edge> inEdges = new ArrayList<Edge>();
	
	@JsonIgnore
	private List<Edge> outEdges = new ArrayList<Edge>();


	Vertex() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
	

	public List<Edge> getInEdges() {
		return inEdges;
	}

	public List<Edge> getOutEdges() {
		return outEdges;
	}
	
	public void addInEdges(Edge edge) {
		inEdges.add(edge);
	}
	
	public void addOutEdges(Edge edge) {
		outEdges.add(edge);
	}

	@Override
	public String toString() {
		return id;
	}

}
