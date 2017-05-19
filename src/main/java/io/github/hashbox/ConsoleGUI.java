package io.github.hashbox;

import javax.swing.*;
import java.util.List;

/**
 * Created by js on 2017. 5. 20..
 */
public class ConsoleGUI extends JFrame {
	private JLabel console;

	public ConsoleGUI() {
		setSize(1000, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Simulator Console");

		JPanel panel = new JPanel();
		console = new JLabel("None.");

		panel.add(console);

		add(panel);
		setVisible(true);
	}

	public void setConsoleText(List<Car> cars) {
		StringBuffer stringBuffer = new StringBuffer();
		if(cars != null) {
			stringBuffer.append("<html>");
			for (Car car : cars) {
				stringBuffer.append(car.toString());
			}
			stringBuffer.append("</html>");
			console.setText(stringBuffer.toString());
		}
	}
}
