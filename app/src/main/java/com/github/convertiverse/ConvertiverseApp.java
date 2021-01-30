package com.github.convertiverse;

import com.github.convertiverse.category.CategoryRegistry;
import com.github.convertiverse.category.ConverterCategory;
import com.github.convertiverse.converter.Converter;
import com.github.convertiverse.converter.ConverterRegistry;
import com.github.convertiverse.converter.ExchangeRateManager;
import com.github.convertiverse.converter.currency.EuroToDollarConverter;
import com.github.convertiverse.converter.currency.YenToDollarConverter;
import com.github.convertiverse.converter.distance.CentimetreToMetreConverter;
import com.github.convertiverse.converter.distance.KilometreToMetreConverter;
import com.github.convertiverse.converter.distance.MillimetreToMetreConverter;
import com.github.convertiverse.converter.speed.KilometrePerHourToMetrePerSecondConverter;
import com.github.convertiverse.converter.weight.KilogramToGramConverter;
import com.github.convertiverse.converter.weight.MilligramToGramConverter;
import com.github.convertiverse.converter.weight.TonneToGramConverter;
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

	private final ExchangeRateManager exchangeRateManager;

	private String uniqueUserId;
	private UserDao userDao;

	public ConvertiverseApp() {
		this.exchangeRateManager = new ExchangeRateManager("d1e801414d993d532aa54dde");
		this.exchangeRateManager.request();

		// initialize default categories
		categoryRegistry.register(new ConverterCategory("currency", "Währung", "empty", "#264653"));
		categoryRegistry.register(new ConverterCategory("weight", "Gewicht", "empty", "#e76f51"));
		categoryRegistry.register(new ConverterCategory("distance", "Entfernung", "empty", "#e9c46a"));
		categoryRegistry.register(new ConverterCategory("speed", "Geschwindigkeit", "empty", "#275c62"));

		// initialize default units
		unitRegistry.register(new Unit("euro", "currency", "Euro", "EUR"));
		unitRegistry.register(new Unit("dollar", "currency", "Dollar", "EUR"));
		unitRegistry.register(new Unit("japanese_yen", "currency", "Japanische Yen", "JPY"));

		unitRegistry.register(new Unit("milligram", "weight", "Milligramm", "mg"));
		unitRegistry.register(new Unit("gram", "weight", "Gramm", "g"));
		unitRegistry.register(new Unit("kilogram", "weight", "Kilogramm", "kg"));
		unitRegistry.register(new Unit("tonne", "weight", "Tonne", "t"));

		unitRegistry.register(new Unit("millimetre", "distance", "Millimeter", "mm"));
		unitRegistry.register(new Unit("centimetre", "distance", "Centimeter", "cm"));
		unitRegistry.register(new Unit("metre", "distance", "Meter", "m"));
		unitRegistry.register(new Unit("kilometre", "distance", "Kilometer", "km"));

		unitRegistry.register(new Unit("metre_per_second", "speed", "Meter pro Sekunde", "m/s"));
		unitRegistry.register(new Unit("kilometre_per_hour", "speed", "Kilometer pro Stunde", "km/h"));

		// initialize default converter
		converterRegistry.register(new EuroToDollarConverter(exchangeRateManager));
		converterRegistry.register(new YenToDollarConverter(exchangeRateManager));

		converterRegistry.register(new MilligramToGramConverter());
		converterRegistry.register(new KilogramToGramConverter());
		converterRegistry.register(new TonneToGramConverter());

		converterRegistry.register(new MillimetreToMetreConverter());
		converterRegistry.register(new CentimetreToMetreConverter());
		converterRegistry.register(new KilometreToMetreConverter());

		converterRegistry.register(new KilometrePerHourToMetrePerSecondConverter());

		// connect to db
		/*FirebaseFirestore db = FirebaseFirestore.getInstance();
		this.userDao = new FirestoreUserDao(db);

		FirebaseInstallations.getInstance().getId().addOnSuccessListener(s -> uniqueUserId = s);*/
	}

	public double convert(String fromUnitKey, double value, String toUnitKey) {
		Converter converter = converterRegistry.get(fromUnitKey, toUnitKey);
		if (converter == null) throw new IllegalStateException("Such a conversion is currently not possible.");
		return converter.forwards(value);
	}

	public Unit getUnit(String unitKey) {
		return this.unitRegistry.get(unitKey);
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
