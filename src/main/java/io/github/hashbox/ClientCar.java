package io.github.hashbox;

/**
 * Created by js on 2017. 5. 19..
 */
public class ClientCar extends Car {
	private String driver;
	private int nTime;

	public ClientCar(String driver, Vertex departure, boolean isClient) {
		super(driver, departure, isClient);
		this.driver = driver;
		this.nTime = 0;
	}

	public boolean nextLocated() {
		nTime++;
		return super.nextLocated();
	}

	public int getnTime() {
		return nTime;
	}

	@Override
	public void setDeparture(Vertex source, boolean isClient) {
		this.nTime = 0;
		super.setDeparture(source, isClient);
	}
}
