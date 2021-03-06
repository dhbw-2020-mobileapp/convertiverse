package com.github.convertiverse.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tobias Büser
 */
public class ConverterRegistry {

	private final List<Converter> list = new ArrayList<>();

	public void register(Converter converter) {
		list.add(converter);
	}

	public List<Converter> getAll(String fromOrToKey) {
		return list.stream()
				.filter(converter -> converter.getFromKey().equals(fromOrToKey)
						|| converter.getToKey().equals(fromOrToKey))
				.collect(Collectors.toList());
	}

	public Converter get(String from, String to) {
		for (Converter converter : this.list) {
			if (converter.getFromKey().equals(from) && converter.getToKey().equals(to)) {
				return converter;
			} else if(converter.getFromKey().equals(to) && converter.getToKey().equals(from)) {
				return new Converter(from, to) {
					@Override
					public double forwards(double fromValue) {
						return converter.backwards(fromValue);
					}

					@Override
					public double backwards(double toValue) {
						return converter.forwards(toValue);
					}
				};
			}
		}

		List<Converter> fromConverterList = this.getAll(from);
		List<Converter> toConverterList = this.getAll(to);

		for (Converter fromConverter : fromConverterList) {
			for (Converter toConverter : toConverterList) {
				if (!fromConverter.isCompatibleWith(toConverter)) continue;

				String commonUnit = fromConverter.getCommonUnit(toConverter);

				return new Converter(from, to) {
					@Override
					public double forwards(double fromValue) {
						return toConverter.convert(fromConverter.convert(fromValue, from), commonUnit);
					}

					@Override
					public double backwards(double toValue) {
						return fromConverter.convert(toConverter.convert(toValue, to), commonUnit);
					}
				};
			}
		}
		return null;
	}

}
