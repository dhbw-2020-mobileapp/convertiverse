package com.github.convertiverse.converter.distance;

import com.github.convertiverse.converter.Converter;

/**
 * @author Tobias Büser
 */
public class CentimetreToMetreConverter extends Converter {

	public CentimetreToMetreConverter() {
		super("centimetre", "metre");
	}

	@Override
	public double forwards(double fromValue) {
		return fromValue * 0.01;
	}

	@Override
	public double backwards(double toValue) {
		return toValue / 0.01;
	}
}
