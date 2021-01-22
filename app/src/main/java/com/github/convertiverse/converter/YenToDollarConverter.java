package com.github.convertiverse.converter;

import com.github.convertiverse.unit.DollarUnit;
import com.github.convertiverse.unit.YenUnit;

/**
 * @author Tobias Büser
 */
public class YenToDollarConverter extends Converter<YenUnit, DollarUnit> {

	public YenToDollarConverter() {
		super("yen_to_dollar", YenUnit.class, DollarUnit.class);
	}

	@Override
	public double forwards(double fromValue) {
		System.out.println("YEN: forw");
		return fromValue * 0.0096;
	}

	@Override
	public double backwards(double toValue) {
		System.out.println("YEN: backw");
		return toValue / 0.0096;
	}

}
