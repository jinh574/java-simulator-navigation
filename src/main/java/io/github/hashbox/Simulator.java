package io.github.hashbox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by js on 2017. 5. 19..
 */
public class Simulator extends Thread {
	private final int MAX_SIMULATTE_CAR;
	private static final int MAX_CLIENT_CAR = 10;
	private static final int UPDATE_TIME_MILLISECOND = 1000;
	private static final int CREATE_VIRTUAL_CAR_TIME_MILLISECOND = 100;
	private SimulatorGUI simulatorGUI;
	
	//private final float PROBABILITY_OF_ACCIDENT;
	private List<Car> virtualCars;
	private List<Car> clientCars;
	private VirtualMap virtualMap;
	private int count;

	public Simulator(int maxSimulateCar) {
		this.MAX_SIMULATTE_CAR = maxSimulateCar;
		simulatorGUI = SimulatorGUI.getInstance();
		simulatorGUI.setSimulator(this);

		virtualCars = new ArrayList<Car>(MAX_SIMULATTE_CAR);
		clientCars = new ArrayList<Car>(MAX_CLIENT_CAR);
		virtualMap = VirtualMap.getInstance();
		count = 0;
	}

	@Override
	public void run() {
		while (true) {
			while (virtualCars.size() < MAX_SIMULATTE_CAR) {
				try {
					synchronized (virtualCars){
						virtualMap.resetVertex();
						Car car = new ClientCar("V" + count++, virtualMap.getVertexes().get((int)(Math.random()*100)%15), false);
						car.setArrival(virtualMap.getVertexes().get((int)(Math.random()*100)%15));
						virtualCars.add(car);
					}
					Thread.sleep(CREATE_VIRTUAL_CAR_TIME_MILLISECOND);
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateLocate() {
		new Thread(()->{
			while(true) {
				synchronized (virtualCars) {
					List<Car> removeList = new ArrayList<>();
					for (Car car : virtualCars) {
						if(!car.nextLocated()) {
							removeList.add(car);
						}
					}
					virtualCars.removeAll(removeList);

					simulatorGUI.setConsoleText(virtualCars, clientCars);
				}
				try {
					Thread.sleep(UPDATE_TIME_MILLISECOND);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void addClient(String name, int sourceIndex, int destinationIndex) throws CloneNotSupportedException {
		synchronized (virtualCars){
			virtualMap.resetVertex();
			Car car = new ClientCar(name, virtualMap.getVertexes().get(sourceIndex), true);
			car.setArrival(virtualMap.getVertexes().get(destinationIndex));
			virtualCars.add(car);
			clientCars.add(car);
		}
	}

	public void addClient(Car car) {
		synchronized (virtualCars) {
			virtualCars.add(car);
		}
	}

	public List<Car> getClientCar() {
		return this.clientCars;
	}
	public List<Car> getAllCar() {
		return this.virtualCars;
	}
}
