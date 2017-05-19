package io.github.hashbox;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by js on 2017. 5. 19..
 */
@Data
public class VirtualMap {
	private static VirtualMap instance = new VirtualMap();
	private List<Vertax> vertaxes;
	private List<Edge> edges;
	private int vertaxCount;

	private VirtualMap() {
		System.out.println("Call VirtualMap Constructor.");
		this.edges = null;
		this.vertaxCount = 0;
	}

	public static VirtualMap getInstance() {
		return instance;
	}

	public void setInfo(List<Edge> edges, List<Vertax> vertaxes) {
		this.edges = edges;
		this.vertaxes = vertaxes;
		this.vertaxCount = vertaxes.size();
	}

	public List<Edge> getConnectedEdge(Vertax source) {
		List<Edge> result = new ArrayList<Edge>();
		for (Edge edge : edges) {
			if(edge.getSource() == source) {
				result.add(edge);
			}
		}

		return result;
	}

	public Vertax findVertax(int id) {
		return vertaxes.get(id);
	}

	public void printEdges() {
		for (Edge edge : edges) {
			System.out.println(edge);
		}
	}
}
