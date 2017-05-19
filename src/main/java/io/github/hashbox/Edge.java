package io.github.hashbox;

import lombok.Data;

/**
 * Created by js on 2017. 5. 19..
 */
@Data
class Edge {
	private final Vertex target;
	private double weight;
	private int nCurrentCar;
	private int nPredictCar;

	public Edge(Vertex argTarget, double argWeight) {
		this.target = argTarget;
		this.weight = argWeight;
		this.nCurrentCar = 0;
		this.nPredictCar = 0;
	}

	public double getWeight() {
		return weight + nCurrentCar*20 + nPredictCar*10;
	}

	synchronized public void upCurrentCar() {
		nCurrentCar++;
	}

	synchronized public void downCurrentCar() {
		nCurrentCar--;
	}

	synchronized public void upPredictCar() {
		nPredictCar++;
	}

	synchronized public void downPredictCar() {
		nPredictCar--;
	}

}
