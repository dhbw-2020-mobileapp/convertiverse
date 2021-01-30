package com.github.convertiverse.converter.weight;

import com.github.convertiverse.converter.Converter;

/**
 * @author Tobias Büser
 */
public class TonneToGramConverter extends Converter {

	public TonneToGramConverter() {
		super("tonne", "gram");
	}

	@Override
	public double forwards(double fromValue) {
		return fromValue * 1000 * 1000;
	}

	@Override
	public double backwards(double toValue) {
		return toValue / 1000 / 1000;
	}
}
