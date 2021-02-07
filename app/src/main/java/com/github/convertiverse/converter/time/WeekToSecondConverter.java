package com.github.convertiverse.converter.time;

import com.github.convertiverse.converter.Converter;

/**
 * @author Tobias Büser
 */
public class WeekToSecondConverter extends Converter {

	public WeekToSecondConverter() {
		super("week", "second");
	}

	@Override
	public double forwards(double fromValue) {
		return fromValue * (7 * 24 * 60 * 60);
	}

	@Override
	public double backwards(double toValue) {
		return toValue / (7 * 24 * 60 * 60);
	}
}
