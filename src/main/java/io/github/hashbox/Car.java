package io.github.hashbox;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by js on 2017. 5. 19..
 */
@Data
public class Car {
	private String name;
	private CarState carState;
	private float speed;
	private int secedeCount;
	private int locate;
	private List<Vertex> route;
	private double distance;
	private boolean isClient;

	//경로
	private Vertex source;
	private Vertex destination;

	public Car(String name, Vertex departure, boolean isClient) {
		this.name = name;
		this.secedeCount = 0;
		this.speed = (float)(Math.random() * 100) % 20 + 40;

		setDeparture(departure, isClient);
	}

	public void secede() {
		secedeCount++;
	}

	public void setDeparture(Vertex source, boolean isClient) {
		this.carState = CarState.IDLE;
		this.locate = 0;
		this.isClient = isClient;

		this.source = source;
		this.destination = null;
		this.route = null;
		Navigation.computePaths(source);
	}

	public void setArrival(Vertex destination) throws CloneNotSupportedException {
		this.destination = destination;
		this.route = Navigation.getShortestPathTo(destination);
		this.source = route.get(0);
		this.destination = route.get(route.size()-1);
		this.carState = CarState.DRIVING;

		synchronized (route) {
			if (route.size() > 1) {
				for (int i = 1; i < route.size(); i++) {
					if (i == 1) {
						route.get(i).getCurrentEdge().upCurrentCar();
					} else {
						route.get(i).getCurrentEdge().upPredictCar();
					}
				}
			}
		}
	}

	public boolean nextLocated() {
		// TODO 큐에서 경로를 빼서 위치 이동
		if(route != null && !route.isEmpty()) {
			reCalcRoute();
			if(route.size() == 1) {
				setDeparture(route.get(0), isClient);
				return false;
			}
			else {
				Vertex target = route.get(1);
				distance = target.getCurrentDistance();
				locate += speed;
				if (distance < locate && carState == CarState.DRIVING) {
					source = target;
					locate = locate - (int) distance;

					for (int i = 1; i < route.size(); i++) {
						if (i == 1) {
							route.get(i).getCurrentEdge().downCurrentCar();
						}
						if (i == 2) {
							route.get(i).getCurrentEdge().upCurrentCar();
							route.get(i).getCurrentEdge().downPredictCar();
						}
					}
					synchronized (route) {
						route.remove(0);
					}
				} else {
					distance = route.get(1).getCurrentDistance();
				}
				return true;
			}
		}
		return false;
	}

	public void reCalcRoute() {
		if (carState == CarState.DRIVING && route.size() > 1) {
			for (int i = 1; i < route.size(); i++) {
				route.get(i).setCurrentDistance(route.get(i).getCurrentEdge().getWeight());
			}
		}
	}

	public String getLocatedToString() {
		String result = "??";
		if(route != null) {
			synchronized (route) {
				if (route.size() == 1) {
					result = route.get(0).getName() + "을(를) 도착";
				} else {
					result = route.get(0).getName() + "->" + route.get(1);
				}
			}
		}
		else {
			result = source.getName();
		}
		return result;
	}
}
