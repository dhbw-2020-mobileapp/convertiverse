package com.github.convertiverse.converter.currency;

import com.github.convertiverse.converter.Converter;

/**
 * @author Tobias Büser
 */
public class EuroToDollarConverter extends Converter {

	public EuroToDollarConverter() {
		super("euro", "dollar");
	}

	@Override
	public double forwards(double fromValue) {
		return fromValue * 1.22;
	}

	@Override
	public double backwards(double toValue) {
		return toValue / 1.22;
	}

}
