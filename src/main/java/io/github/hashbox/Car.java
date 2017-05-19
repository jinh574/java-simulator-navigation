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
	private String driver;
	private float speed;
	private int secedeCount;
	private int locate;
	private List<Edge> route;

	//경로
	private Vertax departure;
	private Vertax arrival;

	public Car(String driver, Vertax departure) {
		this.carState = CarState.IDLE;
		this.driver = driver;
		this.secedeCount = 0;
		this.speed = (float)(Math.random() * 100) % 20 + 40;
		this.locate = 0;

		this.departure = departure;
		this.arrival = null;
		this.route = null;

	}

	public void secede() {
		secedeCount++;
	}

	public void setArrival(Vertax arrival, ArrayList<Edge> route) {
		this.arrival = arrival;
		this.route = route;
		this.carState = CarState.DRIVING;
	}

	public void nextLocated() {
		// TODO 큐에서 경로를 빼서 위치 이동
	}
}
