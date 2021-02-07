package com.github.convertiverse.converter.temperature;

import com.github.convertiverse.converter.Converter;

/**
 * @author Tobias Büser
 */
public class CelsiusToKelvinConverter extends Converter {

	public CelsiusToKelvinConverter() {
		super("celsius", "kelvin");
	}

	@Override
	public double forwards(double fromValue) {
		return fromValue + 273.15;
	}

	@Override
	public double backwards(double toValue) {
		return toValue - 273.15;
	}
}
