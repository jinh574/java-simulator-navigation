package io.github.hashbox;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by js on 2017. 5. 21..
 */
public class SimulatorEdgeMoel extends AbstractTableModel {
	private List<Edge> edgeList;

	public void setEdgeList(List<Edge> edgeList) {
		this.edgeList = edgeList;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Edge edge = edgeList.get(rowIndex);
		Object val = null;
		if(edgeList == null) {
			val = "??";
		}
		else {
			switch (columnIndex) {
				case 0:
					val = edge.toString();
					break;
				case 1:
					val = edge.getNCurrentCar();
					break;
				case 2:
					val = edge.getNPredictCar();
					break;
				case 3:
					val = edge.getWeightOriginal()+ "(+" + (edge.getWeight()-edge.getWeightOriginal()) + ")";
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
				name = "도로";
				break;
			case 1:
				name = "현재 차량대수";
				break;
			case 2:
				name = "예상 차량대수";
				break;
			case 3:
				name = "가중치";
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
		if(edgeList != null) {
			result = edgeList.size();
		}
		return result;
	}

	public SimulatorEdgeMoel(List<Edge> edgeList) {
		this.edgeList = edgeList;
	}
}
