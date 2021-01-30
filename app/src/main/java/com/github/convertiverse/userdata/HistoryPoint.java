package com.github.convertiverse.userdata;

/**
 * @author Tobias Büser
 */
public class HistoryPoint {

	private final String unit;
	private final double value;

	public HistoryPoint(String unit, double value) {
		this.unit = unit;
		this.value = value;
	}

	public String getUnit() {
		return unit;
	}

	public double getValue() {
		return value;
	}

}
