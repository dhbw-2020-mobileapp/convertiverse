package com.github.convertiverse.converter;

import com.github.convertiverse.unit.Unit;

/**
 * @author Tobias Büser
 */
public abstract class Converter<S extends Unit, T extends Unit> {

	private final String key;
	private final Class<S> fromClass;
	private final Class<T> toClass;

	public Converter(String key, Class<S> from, Class<T> to) {
		this.key = key;
		this.fromClass = from;
		this.toClass = to;
	}

	public abstract double forwards(double fromValue);

	public abstract double backwards(double toValue);

	public double convert(double fromValue, Class<?> fromClass) {
		if (this.fromClass.equals(fromClass)) return this.forwards(fromValue);
		else if (this.toClass.equals(fromClass)) return this.backwards(fromValue);

		throw new IllegalArgumentException("Given class is not part of this converter");
	}

	public Class<?> getCommonUnit(Converter<?, ?> other) {
		if(other.getToClass().equals(fromClass) || other.getFromClass().equals(fromClass)) {
			return fromClass;
		} else if(other.getToClass().equals(toClass) || other.getFromClass().equals(toClass)) {
			return toClass;
		}
		return null;
	}

	public boolean isCompatibleWith(Converter<?, ?> other) {
		return getCommonUnit(other) != null;
	}

	public String getKey() {
		return key;
	}

	public Class<S> getFromClass() {
		return fromClass;
	}

	public Class<T> getToClass() {
		return toClass;
	}

}
