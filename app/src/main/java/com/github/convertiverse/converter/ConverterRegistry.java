package com.github.convertiverse.converter;

import com.github.convertiverse.unit.Unit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tobias Büser
 */
public class ConverterRegistry {

	private final List<Converter<?, ?>> list = new ArrayList<>();

	public <S extends Unit, T extends Unit> void register(Converter<S, T> converter) {
		list.add(converter);
	}

	public List<Converter<?, ?>> getAll(Class<?> fromOrToClass) {
		return list.stream()
				.filter(converter -> converter.getFromClass().equals(fromOrToClass)
						|| converter.getToClass().equals(fromOrToClass))
				.collect(Collectors.toList());
	}

	public <S extends Unit, T extends Unit> Converter<S, T> get(Class<S> from, Class<T> to) {
		for (Converter<?, ?> converter : this.list) {
			if (converter.getFromClass().equals(from) && converter.getToClass().equals(to)) {
				return (Converter<S, T>) converter;
			}
		}

		List<Converter<?, ?>> fromConverterList = this.getAll(from);
		List<Converter<?, ?>> toConverterList = this.getAll(to);

		for (Converter<?, ?> fromConverter : fromConverterList) {
			for (Converter<?, ?> toConverter : toConverterList) {
				if (!fromConverter.isCompatibleWith(toConverter)) continue;

				Class<?> commonUnit = fromConverter.getCommonUnit(toConverter);

				return new Converter<S, T>(null, from, to) {
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
