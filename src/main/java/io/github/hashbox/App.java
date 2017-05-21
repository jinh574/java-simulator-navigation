package io.github.hashbox;

/**
 * Hello world!
 */
public class App {
	private static final int MAX_SIMULATTE_CAR = 500;


	public static void main(String[] args) {
		Simulator simulator = new Simulator(MAX_SIMULATTE_CAR);
		SimulatorGUI simulatorGUI;

		simulator.start();
		simulator.updateLocate();
	}
}
