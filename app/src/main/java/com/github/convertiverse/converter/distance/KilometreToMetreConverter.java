package com.github.convertiverse.converter.distance;

import com.github.convertiverse.converter.Converter;

/**
 * @author Tobias Büser
 */
public class KilometreToMetreConverter extends Converter {

	public KilometreToMetreConverter() {
		super("kilometre", "metre");
	}

	@Override
	public double forwards(double fromValue) {
		return fromValue * 1000;
	}

	@Override
	public double backwards(double toValue) {
		return toValue / 1000;
	}
}
