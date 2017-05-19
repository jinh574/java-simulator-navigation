package io.github.hashbox;

/**
 * Created by js on 2017. 5. 19..
 */
public class ClientCar extends Car {
	private String driver;

	public ClientCar(String driver, Vertex departure) {
		super(departure);
		this.driver = driver;
	}
}
