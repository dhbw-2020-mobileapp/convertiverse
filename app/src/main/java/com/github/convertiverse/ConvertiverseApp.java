package com.github.convertiverse;

import com.github.convertiverse.category.CategoryRegistry;
import com.github.convertiverse.category.ConverterCategory;
import com.github.convertiverse.converter.Converter;
import com.github.convertiverse.converter.ConverterRegistry;
import com.github.convertiverse.converter.ExchangeRateManager;
import com.github.convertiverse.converter.area.CentimetreSquaredToMetreSquaredConverter;
import com.github.convertiverse.converter.area.HectareToMetreSquaredConverter;
import com.github.convertiverse.converter.area.KilometreSquaredToMetreSquaredConverter;
import com.github.convertiverse.converter.bytes.BitToByteConverter;
import com.github.convertiverse.converter.bytes.GigaByteToByteConverter;
import com.github.convertiverse.converter.bytes.KiloByteToByteConverter;
import com.github.convertiverse.converter.bytes.MegaByteToByteConverter;
import com.github.convertiverse.converter.currency.CanadianDollarToDollarConverter;
import com.github.convertiverse.converter.currency.EuroToDollarConverter;
import com.github.convertiverse.converter.currency.FrancToDollarConverter;
import com.github.convertiverse.converter.currency.KroneToDollarConverter;
import com.github.convertiverse.converter.currency.PoundSterlingToDollarConverter;
import com.github.convertiverse.converter.currency.YenToDollarConverter;
import com.github.convertiverse.converter.distance.CentimetreToMetreConverter;
import com.github.convertiverse.converter.distance.KilometreToMetreConverter;
import com.github.convertiverse.converter.distance.MillimetreToMetreConverter;
import com.github.convertiverse.converter.speed.KilometrePerHourToMetrePerSecondConverter;
import com.github.convertiverse.converter.temperature.CelsiusToKelvinConverter;
import com.github.convertiverse.converter.temperature.FahrenheitToKelvinConverter;
import com.github.convertiverse.converter.time.DayToSecondConverter;
import com.github.convertiverse.converter.time.HourToSecondConverter;
import com.github.convertiverse.converter.time.MinuteToSecondConverter;
import com.github.convertiverse.converter.time.MonthToSecondConverter;
import com.github.convertiverse.converter.time.WeekToSecondConverter;
import com.github.convertiverse.converter.volume.CubicCentimetreToCubicMetreConverter;
import com.github.convertiverse.converter.volume.CubicDecimetreToCubicMetreConverter;
import com.github.convertiverse.converter.weight.KilogramToGramConverter;
import com.github.convertiverse.converter.weight.MilligramToGramConverter;
import com.github.convertiverse.converter.weight.TonneToGramConverter;
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

	public ConvertiverseApp() {
		this.exchangeRateManager = new ExchangeRateManager("d1e801414d993d532aa54dde");
		this.exchangeRateManager.request();

		// initialize default categories
		categoryRegistry.register(new ConverterCategory("currency", "Währung", "empty", "#264653"));
		categoryRegistry.register(new ConverterCategory("weight", "Gewicht", "empty", "#e76f51"));
		categoryRegistry.register(new ConverterCategory("distance", "Entfernung", "empty", "#e9c46a"));
		categoryRegistry.register(new ConverterCategory("speed", "Geschwindigkeit", "empty", "#275c62"));
		categoryRegistry.register(new ConverterCategory("area", "Fläche", "empty", "#275c62"));
		categoryRegistry.register(new ConverterCategory("volume", "Volumen", "empty", "#275c62"));
		categoryRegistry.register(new ConverterCategory("temperature", "Temperatur", "empty", "#275c62"));
		categoryRegistry.register(new ConverterCategory("bytes", "Speichergrößen", "empty", "#275c62"));
		categoryRegistry.register(new ConverterCategory("time", "Zeit", "empty", "#275c62"));

		// initialize default units
		unitRegistry.register(new Unit("euro", "currency", "Euro", "EUR"));
		unitRegistry.register(new Unit("dollar", "currency", "Dollar", "USD"));
		unitRegistry.register(new Unit("japanese_yen", "currency", "Japanische Yen", "JPY"));
		unitRegistry.register(new Unit("swiss_franc", "currency", "Schweizer Franken", "CHF"));
		unitRegistry.register(new Unit("danish_krone", "currency", "Dänische Kronen", "DKK"));
		unitRegistry.register(new Unit("canadian_dollar", "currency", "Kanadische Dollar", "CAD"));
		unitRegistry.register(new Unit("pound_sterling", "currency", "Pfund Sterling", "GBP"));

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

		unitRegistry.register(new Unit("centimetre_squared", "area", "Quadratcentimeter", "cm^2"));
		unitRegistry.register(new Unit("metre_squared", "area", "Quadratmeter", "m^2"));
		unitRegistry.register(new Unit("hectare", "area", "Hektar", "ha"));
		unitRegistry.register(new Unit("kilometre_squared", "area", "Quadratkilometer", "km^2"));

		unitRegistry.register(new Unit("cubic_metre", "volume", "Kubikmeter", "m^3"));
		unitRegistry.register(new Unit("cubic_decimetre", "volume", "Kubikdezimeter", "dm^3"));
		unitRegistry.register(new Unit("cubic_centimetre", "volume", "Kubikcentimeter", "cm^3"));

		unitRegistry.register(new Unit("fahrenheit", "temperature", "Fahrenheit", "°F"));
		unitRegistry.register(new Unit("celcius", "temperature", "Celsius", "°C"));
		unitRegistry.register(new Unit("kelvin", "temperature", "Kelvin", "K"));

		unitRegistry.register(new Unit("bit", "bytes", "Bit", "bit"));
		unitRegistry.register(new Unit("byte", "bytes", "Byte", "B"));
		unitRegistry.register(new Unit("kilobyte", "bytes", "Kilobyte", "KB"));
		unitRegistry.register(new Unit("megabyte", "bytes", "Megabyte", "MB"));
		unitRegistry.register(new Unit("gigabyte", "bytes", "Gigabyte", "GB"));

		unitRegistry.register(new Unit("second", "time", "Sekunden", "s"));
		unitRegistry.register(new Unit("minute", "time", "Minuten", "m"));
		unitRegistry.register(new Unit("hour", "time", "Stunden", "h"));
		unitRegistry.register(new Unit("day", "time", "Tage", "d"));
		unitRegistry.register(new Unit("week", "time", "Wochen", "w"));
		unitRegistry.register(new Unit("month", "time", "Monate", "M"));

		// initialize default converter
		converterRegistry.register(new EuroToDollarConverter(exchangeRateManager));
		converterRegistry.register(new YenToDollarConverter(exchangeRateManager));
		converterRegistry.register(new FrancToDollarConverter(exchangeRateManager));
		converterRegistry.register(new KroneToDollarConverter(exchangeRateManager));
		converterRegistry.register(new PoundSterlingToDollarConverter(exchangeRateManager));
		converterRegistry.register(new CanadianDollarToDollarConverter(exchangeRateManager));

		converterRegistry.register(new MilligramToGramConverter());
		converterRegistry.register(new KilogramToGramConverter());
		converterRegistry.register(new TonneToGramConverter());

		converterRegistry.register(new MillimetreToMetreConverter());
		converterRegistry.register(new CentimetreToMetreConverter());
		converterRegistry.register(new KilometreToMetreConverter());

		converterRegistry.register(new KilometrePerHourToMetrePerSecondConverter());

		converterRegistry.register(new CentimetreSquaredToMetreSquaredConverter());
		converterRegistry.register(new HectareToMetreSquaredConverter());
		converterRegistry.register(new KilometreSquaredToMetreSquaredConverter());

		converterRegistry.register(new CubicDecimetreToCubicMetreConverter());
		converterRegistry.register(new CubicCentimetreToCubicMetreConverter());

		converterRegistry.register(new FahrenheitToKelvinConverter());
		converterRegistry.register(new CelsiusToKelvinConverter());

		converterRegistry.register(new BitToByteConverter());
		converterRegistry.register(new KiloByteToByteConverter());
		converterRegistry.register(new MegaByteToByteConverter());
		converterRegistry.register(new GigaByteToByteConverter());

		converterRegistry.register(new DayToSecondConverter());
		converterRegistry.register(new HourToSecondConverter());
		converterRegistry.register(new MinuteToSecondConverter());
		converterRegistry.register(new MonthToSecondConverter());
		converterRegistry.register(new WeekToSecondConverter());

		// connect to db
		/*FirebaseFirestore db = FirebaseFirestore.getInstance();
		this.userDao = new FirestoreUserDao(db);

		FirebaseInstallations.getInstance().getId().addOnSuccessListener(s -> uniqueUserId = s);*/
	}

	public double convert(String fromUnitKey, double value, String toUnitKey) throws IllegalStateException {
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

	public UserDataManager getUserDataManager() {
		return userDataManager;
	}

}
