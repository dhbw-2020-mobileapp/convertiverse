package com.github.convertiverse.converter.temperature;

import com.github.convertiverse.converter.Converter;

/**
 * @author Tobias Büser
 */
public class FahrenheitToKelvinConverter extends Converter {

	public FahrenheitToKelvinConverter() {
		super("fahrenheit", "kelvin");
	}

	@Override
	public double forwards(double fromValue) {
		return (fromValue - 32) * (5.0 / 9.0) + 273.15;
	}

	@Override
	public double backwards(double toValue) {
		return (toValue - 273.15) * (9.0 / 5.0) + 32;
	}
}
