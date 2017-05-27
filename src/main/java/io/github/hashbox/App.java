package io.github.hashbox;

/**
 * Hello world!
 */
public class App {
	//TODO 리팱토링이 필요
	private static final int MAX_SIMULATTE_CAR = 300;


	public static void main(String[] args) {
		Simulator simulator = new Simulator(MAX_SIMULATTE_CAR);

		simulator.start();
		simulator.updateLocate();
	}
}
