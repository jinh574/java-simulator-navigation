package io.github.hashbox;

import lombok.Data;

/**
 * Created by js on 2017. 5. 19..
 */
@Data
class Edge {
	private static final int CURRENT_CAR_WEIGHT = 20;
	private static final int PREDICT_CAR_WEIGHT = 10;

	private final Vertex target;
	private double weight;
	private int nCurrentCar;
	private int nPredictCar;
	private int accident;
	private int accidentCount;
	private Vertex source;

	public Edge(Vertex argTarget, double argWeight) {
		this.target = argTarget;
		this.weight = argWeight;
		this.nCurrentCar = 0;
		this.nPredictCar = 0;
		this.accident = 0;
	}

	public double getWeight() {
		return weight + nCurrentCar*CURRENT_CAR_WEIGHT + nPredictCar*PREDICT_CAR_WEIGHT + accident;
	}

	public double getWeightOriginal() {
		return weight;
	}

	@Override
	public String toString() {
		return source.getName() + "->" + target.getName();
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
