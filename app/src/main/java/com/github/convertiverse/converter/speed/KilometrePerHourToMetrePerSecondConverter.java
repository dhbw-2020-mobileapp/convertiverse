package com.github.convertiverse.converter.speed;

import com.github.convertiverse.converter.Converter;

/**
 * @author Tobias Büser
 */
public class KilometrePerHourToMetrePerSecondConverter extends Converter {

	public KilometrePerHourToMetrePerSecondConverter() {
		super("kilometre_per_hour", "metre_per_second");
	}

	@Override
	public double forwards(double fromValue) {
		return fromValue * 0.277778;
	}

	@Override
	public double backwards(double toValue) {
		return toValue * 3.6;
	}
}
