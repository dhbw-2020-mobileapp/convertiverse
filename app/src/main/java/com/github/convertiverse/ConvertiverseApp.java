package com.github.convertiverse;

import com.github.convertiverse.category.CategoryRegistry;
import com.github.convertiverse.category.ConverterCategory;
import com.github.convertiverse.converter.Converter;
import com.github.convertiverse.converter.ConverterRegistry;
import com.github.convertiverse.converter.EuroToDollarConverter;
import com.github.convertiverse.converter.YenToDollarConverter;
import com.github.convertiverse.database.UserDao;
import com.github.convertiverse.unit.Unit;
import com.github.convertiverse.unit.UnitRegistry;
import com.github.convertiverse.userdata.UserDataManager;
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
	private final UserDataManager userDataManager = new UserDataManager();

	private String uniqueUserId;
	private UserDao userDao;

	public ConvertiverseApp() {
		// initialize default categories
		categoryRegistry.register(new ConverterCategory("currency", "Währung", "empty", "#264653"));
		categoryRegistry.register(new ConverterCategory("weight", "Gewicht", "empty", "#e76f51"));
		categoryRegistry.register(new ConverterCategory("distance", "Entfernung", "empty", "#e9c46a"));
		categoryRegistry.register(new ConverterCategory("speed", "Geschwindigkeit", "empty", "#275c62"));

		// initialize default units
		unitRegistry.register(new Unit("euro", "currency", "Euro", "EUR"));
		unitRegistry.register(new Unit("dollar", "currency", "Dollar", "EUR"));
		unitRegistry.register(new Unit("japanese_yen", "currency", "Japanische Yen", "JPY"));

		// initialize default converter
		converterRegistry.register(new EuroToDollarConverter());
		converterRegistry.register(new YenToDollarConverter());

		// connect to db
		/*FirebaseFirestore db = FirebaseFirestore.getInstance();
		this.userDao = new FirestoreUserDao(db);

		FirebaseInstallations.getInstance().getId().addOnSuccessListener(s -> uniqueUserId = s);*/
	}

	public <S extends Unit, T extends Unit> double convert(String fromUnitKey, double value, String toUnitKey) {
		Converter converter = converterRegistry.get(fromUnitKey, toUnitKey);
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

	public UserDataManager getUserDataManager() {
		return userDataManager;
	}

}
