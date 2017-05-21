package io.github.hashbox;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by js on 2017. 5. 19..
 */
@Data
public class VirtualMap {
	private static VirtualMap instance = new VirtualMap();
	private List<Vertex> vertexes;
	private List<Edge> edges;
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
		vertexes.add(new Vertex("성동교사거리"));
		vertexes.add(new Vertex("뚝섬역사거리"));
		vertexes.add(new Vertex("서울숲입구"));
		vertexes.add(new Vertex("상원삼거리"));
		vertexes.add(new Vertex("경동초입구"));
		vertexes.add(new Vertex("경일초입구"));
		vertexes.add(new Vertex("치과의사협회앞"));
		vertexes.add(new Vertex("성수역사거리"));
		vertexes.add(new Vertex("뚝도시장입구"));
		vertexes.add(new Vertex("화양사거리"));
		vertexes.add(new Vertex("성수사거리"));
		vertexes.add(new Vertex("영동대교고가밑"));
		vertexes.add(new Vertex("어린이대공원앞"));
		vertexes.add(new Vertex("건대입구"));
		vertexes.add(new Vertex("신양초교앞"));

		nVertex = vertexes.size();

		// 0기준
		vertexes.get(0).setAdjacencies(
				new Edge[]{
						new Edge(vertexes.get(1), 500),
						new Edge(vertexes.get(3), 1000)
				}
		);

		// 1기준
		vertexes.get(1).setAdjacencies(
				new Edge[]{
						new Edge(vertexes.get(0), 500),
						new Edge(vertexes.get(2), 800),
						new Edge(vertexes.get(4), 600)
				}
		);

		// 2기준
		vertexes.get(2).setAdjacencies(
				new Edge[]{
						new Edge(vertexes.get(1), 800),
						new Edge(vertexes.get(5), 500)
				}
		);

		// 3기준
		vertexes.get(3).setAdjacencies(
				new Edge[]{
						new Edge(vertexes.get(0), 1000),
						new Edge(vertexes.get(4), 600),
						new Edge(vertexes.get(6), 900)
				}
		);

		// 4기준
		vertexes.get(4).setAdjacencies(
				new Edge[]{
						new Edge(vertexes.get(1), 600),
						new Edge(vertexes.get(3), 600),
						new Edge(vertexes.get(5), 700),
						new Edge(vertexes.get(7), 900)
				}
		);

		// 5기준
		vertexes.get(5).setAdjacencies(
				new Edge[]{
						new Edge(vertexes.get(2), 500),
						new Edge(vertexes.get(4), 700),
						new Edge(vertexes.get(8), 1100)
				}
		);

		// 6기준
		vertexes.get(6).setAdjacencies(
				new Edge[]{
						new Edge(vertexes.get(3), 900),
						new Edge(vertexes.get(7), 600),
						new Edge(vertexes.get(9), 1400)
				}
		);

		// 7기준
		vertexes.get(7).setAdjacencies(
				new Edge[]{
						new Edge(vertexes.get(4), 900),
						new Edge(vertexes.get(6), 600),
						new Edge(vertexes.get(8), 800),
						new Edge(vertexes.get(10), 1000)
				}
		);

		// 8기준
		vertexes.get(8).setAdjacencies(
				new Edge[]{
						new Edge(vertexes.get(5), 1100),
						new Edge(vertexes.get(7), 800),
						new Edge(vertexes.get(11), 700)
				}
		);

		// 9기준
		vertexes.get(9).setAdjacencies(
				new Edge[]{
						new Edge(vertexes.get(6), 1400),
						new Edge(vertexes.get(10), 1200),
						new Edge(vertexes.get(12), 800)
				}
		);

		// 10기준
		vertexes.get(10).setAdjacencies(
				new Edge[]{
						new Edge(vertexes.get(7), 1000),
						new Edge(vertexes.get(9), 1200),
						new Edge(vertexes.get(11), 800),
						new Edge(vertexes.get(13), 800)
				}
		);

		// 11기준
		vertexes.get(11).setAdjacencies(
				new Edge[]{
						new Edge(vertexes.get(8), 700),
						new Edge(vertexes.get(10), 800),
						new Edge(vertexes.get(14), 800)
				}
		);

		// 12기준
		vertexes.get(12).setAdjacencies(
				new Edge[]{
						new Edge(vertexes.get(9), 800),
						new Edge(vertexes.get(13), 1500)
				}
		);

		// 13기준
		vertexes.get(13).setAdjacencies(
				new Edge[]{
						new Edge(vertexes.get(10), 800),
						new Edge(vertexes.get(12), 1500),
						new Edge(vertexes.get(14), 700)
				}
		);

		// 14기준
		vertexes.get(14).setAdjacencies(
				new Edge[]{
						new Edge(vertexes.get(11), 800),
						new Edge(vertexes.get(13), 700)
				}
		);


		setVertexes(vertexes);
		for (Vertex vertex : vertexes) {
			for (Edge edge : vertex.getAdjacencies()) {
				edge.setSource(vertex);
				edges.add(edge);
			}
		}
		setEdges(edges);
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
