package org.acme.graph.model;

import java.util.List;

public class Path {
	private List<Edge> edges;
	
	public Path(List<Edge> edges) {
		this.edges = edges;
	}
	
	public int getLength() {
		int result = 0;
		for (Edge edge: edges) {
			result += edge.getGeometry().getLength();
		}
		
		return result;
	}
	
	public int size() {
		return edges.size();
	}
	
	public Edge get(int index) {
		return edges.get(index);
	}

	public List<Edge> getEdges() {
		return edges;
	}
}
