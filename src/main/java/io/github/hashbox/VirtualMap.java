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
	private List<Vertex> vertexes;
	private int nVertex;

	private VirtualMap() {
		System.out.println("Call VirtualMap Constructor.");
		makeMap();
	}

	public static VirtualMap getInstance() {
		return instance;
	}

	public void setVertexes(List<Vertex> vertaxes) {
		this.vertexes = vertaxes;
		this.nVertex = vertaxes.size();
	}

	private void makeMap() {
		List<Vertex> vertexes = new ArrayList<Vertex>();
		List<Edge> edges = new ArrayList<Edge>();

		// 맵만들기
		for(int i = 0; i < 9; i++) {
			vertexes.add(new Vertex(i + ""));
		}

		nVertex = vertexes.size();

		// 0기준
		vertexes.get(0).setAdjacencies(
				new Edge[]{
						new Edge(vertexes.get(1), (double)(Math.random()*1000) % 500 + 300),
						new Edge(vertexes.get(2), (double)(Math.random()*1000) % 500 + 300),
						new Edge(vertexes.get(4), (double)(Math.random()*1000) % 500 + 300)
				}
		);

		// 1기준
		vertexes.get(1).setAdjacencies(
				new Edge[]{
						new Edge(vertexes.get(0), (double)(Math.random()*1000) % 500 + 300),
						new Edge(vertexes.get(3), (double)(Math.random()*1000) % 500 + 300),
						new Edge(vertexes.get(4), (double)(Math.random()*1000) % 500 + 300),
						new Edge(vertexes.get(5), (double)(Math.random()*1000) % 500 + 300),
						new Edge(vertexes.get(6), (double)(Math.random()*1000) % 500 + 300)
				}
		);

		// 2기준
		vertexes.get(2).setAdjacencies(
				new Edge[]{
						new Edge(vertexes.get(0), (double)(Math.random()*1000) % 500 + 300),
						new Edge(vertexes.get(4), (double)(Math.random()*1000) % 500 + 300),
						new Edge(vertexes.get(7), (double)(Math.random()*1000) % 500 + 300)
				}
		);

		// 3기준
		vertexes.get(3).setAdjacencies(
				new Edge[]{
						new Edge(vertexes.get(1), (double)(Math.random()*1000) % 500 + 300),
						new Edge(vertexes.get(5), (double)(Math.random()*1000) % 500 + 300)
				}
		);

		// 4기준
		vertexes.get(4).setAdjacencies(
				new Edge[]{
						new Edge(vertexes.get(0), (double)(Math.random()*1000) % 500 + 300),
						new Edge(vertexes.get(1), (double)(Math.random()*1000) % 500 + 300),
						new Edge(vertexes.get(2), (double)(Math.random()*1000) % 500 + 300),
						new Edge(vertexes.get(5), (double)(Math.random()*1000) % 500 + 300),
						new Edge(vertexes.get(6), (double)(Math.random()*1000) % 500 + 300)
				}
		);

		// 5기준
		vertexes.get(5).setAdjacencies(
				new Edge[]{
						new Edge(vertexes.get(1), (double)(Math.random()*1000) % 500 + 300),
						new Edge(vertexes.get(3), (double)(Math.random()*1000) % 500 + 300),
						new Edge(vertexes.get(4), (double)(Math.random()*1000) % 500 + 300),
						new Edge(vertexes.get(8), (double)(Math.random()*1000) % 500 + 300)
				}
		);

		// 6기준
		vertexes.get(6).setAdjacencies(
				new Edge[]{
						new Edge(vertexes.get(1), (double)(Math.random()*1000) % 500 + 300),
						new Edge(vertexes.get(2), (double)(Math.random()*1000) % 500 + 300),
						new Edge(vertexes.get(7), (double)(Math.random()*1000) % 500 + 300),
						new Edge(vertexes.get(8), (double)(Math.random()*1000) % 500 + 300)
				}
		);

		// 7기준
		vertexes.get(7).setAdjacencies(
				new Edge[]{
						new Edge(vertexes.get(2), (double)(Math.random()*1000) % 500 + 300),
						new Edge(vertexes.get(6), (double)(Math.random()*1000) % 500 + 300)
				}
		);

		// 8기준
		vertexes.get(8).setAdjacencies(
				new Edge[]{
						new Edge(vertexes.get(5), (double)(Math.random()*1000) % 500 + 300),
						new Edge(vertexes.get(6), (double)(Math.random()*1000) % 500 + 300)
				}
		);

		setVertexes(vertexes);
	}

	public void printInfo() {
		for (Vertex v : vertexes) {
			System.out.println(v);
			for (Edge e : v.getAdjacencies()) {
				System.out.println("\t" + e.toString());
			}
		}
	}

	public void resetVertex() {
		for (Vertex v : vertexes) {
			v.setMinDistance(Double.POSITIVE_INFINITY);
			v.setPrevious(null);
		}
	}
}
