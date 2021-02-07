package com.github.convertiverse.converter.currency;

import com.github.convertiverse.converter.Converter;
import com.github.convertiverse.converter.ExchangeRateManager;

/**
 * @author Tobias Büser
 */
public class FrancToDollarConverter extends Converter {

	private final ExchangeRateManager exchangeRateManager;

	public FrancToDollarConverter(ExchangeRateManager rateManager) {
		super("swiss_franc", "dollar");
		this.exchangeRateManager = rateManager;
	}

	@Override
	public double forwards(double fromValue) {
		double rate = exchangeRateManager.getRateOrDefault("CHF", 1.11);

		return fromValue * rate;
	}

	@Override
	public double backwards(double toValue) {
		double rate = exchangeRateManager.getRateOrDefault("CHF", 1.11);

		return toValue / rate;
	}
}
