package io.github.hashbox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by js on 2017. 5. 19..
 */
public class Simulator extends Thread {
	private final int MAX_SIMULATTE_CAR;
//	private final float PROBABILITY_OF_ACCIDENT;
	private List<Car> virtualCars;
	private VirtualMap virtualMap;
	private int count;

	ConsoleGUI consoleGUI;
	public Simulator(int maxSimulateCar) {
		this.MAX_SIMULATTE_CAR = maxSimulateCar;

		virtualCars = new ArrayList<Car>(MAX_SIMULATTE_CAR);
		virtualMap = VirtualMap.getInstance();
		count = 0;
		consoleGUI = new ConsoleGUI();
	}

	@Override
	public void run() {
		while (true) {
			while (virtualCars.size() < MAX_SIMULATTE_CAR) {
				try {
					synchronized (virtualCars){
						virtualMap.resetVertex();
						Car car = new ClientCar("Virtual" + count++, virtualMap.getVertexes().get((int)(Math.random()*10)%8));
						car.setArrival(virtualMap.getVertexes().get((int)(Math.random()*10)%8));
						virtualCars.add(car);
					}
					Thread.sleep(100);
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
					consoleGUI.setConsoleText(virtualCars);
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
