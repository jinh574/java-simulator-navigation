package io.github.hashbox;

/**
 * Created by js on 2017. 5. 19..
 */
public class ClientCar extends Car {
	private String driver;

	public ClientCar(String driver, Vertex departure, boolean isClient) {
		super(driver, departure, isClient);
		this.driver = driver;
	}

	public boolean nextLocated() {
		return super.nextLocated();
	}
}
