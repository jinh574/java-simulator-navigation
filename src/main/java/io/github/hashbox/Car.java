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
	private List<Edge> route;

	//경로
	private Vertax source;
	private Vertax destination;

	public Car(Vertax departure) {
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

	public void setArrival(Vertax source, ArrayList<Edge> route) {
		this.source = source;
		this.route = route;
		this.carState = CarState.DRIVING;
	}

	public void nextLocated() {
		// TODO 큐에서 경로를 빼서 위치 이동
	}
}
