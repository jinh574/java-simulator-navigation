package io.github.hashbox;

import lombok.Data;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Created by js on 2017. 5. 20..
 */
public class SimulatorGUI extends JFrame {
	private static SimulatorGUI instance;
	private Simulator simulator;
	private JLabel nVirutalCarLabel;
	private JTable virtualList;
	private JTable edgeInfo;
	private JTable clientInfo;

	private ImageIcon mapImage;
	private SimulatorCanvas simulatorCanvas;
	private SimulatorConsoleModel simulatorConsoleModel;
	private SimulatorConsoleModel simulatorClientModel;
	private SimulatorEdgeMoel simulatorEdgeMoel;
	private VirtualMap virtualMap;
	private int nClient;

	private JButton createClientButton;

	public static SimulatorGUI getInstance() {
		if(instance == null) {
			instance = new SimulatorGUI();
		}
		return instance;
	}

	public void setSimulator(Simulator simulator) {
		this.simulator = simulator;
	}

	private SimulatorGUI() {
		virtualMap = VirtualMap.getInstance();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Simulator");
		setLayout(new BorderLayout());

		nClient = 0;

		JLayeredPane mapPanel = new JLayeredPane();
//		JPanel mapPanel = new JPanel();
		JPanel consolePanel = new JPanel();
		JPanel infoPanel = new JPanel();
		JPanel clientPanel = new JPanel();
		simulatorCanvas = new SimulatorCanvas();

		// Panel Conf
		consolePanel.setLayout(new BorderLayout());
		infoPanel.setLayout(new BorderLayout());
		clientPanel.setLayout(new BorderLayout());

		mapPanel.setBorder(new TitledBorder(null, "지도", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		consolePanel.setBorder(new TitledBorder(null, "전체 시뮬레이션 차량 정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		clientPanel.setBorder(new TitledBorder(null, "클라이언트 컨트롤", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		mapPanel.setPreferredSize(new Dimension(980, 660));

		// Center
		mapImage = new ImageIcon(getClass().getClassLoader().getResource("simulation-map.png"));
		JLabel mapLabel = new JLabel(mapImage);
		mapLabel.setBounds(15, 15, 950, 620);
		mapPanel.add(mapLabel, 1);
		mapPanel.add(simulatorCanvas, 0);

		// East
		createClientButton = new JButton("사용자 생성");
		simulatorClientModel = new SimulatorConsoleModel(null);
		clientInfo = new JTable(simulatorClientModel);

		simulatorEdgeMoel = new SimulatorEdgeMoel(virtualMap.getEdges());
		edgeInfo = new JTable(simulatorEdgeMoel);

		clientPanel.add(createClientButton, BorderLayout.SOUTH);
		clientPanel.add(new JScrollPane(clientInfo), BorderLayout.CENTER);
		infoPanel.add(new JScrollPane(edgeInfo), BorderLayout.NORTH);
		infoPanel.add(clientPanel, BorderLayout.CENTER);

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

		// Action Listener
		createClientButton.addActionListener((ActionEvent e)->{
			ClientDialog clientDialog = new ClientDialog(this, "클라리언트 생성");
			if(!clientDialog.isCancel) {
				try {
					simulator.addClient(clientDialog.getName(), clientDialog.getSource(), clientDialog.getDestination());
				} catch (CloneNotSupportedException e1) {
					e1.printStackTrace();
				}
			}
			clientDialog.dispose();
		});
		clientInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = clientInfo.rowAtPoint(e.getPoint());
				if(row >= 0 && simulator.getClientCar().get(row) != null) {
					Car car = simulator.getClientCar().get(row);
					ClientEditDialog clientEditDialog = new ClientEditDialog(SimulatorGUI.this, "클라이언트 수정", car);
					clientEditDialog.dispose();
				}
			}
		});

		pack();
		setVisible(true);
	}

	public void setConsoleText(List<Car> allCars, List<Car> clientCars) {
		//Table Conf
		nVirutalCarLabel.setText("시뮬레이션 차량 대수 : " + allCars.size());

		if(simulatorConsoleModel.getConsoleList() == null) {
			simulatorConsoleModel.setConsoleList(allCars);
		}
		simulatorConsoleModel.fireTableDataChanged();

		// Client 테이블 갱신
		if (simulatorClientModel.getConsoleList() == null) {
			simulatorClientModel.setConsoleList(clientCars);
		}
		simulatorClientModel.fireTableDataChanged();
		if(simulator.getClientCar().size() == 10) {
			createClientButton.setEnabled(false);
		}
		else {
			createClientButton.setEnabled(true);
		}

		simulatorEdgeMoel.fireTableDataChanged();
		if(simulator != null && simulator.getAllCar() != null) {
			simulatorCanvas.setCarList(simulator.getAllCar(), simulator.getClientCar());
		}
		simulatorCanvas.repaint();

	}

	class ClientEditDialog extends JDialog implements ActionListener {
		private Vertex source;
		private Vertex destination;
		private boolean isEdit = false;
		private Car car;


		private JComboBox destinationCombo;
		private	JButton deleteButton;
		private JButton editButton;
		private JButton cancelButton;


		public Vertex getSource() {
			return source;
		}

		public Vertex getDestination() {
			return destination;
		}

		public boolean isEdit() {
			return isEdit;
		}

		public ClientEditDialog(JFrame owner, String title, Car car) {
			super(owner, title, true);
			setLayout(new BorderLayout());
			setLocationRelativeTo(owner);
			this.car = car;

			JPanel inputPanel = new JPanel();
			JPanel buttonPanel = new JPanel();

			inputPanel.setBorder(new TitledBorder(null, "클라이언트 정보 및 도착지 지정", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			destinationCombo = new JComboBox();
			destinationCombo.setModel(new DefaultComboBoxModel(virtualMap.makeComboboxString()));

			inputPanel.add(new JLabel("이름 : " + car.getName() + ",\t\t현재위치 : " + car.getSource().getName()));
			inputPanel.add(new JLabel("->"));
			inputPanel.add(destinationCombo);

			deleteButton = new JButton("삭제");
			editButton = new JButton("수정");
			cancelButton = new JButton("취소");

			if(car.getCarState() == CarState.DRIVING) {
				editButton.setEnabled(false);
				destinationCombo.setEnabled(false);
			}
			else {
				editButton.setEnabled(true);
				destinationCombo.setEnabled(true);
			}

			deleteButton.addActionListener(this);
			editButton.addActionListener(this);
			cancelButton.addActionListener(this);
			buttonPanel.add(deleteButton);
			buttonPanel.add(editButton);
			buttonPanel.add(cancelButton);

			add(inputPanel, BorderLayout.CENTER);
			add(buttonPanel, BorderLayout.SOUTH);

			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

			pack();
			setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == deleteButton) {
				synchronized (simulator.getAllCar()) {
					simulator.getClientCar().remove(car);
					simulator.getAllCar().remove(car);
				}
			}
			else if(e.getSource() == editButton) {
				if (!destinationCombo.getSelectedItem().equals(car.getSource().getName())) {
					try {
						simulator.getClientCar().remove(car);
						simulator.addClient(car.getName(), virtualMap.findVertextByName(car.getSource().getName()), destinationCombo.getSelectedIndex());
					} catch (CloneNotSupportedException e1) {
						e1.printStackTrace();
					}
				}
			}
			else if(e.getSource() == cancelButton) {

			}
			setVisible(false);
		}
	}

	@Data
	class ClientDialog extends JDialog {
		private int source;
		private int destination;
		private String name;
		private boolean isCancel = false;
		public ClientDialog(JFrame owner, String title) {
			super(owner, title, true);
			setLayout(new BorderLayout());
			setLocationRelativeTo(owner);


			JPanel selectPanel = new JPanel();
			JPanel buttonPanel = new JPanel();

			// Panel Conf
			selectPanel.setBorder(new TitledBorder(null, "출발지 및 도착지 정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));

			JLabel nameLabel = new JLabel("클라이언트 이름");
			JTextField nameField = new JTextField();
			JComboBox sourceCombo = new JComboBox();
			JComboBox destinationCombo = new JComboBox();
			nameField.setColumns(20);

			selectPanel.add(nameLabel, BorderLayout.NORTH);
			selectPanel.add(nameField, BorderLayout.NORTH);
			sourceCombo.setModel(new DefaultComboBoxModel(virtualMap.makeComboboxString()));
			destinationCombo.setModel(new DefaultComboBoxModel(virtualMap.makeComboboxString()));
			selectPanel.add(sourceCombo, BorderLayout.WEST);
			selectPanel.add(new JLabel("->"),BorderLayout.WEST);
			selectPanel.add(destinationCombo, BorderLayout.EAST);

			JButton createBtn = new JButton("생성");
			JButton cancelBtn = new JButton("취소");
			buttonPanel.add(createBtn);
			buttonPanel.add(cancelBtn);

			// Action
			createBtn.addActionListener((ActionEvent e) -> {
				source = sourceCombo.getSelectedIndex();
				destination = destinationCombo.getSelectedIndex();
				name = nameField.getText();

				if(name == "" || source == destination) {
					setVisible(true);
				}
				else {
					setVisible(false);
				}

			});
			cancelBtn.addActionListener((ActionEvent e) -> {
				isCancel = true;
				setVisible(false);
			});

			add(selectPanel, BorderLayout.CENTER);
			add(buttonPanel, BorderLayout.SOUTH);

			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

			pack();
			setVisible(true);

		}
	}
}
