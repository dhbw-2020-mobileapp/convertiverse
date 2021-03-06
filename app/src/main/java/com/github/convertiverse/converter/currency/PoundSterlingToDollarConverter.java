package com.github.convertiverse.converter.currency;

import com.github.convertiverse.converter.Converter;
import com.github.convertiverse.converter.ExchangeRateManager;

/**
 * @author Tobias Büser
 */
public class PoundSterlingToDollarConverter extends Converter {

	private final ExchangeRateManager exchangeRateManager;

	public PoundSterlingToDollarConverter(ExchangeRateManager rateManager) {
		super("pound_sterling", "dollar");
		this.exchangeRateManager = rateManager;
	}

	@Override
	public double forwards(double fromValue) {
		double rate = exchangeRateManager.getRateOrDefault("GBP", 0.73);

		return fromValue / rate;
	}

	@Override
	public double backwards(double toValue) {
		double rate = exchangeRateManager.getRateOrDefault("GBP", 0.73);

		return toValue * rate;
	}
}
