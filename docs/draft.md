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
