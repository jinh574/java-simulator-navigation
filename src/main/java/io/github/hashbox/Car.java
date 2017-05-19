package io.github.hashbox;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by js on 2017. 5. 19..
 */
@Data
public class Car {
	private CarState carState;
	private float speed;
	private int secedeCount;
	private int locate;
	private List<Vertex> route;

	//경로
	private Vertex source;
	private Vertex destination;

	public Car(Vertex departure) {
		this.carState = CarState.IDLE;
		this.secedeCount = 0;
		this.speed = (float)(Math.random() * 100) % 20 + 40;
		this.locate = 0;

		this.source = departure;
		this.destination = null;
		this.route = null;
	}

	public void secede() {
		secedeCount++;
	}

	public void setArrival(Vertex destination) {
		this.destination = destination;
		Navigation.computePaths(this.source);
		this.route = Navigation.getShortestPathTo(destination);
		this.carState = CarState.DRIVING;
	}

	public void nextLocated() {
		// TODO 큐에서 경로를 빼서 위치 이동

	}
}
