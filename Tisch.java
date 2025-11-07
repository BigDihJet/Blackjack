
/**
 * Beschreiben Sie hier die Klasse Tisch.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Tisch extends Rechteck
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private int x;

    /**
     * Konstruktor für Objekte der Klasse Tisch
     */
    public Tisch()
    {
        x = 100;
        y = 100;
        breite = 700;
        höhe = 700;
        farbe = "dunkelgrün";
        sichtbar = true;
        winkel = 0;
        symbol = Zeichenfenster.SymbolErzeugen(Zeichenfenster.SymbolArt.rechteck);
        symbol.PositionSetzen(x, y);
        symbol.GrößeSetzen(breite, höhe);
        symbol.FarbeSetzen(farbe);
        symbol.SichtbarkeitSetzen(sichtbar);
        symbol.WinkelSetzen(winkel);
        symbol.GanzNachHintenBringen();
    }

    /**
     * Ein Beispiel einer Methode - ersetzen Sie diesen Kommentar mit Ihrem eigenen
     * 
     * @param  y    ein Beispielparameter für eine Methode
     * @return        die Summe aus x und y
     */
    public int beispielMethode(int y)
    {
        // tragen Sie hier den Code ein
        return x + y;
    }
}