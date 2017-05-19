package io.github.hashbox;

import java.util.PriorityQueue;

/**
 * Created by js on 2017. 5. 19..
 */
public class Navigation {
	public static final int INF = Integer.MAX_VALUE;

	public static void findRoute(Car car) {
		boolean[] visited;
		int[] distance;

		VirtualMap virtualMap = VirtualMap.getInstance();
		int vertaxCount = virtualMap.getVertaxCount();


		distance = new int[vertaxCount];
		visited = new boolean[vertaxCount];
		for (int i = 0; i < vertaxCount; i++) {
			distance[i] = INF;
			visited[i] = false;
		}

		int startId = car.getSource().getId();
		PriorityQueue<Element> priorityQueue = new PriorityQueue<Element>();
		distance[startId] = 0;
		priorityQueue.offer(new Element(virtualMap.findVertax(startId), 0));

		while(!priorityQueue.isEmpty()) {
			int curVertax;
			do {
				curVertax = priorityQueue.peek().getIndex().getId();
				priorityQueue.poll();
			} while (!priorityQueue.isEmpty() && visited[curVertax]);

			if(visited[curVertax]) break;

			for (Edge edge : virtualMap.getConnectedEdge(car.getSource())) {
				 int next = edge.getDestination().getId();
				 int oldDistance = distance[next];
				 int newDistance = distance[curVertax] + edge.getWeight();

				 if (newDistance < oldDistance) {
				 	distance[next] = newDistance;
				 	priorityQueue.offer(new Element(virtualMap.findVertax(next), newDistance));
				 }
			}
		}
		for (int n : distance) {
			System.out.print(n + " ");
		}
	}
}

class Element implements Comparable<Element>{
	private Vertax index;
	private int distance;

	Element(Vertax index, int distance){
		this.index = index;
		this.distance = distance;
	}

	public Vertax getIndex(){
		return index;
	}

	public int getDistance(){
		return distance;
	}

	public int compareTo(Element o){
		return distance <= o.distance ? -1 : 1;
	}
}
