package com.github.convertiverse.converter.currency;

import com.github.convertiverse.converter.Converter;

/**
 * @author Tobias Büser
 */
public class YenToDollarConverter extends Converter {

	public YenToDollarConverter() {
		super("japanese_yen", "dollar");
	}

	@Override
	public double forwards(double fromValue) {
		return fromValue * 0.0096;
	}

	@Override
	public double backwards(double toValue) {
		return toValue / 0.0096;
	}

}
