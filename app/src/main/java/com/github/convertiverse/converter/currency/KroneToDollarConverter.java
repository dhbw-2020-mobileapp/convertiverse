package com.github.convertiverse.converter.currency;

import com.github.convertiverse.converter.Converter;
import com.github.convertiverse.converter.ExchangeRateManager;

/**
 * @author Tobias Büser
 */
public class KroneToDollarConverter extends Converter {

	private final ExchangeRateManager exchangeRateManager;

	public KroneToDollarConverter(ExchangeRateManager rateManager) {
		super("danish_krone", "dollar");
		this.exchangeRateManager = rateManager;
	}

	@Override
	public double forwards(double fromValue) {
		double rate = exchangeRateManager.getRateOrDefault("DKK", 6.17);

		return fromValue / rate;
	}

	@Override
	public double backwards(double toValue) {
		double rate = exchangeRateManager.getRateOrDefault("DKK", 6.17);

		return toValue * rate;
	}
}
