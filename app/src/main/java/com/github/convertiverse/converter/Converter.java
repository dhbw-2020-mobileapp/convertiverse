package com.github.convertiverse.converter;

import com.github.convertiverse.Unit;

/**
 * @author Tobias Büser
 */
public abstract class Converter<S extends Unit<?>, T extends Unit<?>> {

	private String key;

	public Converter(String key) {
		this.key = key;
	}

	public abstract T forwards(S inputUnit);

	public String getKey() {
		return key;
	}
}
