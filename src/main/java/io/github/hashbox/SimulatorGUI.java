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
	private JTable edgeInfo;
	private ImageIcon mapImage;
	private SimulatorConsoleModel simulatorConsoleModel;
	private SimulatorEdgeMoel simulatorEdgeMoel;
	private VirtualMap virtualMap;

	public static SimulatorGUI getInstance() {
		if(instance == null) {
			instance = new SimulatorGUI();
		}
		return instance;
	}

	private SimulatorGUI() {
		virtualMap = VirtualMap.getInstance();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Simulator");

		setLayout(new BorderLayout(10, 10));

		JPanel mapPanel = new JPanel();
		JPanel consolePanel = new JPanel();
		JPanel infoPanel = new JPanel();

		// Panel Conf
		consolePanel.setLayout(new BorderLayout());

		// Center
		mapImage = new ImageIcon("simulation-map.png");
		mapPanel.add(new JLabel(mapImage));

		// East
		simulatorEdgeMoel = new SimulatorEdgeMoel(virtualMap.getEdges());
		edgeInfo = new JTable(simulatorEdgeMoel);
		infoPanel.add(new JScrollPane(edgeInfo));

		// South
		nVirutalCarLabel = new JLabel("시뮬레이션 차량 대수 : 0");
		simulatorConsoleModel = new SimulatorConsoleModel(null);
		virtualList = new JTable(simulatorConsoleModel);

		//Table Conf
		virtualList.getColumnModel().getColumn(0).setPreferredWidth(30);
		virtualList.getColumnModel().getColumn(1).setPreferredWidth(200);
		virtualList.getColumnModel().getColumn(2).setPreferredWidth(80);
		virtualList.getColumnModel().getColumn(3).setPreferredWidth(300);

		edgeInfo.getColumnModel().getColumn(0).setPreferredWidth(180);
		edgeInfo.getColumnModel().getColumn(1).setPreferredWidth(80);
		edgeInfo.getColumnModel().getColumn(2).setPreferredWidth(80);
		edgeInfo.getColumnModel().getColumn(3).setPreferredWidth(150);


		consolePanel.add(nVirutalCarLabel, BorderLayout.NORTH);
		consolePanel.add(new JScrollPane(virtualList), BorderLayout.CENTER);

		add(mapPanel, BorderLayout.CENTER);
		add(consolePanel, BorderLayout.SOUTH);
		add(infoPanel, BorderLayout.EAST);

		pack();
		setVisible(true);
	}

	public void setConsoleText(List<Car> cars) {
		//Table Conf
		nVirutalCarLabel.setText("시뮬레이션 차량 대수 : " + cars.size());
		simulatorConsoleModel.setConsoleList(cars);
		simulatorConsoleModel.fireTableDataChanged();
		simulatorEdgeMoel.fireTableDataChanged();
	}
}
