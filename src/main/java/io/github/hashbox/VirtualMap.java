package io.github.hashbox;

import lombok.Data;

import java.util.List;

/**
 * Created by js on 2017. 5. 19..
 */
@Data
public class VirtualMap {
	private static VirtualMap instance = new VirtualMap();
	private List<Edge> edges;

	private VirtualMap() {
		System.out.println("Call VirtualMap Constructor.");
		this.edges = null;
	}

	public static VirtualMap getInstance() {
		return instance;
	}
}
