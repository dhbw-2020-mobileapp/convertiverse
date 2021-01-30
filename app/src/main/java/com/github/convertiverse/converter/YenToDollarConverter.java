package com.github.convertiverse.converter;

/**
 * @author Tobias Büser
 */
public class YenToDollarConverter extends Converter {

	public YenToDollarConverter() {
		super("yen_to_dollar", "japanese_yen", "dollar");
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
