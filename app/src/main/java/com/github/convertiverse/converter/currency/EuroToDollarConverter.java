package com.github.convertiverse.converter.currency;

import com.github.convertiverse.converter.Converter;
import com.github.convertiverse.converter.ExchangeRateManager;

/**
 * @author Tobias Büser
 */
public class EuroToDollarConverter extends Converter {

	private final ExchangeRateManager exchangeRateManager;

	public EuroToDollarConverter(ExchangeRateManager rateManager) {
		super("euro", "dollar");
		this.exchangeRateManager = rateManager;
	}

	@Override
	public double forwards(double fromValue) {
		double rate = exchangeRateManager.getRateOrDefault("EUR", 1.22);

		return fromValue / rate;
	}

	@Override
	public double backwards(double toValue) {
		double rate = exchangeRateManager.getRateOrDefault("EUR", 1.22);

		return toValue * rate;
	}

}
