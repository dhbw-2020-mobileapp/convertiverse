package com.github.convertiverse.converter.bytes;

import com.github.convertiverse.converter.Converter;

/**
 * @author Tobias Büser
 */
public class KiloByteToByteConverter extends Converter {

	public KiloByteToByteConverter() {
		super("kilobyte", "byte");
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
