package com.github.convertiverse.converter.weight;

import com.github.convertiverse.converter.Converter;

/**
 * @author Tobias Büser
 */
public class MilligramToGramConverter extends Converter {

	public MilligramToGramConverter() {
		super("milligram", "gram");
	}

	@Override
	public double forwards(double fromValue) {
		return fromValue * 0.001;
	}

	@Override
	public double backwards(double toValue) {
		return toValue / 0.001;
	}
}
