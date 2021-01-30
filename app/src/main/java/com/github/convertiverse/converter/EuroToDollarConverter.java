package com.github.convertiverse.converter;

/**
 * @author Tobias Büser
 */
public class EuroToDollarConverter extends Converter {

	public EuroToDollarConverter() {
		super("euro_to_dollar", "euro", "dollar");
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
