package com.github.convertiverse.converter.distance;

import com.github.convertiverse.converter.Converter;

/**
 * @author Tobias Büser
 */
public class MillimetreToMetreConverter extends Converter {

	public MillimetreToMetreConverter() {
		super("millimetre", "metre");
	}

	@Override
	public double forwards(double fromValue) {
		return fromValue * 0.001;
	}

	@Override
	public double backwards(double toValue) {
		return toValue / 0.001;
	}
}
