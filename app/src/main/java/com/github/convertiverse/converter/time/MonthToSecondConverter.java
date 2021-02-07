package com.github.convertiverse.converter.time;

import com.github.convertiverse.converter.Converter;

/**
 * @author Tobias Büser
 */
public class MonthToSecondConverter extends Converter {

	public MonthToSecondConverter() {
		super("month", "second");
	}

	@Override
	public double forwards(double fromValue) {
		return fromValue * (4.34524 * 7 * 24 * 60 * 60);
	}

	@Override
	public double backwards(double toValue) {
		return toValue / (4.34524 * 7 * 24 * 60 * 60);
	}
}
