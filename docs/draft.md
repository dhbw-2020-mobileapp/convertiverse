# Mobile Appentwicklung --- Projekt `Convertiverse`

# Themenentwurf

## Idee

- Einheitsrechner/-konverter
    - Kategorien
        - Währungen
        - Längen
        - Flächen
        - Volumen
        - Temperaturen
        - Gewicht (Masse)
        - Druck
        - Energie und Arbeit
        - Kraft
        - Leistung
        - Kleidergrößen
        - Speichergrößen
        - Geschwindigkeit
        - Kochgrößen (EL, TL, Prise, ..)
        - Zeit (Sekunden -> Tage, ...)
    - Übersichtsseite mit Kategorien (+ Bilder)
    - Log von letzten Umrechnungen

# Umsetzung

- Firebase einarbeiten, für ein Grundgerüst der App
    - Außerdem wollen wir Firestore benutzen, um abzuspeichern, welche Kategorien am meisten genutzt werden
- Frontend und Backend Einteilung
    - Frontend: Übersichtsseiten, Design der Rechnerfunktion und generelle UI/UX
    - Backend: Kommunikation zur Datenbank, Verarbeitung der Umrechnung und Kommunikation mit API (wie z.B. für Währungskurse)
- Datenmodell
    - Welche Klassen haben wir? Wie sieht ein Rechner aus? Eine Einheit?
    - Wie speichern wir in die Datenbank? ER Diagramm
- Mockup für das Design
- Implementation des Datenmodells und Design der UI

# Datenmodell

- Paketnamen: com.github.convertiverse
- pro gerät muss also ein `User` angelegt werden, bei dem dann nutzungsdaten gespeichert werden (in firebase dann). Der Key ist `instance_id`(genereted from firebase).
- außerdem muss eine kategorie existieren, in der dann alle converter registriert werden. `Category`. Der Key ist `category_name`(e.g. "speed").
- es gibt verschiedene `Converter`, die eine Eingabezahl in ein Ausgabeformat konvertieren. sie wissen weder etwas von nutzerdaten noch irgendetwas anderes. Ihr Primärschlüssel ist `conversion_name`(e.g. "kmh_mph"), note: we omit the "_to_" inbetween as in the context it is clear, what we mean.

```
Unit<T> {
	key: String,
	value: T
}
UnitKmh : Unit<Double> {
	key: "kilometers_per_hour",
	value: 52,6
}

Converter<T extends Unit, S extends Unit> {
	key: String
	convert(input: T): S
}
BiConverter : Converter<T extends Unit, S extends Unit> {
	convert(input: S): T
}
KmhToMphConverter : BiConverter<UnitKmh, UnitMph> {
	convert(input: S) = input / 1.60934 // kmh zu mph

	convert(input: T) = input * 1.60934 // mph zu kmh
}

Category {
	key: String
	converters: Map<String, Converter>
}

User {
	instanceId: Int
	statistics: UserStatistics
	settings: UserSettings
}

UserStatistics {
	usages: Map<String, Int>
}

UserSettings {
	settings: Map<String, Object>
}
```