package com.github.convertiverse.converter;

import com.github.convertiverse.unit.DollarUnit;
import com.github.convertiverse.unit.EuroUnit;

/**
 * @author Tobias Büser
 */
public class EuroToDollarConverter extends BiConverter<EuroUnit, DollarUnit> {

	public EuroToDollarConverter() {
		super("euro_to_dollar");
	}

	@Override
	public DollarUnit forwards(EuroUnit inputUnit) {
		return new DollarUnit(inputUnit.getValue() * 1.2);
	}

	@Override
	public EuroUnit backwards(DollarUnit inputUnit) {
		return new EuroUnit(inputUnit.getValue() / 1.2);
	}
}
