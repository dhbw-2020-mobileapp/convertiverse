package com.github.convertiverse.converter.volume;

import com.github.convertiverse.converter.Converter;

/**
 * @author Tobias Büser
 */
public class CubicCentimetreToCubicMetreConverter extends Converter {

	public CubicCentimetreToCubicMetreConverter() {
		super("cubic_centimetre", "cubic_metre");
	}

	@Override
	public double forwards(double fromValue) {
		return fromValue / (1000 * 1000);
	}

	@Override
	public double backwards(double toValue) {
		return toValue * (1000 * 1000);
	}
}
