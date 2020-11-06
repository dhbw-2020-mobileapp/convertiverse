package com.github.convertiverse.converter;

import com.github.convertiverse.Unit;

/**
 * @author Tobias Büser
 */
public abstract class BiConverter<S extends Unit<?>, T extends Unit<?>> extends Converter<S, T> {

	public BiConverter(String key) {
		super(key);
	}

	public abstract S backwards(T inputUnit);

}
