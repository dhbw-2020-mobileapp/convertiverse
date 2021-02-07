package com.github.convertiverse.converter.area;

import com.github.convertiverse.converter.Converter;

/**
 * @author Tobias Büser
 */
public class HectareToMetreSquaredConverter extends Converter {

	public HectareToMetreSquaredConverter() {
		super("hectare", "metre_squared");
	}

	@Override
	public double forwards(double fromValue) {
		return fromValue * (100 * 100);
	}

	@Override
	public double backwards(double toValue) {
		return toValue / (100 * 100);
	}
}
