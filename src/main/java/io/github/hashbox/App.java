package io.github.hashbox;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
	private static final int MAX_SIMULATTE_CAR = 500;

	public static void main(String[] args) {
		VirtualMap virtualMap = VirtualMap.getInstance();

		ClientCar car = new ClientCar("hi", virtualMap.getVertexes().get(0));
	}
}
