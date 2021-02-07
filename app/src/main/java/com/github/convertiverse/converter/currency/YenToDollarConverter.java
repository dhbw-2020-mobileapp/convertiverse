package com.github.convertiverse.converter.currency;

import com.github.convertiverse.converter.Converter;
import com.github.convertiverse.converter.ExchangeRateManager;

/**
 * @author Tobias Büser
 */
public class YenToDollarConverter extends Converter {

	private final ExchangeRateManager exchangeRateManager;

	public YenToDollarConverter(ExchangeRateManager rateManager) {
		super("japanese_yen", "dollar");
		this.exchangeRateManager = rateManager;
	}

	@Override
	public double forwards(double fromValue) {
		double rate = exchangeRateManager.getRateOrDefault("JPY", 0.0096);

		return fromValue * rate;
	}

	@Override
	public double backwards(double toValue) {
		double rate = exchangeRateManager.getRateOrDefault("JPY", 0.0096);

		return toValue / rate;
	}

}
