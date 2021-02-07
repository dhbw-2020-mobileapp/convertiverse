package com.github.convertiverse.converter.currency;

import com.github.convertiverse.converter.Converter;
import com.github.convertiverse.converter.ExchangeRateManager;

/**
 * @author Tobias Büser
 */
public class CanadianDollarToDollarConverter extends Converter {

	private final ExchangeRateManager exchangeRateManager;

	public CanadianDollarToDollarConverter(ExchangeRateManager rateManager) {
		super("canadian_dollar", "dollar");
		this.exchangeRateManager = rateManager;
	}

	@Override
	public double forwards(double fromValue) {
		double rate = exchangeRateManager.getRateOrDefault("CAD", 0.78);

		return fromValue * rate;
	}

	@Override
	public double backwards(double toValue) {
		double rate = exchangeRateManager.getRateOrDefault("CAD", 0.78);

		return toValue / rate;
	}
}
