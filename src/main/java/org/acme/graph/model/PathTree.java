package org.acme.graph.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.acme.graph.routing.DijkstraPathFinder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathTree {

	private static final Logger log = LogManager.getLogger(DijkstraPathFinder.class);
	private Map<Vertex, PathNode> nodes = new HashMap<Vertex, PathNode>();

	/**
	 * Prépare le graphe pour le calcul du plus court chemin
	 * 
	 * @param source
	 */
	public PathTree(Vertex origin) {
		log.trace("initGraph({})", origin);

		PathNode pathNode = new PathNode();
		pathNode.setCost(0.0);
		pathNode.setReachingEdge(null);
		pathNode.setVisited(false);

		nodes.put(origin, pathNode);
	}


	/**
	 * Construit le chemin en remontant les relations incoming edge
	 * 
	 * @param target
	 * @return
	 */
	public List<Edge> getPath(Vertex destination) {
		List<Edge> result = new ArrayList<>();
		if (this.isReached(destination)) {
			Edge current = this.getNode(destination).getReachingEdge();
			do {
				result.add(current);
				current = this.getNode(current.getSource()).getReachingEdge();
			} while (current != null);

			Collections.reverse(result);
		}
		return result;
	}

	public PathNode getNode(Vertex vertex) {
		return this.nodes.get(vertex);
	}

	public PathNode getOrCreateNode(Vertex vertex) {
		if (this.getNode(vertex) == null) {
			PathNode pathNode = new PathNode();
			pathNode.setCost(Double.POSITIVE_INFINITY);
			pathNode.setReachingEdge(null);
			pathNode.setVisited(false);
			nodes.put(vertex, pathNode);
		} 
		return this.getNode(vertex);
	}

	public boolean isReached(Vertex destination) {
		return this.getNode(destination).getReachingEdge() != null;
	}
	
	public Collection<Vertex> getReachedVertices(){
		Collection<Vertex> reachedVertices = new HashSet<>();
		for (Map.Entry<Vertex, PathNode> entry : nodes.entrySet()) {
			if(this.isReached(entry.getKey())) {
				reachedVertices.add(entry.getKey());
			}
		}
		return reachedVertices;
	}

}
