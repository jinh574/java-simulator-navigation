package io.github.hashbox;

import lombok.Data;

/**
 * Created by js on 2017. 5. 19..
 */
@Data
public class Edge {
	private Vertax source;
	private Vertax destination;
	private int weight;
	private int currentCarCount;
	private int predictCarCount;

	public Edge(Vertax source, Vertax destination, int weight) {
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}

	public int getPredictWeight() {
		// TODO 차량 대수에 따른 가중치 계산 후 리턴하도록 구현
		return weight;
	}
}
