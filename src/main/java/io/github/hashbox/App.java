package io.github.hashbox;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
	private static final int MAX_SIMULATTE_CAR = 500;

	public static void main(String[] args) {
		VirtualMap virtualMap = VirtualMap.getInstance();

		makeMap();
		virtualMap.printEdges();
		ClientCar clientCar = new ClientCar("한지승", virtualMap.getVertaxes().get(0));
		clientCar.setDestination(virtualMap.getVertaxes().get(5));
		Navigation.findRoute(clientCar);
	}

	public static void makeMap() {
		List<Vertax> vertaxes = new ArrayList<Vertax>();
		List<Edge> edges = new ArrayList<Edge>();
		VirtualMap virtualMap = VirtualMap.getInstance();

		// 맵만들기
		for(int i = 1; i <= 10; i++) {
			vertaxes.add(new Vertax(i+"", i-1));
		}

		// 1기준
		edges.add(new Edge(vertaxes.get(1), vertaxes.get(0), (int)(Math.random()*1000) % 500 + 300));
		edges.add(new Edge(vertaxes.get(0), vertaxes.get(1), (int)(Math.random()*1000) % 500 + 300));
		edges.add(new Edge(vertaxes.get(0), vertaxes.get(2), (int)(Math.random()*1000) % 500 + 300));
		edges.add(new Edge(vertaxes.get(2), vertaxes.get(0), (int)(Math.random()*1000) % 500 + 300));
		edges.add(new Edge(vertaxes.get(0), vertaxes.get(4), (int)(Math.random()*1000) % 500 + 300));
		edges.add(new Edge(vertaxes.get(4), vertaxes.get(0), (int)(Math.random()*1000) % 500 + 300));

		// 2기준
		edges.add(new Edge(vertaxes.get(1), vertaxes.get(3), (int)(Math.random()*1000) % 500 + 300));
		edges.add(new Edge(vertaxes.get(3), vertaxes.get(1), (int)(Math.random()*1000) % 500 + 300));
		edges.add(new Edge(vertaxes.get(1), vertaxes.get(4), (int)(Math.random()*1000) % 500 + 300));
		edges.add(new Edge(vertaxes.get(4), vertaxes.get(1), (int)(Math.random()*1000) % 500 + 300));
		edges.add(new Edge(vertaxes.get(1), vertaxes.get(5), (int)(Math.random()*1000) % 500 + 300));
		edges.add(new Edge(vertaxes.get(5), vertaxes.get(1), (int)(Math.random()*1000) % 500 + 300));
		edges.add(new Edge(vertaxes.get(1), vertaxes.get(6), (int)(Math.random()*1000) % 500 + 300));
		edges.add(new Edge(vertaxes.get(6), vertaxes.get(1), (int)(Math.random()*1000) % 500 + 300));

		// 3기준
		edges.add(new Edge(vertaxes.get(2), vertaxes.get(4), (int)(Math.random()*1000) % 500 + 300));
		edges.add(new Edge(vertaxes.get(4), vertaxes.get(2), (int)(Math.random()*1000) % 500 + 300));
		edges.add(new Edge(vertaxes.get(2), vertaxes.get(7), (int)(Math.random()*1000) % 500 + 300));
		edges.add(new Edge(vertaxes.get(7), vertaxes.get(4), (int)(Math.random()*1000) % 500 + 300));

		// 4기준
		edges.add(new Edge(vertaxes.get(3), vertaxes.get(5), (int)(Math.random()*1000) % 500 + 300));
		edges.add(new Edge(vertaxes.get(5), vertaxes.get(3), (int)(Math.random()*1000) % 500 + 300));

		// 5기준
		edges.add(new Edge(vertaxes.get(4), vertaxes.get(5), (int)(Math.random()*1000) % 500 + 300));
		edges.add(new Edge(vertaxes.get(5), vertaxes.get(4), (int)(Math.random()*1000) % 500 + 300));
		edges.add(new Edge(vertaxes.get(4), vertaxes.get(6), (int)(Math.random()*1000) % 500 + 300));
		edges.add(new Edge(vertaxes.get(6), vertaxes.get(4), (int)(Math.random()*1000) % 500 + 300));

		// 6기준
		edges.add(new Edge(vertaxes.get(5), vertaxes.get(8), (int)(Math.random()*1000) % 500 + 300));
		edges.add(new Edge(vertaxes.get(8), vertaxes.get(5), (int)(Math.random()*1000) % 500 + 300));

		// 7기준
		edges.add(new Edge(vertaxes.get(6), vertaxes.get(7), (int)(Math.random()*1000) % 500 + 300));
		edges.add(new Edge(vertaxes.get(7), vertaxes.get(6), (int)(Math.random()*1000) % 500 + 300));
		edges.add(new Edge(vertaxes.get(6), vertaxes.get(8), (int)(Math.random()*1000) % 500 + 300));
		edges.add(new Edge(vertaxes.get(8), vertaxes.get(6), (int)(Math.random()*1000) % 500 + 300));

		// 8기준

		// 9기준

		virtualMap.setInfo(edges, vertaxes);
	}
}
