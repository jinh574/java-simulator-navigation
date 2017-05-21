package io.github.hashbox;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by js on 2017. 5. 21..
 */
public class SimulatorConsoleModel extends AbstractTableModel {
	private List<Car> consoleList;

	public void setConsoleList(List<Car> consoleList) {
		this.consoleList = consoleList;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Car car = consoleList.get(rowIndex);
		Object val = null;
		if(consoleList == null) {
			val = "??";
		}
		else {
			switch (columnIndex) {
				case 0:
					val = car.getName();
					break;
				case 1:
					val = car.getLocatedToString() + "(" + car.getLocate() + "/" + (int)car.getDistance() + ")";
					break;
				case 2:
					val = car.getDestination().getName();
					break;
				case 3:
					val = car.getRoute().toString();
			}
		}
		return val;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		Class type = String.class;

		return type;
	}

	@Override
	public String getColumnName(int column) {
		String name = "??";
		switch (column) {
			case 0:
				name = "차량이름";
				break;
			case 1:
				name = "현재위치";
				break;
			case 2:
				name = "목적지";
				break;
			case 3:
				name = "남은 경로";
				break;
		}

		return name;
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		int result = 0;
		if(consoleList != null) {
			result = consoleList.size();
		}
		return result;
	}

	public SimulatorConsoleModel(List<Car> consoleList) {
		this.consoleList = consoleList;
	}
}
