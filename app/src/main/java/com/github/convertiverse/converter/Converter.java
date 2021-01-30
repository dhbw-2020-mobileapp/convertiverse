package com.github.convertiverse.converter;

/**
 * @author Tobias Büser
 */
public abstract class Converter {

	private final String fromKey;
	private final String toKey;

	public Converter(String from, String to) {
		this.fromKey = from;
		this.toKey = to;
	}

	public abstract double forwards(double fromValue);

	public abstract double backwards(double toValue);

	public double convert(double fromValue, String fromKey) {
		if (this.fromKey.equals(fromKey)) return this.forwards(fromValue);
		else if (this.toKey.equals(fromKey)) return this.backwards(fromValue);

		throw new IllegalArgumentException("Given class is not part of this converter");
	}

	public String getCommonUnit(Converter other) {
		if(other.getToKey().equals(fromKey) || other.getFromKey().equals(fromKey)) {
			return fromKey;
		} else if(other.getToKey().equals(toKey) || other.getFromKey().equals(toKey)) {
			return toKey;
		}
		return null;
	}

	public boolean isCompatibleWith(Converter other) {
		return getCommonUnit(other) != null;
	}

	public String getFromKey() {
		return fromKey;
	}

	public String getToKey() {
		return toKey;
	}
}
