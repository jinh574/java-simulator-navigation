package io.github.hashbox;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by js on 2017. 5. 20..
 */
public class SimulatorGUI extends JFrame {
	private static SimulatorGUI instance;
	private JLabel nVirutalCarLabel;
	private JTable virtualList;
	private ImageIcon mapImage;
	private SimulatorConsoleModel simulatorConsoleModel;

	public static SimulatorGUI getInstance() {
		if(instance == null) {
			instance = new SimulatorGUI();
		}
		return instance;
	}

	private SimulatorGUI() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Simulator");

		setLayout(new BorderLayout(10, 10));

		JPanel panelLeft = new JPanel();
		JPanel panelRight = new JPanel();

		// Panel Conf
		panelRight.setLayout(new BorderLayout());

		// West
		mapImage = new ImageIcon("simulation-map.png");
		panelLeft.add(new JLabel(mapImage));

		// Right
		nVirutalCarLabel = new JLabel("시뮬레이션 차량 대수 : 0");
		simulatorConsoleModel = new SimulatorConsoleModel(null);
		virtualList = new JTable(simulatorConsoleModel);

		panelRight.add(nVirutalCarLabel, BorderLayout.NORTH);
		panelRight.add(new JScrollPane(virtualList), BorderLayout.CENTER);

		add(panelLeft, BorderLayout.CENTER);
		add(panelRight, BorderLayout.EAST);

		pack();
		setVisible(true);
	}

	public void setConsoleText(List<Car> cars) {
		nVirutalCarLabel.setText("시뮬레이션 차량 대수 : " + cars.size());
		simulatorConsoleModel.setConsoleList(cars);
		simulatorConsoleModel.fireTableDataChanged();
	}
}
