package io.github.hashbox;

import lombok.Data;

/**
 * Created by js on 2017. 5. 19..
 */
@Data
public class Vertax {
	private String name;
	private int id;

	public Vertax(String name, int id) {
		this.name = name;
		this.id = id;
	}
}
