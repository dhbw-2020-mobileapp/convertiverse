package com.github.convertiverse.converter.time;

import com.github.convertiverse.converter.Converter;

/**
 * @author Tobias Büser
 */
public class DayToSecondConverter extends Converter {

	public DayToSecondConverter() {
		super("day", "second");
	}

	@Override
	public double forwards(double fromValue) {
		return fromValue * (24 * 60 * 60);
	}

	@Override
	public double backwards(double toValue) {
		return toValue / (24 * 60 * 60);
	}
}
