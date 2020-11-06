package com.github.convertiverse;

/**
 * @author Tobias Büser
 */
public abstract class Unit<T> {

	private final String key;
	private final T value;

	public Unit(String key, T value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public T getValue() {
		return value;
	}
}
