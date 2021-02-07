package com.github.convertiverse.converter.bytes;

import com.github.convertiverse.converter.Converter;

/**
 * @author Tobias Büser
 */
public class GigaByteToByteConverter extends Converter {

	public GigaByteToByteConverter() {
		super("gigabyte", "byte");
	}

	@Override
	public double forwards(double fromValue) {
		return fromValue * (1000 * 1000 * 1000);
	}

	@Override
	public double backwards(double toValue) {
		return toValue / (1000 * 1000 * 1000);
	}
}
