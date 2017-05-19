package io.github.hashbox;

import lombok.Data;

/**
 * Created by js on 2017. 5. 19..
 */
@Data
class Vertex implements Comparable<Vertex>, Cloneable {
	private final String name;
	private Edge[] adjacencies;
	private double minDistance = Double.POSITIVE_INFINITY;
	private double currentDistance;
	private Edge currentEdge;
	private Vertex previous;

	public Vertex(String argName) {
		name = argName;
		currentEdge = null;
	}

	public String toString() {
		return name;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public void setCurrentEdge(Edge currentEdge) {
		this.currentEdge = currentEdge;
	}

	public int compareTo(Vertex other) {
		return Double.compare(minDistance, other.minDistance);
	}
}