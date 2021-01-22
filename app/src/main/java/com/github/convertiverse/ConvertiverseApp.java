package com.github.convertiverse;

import com.github.convertiverse.category.CategoryRegistry;
import com.github.convertiverse.category.ConverterCategory;
import com.github.convertiverse.converter.Converter;
import com.github.convertiverse.converter.ConverterRegistry;
import com.github.convertiverse.converter.EuroToDollarConverter;
import com.github.convertiverse.converter.YenToDollarConverter;
import com.github.convertiverse.database.UserDao;
import com.github.convertiverse.unit.DollarUnit;
import com.github.convertiverse.unit.EuroUnit;
import com.github.convertiverse.unit.Unit;
import com.github.convertiverse.unit.UnitRegistry;
import com.github.convertiverse.unit.YenUnit;
import java.util.List;

/**
 * @author Tobias Büser
 */
public class ConvertiverseApp {

	private static ConvertiverseApp INSTANCE;

	public static ConvertiverseApp getInstance() {
		if (INSTANCE == null) INSTANCE = new ConvertiverseApp();
		return INSTANCE;
	}

	private final ConverterRegistry converterRegistry = new ConverterRegistry();
	private final UnitRegistry unitRegistry = new UnitRegistry();
	private final CategoryRegistry categoryRegistry = new CategoryRegistry();

	private String uniqueUserId;
	private UserDao userDao;

	public ConvertiverseApp() {
		// initialize default categories
		categoryRegistry.register(new ConverterCategory("currency", "Währung", "empty", DollarUnit.class));
		categoryRegistry.register(new ConverterCategory("weight", "Gewicht", "empty", null));
		categoryRegistry.register(new ConverterCategory("distance", "Entfernung", "empty", null));
		categoryRegistry.register(new ConverterCategory("speed", "Geschwindigkeit", "empty", null));

		// initialize default units
		unitRegistry.register(new EuroUnit("currency"));
		unitRegistry.register(new DollarUnit("currency"));
		unitRegistry.register(new YenUnit("currency"));

		// initialize default converter
		converterRegistry.register(new EuroToDollarConverter());
		converterRegistry.register(new YenToDollarConverter());

		// connect to db
		/*FirebaseFirestore db = FirebaseFirestore.getInstance();
		this.userDao = new FirestoreUserDao(db);

		FirebaseInstallations.getInstance().getId().addOnSuccessListener(s -> uniqueUserId = s);*/
	}

	public <S extends Unit, T extends Unit> double convert(Class<S> fromUnitClass, double value, Class<T> toUnitClass) {
		Converter<S, T> converter = converterRegistry.get(fromUnitClass, toUnitClass);
		if (converter == null) throw new IllegalStateException("Such a conversion is currently not possible.");
		return converter.forwards(value);
	}

	public List<? extends Unit> getUnits(String categoryKey) {
		return this.unitRegistry.getAll(categoryKey);
	}

	public ConverterCategory getCategory(String key) {
		return this.categoryRegistry.get(key);
	}

	public List<ConverterCategory> getCategories() {
		return this.categoryRegistry.getAll();
	}

	public String getUniqueUserId() {
		return uniqueUserId;
	}

	public UserDao getUserDao() {
		return userDao;
	}

}
