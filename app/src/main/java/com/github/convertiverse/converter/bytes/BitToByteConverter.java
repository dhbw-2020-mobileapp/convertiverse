package com.github.convertiverse.converter.bytes;

import com.github.convertiverse.converter.Converter;

/**
 * @author Tobias Büser
 */
public class BitToByteConverter extends Converter {

	public BitToByteConverter() {
		super("bit", "byte");
	}

	@Override
	public double forwards(double fromValue) {
		return fromValue / 8;
	}

	@Override
	public double backwards(double toValue) {
		return toValue * 8;
	}
}
