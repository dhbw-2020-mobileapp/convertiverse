package com.github.convertiverse.converter;

import com.github.convertiverse.Unit;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Tobias Büser
 */
public class ConverterRegistry {

	private final Map<String, BiConverter<?, ?>> map = new HashMap<>();

	public <S extends Unit<?>, T extends Unit<?>> void register(BiConverter<S, T> converter) {
		map.put(converter.getKey(), converter);
	}

	public <S extends Unit<?>, T extends Unit<?>> BiConverter<S, T> get(String key) {
		return (BiConverter<S, T>) map.get(key);
	}

}
