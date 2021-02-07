package com.github.convertiverse.converter.area;

import com.github.convertiverse.converter.Converter;

/**
 * @author Tobias Büser
 */
public class CentimetreSquaredToMetreSquaredConverter extends Converter {

	public CentimetreSquaredToMetreSquaredConverter() {
		super("centimetre_squared", "metre_squared");
	}

	@Override
	public double forwards(double fromValue) {
		return fromValue / (100 * 100);
	}

	@Override
	public double backwards(double toValue) {
		return toValue * (100 * 100);
	}
}
