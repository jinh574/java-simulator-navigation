package io.github.hashbox;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by js on 2017. 5. 21..
 */
public class SimulatorCanvas extends JPanel {
	private java.util.List<Car> allCars;
	private java.util.List<Car> clientCars;


	public SimulatorCanvas() {

		setOpaque(false);
		setBounds(15,15,950,620);
	}

	public void setCarList(List<Car> allCars, List<Car> clientCars) {
		this.allCars = allCars;
		this.clientCars = clientCars;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.BLACK);
		synchronized (allCars) {
			for (Car car : allCars) {
				if (car.getRoute().size() > 1) {
					int sx = car.getRoute().get(0).getXLocated();
					int sy = car.getRoute().get(0).getYLocated();
					int dx = car.getRoute().get(1).getXLocated();
					int dy = car.getRoute().get(1).getYLocated();
					int resultX = (int) ((int) (dx * car.getLocate() + sx * (car.getDistance() - car.getLocate())) / car.getDistance());
					int resultY = (int) ((int) (dy * car.getLocate() + sy * (car.getDistance() - car.getLocate())) / car.getDistance());
					if (car.isClient()) {
						g2d.setColor(Color.RED);
						g2d.drawString(car.getName(), resultX - 10, resultY + 30);
						g2d.drawOval(resultX, resultY, 10, 10);
					} else {
						g2d.setColor(Color.BLACK);
						g2d.drawOval(resultX, resultY, 4, 4);
					}

				}
			}
		}
	}
}
