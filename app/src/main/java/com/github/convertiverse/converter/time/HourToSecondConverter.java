package com.github.convertiverse.converter.time;

import com.github.convertiverse.converter.Converter;

/**
 * @author Tobias Büser
 */
public class HourToSecondConverter extends Converter {

	public HourToSecondConverter() {
		super("hour", "second");
	}

	@Override
	public double forwards(double fromValue) {
		return fromValue * (60 * 60);
	}

	@Override
	public double backwards(double toValue) {
		return toValue / (60 * 60);
	}
}
