
/**
 * Beschreiben Sie hier die Klasse karte.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class karte extends Rechteck
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private int x;

    /**
     * Konstruktor für Objekte der Klasse karte
     */
    public karte ()
    {
        x = 200;
        y = 250;
        breite = 150;
        höhe = 225;
        farbe = "weiß";
        sichtbar = true;
        winkel = 0;
        symbol = Zeichenfenster.SymbolErzeugen(Zeichenfenster.SymbolArt.rechteck);
        symbol.PositionSetzen(x, y);
        symbol.GrößeSetzen(breite, höhe);
        symbol.FarbeSetzen(farbe);
        symbol.SichtbarkeitSetzen(sichtbar);
        symbol.WinkelSetzen(winkel);
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