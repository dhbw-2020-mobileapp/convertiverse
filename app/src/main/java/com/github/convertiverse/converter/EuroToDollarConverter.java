package com.github.convertiverse.converter;

import com.github.convertiverse.unit.DollarUnit;
import com.github.convertiverse.unit.EuroUnit;

/**
 * @author Tobias Büser
 */
public class EuroToDollarConverter extends Converter<EuroUnit, DollarUnit> {

	public EuroToDollarConverter() {
		super("euro_to_dollar", EuroUnit.class, DollarUnit.class);
	}

	@Override
	public double forwards(double fromValue) {
		System.out.println("EURO: forwards");
		return fromValue * 1.22;
	}

	@Override
	public double backwards(double toValue) {
		System.out.println("EURO: backwards");
		return toValue / 1.22;
	}

}
